package com.poc.khlassmvp.controllers;

import com.poc.khlassmvp.dtos.CategoryDto;
import com.poc.khlassmvp.dtos.ClientDto;
import com.poc.khlassmvp.mapper.impl.CategoryMapper;
import com.poc.khlassmvp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(@RequestParam(required = false) Long companyId) {
        List<CategoryDto> categories;
        if (companyId != null) {
            categories = categoryService.getCategoriesByCompanyId(companyId);
        }else {
            categories = new ArrayList<>();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.getCategoryById(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.addCategory(categoryMapper.toEntity(categoryDto));
        return ResponseEntity.created(URI.create("/category/"+category.getId())).body(category);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.updateCategory(categoryMapper.toEntity(categoryDto), categoryId);
        return ResponseEntity.created(URI.create("/category/"+category.getId())).body(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok().build();
    }
}
