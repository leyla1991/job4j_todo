package ru.job4j.service.category;

import ru.job4j.model.Category;

import java.util.Collection;

public interface CategoryService {

    Collection<Category> findAll();

    Collection<Category> getAllFindById(Collection<Integer> categoriesId);
}
