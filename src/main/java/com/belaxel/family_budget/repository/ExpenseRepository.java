package com.belaxel.family_budget.repository;

import com.belaxel.family_budget.model.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {
}
