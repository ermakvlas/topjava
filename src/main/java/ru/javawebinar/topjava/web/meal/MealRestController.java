package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll(){return super.getAll();}

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id") int id){ return super.get(id);}

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {super.delete(id);}

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") int id, @RequestBody Meal meal){
        super.update(meal, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Meal create(@RequestBody Meal meal){
        return super.create(meal);
    }

    @GetMapping(value = "/between")
    public List<MealWithExceed> getBetween(@RequestParam(value = "srartDateTime")LocalDateTime startDateTime, @RequestParam(value = "endDateTime")LocalDateTime endDateTime){
        return super.getBetween(startDateTime.toLocalDate(),startDateTime.toLocalTime(),endDateTime.toLocalDate(),endDateTime.toLocalTime());
    }


}
