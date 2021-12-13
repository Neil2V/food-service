package com.example.Servicefood.Repository;

import com.example.Servicefood.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String> {
}
