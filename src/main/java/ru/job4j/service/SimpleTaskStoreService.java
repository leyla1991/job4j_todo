package ru.job4j.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Task;
import ru.job4j.repository.TaskStoreRepository;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleTaskStoreService  implements TaskStoreService {

    private TaskStoreRepository taskStoreRepository;

    @Override
    public Task save(Task task) {
        return taskStoreRepository.save(task);
    }

    @Override
    public boolean update(Task task) {
        return taskStoreRepository.update(task);
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskStoreRepository.findById(id);
    }

    @Override
    public Collection<Task> findByDone() {
        return taskStoreRepository.findByDone();
    }

    @Override
    public Collection<Task> findByNewTask() {
        return taskStoreRepository.findByNewTask();
    }

    @Override
    public boolean changeDone(int id) {
        return taskStoreRepository.changeDone(id);
    }

    @Override
    public boolean deleteTask(int id) {
       return taskStoreRepository.deleteTask(id);
    }

    @Override
    public Collection<Task> findAll() {
        return taskStoreRepository.findAll();
    }
}
