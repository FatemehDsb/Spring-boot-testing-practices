package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class ProductCategoryRelationTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

@Test
    void shouldSaveProductWithCategory() {

        Category category = new Category("Electronics", "All electronic items");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product("Laptop", 15000.0);
        product.setCategory(savedCategory);


        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertNotNull(savedProduct.getCategory());
        assertEquals("Electronics", savedProduct.getCategory().getName());
    }
}
