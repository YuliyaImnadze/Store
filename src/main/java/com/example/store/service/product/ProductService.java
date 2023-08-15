package com.example.store.service.product;

import com.example.store.dto.product.ProductDtoRequest;
import com.example.store.dto.product.ProductDtoResponse;
import com.example.store.entity.Product;
import com.example.store.entity.ProductLine;
import com.example.store.service.common.CommonService;

import java.util.Set;
import java.util.UUID;

public interface ProductService extends CommonService<Product, ProductDtoRequest, ProductDtoResponse> {

    Set<Product> findProductsByIdsOrThrow(Set<UUID> productsId);
    void changeQuantityFromProductLine(ProductLine productLine);



//    Получение информации о товаре
//    Добавление и изменение информации о товаре
//    Добавление и удаление скидок
//    Добавление отзывов
//    Добавление оценок

}
