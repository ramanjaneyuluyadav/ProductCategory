package com.ecommerce.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.project.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
