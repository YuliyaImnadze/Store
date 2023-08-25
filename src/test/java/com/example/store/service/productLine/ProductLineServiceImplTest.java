package com.example.store.service.productLine;

import com.example.store.entity.Discount;
import com.example.store.entity.Product;
import com.example.store.entity.ProductLine;
import com.example.store.mapper.ProductLineMapper;
import com.example.store.repository.ProductLineRepository;
import com.example.store.service.discount.DiscountService;
import com.example.store.service.discount.DiscountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class ProductLineServiceImplTest {


//    @InjectMocks // это показываем, что сюда @Mock нужно инжектить
    private ProductLineServiceImpl productLineService;

//    @Mock
//    private ProductLineRepository productLineRepository;
//
//    @Mock
//    private ProductLineMapper productLineMapper;
//    @Mock
    private DiscountService discountService;


    @BeforeEach
    void setUp() {
        ProductLineRepository productLineRepository = Mockito.mock(ProductLineRepository.class);
        ProductLineMapper productLineMapper = Mappers.getMapper(ProductLineMapper.class);
        discountService = Mockito.mock(DiscountService.class);
        productLineService = new ProductLineServiceImpl(productLineRepository, productLineMapper, discountService);
    }

    @Test
    public void testCalculateTotalAmountByProductLinesWithDiscount() {

        // отдельный метод
        Discount discount = new Discount();
        discount.setDiscount(10.0);

        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(100));
        product.setDiscount(discount);

        ProductLine productLine = new ProductLine();
        productLine.setProduct(product);
        productLine.setCount(5);

        doNothing().when(discountService).checkDiscountPeriod(discount); // перенести в setUp

        ProductLine result = productLineService.calculateTotalAmountByProductLines(productLine);

        verify(discountService, times(1)).checkDiscountPeriod(discount); // перенести в setUp

        assertEquals(BigDecimal.valueOf(450.0), result.getTotalSum());
    }

    @Test
    public void testCalculateTotalAmountByProductLinesWithoutDiscount() {

        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(100));

        ProductLine productLine = new ProductLine();
        productLine.setProduct(product);
        productLine.setCount(5);


        ProductLine result = productLineService.calculateTotalAmountByProductLines(productLine);


        verify(discountService, never()).checkDiscountPeriod(Mockito.any());

        assertEquals(BigDecimal.valueOf(500), result.getTotalSum());
    }
}

