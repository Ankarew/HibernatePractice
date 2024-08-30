package org.example.dto;

import java.util.Date;

public class PersonCreateDto {

    public String firstName;
    public String lastName;
    public String position;
    public Date birthdate;

    public PersonCreateDto(String firstName, String lastName, String position, Date birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.birthdate = birthdate;
    }
}
