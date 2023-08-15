//package com.example.store.service.productLine;
//
//import com.example.store.entity.Discount;
//import com.example.store.entity.Product;
//import com.example.store.entity.ProductLine;
//import com.example.store.repository.DiscountRepository;
//import com.example.store.service.discount.DiscountService;
//import com.example.store.service.discount.DiscountServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//// как вообще писать эти тесты??? мне метод копировать? 2 экрана делать??
//// doNothing().when(discountService).checkDiscountPeriod(discount); - как я об этом должна вспомнить
//@ExtendWith(MockitoExtension.class) // это чтобы работали аннотации @InjectMocks и @Mock
//class ProductLineServiceImplTest {
//
////    @Test
////    void calculateTotalAmountByProductLines() {
////        DiscountService discountService = new DiscountServiceImpl(Mockito.mock(DiscountRepository.class), )
////        ProductLineService productLineService = new ProductLineServiceImpl() {
////        }
////    }
//
//
//        @InjectMocks // это показываем, что сюда @Mock нужно инжектить
//        private ProductLineService productLineService;
//
//        @Mock // это мы создали мок
//        private DiscountService discountService;
//
////        (я посмотрела пример без ExtendWith и не понимаю, когда может возникнуть
//    //    ситуация, при которой не нужно исп. эту аннотацию)
////    По сути, MockitoAnnotations.openMocks(this) полезен, когда у вас есть тестовые классы без
////    @ExtendWith(MockitoExtension.class), но при использовании @ExtendWith(MockitoExtension.class) это можно опустить,
////    и MockitoExtension сам управляет инициализацией моков.
////        @BeforeEach
////        public void setUp() {
////            MockitoAnnotations.openMocks(this);
////        }
//
//        @Test
//        public void testCalculateTotalAmountByProductLinesWithDiscount() {
////            DiscountService discountService = new DiscountServiceImpl(Mockito.mock(DiscountRepository.class), )
//
//            // Mock discount
//            Discount discount = new Discount();
//            discount.setDiscount(10.0);
//
//            // Mock product
//            Product product = new Product();
//            product.setPrice(BigDecimal.valueOf(100));
//            product.setDiscount(discount);
//
//            // Mock product line
//            ProductLine productLine = new ProductLine();
//            productLine.setProduct(product);
//            productLine.setCount(5);
//
//            // Mock discountService behavior
//            doNothing().when(discountService).checkDiscountPeriod(discount);
//
//            // Call the method
//            ProductLine result = productLineService.calculateTotalAmountByProductLines(productLine);
//
//            // Verify that discountService.checkDiscountPeriod was called
//            // не понимаю зачем это нужно, если выше есть это doNothing().when(discountService).checkDiscountPeriod(discount);
//            verify(discountService, times(1)).checkDiscountPeriod(discount);
//
//            // Check the total sum with discount applied
//            assertEquals(BigDecimal.valueOf(450.0), result.getTotalSum());
//        }
//
//        @Test
//        public void testCalculateTotalAmountByProductLinesWithoutDiscount() {
//            // Mock product
//            // создание product и productLine нельзя в отдельный метод вынести? одно и тоже ведь.
//            Product product = new Product();
//            product.setPrice(BigDecimal.valueOf(100));
//            // No discount
//
//            // Mock product line
//            ProductLine productLine = new ProductLine();
//            productLine.setProduct(product);
//            productLine.setCount(5);
//
//            // Call the method
//            ProductLine result = productLineService.calculateTotalAmountByProductLines(productLine);
//
//            // Verify that discountService.checkDiscountPeriod was NOT called
//            verify(discountService, never()).checkDiscountPeriod(Mockito.any());
//
//            // Check the total sum without discount
//            assertEquals(BigDecimal.valueOf(500.0), result.getTotalSum());
//        }
//    }
//
