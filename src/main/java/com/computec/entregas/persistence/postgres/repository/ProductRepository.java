package com.computec.entregas.persistence.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.computec.entregas.persistence.postgres.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
