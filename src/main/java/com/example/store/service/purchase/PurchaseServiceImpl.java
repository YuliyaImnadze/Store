package com.example.store.service.purchase;

import com.example.store.dto.productLine.ProductLineDtoRequest;
import com.example.store.dto.purchase.PurchaseDtoRequest;
import com.example.store.dto.purchase.PurchaseDtoResponse;
import com.example.store.entity.*;
import com.example.store.exception.InsufficientFundsException;
import com.example.store.mapper.ProductLineMapper;
import com.example.store.mapper.PurchaseMapper;
import com.example.store.repository.PurchaseRepository;
import com.example.store.repository.UserRepository;
import com.example.store.service.common.CommonServiceImpl;
import com.example.store.service.company.CompanyService;
import com.example.store.service.product.ProductService;
import com.example.store.service.productLine.ProductLineService;
import com.example.store.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl extends CommonServiceImpl<Purchase, PurchaseDtoRequest, PurchaseDtoResponse,
        PurchaseRepository, PurchaseMapper>
        implements PurchaseService {

    private final ProductService productService;
    private final UserService userService;
    private final ProductLineService productLineService;
    private final ProductLineMapper productLineMapper;



    public PurchaseServiceImpl(PurchaseRepository repository, PurchaseMapper mapper, ProductService productService, UserService userService, ProductLineService productLineService, ProductLineMapper productLineMapper) {
        super(repository, mapper);
        this.productService = productService;
        this.userService = userService;
        this.productLineService = productLineService;
        this.productLineMapper = productLineMapper;
    }

    // я тут наделала кучу циклов и не знаю как сделать без них
    @Transactional
    @Override
    public PurchaseDtoResponse create(PurchaseDtoRequest purchaseDtoRequest) {
        User user = userService.findByIdEntity(purchaseDtoRequest.getBuyerId());
        Set<ProductLineDtoRequest> productList = purchaseDtoRequest.getProductList();

        Set<UUID> productsId = productList.stream().map(ProductLineDtoRequest::getProductId).collect(Collectors.toSet());
        Set<Product> productSet = productService.findProductsByIdsOrThrow(productsId);
        Set<ProductLine> productLineSet = productLineMapper.toEntitySetFromRequest(productList);
        addFullProductInfoInProductLine(productLineSet, productSet);
        productLineSet.forEach(productLineService::checkQuantityInProductLine);
        productLineSet.forEach(productLineService::calculateTotalAmountByProductLines);
        // подсчет общей суммы по всем productLine
        BigDecimal totalSum = productLineSet.stream()
                .map(ProductLine::getTotalSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal userBalance = user.getBalance();
        if (userBalance.compareTo(totalSum) >= 0) {
            user.setBalance(userBalance.subtract(totalSum));
            userService.createWithoutCheck(user);

            Purchase purchase = mapper.toEntityFromRequest(purchaseDtoRequest);
            purchase.setProductList(productLineSet);
            purchase.setProductListAmount(totalSum);
            purchase.setBuyer(user);

            for (ProductLine productLineDtoResponse : productLineSet) {
                userService.addMoneyToSeller(productLineDtoResponse);
                productService.changeQuantityFromProductLine(productLineDtoResponse);
                productLineDtoResponse.setPurchase(purchase); // проверить с персист в сущности прод л поставь
            }
            Purchase saved = repository.save(purchase);
            return mapper.toDtoResponseFromEntity(saved);
        } else {
            throw new InsufficientFundsException("Not enough funds to make the purchase");
        }
    }

//    @SuppressWarnings("IsPresentCheck")
    private void addFullProductInfoInProductLine(Set<ProductLine> productLineSet, Set<Product> productSet) {
        for (ProductLine productLine : productLineSet) {
            UUID productId = productLine.getProduct().getId();
            Product fullProductInfo = productSet.stream()
                    .filter(product -> product.getId().equals(productId))
                    .findFirst().get(); // он там точно есть. нужен вообще этот Optional?
            productLine.setProduct(fullProductInfo);
        }
    }


}
