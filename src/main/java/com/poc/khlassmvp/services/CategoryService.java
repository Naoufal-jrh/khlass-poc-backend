package com.poc.khlassmvp.services;

import com.poc.khlassmvp.dtos.CategoryDto;
import com.poc.khlassmvp.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getCategoriesByCompanyId(Long companyId);
    CategoryDto addCategory(CategoryEntity categoryEntity);
    CategoryDto updateCategory(CategoryEntity categoryEntity, Long categoryId);
    void deleteCategoryById(Long id);
}
