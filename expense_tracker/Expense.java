package org.lesedibale.projects.expense_tracker.expense_tracker;

import java.time.LocalDate;

public class Expense {
    private long id;
    private String description;
    private LocalDate date;
    private double amount;

    public Expense(){}

    public Expense(String description, LocalDate date, double amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.id = (int) (Math.random() * 100);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
