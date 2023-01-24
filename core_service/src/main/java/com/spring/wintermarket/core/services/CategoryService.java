package com.spring.wintermarket.core.services;

import com.spring.wintermarket.core.entities.Category;
import com.spring.wintermarket.core.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }
}
