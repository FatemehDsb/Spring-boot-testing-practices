package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductRequestDTO;
import com.example.demo.model.dto.ProductResponseDTO;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll(){
        return  repository.findAll();
    }

    public Optional<Product> getById(int id){
        return repository.findById(id);
    }

    public List<Product> searchByName(String name){
        return repository.findByName(name);
    }

    public ProductResponseDTO addProduct(ProductRequestDTO p){

        return toResponseDTO(repository.save(toEntity(p)));
    }

    public boolean deleteById(int id){
        return repository.deleteById(id);
    }

    public void addInternalRating(int id, String internalRating){
        Optional<Product> existing = repository.findById(id);
        if(existing.isPresent()){
            existing.get().setInternalRating(internalRating);
        }
    }

    public ProductResponseDTO updateProduct(int id, ProductRequestDTO productDTO){

        Optional<Product> existing = repository.findById(id);

        if (existing.isPresent()){
            existing.get().setName(productDTO.getName());
            existing.get().setPrice(productDTO.getPrice());
            existing.get().setInternalRating(productDTO.getInternalRating());
            return toResponseDTO(existing.get());
        }

        return null;
    }



    public Product patchProduct(int id, Product product){
        Optional<Product> existing = repository.findById(id);
        if (existing.isPresent()){
            if(product.getName() != null){

            }
            if(product.getPrice() != 0.0){

            }
            Product p = existing.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            return  p;
        }
        return null;

    }

    private ProductResponseDTO toResponseDTO(Product product){

        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice());
    }

    private Product toEntity(ProductRequestDTO request){
       Product product = new Product();

       if (!request.getName().isBlank()){
           product.setName(request.getName());
       }
       if(request.getPrice() != 0.0){
           product.setPrice(request.getPrice());
       }

       if (!request.getInternalRating().isBlank()){
           product.setInternalRating(request.getInternalRating());

       }
       return product;

    }


}
