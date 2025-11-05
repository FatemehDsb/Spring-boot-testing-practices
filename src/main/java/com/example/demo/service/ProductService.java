package com.example.demo.service;

import com.example.demo.model.Product;
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

    public Product addProduct(Product p){
        return repository.save(p);
    }

    public boolean deleteById(int id){
        return repository.deleteById(id);
    }

    public Product updateProduct(int id, Product product){
        Optional<Product> existing = repository.findById(id);

        if (existing.isPresent()){
            Product p = existing.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            return  p;
        }
        return null;
    }


}
