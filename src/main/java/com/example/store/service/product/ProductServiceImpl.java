package com.example.store.service.product;

import com.example.store.dto.product.ProductDtoRequest;
import com.example.store.dto.product.ProductDtoResponse;
import com.example.store.entity.Product;
import com.example.store.entity.ProductLine;
import com.example.store.mapper.ProductMapper;
import com.example.store.repository.ProductRepository;
import com.example.store.service.common.CommonServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends CommonServiceImpl<Product, ProductDtoRequest, ProductDtoResponse,
        ProductRepository,
        ProductMapper>
        implements ProductService {


    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
    }

    @Override
    @Cacheable(value = "ProductService::findAll")
    public List<ProductDtoResponse> findAll() {
        List<Product> productsFromActiveCompanies = repository.findProductsFromActiveCompanies();
        return mapper.toDtoResponseFromEntity(productsFromActiveCompanies);
    }

    @Override
    @Cacheable(value = "ProductService::findById", key = "#id")
    public ProductDtoResponse findById(UUID id) {
        return super.findById(id);
    }

    @Override
    @Caching(cacheable = {
            @Cacheable(value = "ProductService::findAll"),
            @Cacheable(value = "ProductService::findById", key = "#entity.id")
    })
    public ProductDtoResponse create(ProductDtoRequest entity) {
        return super.create(entity);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "ProductService::findAll"),
            @CachePut(value = "ProductService::findById", key = "#entity.id")
    })
    public ProductDtoResponse update(ProductDtoRequest entity) {
        return super.update(entity);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProductService::findAll"),
            @CacheEvict(value = "ProductService::findById", key = "#entity.id")
    })
    public void delete(ProductDtoRequest entity) {
        super.delete(entity);
    }

    @Override
    public Set<Product> findProductsByIdsOrThrow(Set<UUID> productsIds) {
        Set<Product> products = repository.findAllByIdIn(productsIds);
        // проверить по размеру. если меньше, искать
        if (products.size() < productsIds.size()) {
            Set<UUID> missingProductIds = products.stream().map(Product::getId)
                    .filter(uuid -> !productsIds.contains(uuid))
                    .collect(Collectors.toSet());
            throw new EntityNotFoundException("Products with ids: " + missingProductIds + " not found");
        }

        return products;
    }

    @Override
    public void changeQuantityFromProductLine(ProductLine productLine) {
        Product product = productLine.getProduct();
        int newQuantity = product.getQuantity() - productLine.getCount();
        product.setQuantity(newQuantity);
    }

}
