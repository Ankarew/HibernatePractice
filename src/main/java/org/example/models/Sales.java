package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table (name = "sales", schema = "new_schema")
public class Sales {
    @Id
    @GeneratedValue
    private UUID id;
    private Integer quantity;
    private Instant timestamp = Instant.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public Sales() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    @JsonIgnore
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                ", person=" + person +
                ", product=" + product +
                '}';
    }
    public UUID getPersonId() {
        return person.getId();
    }
    public UUID setPersonId(UUID personId) {
        return person.getId();
    }
}
