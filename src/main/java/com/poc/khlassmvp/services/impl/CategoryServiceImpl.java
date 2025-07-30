package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.CategoryDto;
import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.mapper.impl.CategoryMapper;
import com.poc.khlassmvp.repositories.CategoryRepository;
import com.poc.khlassmvp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDto).orElse(null);
    }

    @Override
    public List<CategoryDto> getCategoriesByCompanyId(Long companyId) {
        return categoryRepository.findAllByCompanyId(companyId).stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto addCategory(CategoryEntity categoryEntity) {
        return categoryMapper.toDto(categoryRepository.save(categoryEntity));
    }

    @Override
    public CategoryDto updateCategory(CategoryEntity categoryEntity, Long categoryId) {
        categoryEntity.setId(categoryId);
        return categoryMapper.toDto(categoryRepository.save(categoryEntity));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
