package org.ovation.scrum.repository;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductRepositoryTest {

    @Test
    void should_save_a_product() {
        ProductRepository sut = new ProductRepository();
        String name = "name";
        String description = "description";
        Product product = new Product(description, name);

        Product result = sut.save(product);

        assertThat(result.getProductId()).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
    }
}
