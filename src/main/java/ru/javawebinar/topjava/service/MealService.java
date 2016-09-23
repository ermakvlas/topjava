package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {

    Meal save(Meal meal, int owner);

    void delete(int id, int owner) throws NotFoundException;

    Meal get(int id, int owner) throws NotFoundException;

    Collection<Meal> getAll(int owner);

}
