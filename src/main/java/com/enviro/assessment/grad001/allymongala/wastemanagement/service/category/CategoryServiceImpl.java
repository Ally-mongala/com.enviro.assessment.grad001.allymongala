package com.enviro.assessment.grad001.allymongala.wastemanagement.service.category;

import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.CategoryDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.Category;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.CategoryNotFoundException;
import com.enviro.assessment.grad001.allymongala.wastemanagement.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = Category
                .builder()
                .dayaCreated(LocalDate.now())
                .description(categoryDto.getDescription())
                .name(categoryDto.getName())
                .build();
        categoryRepository.save(category);
        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setId(category.getId());
        BeanUtils.copyProperties(category, categoryDto1);
        return categoryDto1;
    }

    @Override
    public CategoryDto getCategoryById(Long id) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("Category was not found");
        }
        Category category = optionalCategory.get();
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() throws CategoryNotFoundException {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            throw new CategoryNotFoundException("There are no categories found");
        }

        return categories.stream().map(CategoryServiceImpl::mapCategoryToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("There are no categories found");
        }
        Category category = optionalCategory.get();
        if (Objects.nonNull(categoryDto.getName())){
            category.setName(categoryDto.getName());
        }
        if (Objects.nonNull(categoryDto.getDescription())){
            category.setDescription(category.getDescription());
        }
        Category updatedCategory = categoryRepository.save(category);
        return mapCategoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) throws CategoryNotFoundException {
      Optional<Category> optionalCategory = categoryRepository.findById(id);
      if (optionalCategory.isEmpty()){
          throw new CategoryNotFoundException("there are no categories found");
      }
      Category category = optionalCategory.get();
      categoryRepository.delete(category);
    }

    private static CategoryDto mapCategoryToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDayaCreated(LocalDate.now());
        return categoryDto;
    }
}
