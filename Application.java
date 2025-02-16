package org.lesedibale.projects.expense_tracker;

import org.lesedibale.projects.expense_tracker.cli.CLI;

public class Application {
    public static void main(String[] args) {
        var cli = new CLI(args);
        cli.start();
    }

}