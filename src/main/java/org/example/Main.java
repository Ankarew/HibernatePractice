package org.example;

import org.example.dao.ProductDao;
import org.example.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;



@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Product product = new Product();
        product.setName("First Product");
        product.setDescription("Test desc");
        product.setType("Pivo");
        product.setPrice(100.0);
        product.setInStorage(5);
        ProductDao productDao = new ProductDao();
        productDao.create(product);
    }

}