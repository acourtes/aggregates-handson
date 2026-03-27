package org.ovation.scrum.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

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

        products.add(product);
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
        return products.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst();
    }
}
