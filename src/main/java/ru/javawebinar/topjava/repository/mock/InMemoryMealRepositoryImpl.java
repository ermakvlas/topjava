package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    private void save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
    }


    @Override
    public Meal save(Meal meal, int owner) {
        LOG.info("save " + meal.getId());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setOwner(owner);
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int owner) {
        LOG.info("delete " + id);
        if (repository.get(id).getOwner()!=owner){return false;}
        repository.remove(id);
        return true;
    }

    @Override
    public Meal get(int id, int owner) {
        LOG.info("get " + id);
        if (repository.get(id).getOwner()!=owner){return null;}
        if (!repository.containsKey(id)){return null;}
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(int owner) {
        List<Meal> res = repository.values().stream()
                .filter(meal -> meal.getOwner()==owner)
                .sorted((m1,m2)->m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
        return res.size()==0 ? Collections.EMPTY_LIST:res;
    }
}

