package com.micromimariwork.micromimariwork.controller;

import com.micromimariwork.micromimariwork.enums.Language;
import com.micromimariwork.micromimariwork.exceptions.enums.FriendlyMessageCodes;
import com.micromimariwork.micromimariwork.exceptions.utils.FriendlyMessageUtils;
import com.micromimariwork.micromimariwork.repository.entity.Product;
import com.micromimariwork.micromimariwork.request.ProductCreateRequest;
import com.micromimariwork.micromimariwork.respond.FriendlyMessage;
import com.micromimariwork.micromimariwork.respond.InternalApiResponse;
import com.micromimariwork.micromimariwork.respond.ProductResponse;
import com.micromimariwork.micromimariwork.services.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/1.0/product")
@RequiredArgsConstructor
class ProductController {
    private  final IProductRepositoryService productRepositoryService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct (@PathVariable("language")Language language,
                                                               @RequestBody ProductCreateRequest productCreateRequest){
        log.debug("[{}][createProduct]-> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = productRepositoryService.createProduct(language,productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct]-> request: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{language}/{productId}")
    public InternalApiResponse<ProductResponse> getProduct (@PathVariable("language")Language language,
                                                            @PathVariable("productId") Long productId){
        log.debug("[{}][getProduct]-> productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.getProduct(language,productId);
        ProductResponse productResponse = convertProductResponse(product);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_FOUNDED))
                        .build())
                .httpStatus(HttpStatus.FOUND)
                .hasError(false)
                .payload(productResponse)
                .build();
    }


    private ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreateDate(product.getProductCreateDate().getTime())
                .productUpdateDate(product.getProductUpdateDate().getTime())
                .build();
    }


}
