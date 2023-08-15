package com.example.store.service.reviews;

import com.example.store.dto.reviews.ReviewsDtoRequest;
import com.example.store.dto.reviews.ReviewsDtoResponse;
import com.example.store.entity.Product;
import com.example.store.entity.Reviews;
import com.example.store.entity.User;
import com.example.store.exception.ProductNotFoundException;
import com.example.store.mapper.ReviewsMapper;
import com.example.store.repository.ReviewsRepository;
import com.example.store.service.common.CommonServiceImpl;
import com.example.store.service.product.ProductService;
import com.example.store.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ReviewsServiceImpl extends CommonServiceImpl<Reviews, ReviewsDtoRequest, ReviewsDtoResponse,
        ReviewsRepository,
        ReviewsMapper>
        implements ReviewsService {

    private final UserService userService;
    private final ProductService productService;

    public ReviewsServiceImpl(ReviewsRepository repository, ReviewsMapper mapper, UserService userService, ProductService productService) {
        super(repository, mapper);
        this.userService = userService;
        this.productService = productService;
    }

    @Transactional
    @Override
    public ReviewsDtoResponse create(UUID userId, UUID productId, ReviewsDtoRequest reviewsDtoRequest) {
        // где мне проверять есть такие юзер и покупка. тут или в методе ниже?
        User user = userService.findByIdEntity(userId);
        Product product = productService.findByIdEntity(productId);
        boolean wasTheProductPurchased = userService.wasTheProductPurchased(userId, productId);
        if (wasTheProductPurchased) {
            Reviews review = mapper.toEntityFromRequest(reviewsDtoRequest);
            review.setUser(user);
            review.setProduct(product);
            Reviews saved = repository.save(review);
            return mapper.toDtoResponseFromEntity(saved);
        } else {
            throw new ProductNotFoundException("A review can only be left for purchased products");
        }
    }
}
