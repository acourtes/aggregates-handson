package org.ovation.scrum.repository;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

public class ProductRepositoryTest {

    private static final ProductId productId = new ProductId();
    private ProductRepository sut;

    @BeforeEach
    void setup() {
        sut = new InMemoryProductRepository();
    }

    @Test
    void should_save_a_product_with_no_id() {
        Product product = getSimpleProduct();

        Product result = sut.save(product);

        assertThat(result.getProductId()).isNotNull();
        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getDescription()).isEqualTo("description");
    }

    @Test
    void should_save_a_product_with_an_existing_id() {
        Product product = getSimpleProduct();
        addProductIdToProduct(product);
        addVersionToProduct(product);
        product.updateVersion();

        Product result = sut.save(product);

        assertThat(result.getProductId()).isEqualTo(productId);
        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getDescription()).isEqualTo("description");
    }

    @Test
    void should_save_a_new_product_without_version() {
        Product product = getSimpleProduct();

        Product result = sut.save(product);

        assertThat(result.getProductId()).isNotNull();
        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getDescription()).isEqualTo("description");
        assertThat(result.getVersion()).isEqualTo(1);
    }

    @Test
    void should_save_a_product_with_existing_version() {
        Product product = getSimpleProduct();
        addProductIdToProduct(product);
        addVersionToProduct(product);
        product.updateVersion();

        Product result = sut.save(product);

        assertThat(result.getProductId()).isEqualTo(productId);
        assertThat(result.getVersion()).isEqualTo(3);
        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getDescription()).isEqualTo("description");
    }

    @Test
    void should_raise_exception_when_product_has_version_equal_to_what_is_already_in_database() {
        Product product = getSimpleProduct();
        addProductIdToProduct(product);
        addVersionToProduct(product);
        sut.save(product);

        assertThatException().isThrownBy(() -> sut.save(product))
                .isInstanceOf(ProductException.class)
                .withMessage("Product has wrong version");
    }

    @Test
    void should_get_by_id_saved_product() {
        Product product = getSimpleProduct();
        sut.save(product);

        Optional<Product> result = sut.getById(product.getProductId());

        assertThat(result).isNotEmpty();
        assertThat(result).contains(product);
    }

    @Test
    void should_add_a_new_release_to_the_product() {
        Product product = getSimpleProduct();
        Release release = new Release("Release 1");
        product.addRelease(release);

        Product result = sut.save(product);

        assertThat(result.getReleases()).contains(release);
    }

    @Test
    void should_modify_description_of_the_product() {
        Product product = getSimpleProduct();
        String newDescription = "new description";
        product.modifyDescriptionWith(newDescription);

        Product result = sut.save(product);

        assertThat(result.getDescription()).isEqualTo(newDescription);
    }

    @Test
    void should_modify_name_of_the_product() {
        Product product = getSimpleProduct();
        String newName = "new description";
        product.modifyNameWith(newName);

        Product result = sut.save(product);

        assertThat(result.getName()).isEqualTo(newName);
    }

    private static void addVersionToProduct(Product product) {
        Version version = new Version();
        product.setVersion(version);
    }

    private static void addProductIdToProduct(Product product) {
        product.setProductId(productId);
    }

    private static @NonNull Product getSimpleProduct() {
        String name = "name";
        String description = "description";
        return new Product(description, name);
    }
}
