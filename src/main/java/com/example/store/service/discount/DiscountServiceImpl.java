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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

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

    @Transactional
    @Scheduled(cron = "@daily") // (fixedDelay = 10000) //
    public void updateExpiredDiscounts() { // делать флаг, потом эти флаги не смотреть
        List<Discount> expiredDiscounts = repository.findByDiscountPeriodLessThan(LocalDate.now());
        for (Discount discount : expiredDiscounts) {
            Set<Product> discountedProducts = discount.getDiscountedProducts();
            for (Product product : discountedProducts) {
                product.setDiscount(null);
//                productRepository.create(product);
                productService.createWithoutCheck(product);
            }
            discount.setActive(false);
            repository.save(discount);
        }
    }


    @Transactional
    @Override // подумать - нужно ли пролверять закончился у товара срок предыдущей скидки или нет.
    // по идее же нужно, чтобы не уменьшить и не увеличить скидку
    public DiscountDtoResponse create(DiscountDtoRequest discountDtoRequest) {
        Discount discount = mapper.toEntityFromRequest(discountDtoRequest);
        Set<Product> discountedProducts = discount.getDiscountedProducts();
        // ПИСАЛА С ВЕРОЙ, МОЖЕТ БЫТЬ ДИЧЬ
        // id для проверки
        // stream
        Set<UUID> productIdList = new HashSet<>();
        for (Product product : discountedProducts) {
            productIdList.add(product.getId());
        }
        // проверить есть такие продукты или нет
        Set<Product> productSet = productService.findProductsByIdsOrThrow(productIdList);
        // проверить истек ли срок прошлой скидки
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
