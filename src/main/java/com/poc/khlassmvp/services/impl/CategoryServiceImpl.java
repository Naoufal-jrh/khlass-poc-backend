package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.CategoryDto;
import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.mapper.impl.CategoryMapper;
import com.poc.khlassmvp.repositories.CategoryRepository;
import com.poc.khlassmvp.repositories.ClientRepository;
import com.poc.khlassmvp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryDto getCategoryById(Long id) throws NoSuchElementException {
        return categoryRepository
                .findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found"));
    }

    @Override
    public List<CategoryDto> getCategoriesByCompanyId(Long companyId) {
        return categoryRepository.findAllByCompanyId(companyId).stream().map(categoryMapper::toDto).toList();
    }

    // TODO : add a list of categories
    @Override
    public CategoryDto addCategory(CategoryEntity categoryEntity) {
        boolean exists = categoryRepository.existsByName(categoryEntity.getName());
        if (exists)
            throw new IllegalArgumentException("Category with name " + categoryEntity.getName() + " already exists");
        else
            return categoryMapper.toDto(categoryRepository.save(categoryEntity));
    }

    @Override
    public CategoryDto updateCategory(CategoryEntity categoryEntity, Long categoryId) {
        CategoryEntity existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id " + categoryId));

        if (categoryEntity.getName() != null) {
            existingCategory.setName(categoryEntity.getName());
        }
        if (categoryEntity.getColor() != null) {
            existingCategory.setColor(categoryEntity.getColor());
        }
        if (categoryEntity.getCompany() != null) {
            existingCategory.setCompany(categoryEntity.getCompany());
        }
        if (categoryEntity.getDescription() != null) {
            existingCategory.setDescription(categoryEntity.getDescription());
        }
        if (categoryEntity.getDefaultAmount() != null) {
            existingCategory.setDefaultAmount(categoryEntity.getDefaultAmount());
        }

        return categoryMapper.toDto(categoryRepository.save(existingCategory));

    }

    @Override
    public void deleteCategoryById(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id " + id));

        categoryRepository.delete(category);
    }
}
