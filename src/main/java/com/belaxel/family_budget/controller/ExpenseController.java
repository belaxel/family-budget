package com.belaxel.family_budget.controller;

import com.belaxel.family_budget.model.Expense;
import com.belaxel.family_budget.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/save")
    public Expense saveExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping("/findAll")
    public List<Expense> findAllExpenses() {
        return expenseService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }
}
