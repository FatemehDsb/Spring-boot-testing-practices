package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();


    public ProductRepository(){
        products.add(new Product(1,"banana",20.0));
        products.add(new Product(2,"apple",10.0));
        products.add(new Product(3,"orange",7.0));
    }

    public List<Product> findAll(){
        return products;
    }

    public Optional<Product> findById(int id){
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public List<Product> findByName(String name){
        return products.stream()
                .filter(p -> p.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    public Product save(Product product){
        int nextId = products.stream()
                .mapToInt(p -> p.getId())
                .max()
                .orElse(0) +1;
        product.setId(nextId);
        products.add(product);
        return product;
    }

    public boolean deleteById(int id){
        return products.removeIf(p -> p.getId() == id );
    }

}
