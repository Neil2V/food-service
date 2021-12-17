package com.example.Servicefood.Controller;

import com.example.Servicefood.Service.FoodService;
import com.example.Servicefood.model.Food;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody Food food){
        if(!foodService.findById(id).isPresent())
            return new ResponseEntity("Este platillo no existe", HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(food.getName()))
            return new ResponseEntity("El nombre es requerido", HttpStatus.BAD_REQUEST);

        Food foodd = foodService.findById(id).get();
        foodd.setName(food.getName());
        foodd.setDescription(food.getDescription());
        foodd.setPrice(food.getPrice());
        foodd.setStock(food.getStock());

        return new ResponseEntity(foodService.guardar(foodd), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){

        if(!foodService.findById(id).isPresent())
            return ResponseEntity.notFound().build();

        foodService.delete(id);
        return ResponseEntity.ok().build();
    }
}
