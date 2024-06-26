package Controller.Gui;

import Model.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginController implements ActionListener{
    JFrame frame;

    JButton loginButton;
    JTextField nameField, budgetField, resultField;
    JLabel nameLabel, budgetLabel;
    JPanel panel;
    Font myFont = new Font("Calibri", Font.BOLD,30);
    Font inputFont = new Font("Calibri", Font.BOLD,10);

    public LoginController(){

        frame = new JFrame("Login| Personal Finance Tracker");
        frame.setSize(600,800);
        frame.setLayout(null);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(75,50,100,50);
        nameLabel.setFont(myFont);
        frame.add(nameLabel);

        budgetLabel = new JLabel("Budget:");
        budgetLabel.setBounds(75,150,100,50);
        budgetLabel.setFont(myFont);
        frame.add(budgetLabel);

        nameField = new JTextField();
        nameField.setBounds(75,100,450,20);
        nameField.setFont(inputFont);

        budgetField = new JTextField();
        budgetField.setBounds(75,200,450,20);
        budgetField.setFont(inputFont);

        resultField = new JTextField();
        resultField.setBounds(75,300,450,50);
        resultField.setFont(myFont);
        resultField.setEditable(false);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setFocusable (false);

        loginButton.setBounds(75,450,450,50);

        frame.add(nameField);
        frame.add(budgetField);
        frame.add(resultField);
        frame.add(loginButton);
        frame.setVisible(true);
    }
    public boolean nameisEmpty(){
        if(nameField.getText().isEmpty()){
            return true;
        }
        return false;
    }
    public boolean budgetisEmpty(){
        if(budgetField.getText().isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton) {
            if(nameisEmpty()  ){
                resultField.setText("Enter a username");
            }
            else if( budgetisEmpty() ){
                resultField.setText("Enter a budget");
            }
            else
            {
                String name = nameField.getText();
                try {
                    Double budget = Double.parseDouble(budgetField.getText());
                    User userReciever = new User(name, budget);
                    new MainExpenseController(userReciever);
                } catch (NumberFormatException ex) {
                    resultField.setText("Budget must be a number");
                }
            }
        }
    }
}
