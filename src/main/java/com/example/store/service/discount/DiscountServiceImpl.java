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
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DiscountServiceImpl extends CommonServiceImpl<Discount, DiscountDtoRequest, DiscountDtoResponse,
        DiscountRepository, DiscountMapper>
        implements DiscountService {

    private final ProductService productService;
    private final RedisTemplate<String, String> redisTemplate;
    private static final String LOCK_KEY = "scheduled-task-lock";

    private final LockRegistry lockRegistry;

    public DiscountServiceImpl(DiscountRepository repository, DiscountMapper mapper, ProductService productService, RedisTemplate<String, String> redisTemplate, LockRegistry lockRegistry) {
        super(repository, mapper);
        this.productService = productService;
        this.redisTemplate = redisTemplate;
        this.lockRegistry = lockRegistry;
    }


    @Override
    public void checkDiscountPeriod(Discount discount) {
        if (discount.getDiscountPeriod() != null && (LocalDate.now().isAfter(discount.getDiscountPeriod())
                || LocalDate.now().isEqual(discount.getDiscountPeriod()))) {
            discount.getDiscountedProducts().forEach(product -> product.setDiscount(null));
        }
    }

//    @Override
//    @Transactional
//    @Scheduled(fixedDelay = 10000) // (cron = "@daily")
//    @SchedulerLock(name = "discount_update") // TODO
////    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
//    @Async
//    public void updateExpiredDiscounts() {
//        if (tryLock()) {
//            try {
//                repository.removeExpiredDiscountsFromProducts(LocalDate.now());
//                repository.deactivateExpiredDiscounts(LocalDate.now());
//            } finally {
//                unlock();
//            }
//        }
//    }


    @Override
    @Transactional
    @Scheduled(cron = "@daily") // (fixedDelay = 10000)
    @Async
    public void updateExpiredDiscounts() {
        Lock lock = lockRegistry.obtain("discount-update");
        try {
            if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
                repository.removeExpiredDiscountsFromProducts(LocalDate.now());
                repository.deactivateExpiredDiscounts(LocalDate.now());
            } else {
                log.warn("Lock is not available. The task will be retried later.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }


//    @Override
//    @Transactional
//    @Scheduled (fixedDelay = 10000) // (cron = "@daily") //
//    @SchedulerLock(name = "store")
//    public void updateExpiredDiscounts() {
//        repository.removeExpiredDiscountsFromProducts(LocalDate.now());
//        repository.deactivateExpiredDiscounts(LocalDate.now());
//    }

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

    private boolean tryLock() {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, "locked")); // redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, "locked");
    }

    private void unlock() {
        redisTemplate.delete(LOCK_KEY);
    }

}
