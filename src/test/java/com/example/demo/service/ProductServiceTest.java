package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductRequestDTO;
import com.example.demo.model.dto.ProductResponseDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    @Spy
    ProductService productService;


    @Test
    void callingServiceForAddingNewObjectWorks(){


        Product savedProduct = new Product(1, "laptop", 1000.0);
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductRequestDTO productRequestDTO= new ProductRequestDTO();

        //act
        productService.addProduct(productRequestDTO);

       verify(productRepository, times(1)).save(any(Product.class));

    }

    //service.get(id) returnerar rätt objekt.
    @Test
    void serviceShouldReturnCorrectObjectById(){

        //arrange
        //we create an object, tell repo you should return this
        Product fakeProduct = new Product(1,"product1", 1000.0);
        when(productRepository.findById(1)).thenReturn(Optional.of(fakeProduct));
        //act
        Optional<ProductResponseDTO> productResponseDTO = productService.getById(1);
        //service.get(id)
        assertTrue(productResponseDTO.isPresent());
        assertEquals("product1", productResponseDTO.get().getName());
    }


    //Service kastar eget exception (ex. NotFoundException) när repository returnerar tomt.
    @Test
    void serviceReturnException(){

        //arrange
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        //act
        NotFoundException exceptionResult = assertThrows(NotFoundException.class, ()-> productService.getById(1));
        //asses
        assertEquals("user with id 1 not found", exceptionResult.getMessage());
        verify(productRepository).findById(1);
    }

    //Verifierar interaktioner:
    //○ verify(repository, times(1)).save(any())

    @Test
    void verifyThatRepositorySaveNewObjects(){

        //arrange
        //hey repo. when you being called, give me a faked product
        Product product = new Product(1, "product1", 1000.0);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        //act -> service.addProduct => call repo.save
        ProductRequestDTO productRequestDTO = new ProductRequestDTO("product1", 1000.0);
        ProductResponseDTO productResponseDTO = productService.addProduct(productRequestDTO);
        //asses

        assertEquals("product1", productResponseDTO.getName());
    }

    //○ verify(repository, never()).delete(any())
    @Test
    void shouldNotDeleteIfProductNotFound(){


    }






}
