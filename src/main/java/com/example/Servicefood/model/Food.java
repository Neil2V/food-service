package com.example.Servicefood.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Food")
@Data
public class Food {

    @Id()
    private String _id;

    private String name;

    private String description;

    private Float price;

    private int stock;
}
