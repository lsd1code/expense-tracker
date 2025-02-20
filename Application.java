package org.lesedibale.projects.expense_tracker;

import org.lesedibale.projects.expense_tracker.cli.CLI;
import org.lesedibale.projects.expense_tracker.expense_tracker.Expense;
import org.lesedibale.projects.expense_tracker.expense_tracker.ExpenseTracker;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        var cli = new CLI(args);
        cli.start();
    }

}