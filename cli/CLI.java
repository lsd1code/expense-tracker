package org.lesedibale.projects.expense_tracker.cli;

import org.lesedibale.projects.expense_tracker.expense_tracker.ExpenseTracker;

import java.time.LocalDate;

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
                    if(deleteExpense()) {
                        System.out.println("Expense Deleted Successfully");
                    } else {
                        System.out.println("""
                        Error: Could not delete expense.
                        Usage: java Application.java delete --id [expense_id]
                        """);
                    }
                    System.exit(0);
                }
                case "add" -> {
                    if(addExpense()) {
                        System.out.println("Expense added successfully");
                    } else {
                        System.out.println("""
                        Error: Invalid arguments.
                        Usage: java Application.java add --description [description] --amount [amount]
                        """);
                    }
                    System.exit(0);
                }
                case "summary" -> {
                    if (args.length == 1) {
                        summarize();
                    } else {
                        summarizeMonthly();
                    }
                    System.exit(0);
                }
                default -> System.out.println("enter a valid argument");
            }
        }
    }

    public boolean addExpense() {
        var descFlag = args[1];
        var amountFlag = args[3];

        if(!descFlag.equals("--description") || !amountFlag.equals("--amount") || args.length != 5) {
            System.out.println("""
            Error: Invalid arguments.
            Usage: java Application.java add --description [description] --amount [amount]
            """);
            System.exit(1);
        }

        try {
            var description = args[2];
            var amount = Double.parseDouble(args[args.length - 1]);

            expenseTracker.addExpense(description, LocalDate.now(), amount);
        } catch (NumberFormatException ignored) {
            System.out.println("""
            Error: Invalid arguments.
            Usage: java Application.java add --description [description] --amount [amount]
            """);
            System.exit(1);
        }

        return true;
    }

    public void summarize() {
        expenseTracker.summary().ifPresent(value -> System.out.println("Total Expenses: R" + value));
    }

    public void summarizeMonthly() {
        var flag = args[1];

        if(!flag.equals("--month")) {
            System.out.println("""
            Error: Something went wrong.
            Usage: java Application.java summary --month [month_number]
            """);
            System.exit(1);
        }

        try {
            var month = Long.parseLong(args[2]);

            if(month < 1 || month > 12) {
                System.out.println("Error: Enter a valid month number[1-12].");
                System.exit(1);
            }

            expenseTracker.summary(month).ifPresent(value -> System.out.println("Total Expenses: R" + value));
        } catch (NumberFormatException ignored) {
            System.out.println("""
            Error: Something went wrong.
            Usage: java Application.java summary --month [month_number]
            """);
            System.exit(1);
        }
    }

    public boolean deleteExpense() {
        var flag = args[1];

        if(args.length != 3 || !flag.equals("--id")) {
            System.out.println("""
            Error: Could not delete expense.
            Usage: java Application.java delete --id [expense_id]
            """);
            System.exit(1);
        }

        try {
            var id = Long.parseLong(args[2]);
            return expenseTracker.removeExpense(id);
        } catch (NumberFormatException ignored) {
            return false;
        }
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
