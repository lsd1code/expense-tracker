package org.lesedibale.projects.expense_tracker.cli;

public class CLI {
    private final String[] args;

    public CLI(String[] args) {
        this.args = args;
    }

    public void start() {
        handleArgs(args);
    }

    public void handleArgs(String[] args) {
        for(var arg : args) {
            switch(arg) {
                case "add" -> System.out.println("add expense");
                case "list" -> System.out.println("list expenses");
                case "summary" -> System.out.println("summarize expenses");
                case "delete" -> System.out.println("delete expense");
                default -> System.out.println("enter a valid argument");
            }
        }
    }
}
