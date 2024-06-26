package Controller.Commands;

import Model.Expense.Expense;
import Model.User.User;

public class AddCommand implements Command
{

    private Expense expense;
    private User user;

    public AddCommand(User user, Expense expense) {
        this.expense = expense;
        this.user = user;
    }

    @Override
    public void execute() {
        user.addExpense(expense);
    }
}
