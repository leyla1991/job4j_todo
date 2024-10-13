package ru.job4j.service.task;

import ru.job4j.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskStoreService {

    Task save(Task task);

    boolean update(Task task);

    Optional<Task> findById(int id);

    Collection<Task> findByDone();

    Collection<Task> findByNewTask();

    boolean deleteTask(int id);

    boolean changeDone(int id);

    Collection<Task> findAll();
}
