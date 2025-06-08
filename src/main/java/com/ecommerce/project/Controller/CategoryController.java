package com.ecommerce.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.project.Entity.Category;
import com.ecommerce.project.Service.CategoryService;

@RestController
@RequestMapping("/api/v1/")
public class CategoryController {

	// field injection
	@Autowired
	private CategoryService categoryservice;

// Contractor injection
//	public CategoryController(CategoryService categoryservice) {
//		super();
//		this.categoryservice = categoryservice;
//	}

	@GetMapping("/public/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		return categoryservice.getAllCategories();
	}

	@PostMapping("/public/categories/create")
	public ResponseEntity<Category> createCategories(@RequestBody Category category) {
		return categoryservice.createCategories(category);
	}

	@PutMapping("/admin/categories/update/{categoryId}")
	public ResponseEntity<String> deleteCategories(@RequestBody Category category, @PathVariable long categoryId) {
		return categoryservice.updateCategories(category, categoryId);
	}

	@DeleteMapping("/admin/categories/delete/{categoryId}")
	public ResponseEntity<String> deleteCategories(@PathVariable long categoryId) {
		return categoryservice.deleteCategories(categoryId);
	}

}
