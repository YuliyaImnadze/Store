package com.example.store.service.discount;

import com.example.store.dto.discount.DiscountDtoRequest;
import com.example.store.dto.discount.DiscountDtoResponse;
import com.example.store.entity.Discount;
import com.example.store.entity.Product;
import com.example.store.exception.DiscountNotExpiredException;
import com.example.store.mapper.DiscountMapper;
import com.example.store.repository.DiscountRepository;
import com.example.store.service.common.CommonServiceImpl;
import com.example.store.service.product.ProductService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl extends CommonServiceImpl<Discount, DiscountDtoRequest, DiscountDtoResponse,
        DiscountRepository, DiscountMapper>
        implements DiscountService {

    private final ProductService productService;

    public DiscountServiceImpl(DiscountRepository repository, DiscountMapper mapper, ProductService productService) {
        super(repository, mapper);
        this.productService = productService;
    }


    @Override
    public void checkDiscountPeriod(Discount discount) {
        if (discount.getDiscountPeriod() != null && (LocalDate.now().isAfter(discount.getDiscountPeriod())
                || LocalDate.now().isEqual(discount.getDiscountPeriod()))) {
            discount.getDiscountedProducts().forEach(product -> product.setDiscount(null));
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "@daily") // (fixedDelay = 10000) //
//    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @Async
    public void updateExpiredDiscounts() {
        repository.removeExpiredDiscountsFromProducts(LocalDate.now());
        repository.deactivateExpiredDiscounts(LocalDate.now());
    }

    @Transactional
    @Override
    public DiscountDtoResponse create(DiscountDtoRequest discountDtoRequest) {
        Discount discount = mapper.toEntityFromRequest(discountDtoRequest);
        Set<Product> discountedProducts = discount.getDiscountedProducts();
        Set<UUID> productsIds = discountedProducts.stream()
                .map(Product::getId)
                .collect(Collectors.toSet());
        Set<Product> productSet = productService.findProductsByIdsOrThrow(productsIds);
        for (Product product : productSet) {
            Discount productDiscount = product.getDiscount();
            if (productDiscount != null &&
                    (productDiscount.getDiscountPeriod().isEqual(LocalDate.now())
                            || productDiscount.getDiscountPeriod().isAfter(LocalDate.now()))) {
                throw new DiscountNotExpiredException("Discount period for the product with id "
                        + product.getId() + " has not expired yet");
            }
        }
        Discount saved = repository.save(discount);
        productSet.forEach(product -> product.setDiscount(saved)); // почему не сохраняется автоматически?? из-за связи
// entityGrath
        return mapper.toDtoResponseFromEntity(saved);
    }


}
