package com.belaxel.family_budget.repository;

import com.belaxel.family_budget.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findByName(String name);

}
