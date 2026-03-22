package org.ovation.scrum.repository;

import java.util.UUID;

public class ProductId {
    private final UUID id;

    public ProductId() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ProductId{" +
                "id=" + id +
                '}';
    }
}
