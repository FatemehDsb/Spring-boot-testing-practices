package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductRequestDTO;
import com.example.demo.model.dto.ProductResponseDTO;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll(){
        return service.getAll();
    }

    //localhost:port/products/3
    @GetMapping("/{id}")
    public ResponseEntity<Product> getByid(@PathVariable int id){
        return service.getById(id)
                .map(p -> ResponseEntity.status(200).body(p))
                .orElse(ResponseEntity.status(418).build());
    }


    //localhost:port/products/search?name=banana
    @GetMapping("/search")
    public List<Product> search(@RequestParam
                                    @NotBlank
                                    @Size(min = 2, message ="query must be at least 2 characters long")
                                    String query){
        return service.searchByName(query);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductRequestDTO request){

        ProductResponseDTO response = service.addProduct(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean removed = service.deleteById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestParam String name, @RequestParam double price){
//        Product result = service.updateProduct(id, new Product(-1,name,price));
//        if (result != null){
//            return ResponseEntity.ok(result);
//        } else{
//            return ResponseEntity.notFound().build();
//        }
//
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable int id, @RequestBody ProductRequestDTO productDTO){
        ProductResponseDTO result = service.updateProduct(id, productDTO);
        if (result != null){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.notFound().build();
        }

    }


}
