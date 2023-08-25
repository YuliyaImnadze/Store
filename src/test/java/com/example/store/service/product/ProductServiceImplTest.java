package com.example.store.service.product;

import com.example.store.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

//    @BeforeEach
//    void setUp() {
//        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
////        when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
////        when(productRepository.findById(UUID.fromString("4a4dbb95-92b3-4420-abc2-1806a09bc052"))).thenReturn(Optional.of(new Product()));
////        when(productRepository.save(any(Product.class))).thenReturn(new Product());
//
//        ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
//        productService = new ProductServiceImpl(productRepository, mapper);
//    }

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