package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductRequestDTO {

    @NotBlank(message = "name must be provided")
    @Size(min = 2, message = "name must contain at least 2 characters")
    private String name;
    @Positive(message = "price must be positive")
    private Double price;

    //användren behöver inte veta drtta
    private String internalRating;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, Double price) {
        this.name = name;
        this.price = price;
        this.internalRating = "";
    }

    public ProductRequestDTO(String name, Double price, String rating) {
        this.name = name;
        this.price = price;
        this.internalRating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInternalRating() {
        return internalRating;
    }

    public void setInternalRating(String internalRating) {
        this.internalRating = internalRating;
    }

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", internalRating='" + internalRating + '\'' +
                '}';
    }
}
