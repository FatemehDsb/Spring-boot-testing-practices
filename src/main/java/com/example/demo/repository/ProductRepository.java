package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {


    //SQL @Query(value = "SELECT * FROM products WHERE name LIKE :keyword",nativeQuery = true)
    //JPQL
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    public List<Product> searchByName(@Param("keyword") String name);

//derived Query JPA
    public List<Product> findByCategoryName(String name);

    public List<Product> findByPriceBetween(Double min, Double max);

    public List<Product> findByName(String name);
}
