package org.lesedibale.projects.expense_tracker.expense_tracker;

import java.time.LocalDate;

public class Expense {
    private long id;
    private String description;
    private String date;
    private double amount;

    public Expense(){}

    public Expense(String description, String date, double amount, long id) {
        this.description = description;
         this.date = date;
        this.amount = amount;
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
