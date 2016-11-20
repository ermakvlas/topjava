package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.NotEmpty;
import ru.javawebinar.topjava.model.Meal;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Created by nassuka on 20.11.2016.
 */
public class MealTo {

    protected int id;

    @NotEmpty
    protected LocalDateTime dateTime;

    @NotEmpty
    protected String description;

    @Size(min=2, max=4)
    protected int calories;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void updateMeal(Meal meal){
        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);
    }

    public Meal asNewMeal(){
        return new Meal(dateTime,description,calories);
    }
}
