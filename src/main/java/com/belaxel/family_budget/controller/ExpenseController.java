package com.belaxel.family_budget.controller;

import com.belaxel.family_budget.dto.ExpenseDto;
import com.belaxel.family_budget.model.Consumer;
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
    public ExpenseDto saveExpense(@RequestBody ExpenseDto expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping("/findAllExpenses")
    public List<ExpenseDto> findAllExpenses() {
        return expenseService.findAllExpenses();
    }

    @GetMapping("/findAllConsumersName")
    public List<String> findAllConsumersName() {
        return expenseService.findAllConsumersName();
    }

    @GetMapping("/findAllOrganizationsName")
    public List<String> findAllOrganizationsName() {
        return expenseService.findAllOrganizationsName();
    }

    @GetMapping("/findAllCategoriesName")
    public List<String> findAllCategoriesName() {
        return expenseService.findAllCategoriesName();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }
}
