package ru.job4j.service.priority;

import ru.job4j.model.Priority;

import java.util.Collection;

public interface PriorityService {

    Collection<Priority> findAll();
}
