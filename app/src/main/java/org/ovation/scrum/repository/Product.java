package org.ovation.scrum.repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Product {

    private ProductId productId;
    private String description;
    private String name;
    private Version version;
    private final Set<Release> releases;

    public Product(String description, String name) {
        this.description = description;
        this.name = name;
        releases = new HashSet<>();
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public long getVersion() {
        return Optional.ofNullable(version)
                .map(Version::version)
                .orElse(0L);
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void updateVersion() {
        version.updateVersion();
    }

    public void addRelease(Release release) {
        releases.add(release);
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void modifyDescriptionWith(String newDescription) {
        this.description = newDescription;
    }

    public void modifyNameWith(String newName) {
        this.name = newName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }
}
