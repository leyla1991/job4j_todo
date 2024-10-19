package ru.job4j.repository.priority;

import ru.job4j.model.Priority;

import java.util.Collection;

public interface PriorityRepository {

    Collection<Priority> findALl();
}
