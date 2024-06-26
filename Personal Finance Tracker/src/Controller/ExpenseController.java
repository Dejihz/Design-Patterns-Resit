package Controller;

import Model.Expense.Expense;
import View.ExpenseView;

public class ExpenseController {
    private final Expense model;
    private final ExpenseView view;

    public ExpenseController(Expense model, ExpenseView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        view.printExpense(model);
    }
}
