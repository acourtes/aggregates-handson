package org.ovation.scrum.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryProductRepository implements ProductRepository {

    private final Set<Product> products = new HashSet<>();

    @Override
    public Product save(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(new ProductId());
            product.setVersion(new Version());
        } else {
            product.updateVersion();
            checkProductVersion(product);
        }

        products.remove(product);
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
