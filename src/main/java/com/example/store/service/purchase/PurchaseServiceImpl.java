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
    private final CompanyService companyService;

    @Value("${file.path}")
    private String path;


    public PurchaseServiceImpl(PurchaseRepository repository, PurchaseMapper mapper, ProductService productService, UserService userService, ProductLineService productLineService, ProductLineMapper productLineMapper, CompanyService companyService) {
        super(repository, mapper);
        this.productService = productService;
        this.userService = userService;
        this.productLineService = productLineService;
        this.productLineMapper = productLineMapper;
        this.companyService = companyService;
    }

    // я тут наделала кучу циклов и не знаю как сделать без них
    @Transactional
    @Override
    public PurchaseDtoResponse create(PurchaseDtoRequest purchaseDtoRequest) {
        // сделать метод отдельный на валидацию - не знаю как, потому что получаю списки прод и комп,
        // кот мне дальше нужны
        User user = userService.findByIdEntity(purchaseDtoRequest.getBuyerId());
        Set<ProductLineDtoRequest> productList = purchaseDtoRequest.getProductList();

        Set<UUID> productsId = productList.stream().map(ProductLineDtoRequest::getProductId).collect(Collectors.toSet());
    //    Set<UUID> companiesId = productList.stream().map(ProductLineDtoRequest::getCompanyId).collect(Collectors.toSet());
        // получаею данные из базы
        Set<Product> productSet = productService.findProductsByIdsOrThrow(productsId);
        // вот тут стоит переделать. зачем передавать id компании, если можно взять его из продукты

        // так, сет компаний не нужен вообще. уже это все есть в продукте
//        Set<Company> companySet = companyService.findCompaniesByIdsOrThrow(companiesId);

        Set<ProductLine> productLineSet = productLineMapper.toEntitySetFromRequest(productList);
        // добавить найденные продукты в базе в ProductLine (чтобы было не только id)
        addFullProductInfoInProductLine(productLineSet, productSet);

        // добавить найденные компании в базе в ProductLine (чтобы было не только id)
//        addFullCompanyInfoInProductLine(productLineSet, companySet);

        // это проверка на количество
        productLineSet.forEach(productLineService::checkQuantityInProductLine);
        // это список с общей суммой по каждому продукт лайну
        productLineSet.forEach(productLineService::calculateTotalAmountByProductLines);
        // теперь нужно посчитать общую стоимость всех продуктлайнов
        BigDecimal totalSum = productLineSet.stream()
                .map(ProductLine::getTotalSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // проверить есть ли у юзера деньги

        BigDecimal userBalance = user.getBalance();
        if (userBalance.compareTo(totalSum) >= 0) {
            user.setBalance(userBalance.subtract(totalSum)); // если есть, то деньги списать
            userService.createWithoutCheck(user); // подумать насчет сохр через сервис

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
            throw new InsufficientFundsException("Not enough funds to make the purchase"); // сообщение, что денег нет
        }
    }

    // вот тут я сомневаюсь. метод void меняет состояние. помню, что нельзя так делать
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

    private void addFullCompanyInfoInProductLine(Set<ProductLine> productLineSet, Set<Company> companySet) {
        for (ProductLine productLine : productLineSet) {
            UUID companyId = productLine.getCompany().getId();
            Company fullCompanyInfo = companySet.stream()
                    .filter(company -> company.getId().equals(companyId))
                    .findFirst().get(); // он там точно есть. нужен вообще этот Optional?
            productLine.setCompany(fullCompanyInfo);
        }
    }


    // а в этом случае пустой build -нет. метод этот где должен быть вообще? приватный в NotificationServiceImpl или тут
    @Override
    public void writePurchaseToFile(UUID purchaseId) throws IOException {
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        Purchase purchase = repository.findById(purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Purchase not found"));
        String currentWorkingDirectory = System.getProperty("user.dir");
        String filePath = currentWorkingDirectory + path + purchaseId + ".json";
        objectMapper.writeValue(new File(filePath), purchase);
    }


}
