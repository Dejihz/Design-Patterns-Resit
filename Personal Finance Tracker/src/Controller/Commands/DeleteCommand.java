package Controller.Commands;

import Model.Expense.Expense;
import Model.User.User;

public class DeleteCommand implements Command {
    private Expense expense;
    private User user;

    public DeleteCommand(User user, Expense expense) {

        this.user = user;
        this.expense= expense;
    }


    public void execute() {
        user.deleteExpense(expense);
    }

}