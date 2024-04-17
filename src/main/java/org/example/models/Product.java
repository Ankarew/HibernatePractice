package org.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "product", schema = "new_schema")
public class Product {
    @Id
    private UUID id;
    private String name;
    private String description;
    private String type;
    private Double price;
    @Column (name = "in_storage")
    private Integer inStorage;


    public Product(UUID id, String name, String description, String type, Double price, Integer inStorage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.inStorage = inStorage;

    }

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInStorage() {
        return inStorage;
    }

    public void setInStorage(Integer inStorage) {
        this.inStorage = inStorage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", inStorage=" + inStorage +
                '}';
    }
}
