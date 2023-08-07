package ui.panels.logs;

import ui.Colors;
import ui.panels.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class InnerPanel implements ActionListener, Panels {
    protected JPanel main;
    protected JTextField nameTextField;
    protected JButton submitButton;
    protected JButton doneButton;
    protected JLabel error;

    public InnerPanel() {
        main = new JPanel();
        nameTextField = new JTextField();
        submitButton = new JButton("Submit");
        doneButton = new JButton("Finish");
        error = generateLabels("Invalid input.", 157, 360);
        error.setVisible(false);
        setUpInnerPanel();

    }

    public void generatePanelItems() {
        generateButton(submitButton, 161, 310, 80);
        generateButton(doneButton, 161, 340, 80);
        generateTextField(nameTextField, 60);
    }

    public void addToFoodPanel() {
        main.add(submitButton);
        main.add(doneButton);
        main.add(nameTextField);
        main.add(generateLabels("name: ", 50, 54));
        main.add(error);

    }

    public JPanel getPanel() {
        return main;
    }

    public void setUpInnerPanel() {
        main.setLayout(null);
        main.setBackground(Colors.SIDEBAR);
        main.setBounds(75, 200, 400, 400);
    }

    public void generateTextField(JTextField textField, int y) {
        textField.setBounds(110, y, 230, 30);
        textField.setFont(new Font("Monospace", Font.PLAIN, 13));
        textField.setForeground(Colors.SIDEBAR);
        textField.setBackground(Colors.MAIN_COLOUR);
        textField.setCaretColor(Colors.SIDEBAR);
    }

    // EFFECTS: produces true if given string is an integer
    protected boolean isInteger(String command) {
        try {
            Integer.parseInt(command);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public JLabel generateLabels(String name, int x, int y) {
        JLabel label = new JLabel(name);
        label.setForeground(Colors.MAIN_COLOUR);
        label.setFont(new Font("Monospace", Font.BOLD, 13));
        label.setBounds(x, y, 300, 40);
        return label;
    }

    public void generateButton(JButton button, int x, int y, int width) {
        button.setBounds(x, y, width, 30);
        button.setFont(new Font("Monospace", Font.PLAIN, 12));
        button.addActionListener(this);
        button.setFocusable(false);
        button.setForeground(Colors.SIDEBAR);
    }

    public abstract boolean checkValid();

    public abstract void clearTextFields();
}