package com.example.store.service.productLine;

import com.example.store.entity.Discount;
import com.example.store.entity.ProductLine;
import com.example.store.exception.InsufficientStockException;
import com.example.store.service.discount.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductLineServiceImpl  implements ProductLineService { // не могу сделать наследование от общего сервиса из-за маппера

    private final DiscountService discountService;

    public ProductLineServiceImpl(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public ProductLine checkQuantityInProductLine(ProductLine productLine) {
        Integer quantityInStock = productLine.getProduct().getQuantity();
        Integer quantity = productLine.getCount();
        if (quantityInStock < quantity) {
            throw new InsufficientStockException("Not enough quantity in stock");
        }
        return productLine;
    }

    @Override
    public ProductLine calculateTotalAmountByProductLines(ProductLine productLine) {
        BigDecimal price = productLine.getProduct().getPrice();
        Discount discount = productLine.getProduct().getDiscount();
        Integer quantity = productLine.getCount();
        if (discount != null) {
            discountService.checkDiscountPeriod(discount); // нужно или нет??
            BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(quantity));
            // это нормально, что я просто на 100 делю? или как то нужно указать %? в сущности хранить в %
            BigDecimal discountAmount = totalAmount.multiply(BigDecimal.valueOf(discount.getDiscount() / 100.0));
            totalAmount = totalAmount.subtract(discountAmount);
            productLine.setTotalSum(totalAmount);
            return productLine;
        }
        BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(quantity));
        productLine.setTotalSum(totalAmount);
        return productLine;
    }



}
