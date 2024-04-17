package org.example.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table (name = "sales", schema = "new_schema")
public class Sales {
    @Id
    private UUID id;
    @Column (name = "person_id")
    private UUID personId;
    @Column (name = "product_id")
    private UUID productId;
    private Integer quantity;
    private Instant timestamp = Instant.now();

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;*/
    /*@ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;*/


    public Sales(UUID id, UUID personId, UUID productId, Integer quantity, Timestamp timestamp) {
        this.id = id;
        this.personId = personId;
        this.productId = productId;
        this.quantity = quantity;
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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
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
