package org.ovation.scrum.repository;

public class Product {

    private ProductId productId;
    private String description;
    private String name;

    public Product(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
