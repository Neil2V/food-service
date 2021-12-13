package com.example.Servicefood.Controller;

import com.example.Servicefood.Service.FoodService;
import com.example.Servicefood.model.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/create")
    public ResponseEntity<Food> create(@RequestBody Food food){
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.guardar(food));
    }

    @GetMapping("/")
    public ResponseEntity<List<Food>> getFoods(){
        List<Food> list = foodService.getFoods();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        Optional<Food> food = foodService.findById(id);

        if(!food.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(food);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){

        if(!foodService.findById(id).isPresent())
            return ResponseEntity.notFound().build();

        foodService.delete(id);
        return ResponseEntity.ok().build();
    }
}
