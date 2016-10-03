package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertMeal;

    public JdbcMealRepositoryImpl(DataSource dataSource) {
        this.insertMeal = new SimpleJdbcInsert(dataSource).withTableName("MEALS").usingGeneratedKeyColumns("id");
    }

    @Override
    public Meal save(Meal Meal, int userId) {
        if (AuthorizedUser.id()!=userId){return null;}

        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", Meal.getId())
                .addValue("date_time", Meal.getDateTime())
                .addValue("description", Meal.getDescription())
                .addValue("calories", Meal.getCalories())
                .addValue("user_id", userId);

        if (Meal.isNew()){
            Number newKey = insertMeal.executeAndReturnKey(map);
            Meal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update("UPDATE meals SET date_time=:date_time, description=:description, calories=:calories, user_id=:id WHERE id=:id", map);
        }
        return Meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (AuthorizedUser.id()!=userId){return false;}
        return jdbcTemplate.update("DELETE FROM meals WHERE id=?", id) != 0;
    }

    @Override
    public Meal get(int id, int userId)
    {
        if (AuthorizedUser.id()!=userId){return null;}
        return jdbcTemplate.queryForObject("SELECT id, date_time, description, calories, user_id FROM meals WHERE id=?", ROW_MAPPER, id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        if (AuthorizedUser.id()!=userId){return null;}
        return jdbcTemplate.query("SELECT * FROM meals ORDER BY date_time", ROW_MAPPER);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        if (AuthorizedUser.id()!=userId){return null;}
        return jdbcTemplate.query("SELECT * FROM meals WHERE date_time>=? AND date_time<=? ORDER BY date_time", ROW_MAPPER, startDate, endDate);
    }
}
