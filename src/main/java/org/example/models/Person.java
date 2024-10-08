package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "person", schema = "new_schema")
public class Person {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column (name = "id")
     private UUID id;

     @NotNull
     @Column (name = "first_name")
     @Size(min = 3, max = 20, message = "minimum first name " +
             "length is 3, maximum 20")
     private String firstName;

     @NotNull
     @Column (name = "last_name")
     @Size(min = 3, max = 20, message = "minimum last name " +
             "length is 3, maximum 20")
     private String lastName;

     @NotNull
     private String position;

     @PastOrPresent
     private Date birthdate;

     @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Sales> salesList;

    public Person(UUID id, String firstName, String lastName, String position, Date birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.birthdate = birthdate;
        salesList = new ArrayList<>();
    }

    public Person() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /*public List<Sales> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sales> salesList) {
        this.salesList = salesList;
    }*/

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
