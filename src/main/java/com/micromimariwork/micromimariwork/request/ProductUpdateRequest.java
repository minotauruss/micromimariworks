package com.micromimariwork.micromimariwork.request;

import lombok.Data;

import java.util.Date;

@Data
public class ProductUpdateRequest {
    private Long productId;
    private  String productName;
    private Integer quantity;
    private  Double price;
    private Date productUpdateDate= new Date();
}
