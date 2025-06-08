package com.ecommerce.project.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecommerce.project.Entity.Category;

//for loose coupling
public interface CategoryService {

	ResponseEntity<List<Category>> getAllCategories();

	ResponseEntity<?> createCategories(Category category);

	ResponseEntity<String> updateCategories(Category category, long categoryId);
	
	ResponseEntity<String> deleteCategories(long categoryId);
  
	
}
