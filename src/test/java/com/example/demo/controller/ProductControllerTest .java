package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

class ProductControllerTest  {


    @Autowired
    MockMvc mockMvc;


    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;


    //GET: testar
    //○ HTTP 200
    //○ JSON-fält matchar det mockade service-resultatet

    int  savedProductId;
    @BeforeEach
    void setUp(){
        productRepository.deleteAll();

        Product product = new Product();
        product.setName("Banana");
        product.setPrice(10.0);
        savedProductId = productRepository.save(product).getId();
    }


    @Test
    void getProductShouldReturn200() throws Exception{
        mockMvc.perform(get("/products/" + savedProductId))
                .andExpect(status().isOk());

    }

    //JSON-fält matchar det mockade service-resultatet

    @Test
    void jsonResponseShouldNBeExpectedObject() throws Exception{
        mockMvc.perform(get("/products/" + savedProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Banana"))
                .andExpect(jsonPath("$.price").value(10.0));
    }

    //POST: testar
//○ HTTP 201
  //  ○ att rätt JSON skickas in

    @Test
    void postProductShouldReturn201() throws Exception{

        String requestBody = """
        {
          "name": "Laptop",
          "price": 1000.0
        }
    """;

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated());
    }


    //POST med felaktig body → 400 BAD REQUEST
    @Test
    void PostFelBodyShouldReturn400() throws Exception{

        String badJson = """
        {
          "name": "",
          "price": 1000.0
        }
    """;
        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(badJson))
                .andExpect(status().isBadRequest());
    }

    //Service kastar exception → controller returnerar rätt status, typ 404



}