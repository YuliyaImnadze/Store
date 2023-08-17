package com.example.store.service.product;

import com.example.store.entity.*;
import com.example.store.mapper.ProductMapper;
import com.example.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
        when(productRepository.findById(UUID.fromString("4a4dbb95-92b3-4420-abc2-1806a09bc052"))).thenReturn(Optional.of(new Product()));
        when(productRepository.save(any(Product.class))).thenReturn(new Product());

        ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
        productService = new ProductServiceImpl(productRepository, mapper);
    }

    @Test
    void changeQuantityFromProductLine() {
        Product product = new Product("Buka",
                "Buka",
                new Company(),
                BigDecimal.valueOf(100),
                20,
                new Discount(),
                Collections.emptyList(),
                new Characteristic());
        ProductLine productLine = new ProductLine(product,
                2,
                BigDecimal.ZERO,
                new Company(),
                new Purchase());
        productService.changeQuantityFromProductLine(productLine);
        assertEquals(18, product.getQuantity());
    }
}