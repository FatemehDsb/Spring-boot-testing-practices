package com.example.demo.repository;


import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    //spara en entity - varify ID genereras
    @Test
    void anIdShouldBeGeneratedForNewlySavedEntity(){
        //arrange
        Product product = new Product();
        product.setName("product1");
        //act
        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId());
    }

    //2. Hämtar entiteten med findById.
    @Test
    void getProductWithFindById(){
        //arrange
        Product product = new Product();
        product.setName("product1");
        Product savedProductInDataBase =  productRepository.save(product);
        int idForSavedProductDB = savedProductInDataBase.getId();

        //act
          Optional<Product>foundedProduct  = productRepository.findById(idForSavedProductDB);

        //assert
        assertEquals(idForSavedProductDB, foundedProduct.get().getId());
    }

    @Test
    void productShouldBeFoundByName(){
        //arrange
        Product product = new Product();
        product.setName("product1");
        productRepository.save(product);

        //act
        Optional<Product> foundedProductFromDatabase = productRepository.findByName("product1");

        //assess
        assertEquals("product1", foundedProductFromDatabase.get().getName());
    }

   // unikt fält → assertThrows(DataIntegrityViolationException.class, ...)

    @Test
    void nameFieldShouldBeUnique(){
        //arrange
        Product product1 = new Product();
        product1.setName("product1");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("product1");
        //act
        //assess
        assertThrows(DataIntegrityViolationException.class,()->
                productRepository.save(product2));
    }





}