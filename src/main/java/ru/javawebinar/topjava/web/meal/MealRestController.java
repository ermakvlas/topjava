package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.MealServlet;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    @Autowired
    private MealServiceImpl service;

    public Meal get(int id){
        LOG.info("get meal id: " + id + "user id: " + AuthorizedUser.id());
        return service.get(id, AuthorizedUser.id());
    }

    public void delete(int id){
        LOG.info("delete meal id: " + id + "user id: " + AuthorizedUser.id());
        service.delete(id, AuthorizedUser.id());
    }

    public List<MealWithExceed> getAll(){
        LOG.info("getAll meals for user id: " + AuthorizedUser.id());
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()),MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void update(Meal meal){
        LOG.info("update meal id: " + meal.getId() + "for user id: " + AuthorizedUser.id());
        service.save(meal, AuthorizedUser.id());
    }

    public void save(Meal meal){
        LOG.info("save meal id: " + meal.getId() + "for user id: " + AuthorizedUser.id());
        service.save(meal, AuthorizedUser.id());
    }

}
