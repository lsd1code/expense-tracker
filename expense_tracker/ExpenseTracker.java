package org.lesedibale.projects.expense_tracker.expense_tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ExpenseTracker {
    private final List<Expense> expenses;
    private static long id = 1;

    public ExpenseTracker(){
        this.expenses = new ArrayList<>();
    }

    public boolean addExpense(String description, LocalDate date, double amount) {
        expenses.add(
                new Expense(description, date, amount, genId())
        );
        return true;
    }

    public boolean updateExpense(long expenseId, String description, LocalDate date, double amount) {
        return expenses.stream()
                .findFirst()
                .map(e -> {
                    e.setDescription(description);
                    e.setDate(date);
                    e.setAmount(amount);
                    return true;
                }).orElse(false);
    }

    public boolean removeExpense(long id) {
        return expenses.removeIf(e -> e.getId() == id);
    }

    public Optional<Double> summary() {
        return expenses.stream().map(Expense::getAmount).reduce(Double::sum);
    }

    public Optional<Double> summary(long month) {
        return expenses.stream()
                .filter(e -> e.getDate().getMonthValue() == month)
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
