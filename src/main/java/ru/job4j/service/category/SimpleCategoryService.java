package ru.job4j.service.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.model.Category;
import ru.job4j.repository.category.CategoryRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Collection<Category> getAllFindById(Collection<Integer> categoriesId) {
        return categoryRepository.getAllById(categoriesId);
    }
}
