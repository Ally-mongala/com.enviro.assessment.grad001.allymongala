package com.enviro.assessment.grad001.allymongala.wastemanagement.controller;

import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.CategoryDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.DisposalGuideDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.dto.RecyclingTipsDto;
import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.Category;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.CategoryNotFoundException;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.DisposalGuideNotFoundException;
import com.enviro.assessment.grad001.allymongala.wastemanagement.exceptions.RecyclingTipsException;
import com.enviro.assessment.grad001.allymongala.wastemanagement.service.category.CategoryService;
import com.enviro.assessment.grad001.allymongala.wastemanagement.service.disposalGuide.DisposalGuideService;
import com.enviro.assessment.grad001.allymongala.wastemanagement.service.recyclingTips.RecyclingTipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard/")
public class Dashboard {
    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private DisposalGuideService disposalGuideService;

    @Autowired
    private RecyclingTipsService recyclingTipsService;

    @PostMapping("/create/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("category/get")
    public ResponseEntity<List<CategoryDto>> getAllCategories() throws CategoryNotFoundException {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) throws CategoryNotFoundException {
        CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws CategoryNotFoundException {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    //====================E N D OF CATEGORY E N D POINTS=========================================================>



    @PostMapping("/disposal/create")
    public ResponseEntity<DisposalGuideDto> createDisposalGuide(@RequestBody DisposalGuideDto disposalGuideDto,
                                                                @RequestParam("categoryId") Long categoryId) {
        Category category = new Category();
        category.setId(categoryId); // Assuming you have a Category entity with an ID
        DisposalGuideDto createdGuide = disposalGuideService.createDisposalGuide(disposalGuideDto, category);
        return new ResponseEntity<>(createdGuide, HttpStatus.CREATED);
    }

    @GetMapping("/disposal/{id}")
    public ResponseEntity<DisposalGuideDto> getDisposalGuideById(@PathVariable Long id) throws DisposalGuideNotFoundException {
        DisposalGuideDto disposalGuideDto = disposalGuideService.getDisposalGuideById(id);
        return new ResponseEntity<>(disposalGuideDto, HttpStatus.OK);
    }

    @GetMapping("/disposal/all")
    public ResponseEntity<List<DisposalGuideDto>> getAllDisposalGuides() {
        List<DisposalGuideDto> disposalGuides = disposalGuideService.getAllDisposalGuides();
        return new ResponseEntity<>(disposalGuides, HttpStatus.OK);
    }

    @PutMapping("/disposal/update/{id}")
    public ResponseEntity<DisposalGuideDto> updateDisposalGuide(@PathVariable Long id,
                                                                @RequestBody DisposalGuideDto disposalGuideDto) throws DisposalGuideNotFoundException {
        DisposalGuideDto updatedGuide = disposalGuideService.updateDisposalGuide(id, disposalGuideDto);
        return new ResponseEntity<>(updatedGuide, HttpStatus.OK);
    }

    @DeleteMapping("/disposal/delete/{id}")
    public ResponseEntity<Void> deleteDisposalGuide(@PathVariable Long id) throws DisposalGuideNotFoundException {
        disposalGuideService.deleteDisposalGuide(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //================================== E N D OF DISPOSAL E N D POINTS===================================

    @PostMapping("/recycle/create")
    public ResponseEntity<RecyclingTipsDto> createRecyclingTip(@RequestBody RecyclingTipsDto recyclingTipsDto,
                                                               @RequestParam("categoryId") Long categoryId) {
        Category category = new Category();
        category.setId(categoryId); // Assuming you have a Category entity with an ID
        RecyclingTipsDto createdTip = recyclingTipsService.createRecyclingTip(recyclingTipsDto, category);
        return new ResponseEntity<>(createdTip, HttpStatus.CREATED);
    }

    @GetMapping("recycle/get/{id}")
    public ResponseEntity<RecyclingTipsDto> getRecyclingTipById(@PathVariable Long id) throws RecyclingTipsException {
        RecyclingTipsDto recyclingTipsDto = recyclingTipsService.getRecyclingTipById(id);
        return new ResponseEntity<>(recyclingTipsDto, HttpStatus.OK);
    }

    @GetMapping("/recycle/all")
    public ResponseEntity<List<RecyclingTipsDto>> getAllRecyclingTips() {
        List<RecyclingTipsDto> recyclingTipsList = recyclingTipsService.getAllRecyclingTip();
        return new ResponseEntity<>(recyclingTipsList, HttpStatus.OK);
    }

    @PutMapping("/recycle/update/{id}")
    public ResponseEntity<RecyclingTipsDto> updateRecyclingTip(@PathVariable Long id,
                                                               @RequestBody RecyclingTipsDto recyclingTipsDto) throws RecyclingTipsException {
        RecyclingTipsDto updatedTip = recyclingTipsService.updateRecyclingTip(id, recyclingTipsDto);
        return new ResponseEntity<>(updatedTip, HttpStatus.OK);
    }

    @DeleteMapping("/recycle/delete/{id}")
    public ResponseEntity<Void> deleteRecyclingTip(@PathVariable Long id) throws RecyclingTipsException {
        recyclingTipsService.deleteRecyclingTip(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
