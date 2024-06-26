package Model.User;

import Model.Expense.Expense;
import Model.Expense.TaxDeductibleExpense;

import java.util.ArrayList;

public class User {
    private String Name;
    private Double Budget;
    public ArrayList<Expense> expenseList = new ArrayList<>();


    public User(String name, Double budget) {
        Name = name;
        Budget = budget;

    }

    public String getName() {
        return Name;
    }

    public Double calculateTotalTax() {
        double yearlyTax = 0.0;
        for (Expense expense : expenseList) {
            if (expense instanceof TaxDeductibleExpense) {
                yearlyTax += ((TaxDeductibleExpense) expense).getTax();
            }
        }
        return yearlyTax;
    }

    public Double calculateSpending() {
        double spending = 0.0;
        for (Expense expense : expenseList) {
                spending += expense.calculateTotal();
            }

        return spending;
    }


    public void setName(String name) {
        Name = name;
    }

    public Double getBudget() {
        return Budget;
    }

    public void setBudget(Double budget) {
        Budget = budget;
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public void deleteExpense(Expense expense) {
        expenseList.remove(expense);
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

}
