package org.ovation.scrum.repository;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductRepositoryTest {

    @Test
    void should_save_a_product_with_no_id() {
        ProductRepository sut = new ProductRepository();
        String name = "name";
        String description = "description";
        Product product = new Product(description, name);

        Product result = sut.save(product);

        assertThat(result.getProductId()).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
    }

    @Test
    void should_save_a_product_with_an_existing_id() {
        ProductRepository sut = new ProductRepository();
        String name = "name";
        String description = "description";
        Product product = new Product(description, name);
        ProductId productId = new ProductId();
        product.setProductId(productId);
        product.setVersion(new Version());

        Product result = sut.save(product);

        assertThat(result.getProductId()).isEqualTo(productId);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
    }

    @Test
    void should_save_a_new_product_without_version() {
        ProductRepository sut = new ProductRepository();
        String name = "name";
        String description = "description";
        Product product = new Product(description, name);

        Product result = sut.save(product);

        assertThat(result.getProductId()).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
        assertThat(result.getVersion()).isEqualTo(1);
    }

    @Test
    void should_save_a_product_with_existing_version() {
        ProductRepository sut = new ProductRepository();
        String name = "name";
        String description = "description";
        Product product = new Product(description, name);
        ProductId productId = new ProductId();
        product.setProductId(productId);
        Version version = new Version();
        product.setVersion(version);

        Product result = sut.save(product);

        assertThat(result.getProductId()).isEqualTo(productId);
        assertThat(result.getVersion()).isEqualTo(2);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
    }
}
