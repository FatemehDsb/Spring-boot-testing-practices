package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@DataJpaTest
class ProductJPATest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    //public List<Product> findByCategoryName(String name);
    @Test
    @DisplayName("findByCategoryName should return list of products")
    void findByCategoryName(){
        //arrange
        Category cat = new Category();
        cat.setName("fruit");
        categoryRepository.save(cat);

        Product p1 = new Product();
        p1.setName("Banana");
        p1.setPrice(10.0);
        p1.setCategory(cat);
        productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Apple");
        p2.setPrice(11.0);
        p2.setCategory(cat);
        productRepository.save(p2);

        //act
        List<Product> result = productRepository.findByCategoryName("fruit");

        //assert
        assertThat(result).hasSize(2);
        assertThat(result).extracting(Product::getName).containsExactlyInAnyOrder("Apple","Banana");

    }

}