package org.ovation.scrum.repository;

public class ProductRepository {
    public Product save(Product product) {
        if (product.getProductId() == null) {
            ProductId productId = new ProductId();
            product.setProductId(productId);
        }

        return product;
    }
}
