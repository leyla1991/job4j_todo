package ru.job4j.repository.task;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;
import ru.job4j.repository.crud.CrudRepository;

import java.util.*;

@Repository
@AllArgsConstructor
public class Sql2oTaskStoreRepository implements TaskStoreRepository {

    private CrudRepository crudRepository;

    static final Logger LOG = LoggerFactory.getLogger(Sql2oTaskStoreRepository.class.getName());

    @Override
    public Task save(Task task) {
        crudRepository.run(session -> session.save(task));
        return task;
    }

    @Override
    public Optional<Task> findById(int id) {
        return crudRepository.optional(
                "FROM Task as i JOIN FETCH i.priority WHERE i.id = :fId ", Task.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Task> findByDone() {
        return crudRepository.query(
                "FROM Task as i JOIN FETCH i.priority WHERE i.done = true ", Task.class);
    }

    @Override
    public boolean changeDone(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(
                    "UPDATE Task SET done = true WHERE id = :fId",
                    Map.of("fId", id)
            );
            rsl = true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rsl;
    }

    @Override
    public Collection<Task> findByNewTask() {
        return crudRepository.query(
                "FROM Task as i JOIN FETCH i.priority WHERE i.done = false", Task.class
        );
    }

    @Override
    public boolean update(Task task) {
           return crudRepository.query("UPDATE FROM Task SET title = :fTitle, description = :fDescription, priority = :fPriority WHERE id = :fId",
                    Map.of("fTitle", task.getTitle(),
                            "fDescription", task.getDescription(),
                            "fPriority", task.getPriority(),
                            "fId", task.getId())
           );
    }

    @Override
    public boolean deleteTask(int id) {
        try {
            crudRepository.run(
                    "DELETE Task WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public Collection<Task> findAll() {
        return crudRepository.query(
                "from Task f JOIN FETCH f.priority", Task.class
        );
    }
}