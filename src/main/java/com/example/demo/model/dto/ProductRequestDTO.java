package com.example.demo.model.dto;

public class ProductRequestDTO {

    private String name;
    private double price;

    //användren behöver inte veta drtta
    private String internalRating;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, double price) {
        this.name = name;
        this.price = price;
        this.internalRating = "";
    }

    public ProductRequestDTO(String name, double price, String rating) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
