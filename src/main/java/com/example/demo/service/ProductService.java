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

    public List<ProductResponseDTO> getAll(){
        return  repository.findAll()
                .stream().map(this::toResponseDTO)
                .toList();
    }

    public Optional<ProductResponseDTO> getById(int id){
        if(repository.findById(id).isPresent()){
            return Optional.of(toResponseDTO(repository.findById(id).get()));
        } else return Optional.empty();

    }

    public List<ProductResponseDTO> searchByName(String name){
        return repository.findByName(name)
                .stream()
                .map(product -> toResponseDTO(product))
                .toList();
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



    public ProductResponseDTO patchProduct(int id, ProductRequestDTO product){
        Optional<Product> existing = repository.findById(id);
        if (existing.isPresent()){
            if(product.getName() != null){
                existing.get().setName(product.getName());
            }
            if(product.getPrice() != null){
                existing.get().setPrice(product.getPrice());
            }
            if (product.getInternalRating()!= null){
                existing.get().setInternalRating(product.getInternalRating());
            }
            return  toResponseDTO(existing.get());
        }
        return null;

    }

    private ProductResponseDTO toResponseDTO(Product product){

        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice());
    }

    private Product toEntity(ProductRequestDTO request){
       Product product = new Product();

       if (request.getName() != null){
           product.setName(request.getName());
       }
       if(request.getPrice() != null){
           product.setPrice(request.getPrice());
       }

       if (request.getInternalRating() != null){
           product.setInternalRating(request.getInternalRating());

       }
       return product;

    }


}
