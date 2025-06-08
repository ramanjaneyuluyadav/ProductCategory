package com.ecommerce.project.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecommerce.project.Entity.Category;

public interface CategoryService {

	ResponseEntity<List<Category>> getAllCategories();

	ResponseEntity<Category> createCategories(Category category);

	ResponseEntity<String> updateCategories(Category category, long categoryId);
	
	ResponseEntity<String> deleteCategories(long categoryId);
  
	
}
