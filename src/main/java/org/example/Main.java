package org.example;

import org.example.dao.ProductDao;
import org.example.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;



@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {
    @Autowired
    ProductDao dao;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}