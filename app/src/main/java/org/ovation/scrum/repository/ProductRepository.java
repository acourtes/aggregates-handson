package org.ovation.scrum.repository;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> getById(ProductId productId);
}
