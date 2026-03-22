package org.ovation.scrum.repository;

public class ProductRepository {
    public Product save(Product product) {
        if (product.getProductId() == null) {
            ProductId productId = new ProductId();
            product.setProductId(productId);
            Version version = new Version();
            product.setVersion(version);
        } else {
            product.updateVersion();
        }

        return product;
    }
}
