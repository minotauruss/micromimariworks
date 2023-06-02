package com.micromimariwork.micromimariwork.services;

import com.micromimariwork.micromimariwork.enums.Language;
import com.micromimariwork.micromimariwork.repository.entity.Product;
import com.micromimariwork.micromimariwork.request.ProductCreateRequest;
import com.micromimariwork.micromimariwork.request.ProductUpdateRequest;

import java.util.List;

public interface IProductRepositoryService {
    Product createProduct (Language language, ProductCreateRequest productCreateRequest);

    Product getProduct (Language language, Long productId);

    List<Product> getProducts(Language language);

    Product updateProduct(Language language,Long productId, ProductUpdateRequest productUpdateRequest);

    Product deleteProduct (Language language, Long productId);

}
