package ru.job4j.repository.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Category;
import ru.job4j.repository.crud.CrudRepository;

import java.util.Collection;
import java.util.Map;

@AllArgsConstructor
@Repository
public class HBCategoryRepository implements CategoryRepository {

    private CrudRepository crudRepository;

    @Override
    public Collection<Category> findAll() {
        return crudRepository.query("FROM Category", Category.class);
    }

    @Override
    public Collection<Category> getAllById(Collection<Integer> categoriesId) {
        return crudRepository.query("FROM Category f WHERE f.id IN :fIds", Category.class,
                Map.of("fIds", categoriesId));
    }
}
