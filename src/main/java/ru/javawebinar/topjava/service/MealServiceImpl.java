package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    public Meal save(Meal meal, int owner) {
        return repository.save(meal, owner);
    }

    public void delete(int id, int owner) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, owner), id);
    }

    public Meal get(int id, int owner) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id, owner), id);
    }

    public Collection<Meal> getAll(int owner) {
        return repository.getAll(owner);
    }

}
