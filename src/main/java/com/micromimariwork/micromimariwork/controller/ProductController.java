package com.micromimariwork.micromimariwork.controller;

import com.micromimariwork.micromimariwork.enums.Language;
import com.micromimariwork.micromimariwork.exceptions.enums.FriendlyMessageCodes;
import com.micromimariwork.micromimariwork.exceptions.utils.FriendlyMessageUtils;
import com.micromimariwork.micromimariwork.repository.entity.Product;
import com.micromimariwork.micromimariwork.request.ProductCreateRequest;
import com.micromimariwork.micromimariwork.request.ProductUpdateRequest;
import com.micromimariwork.micromimariwork.respond.FriendlyMessage;
import com.micromimariwork.micromimariwork.respond.InternalApiResponse;
import com.micromimariwork.micromimariwork.respond.ProductResponse;
import com.micromimariwork.micromimariwork.services.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{language}/products")
    public InternalApiResponse<List<ProductResponse>> getProducts (@PathVariable("language")Language language){
        log.debug("[{}][getProduct]-> productId: {}", this.getClass().getSimpleName());
        List<Product> productList= productRepositoryService.getProducts(language);
        List<ProductResponse> responseList = convertProductResponseList(productList);

        return InternalApiResponse.<List<ProductResponse>>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_FOUNDED))
                        .build())
                .httpStatus(HttpStatus.FOUND)
                .hasError(false)
                .payload(responseList)
                .build();
    }

    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    @PutMapping("/{language}/update")
    public InternalApiResponse<ProductResponse> updateProduct (@PathVariable("language")Language language,
                                                               @RequestBody ProductUpdateRequest productUpdateRequest){
        log.debug("[{}][createProduct]-> request: {}", this.getClass().getSimpleName(), productUpdateRequest);
        Product product = productRepositoryService.updateProduct(language, productUpdateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.FriendlyMessageCodes__PRODUCT_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.UPGRADE_REQUIRED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }






    @ResponseStatus(HttpStatus.FOUND)
    @DeleteMapping("/{language}/{productId}/delete")
    public InternalApiResponse<ProductResponse> deleteProduct(@PathVariable("language") Language language,
                                                              @PathVariable("productId") Long productId){
        log.debug("[{}][createProduct]-> request: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.deleteProduct(language,productId);
        ProductResponse productResponse = convertProductResponse(product);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.FriendlyMessageCodes__PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.FOUND)
                .hasError(false)
                .payload(productResponse)
                .build();
    }








    private  List<ProductResponse> convertProductResponseList( List<Product> productList){
        return productList.stream()
                .map(item -> ProductResponse.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .productCreateDate(item.getProductCreateDate().getTime())
                        .productUpdateDate(item.getProductUpdateDate().getTime())
                        .build())
                .collect(Collectors.toList());

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
