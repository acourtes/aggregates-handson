package org.ovation.scrum.repository;

import java.util.Optional;

public class Product {

    private ProductId productId;
    private String description;
    private String name;
    private Version version;

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

    public long getVersion() {
        return Optional.ofNullable(version)
                .map(Version::version)
                .orElse(0L);
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void updateVersion() {
        version.updateVersion();
    }
}
