package org.ovation.scrum.repository;

import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    @Override
    public Product save(Product product) {
        if (product.getProductId() == null) {
            ProductId productId = new ProductId();
            product.setProductId(productId);
            Version version = new Version();
            product.setVersion(version);
        } else {
            product.updateVersion();
            checkProductVersion(product);
        }

        return product;
    }

    private void checkProductVersion(Product product) {
        Optional<Product> existingProduct = getById(product.getProductId());
        Long existingVersion = existingProduct.map(Product::getVersion)
                .orElse(0L);

        if (existingVersion.equals(product.getVersion())) {
            throw new ProductException("Product has wrong version");
        }
    }

    @Override
    public Optional<Product> getById(ProductId productId) {
        Product product = new Product("description", "name");
        product.setProductId(productId);
        Version version = new Version();
        version.updateVersion();
        product.setVersion(version);
        return Optional.of(product);
    }
}
