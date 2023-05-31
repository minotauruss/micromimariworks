package com.micromimariwork.micromimariwork.repository;

import com.micromimariwork.micromimariwork.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


}
