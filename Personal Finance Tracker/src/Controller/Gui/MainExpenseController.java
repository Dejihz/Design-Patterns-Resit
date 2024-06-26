package Controller.Gui;

import Model.Expense.Expense;
import Model.Expense.ExpenseWrapper;
import Model.Expense.GlassTaxDecorator;
import Model.Expense.PlasticTaxDecorator;
import Model.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainExpenseController {
    private User User;
    private JLabel budgetLabel;
    private JLabel moneyLeftLabel;
    private JLabel spendingLabel;
    private JPanel expensesPanel;
    private JFrame frame;

    public MainExpenseController(User User) {
        this.User = User;

        frame = new JFrame("Main Expense");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel budgetPanel = new JPanel(new GridLayout(2, 2));
        budgetPanel.setBackground(Color.WHITE);
        mainPanel.add(budgetPanel, BorderLayout.NORTH);

        budgetLabel = new JLabel("Name: " + User.getName() + "  Budget: €" + User.getBudget());

        JButton changeBudgetButton = new JButton("Change Budget");
        changeBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newBudgetString = JOptionPane.showInputDialog("Enter new budget:");
                try {
                    double newBudget = Double.parseDouble(newBudgetString);
                    User.setBudget(newBudget);
                    updateLabels();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input a number");
                }
            }
        });
        budgetPanel.add(budgetLabel);
        budgetPanel.add(changeBudgetButton);

        expensesPanel = new JPanel(new GridLayout(0, 3));
        expensesPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(expensesPanel, BorderLayout.CENTER);

        loadExpenses();

        JPanel footerPanel = new JPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        spendingLabel = new JLabel("Spending: €" + String.format("%.2f", User.calculateSpending()));
        moneyLeftLabel = new JLabel("Money Left: €" + String.format("%.2f", User.getBudget() - User.calculateSpending()));
        footerPanel.add(spendingLabel);
        footerPanel.add(moneyLeftLabel);

        JButton addButton = new JButton("Add expense");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddExpenseController(User);
                frame.dispose();
            }
        });
        footerPanel.add(addButton);

        frame.setVisible(true);
    }

    private void loadExpenses() {
        expensesPanel.removeAll();

        boolean firstExpense = true;
        for (Expense expense : User.getExpenseList()) {
            String formattedTotal = String.format("%.2f", expense.calculateTotal());
            JButton expenseButton = new JButton(expense.getName() + ": €" + formattedTotal);
            expenseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (expense instanceof GlassTaxDecorator || expense instanceof PlasticTaxDecorator) {
                        if (!((ExpenseWrapper) expense).isRefunded()) {
                            int response = JOptionPane.showOptionDialog(
                                    null,
                                    "Do you want to refund or delete this expense?",
                                    "Choose an option",
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    new String[]{"Refund", "Delete", "Cancel"},
                                    "Cancel"
                            );

                            if (response == JOptionPane.YES_OPTION) {
                                if (expense instanceof GlassTaxDecorator) {
                                    ((GlassTaxDecorator) expense).refund();
                                } else if (expense instanceof PlasticTaxDecorator) {
                                    ((PlasticTaxDecorator) expense).refund();
                                }
                                refreshExpenses();
                            } else if (response == JOptionPane.NO_OPTION) {
                                frame.dispose();
                                new DeleteExpenseController(expense, User);
                            }
                        } else {
                            int response = JOptionPane.showOptionDialog(
                                    null,
                                    "Do you want to delete this expense?",
                                    "Choose an option",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    new String[]{"Delete", "Cancel"},
                                    "Cancel"
                            );

                            if (response == JOptionPane.YES_OPTION) {
                                frame.dispose();
                                new DeleteExpenseController(expense, User);
                            }
                        }
                    } else {
                        int response = JOptionPane.showOptionDialog(
                                null,
                                "Do you want to delete this expense?",
                                "Choose an option",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new String[]{"Delete", "Cancel"},
                                "Cancel"
                        );

                        if (response == JOptionPane.YES_OPTION) {
                            frame.dispose();
                            new DeleteExpenseController(expense, User);
                        }
                    }
                }
            });
            if (firstExpense) {
                expensesPanel.add(new JLabel());
                firstExpense = false;
            }
            expensesPanel.add(expenseButton);
        }

        expensesPanel.revalidate();
        expensesPanel.repaint();
    }

    private void refreshExpenses() {
        updateLabels();
        loadExpenses();
    }

    private void updateLabels() {
        budgetLabel.setText("Name: " + User.getName() + "  Budget: €" + User.getBudget());
        spendingLabel.setText("Spending: €" + String.format("%.2f", User.calculateSpending()));
        moneyLeftLabel.setText("Money Left: €" + String.format("%.2f", User.getBudget() - User.calculateSpending()));
    }
}
