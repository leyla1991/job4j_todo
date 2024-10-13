package ru.job4j.repository.task;

import ru.job4j.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskStoreRepository {

   Task save(Task task);

   boolean update(Task task);

   Collection<Task> findByDone();

   Collection<Task> findByNewTask();

   Optional<Task> findById(int id);

   boolean changeDone(int id);

   boolean deleteTask(int id);

   Collection<Task> findAll();

}
