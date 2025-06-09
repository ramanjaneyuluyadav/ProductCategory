package com.ecommerce.project.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.Entity.Category;
import com.ecommerce.project.Repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	// private long categoryId = 1L;

	// List<Category> categories = new ArrayList<>();

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ResponseEntity<List<Category>> getAllCategories() {

		// return new ResponseEntity<>(categories, HttpStatus.OK);

		// though the repository
		return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> createCategories(Category category) {

//      category.setCategoryId(categoryId++);

//		category.setCategoryId(new java.util.Random().nextLong(0, 1000));
//		categories.add(category);
//		return new ResponseEntity<>(category, HttpStatus.CREATED);

		// Check for duplicate category name
		boolean exists = categoryRepository.findAll().stream()
				.anyMatch(c -> c.getCategoryName().equalsIgnoreCase(category.getCategoryName()));

		if (exists) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Category already exists!");
		}

		Category savedCategory = categoryRepository.save(category);

		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<String> updateCategories(Category category, long categoryId) {

		// Optional<Category> categoryOpt = categories.stream().filter(c ->
		// c.getCategoryId() == categoryId).findFirst();

		// though the repository
		Collection<Category> categories = categoryRepository.findAll();
		Optional<Category> categoryOpt = categories.stream().filter(c -> c.getCategoryId() == categoryId).findFirst();

		if (categoryOpt.isPresent()) {
			boolean exist = categories.stream()
					.anyMatch(n -> n.getCategoryName().equalsIgnoreCase(category.getCategoryName()));
			if (exist) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Category name already exists! please choose another name...");
			}

			Category categoryObj = categoryOpt.get();
			// int index = categories.indexOf(categoryObj);
			categoryObj.setCategoryName(category.getCategoryName());
			categoryObj.setProducts(category.getProducts());

			// categories.set(index, categoryObj);

			categoryRepository.save(categoryObj);

			return ResponseEntity.ok("Category with id " + categoryId + " update succfully!..");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!..");
		}
	}

	@Override
	public ResponseEntity<String> deleteCategories(long categoryId) {
		try {
//			Category category = categories.stream().filter(c -> c.getCategoryId() == categoryId).findFirst()
//					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
//
//			categories.remove(category);

			// though the repository
			Collection<Category> categories = categoryRepository.findAll();
			Category category = categories.stream().filter(c -> c.getCategoryId() == categoryId).findFirst()
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

			categoryRepository.delete(category);
			return ResponseEntity.status(HttpStatus.OK)
					.body("Category with categoryId " + categoryId + " deleted successfully");
		} catch (ResponseStatusException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		}
	}

	// getByCategoryId
	@Override
	public ResponseEntity<?> getByCategoryIdAlongWithProducts(long CategoryId) {

		try {
			Category category = categoryRepository.findById(CategoryId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
			return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		}

	}
}
