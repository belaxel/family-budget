package com.belaxel.family_budget.service;

import com.belaxel.family_budget.dto.ExpenseDto;
import com.belaxel.family_budget.model.Category;
import com.belaxel.family_budget.model.Consumer;
import com.belaxel.family_budget.model.Expense;
import com.belaxel.family_budget.model.Organization;
import com.belaxel.family_budget.repository.CategoryRepository;
import com.belaxel.family_budget.repository.ConsumerRepository;
import com.belaxel.family_budget.repository.ExpenseRepository;
import com.belaxel.family_budget.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ExpenseDto saveExpense(ExpenseDto expense) throws IllegalArgumentException {
        validateExpenseDto(expense);
        return fromExpenseToExpenseDto(expenseRepository.save(fromExpenseDtoToExpense(expense)));
    }

    @Override
    public void deleteExpense(Integer expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<ExpenseDto> findAllExpenses() {
        List<ExpenseDto> expenses = new ArrayList<>();
        expenseRepository.findAll().forEach(expense -> expenses.add(fromExpenseToExpenseDto(expense)));
        return expenses;
    }

    @Override
    public List<String> findAllConsumersName() {
        List<String> consumers = new ArrayList<>();
        consumerRepository.findAll().forEach(consumer -> consumers.add(consumer.getName()));
        return consumers;
    }

    @Override
    public List<String> findAllOrganizationsName() {
        List<String> organizations = new ArrayList<>();
        organizationRepository.findAll().forEach(organization -> organizations.add(organization.getName()));
        return organizations;
    }

    @Override
    public List<String> findAllCategoriesName() {
        List<String> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categories.add(category.getName()));
        return categories;
    }

    private void validateExpenseDto(ExpenseDto expenseDto) throws IllegalArgumentException {
        if (expenseDto == null) {
            throw new IllegalArgumentException("Объект Expense пуст");
        }
        if (expenseDto.getTime() == null) {
            throw new IllegalArgumentException("Время пусто");
        }
        if (expenseDto.getName() == null || expenseDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Наименование пусто");
        }
        if (expenseDto.getAmount() == null) {
            throw new IllegalArgumentException("Расход пуст");
        }
        if (expenseDto.getOrganization() == null || expenseDto.getOrganization().trim().isEmpty()) {
            throw new IllegalArgumentException("Организация пуста");
        }
        if (expenseDto.getCategory() == null || expenseDto.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Категория пуста");
        }
    }

    private ExpenseDto fromExpenseToExpenseDto(Expense expense) {
        return ExpenseDto
                .builder()
                .id(expense.getId())
                .name(expense.getName())
                .time(expense.getTime())
                .amount(expense.getAmount())
                .consumer(Optional.ofNullable(expense.getConsumer()).map(Consumer::getName).orElse(null))
                .organization(expense.getOrganization().getName())
                .category(expense.getCategory().getName())
                .build();
    }

    private Expense fromExpenseDtoToExpense(ExpenseDto expenseDto) {
        Expense expense = Expense
                .builder()
                .name(expenseDto.getName())
                .time(expenseDto.getTime())
                .amount(expenseDto.getAmount())
                .build();

        Consumer consumer = null;
        if (expenseDto.getConsumer() != null && !expenseDto.getConsumer().trim().isEmpty()) {
            consumer = consumerRepository.findByName(expenseDto.getConsumer());
            if (consumer == null) {
                consumer = Consumer.builder()
                        .name(expenseDto.getConsumer())
                        .expenses(new ArrayList<>())
                        .build();
                consumerRepository.save(consumer);
            }
            expense.setConsumer(consumer);
        }

        Organization organization = organizationRepository.findByName(expenseDto.getOrganization());
        if (organization == null) {
            organization = Organization.builder()
                    .name(expenseDto.getOrganization())
                    .expenses(new ArrayList<>())
                    .categories(new ArrayList<>())
                    .build();
            organizationRepository.save(organization);
        }
        expense.setOrganization(organization);

        Category category = categoryRepository.findByName(expenseDto.getCategory());
        if (category == null) {
            category = Category.builder()
                    .name(expenseDto.getCategory())
                    .expenses(new ArrayList<>())
                    .organizations(new ArrayList<>())
                    .build();
            categoryRepository.save(category);
        }
        expense.setCategory(category);

        if (consumer != null) {
            consumer.getExpenses().add(expense);
        }
        organization.getExpenses().add(expense);
        category.getExpenses().add(expense);

        if (!organization.getCategories().contains(category)) {
            organization.getCategories().add(category);
        }
        if (!category.getOrganizations().contains(organization)) {
            category.getOrganizations().add(organization);
        }

        return expense;
    }
}
