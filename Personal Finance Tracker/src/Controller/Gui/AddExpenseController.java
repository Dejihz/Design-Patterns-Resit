package Controller.Gui;

import Controller.Commands.AddCommand;
import Controller.Commands.Invoker;
import Model.Expense.Expense;
import Model.Expense.ExpenseFactory;
import Model.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddExpenseController {
    public AddExpenseController(User user) {
        JFrame frame = new JFrame("Add | Personal Finance Tracker");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                new MainExpenseController(user);
            }
        });

        JPanel panel = new JPanel(new GridLayout(0, 2));
        frame.add(panel);

        String[] expenseTypes = {"NonDeductible", "TaxDeductible", "Tin(TaxDeductible)", "Plastic(TaxDeductible)", "Glass(TaxDeductible)"};
        JComboBox<String> typeDropdown = new JComboBox<>(expenseTypes);
        panel.add(new JLabel("Expense Type:"));
        panel.add(typeDropdown);

        JTextField nameField = new JTextField();
        panel.add(new JLabel("Expense Name:"));
        panel.add(nameField);

        JTextField costField = new JTextField();
        panel.add(new JLabel("Expense Cost:"));
        panel.add(costField);

        Integer[] counts = {1, 2, 3, 4, 5};
        JComboBox<Integer> countDropdown = new JComboBox<>(counts);
        countDropdown.setSelectedIndex(0);  // Set default value to 1
        panel.add(new JLabel("Number of Expenses:"));
        panel.add(countDropdown);

        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expenseType = (String) typeDropdown.getSelectedItem();
                String expenseName = nameField.getText();
                double expenseCost = Double.parseDouble(costField.getText());
                int expenseCount = (Integer) countDropdown.getSelectedItem();  // Get selected number of expenses

                ExpenseFactory factory = new ExpenseFactory();

                if (expenseType != null) {
                    Invoker invoker = new Invoker();

                    for (int i = 0; i < expenseCount; i++) {
                        Expense expense = null;
                        switch (expenseType) {
                            case "NonDeductible" ->
                                    expense = factory.createExpense("Factory.NonDeductible", expenseName, expenseCost);
                            case "TaxDeductible" ->
                                    expense = factory.createExpense("Factory.TaxDeductible", expenseName, expenseCost);
                            case "Tin(TaxDeductible)" ->
                                    expense = factory.createExpense("Factory.Tin", expenseName, expenseCost);
                            case "Plastic(TaxDeductible)" ->
                                    expense = factory.createExpense("Factory.Plastic", expenseName, expenseCost);
                            case "Glass(TaxDeductible)" ->
                                    expense = factory.createExpense("Factory.Glass", expenseName, expenseCost);
                        }
                        AddCommand addCommand = new AddCommand(user, expense);
                        invoker.setCommands(addCommand);
                    }

                    invoker.executeCommands();

                    new MainExpenseController(user);
                    frame.dispose();
                }
            }
        });
        panel.add(addButton);

        frame.setVisible(true);
    }
}
