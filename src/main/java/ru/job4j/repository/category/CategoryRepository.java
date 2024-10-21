package ru.job4j.repository.category;

import ru.job4j.model.Category;

import java.util.Collection;

public interface CategoryRepository {

    Collection<Category> findAll();

    Collection<Category> getAllById(Collection<Integer> categoriesId);
}
