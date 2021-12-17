package com.example.Servicefood.Service;

import com.example.Servicefood.Repository.FoodRepository;
import com.example.Servicefood.model.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public Food guardar(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> getFoods(){
        return foodRepository.findAll();
    }

    public Optional<Food> findById(String id){
        return foodRepository.findById(id);
    }

    public void delete(String id){
        foodRepository.deleteById(id);
    }
}
