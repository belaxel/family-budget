package com.belaxel.family_budget.service;

import com.belaxel.family_budget.dto.ExpenseDto;
import com.belaxel.family_budget.model.Category;
import com.belaxel.family_budget.model.Consumer;
import com.belaxel.family_budget.model.Organization;

import java.util.List;

public interface ExpenseService {

    ExpenseDto saveExpense(ExpenseDto expense);

    void deleteExpense(Integer expenseId);

    List<ExpenseDto> findAllExpenses();

    List<String> findAllConsumersName();

    List<String> findAllOrganizationsName();

    List<String> findAllCategoriesName();
}
