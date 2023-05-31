package com.micromimariwork.micromimariwork.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product", schema = "stock_management")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private  String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private  double price;

    @Builder.Default
    @Column(name = "product_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productUpdateDate = new Date();

    @Builder.Default
    @Column(name = "product_create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productCreateDate= new Date();

    @Column(name = "is_delete")
    private  boolean deleted;






}
