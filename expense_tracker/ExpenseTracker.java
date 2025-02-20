package org.lesedibale.projects.expense_tracker.expense_tracker;

import org.lesedibale.projects.expense_tracker.persistence.FileStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ExpenseTracker {
    private final List<Expense> expenses;
    private final FileStorage storage;

    public ExpenseTracker(){
        this.storage = new FileStorage();
        this.expenses = storage.load();
    }

    public boolean addExpense(String description, LocalDate date, double amount) {
        expenses.add(
                new Expense(description, String.valueOf(date), amount, genId())
        );
        storage.save(expenses);

        return true;
    }

    public boolean updateExpense(long expenseId, String description, LocalDate date, double amount) {
        var result = expenses.stream()
                .findFirst()
                .map(e -> {
                    e.setDescription(description);
                    e.setDate(String.valueOf(date));
                    e.setAmount(amount);
                    return true;
                }).orElse(false);
        storage.save(expenses);
        return result;
    }

    public boolean removeExpense(long id) {
        var result = expenses.removeIf(e -> e.getId() == id);

        storage.save(expenses);
        return result;
    }

    public Optional<Double> summary() {
        return expenses.stream().map(Expense::getAmount).reduce(Double::sum);
    }

    public Optional<Double> summary(long month) {
        return expenses.stream()
                .filter(e -> LocalDate.parse(e.getDate()).getMonthValue() == month)
                .map(Expense::getAmount)
                .reduce(Double::sum);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    private long genId() {
        long result = 0;

        for (int i = 0; i < 5; i++) {
            result = result * 10 + ((int) (Math.random() * 10));
        }

        return result;
    }
}
