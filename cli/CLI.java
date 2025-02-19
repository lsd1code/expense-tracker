package org.lesedibale.projects.expense_tracker.cli;

import org.lesedibale.projects.expense_tracker.expense_tracker.ExpenseTracker;

import java.util.Arrays;

public class CLI {
    public final ExpenseTracker expenseTracker;
    private final String[] args;

    public CLI(String[] args) {
        this.expenseTracker = new ExpenseTracker();
        this.args = args;
    }

    public void start() {
        handleArgs(args);
    }

    public void handleArgs(String[] args) {
        for(var arg : args) {
            switch(arg) {
                case "list" -> listExpenses();
                case "delete" -> {
                    if(args.length < 4) {
                        System.out.println("""
                        Invalid number of arguments.
                        Usage: java Application.java delete --id [expense_id]
                        """);

                        System.exit(1);
                    }
                }
                case "add" -> {
                    var values = Arrays.copyOfRange(args, 2, 5);
                    System.out.println("add expense");
                }
                case "summary" -> System.out.println("summarize expenses");
                default -> System.out.println("enter a valid argument");
            }
        }
    }

    public void summarize() {
        expenseTracker.summary().ifPresent(System.out::println);
    }

    public void summarize(long id) {
        expenseTracker.summary(id).ifPresent(System.out::println);
    }

    public boolean deleteExpense(long id) {
        var flag = args[1];

        if(args.length < 4) {
            System.out.println("""
                        Invalid number of arguments.
                        Usage: java Application.java delete --id [expense_id]
                        """);
            System.exit(1);
        }

        return expenseTracker.removeExpense(id);
    }

    public void listExpenses() {
        var expenses = expenseTracker.getExpenses();

        if (expenses.isEmpty()) {
            System.out.println("You have no expenses");
            return;
        }

        System.out.printf("| %-7s | %-10s | %-18s | %-7s |\n", "ID", "DATE", "DESCRIPTION", "AMOUNT");
        System.out.printf(
                "+%-9s+%-12s+%-20s+%-9s+\n",
                "-".repeat(9), "-".repeat(12), "-".repeat(20), "-".repeat(9)
        );

        for (var expense : expenses) {
            System.out.printf(
                "| %-7s | %-10s | %-18s | $%-7s |\n",
                expense.getId(),
                expense.getDate(),
                expense.getDescription(),
                expense.getAmount()
            );
        }

    }
}
