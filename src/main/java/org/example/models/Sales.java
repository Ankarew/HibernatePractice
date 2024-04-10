package org.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.UUID;
@Entity
@Table (name = "sales")
public class Sales {
    @Id
    private UUID id;
    @Column (name = "person_id")
    private UUID personId;
    @Column (name = "product_id")
    private UUID productId;
    private Integer quantity;
    private Timestamp timestamp;


    public Sales(UUID id, UUID personId, UUID productId, Integer quantity, Timestamp timestamp) {
        this.id = id;
        this.personId = personId;
        this.productId = productId;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public Sales() {
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

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", personId=" + personId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                '}';
    }
}
