package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal, int owner);

    boolean delete(int id, int owner);

    Meal get(int id, int owner);

    Collection<Meal> getAll(int owner);
}
