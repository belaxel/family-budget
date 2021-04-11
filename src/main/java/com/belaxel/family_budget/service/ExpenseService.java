package com.belaxel.family_budget.service;

import com.belaxel.family_budget.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseDto saveExpense(ExpenseDto expense);

    void deleteExpense(Integer expenseId);

    List<ExpenseDto> findAllExpenses();

    List<String> findAllConsumersName();

    List<String> findAllOrganizationsName();

    List<String> findAllCategoriesName();
}
