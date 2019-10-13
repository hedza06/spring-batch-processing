package com.springdemo.batch.repositories;

import com.springdemo.batch.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
