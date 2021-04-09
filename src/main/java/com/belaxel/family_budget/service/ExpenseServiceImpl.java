package com.belaxel.family_budget.service;

import com.belaxel.family_budget.model.Expense;
import com.belaxel.family_budget.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Integer expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<Expense> findAll() {
        List<Expense> expenses = new ArrayList<Expense>();
        expenseRepository.findAll().forEach(expenses::add);
        return expenses;
    }
}
