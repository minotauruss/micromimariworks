package com.micromimariwork.micromimariwork.services.impl;

import com.micromimariwork.micromimariwork.enums.Language;
import com.micromimariwork.micromimariwork.exceptions.enums.FriendlyMessageCodes;
import com.micromimariwork.micromimariwork.exceptions.exception.ProductNotCreateException;
import com.micromimariwork.micromimariwork.repository.ProductRepository;
import com.micromimariwork.micromimariwork.repository.entity.Product;
import com.micromimariwork.micromimariwork.request.ProductCreateRequest;
import com.micromimariwork.micromimariwork.request.ProductUpdateRequest;
import com.micromimariwork.micromimariwork.services.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductRepositoryServiceImpl implements IProductRepositoryService {
    private  final ProductRepository productRepository;


    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request {}", this.getClass().getSimpleName(), productCreateRequest);
try {
    Product product = Product.builder()
            .productName(productCreateRequest.getProductName())
            .quantity(productCreateRequest.getQuantity())
            .price(productCreateRequest.getPrice())
            .deleted(false)
            .build();
    Product productResponse = productRepository.save(product);
    log.debug("[{}][createProduct] -> request {}", this.getClass().getSimpleName(),productResponse );
    return product;
}catch (Exception e) {
    throw new ProductNotCreateException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION, "product request : " + productCreateRequest.toString());
}
    }

    @Override
    public Product getProduct(Language language, Long productId) {
        log.debug("[{}][getProduct] -> request {}", this.getClass().getSimpleName(), productId);
        Product product = productRepository.getByProductIdAndDeletedFalse(productId);
        if(Objects.isNull(product)){
            throw new ProductNotCreateException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "product  : " + productId);
        }
        return product;
    }

    @Override
    public List<Product> getProducts(Language language) {
        log.debug("[{}][getProduct] ", this.getClass().getSimpleName());
        List<Product> products = productRepository.getAllByDeletedFalse();
        if(products.isEmpty()){
            throw new ProductNotCreateException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "product");
        }
        return products;
    }

    @Override
    public Product updateProduct(Language language, ProductUpdateRequest productUpdateRequest) {
        log.debug("[{}][createProduct] -> request {}", this.getClass().getSimpleName(), productUpdateRequest);
        Product product = productRepository.getByProductIdAndDeletedFalse(productUpdateRequest.getProductId());
        if(Objects.isNull(product)){
            throw new ProductNotCreateException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "product  : " + productUpdateRequest);
        }
        product.setProductName(productUpdateRequest.getProductName());
        product.setPrice(productUpdateRequest.getPrice());
        product.setQuantity(productUpdateRequest.getQuantity());
        product.setProductUpdateDate(productUpdateRequest.getProductUpdateDate());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        log.debug("[{}][createProduct] -> request {}", this.getClass().getSimpleName(), productId);
        Product product = productRepository.getByProductIdAndDeletedFalse(productId);
        if(Objects.isNull(product)){
            throw new ProductNotCreateException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "product  : " + productId);
        }
        product.setDeleted(true);
        productRepository.save(product);
        return product;
    }


}
