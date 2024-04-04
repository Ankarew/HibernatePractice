package org.example;

import java.util.UUID;

public class Sales {
    private UUID id;
    private UUID personId;
    private Integer quantity;

    public Sales(UUID id, UUID personId, Integer quantity) {
        this.id = id;
        this.personId = personId;
        this.quantity = quantity;
        System.out.println("Full body");
    }

    public Sales(UUID personId, Integer quantity) {
        this.id = UUID.randomUUID();
        this.personId = personId;
        this.quantity = quantity;
        System.out.println("Random UUID");
    }

    public Sales() {
        this.personId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        this.quantity = 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", personId=" + personId +
                ", quantity=" + quantity +
                '}';
    }
}
