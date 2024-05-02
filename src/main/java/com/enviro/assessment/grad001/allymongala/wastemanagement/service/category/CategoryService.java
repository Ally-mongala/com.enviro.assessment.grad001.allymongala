package com.enviro.assessment.grad001.allymongala.wastemanagement.service.category;

import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.CategoryDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long id) throws CategoryNotFoundException;
    List<CategoryDto> getAllCategories() throws CategoryNotFoundException;
    CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws CategoryNotFoundException;
    void deleteCategory(Long id) throws CategoryNotFoundException;
}
