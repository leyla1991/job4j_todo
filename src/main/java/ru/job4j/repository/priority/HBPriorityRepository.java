package ru.job4j.repository.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Priority;
import ru.job4j.repository.crud.CrudRepository;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class HBPriorityRepository implements PriorityRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Priority> findALl() {
        return crudRepository
                .query("FROM Priority", Priority.class);
    }
}
