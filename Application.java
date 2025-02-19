package org.lesedibale.projects.expense_tracker;

import org.lesedibale.projects.expense_tracker.cli.CLI;
import org.lesedibale.projects.expense_tracker.expense_tracker.Expense;
import org.lesedibale.projects.expense_tracker.expense_tracker.ExpenseTracker;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(i + " " + args[i]);
        }


        System.exit(1);
        var cli = new CLI(args);
        cli.expenseTracker.addExpense("Groceries", LocalDate.now(), 200.5);
        cli.expenseTracker.addExpense("Takeout", LocalDate.now(), 180);
        cli.expenseTracker.addExpense("Toiletry", LocalDate.now(), 100.75);

        cli.start();
    }

}