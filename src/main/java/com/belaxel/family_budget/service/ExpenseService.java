package com.belaxel.family_budget.service;

import com.belaxel.family_budget.model.Expense;

import java.util.List;

public interface ExpenseService {

    Expense saveExpense(Expense expense);

    void deleteExpense(Integer expenseId);

    List<Expense> findAll();

}
