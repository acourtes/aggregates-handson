package org.ovation.scrum.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

public class ProductRepositoryTest {

    private static ProductRepository sut;

    @BeforeAll
    static void beforeAll() {
        sut = new InMemoryProductRepository();
    }

    @Test
    void should_save_a_product_with_no_id() {
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
        String name = "name";
        String description = "description";
        Product product = new Product(description, name);
        ProductId productId = new ProductId();
        product.setProductId(productId);
        product.setVersion(new Version());
        product.updateVersion();

        Product result = sut.save(product);

        assertThat(result.getProductId()).isEqualTo(productId);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
    }

    @Test
    void should_save_a_new_product_without_version() {
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
        String name = "name";
        String description = "description";
        Product product = new Product(description, name);
        ProductId productId = new ProductId();
        product.setProductId(productId);
        Version version = new Version();
        product.setVersion(version);
        product.updateVersion();

        Product result = sut.save(product);

        assertThat(result.getProductId()).isEqualTo(productId);
        assertThat(result.getVersion()).isEqualTo(3);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getDescription()).isEqualTo(description);
    }

    @Test
    void should_raise_exception_when_product_has_version_equal_to_what_is_already_in_database() {
        String name = "name";
        String description = "description";
        Product product = new Product(description, name);
        ProductId productId = new ProductId();
        product.setProductId(productId);
        Version version = new Version();
        product.setVersion(version);

        assertThatException().isThrownBy(() -> sut.save(product))
                .isInstanceOf(ProductException.class)
                .withMessage("Product has wrong version");
    }
}
