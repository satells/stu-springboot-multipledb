package com.multipledb.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multipledb.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
