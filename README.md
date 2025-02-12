# Expense Tracker

- A simple expense tracker to manage finances

## Features

- Add expense with description and amount
- Can update an expense
- Can view all expenses
- Can view a summary of all expenses
- Can view summary of expenses for a specific month(of the current year)

### Additional Features

- Group expenses by category and allow user to filter expenses by category
- Allow user to set a budget for each month and show a warning when the user exceeds the given budget
- Allow user to export expenses as CSV/JSON file

```shell
# List of commands
# filename: [0], operation: [1], flag1: [2], arg1: [3], flag2: [4], arg2: [5]
expense-tracker add --description [expense_desc] --amount # Expense added successfully (ID: eId)

# filename: [0], operation: [1]
expense-tracker list # returns a list of all expenses

expense-tracker summary # Total expenses = totalAmount

expense-tracker delete --id [expense_id] # Task deleted successfully

expense-tracker summary --month [month_num] # Summary for the given month
```

## Requirements

- Java 11+
- Maven(Optional)