package com.github.nazrulkabir.springbootrestservicehateoas.repository;

import com.github.nazrulkabir.springbootrestservicehateoas.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> getProductById(long id);
}
