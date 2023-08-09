package ui.panels.logs;

import model.AllData;
import ui.ColourPicker;
import ui.FitnessInterface;
import ui.panels.Panels;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// instantiates panel to log items
public abstract class InnerPanel implements ActionListener, Panels {
    protected JPanel main;
    protected JFrame frame;
    protected JTextField nameTextField;
    protected JButton submitButton;
    protected JButton doneButton;
    protected JLabel error;
    protected AllData allData;


    // EFFECTS: constructs InnerPanel and necessary items
    public InnerPanel() {
        main = new JPanel();
        nameTextField = new JTextField();
        submitButton = new JButton("Submit");
        doneButton = new JButton("Finish");
        error = generateLabels("Invalid input.", 157, 360);
        error.setVisible(false);
        setUpInnerPanel();

    }

    // MODIFIES: this
    // EFFECTS: generate panel items
    public void generatePanelItems() {
        generateButton(submitButton, 161, 310, 80);
        generateButton(doneButton, 161, 340, 80);
        generateTextField(nameTextField, 60);
    }

    // MODIFIES: this
    // EFFECTS: adds all items to main panel
    public void addToFoodPanel() {
        main.add(submitButton);
        main.add(doneButton);
        main.add(nameTextField);
        main.add(generateLabels("name: ", 50, 54));
        main.add(error);

    }

    // EFFECTS: returns main panel
    @Override
    public JPanel getPanel() {
        return main;
    }

    // MODIFIES: this
    // EFFECTS: sets up main panel
    public void setUpInnerPanel() {
        main.setLayout(null);
        main.setBackground(ColourPicker.SIDEBAR);
        main.setBounds(75, 200, 400, 400);
    }

    // MODIFIES: textField
    // EFFECTS: generates generic text field
    public void generateTextField(JTextField textField, int y) {
        textField.setBounds(110, y, 230, 30);
        textField.setFont(new Font("Monospace", Font.PLAIN, 13));
        textField.setForeground(ColourPicker.SIDEBAR);
        textField.setBackground(ColourPicker.MAIN_COLOUR);
        textField.setCaretColor(ColourPicker.SIDEBAR);
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

    // MODIFIES: label
    // EFFECTS: generates generic text field label
    public JLabel generateLabels(String name, int x, int y) {
        JLabel label = new JLabel(name);
        label.setForeground(ColourPicker.MAIN_COLOUR);
        label.setFont(new Font("Monospace", Font.BOLD, 13));
        label.setBounds(x, y, 300, 40);
        return label;
    }

    // MODIFIES: button
    // EFFECTS: generates generic button
    public void generateButton(JButton button, int x, int y, int width) {
        button.setBounds(x, y, width, 30);
        button.setFont(new Font("Monospace", Font.PLAIN, 12));
        button.addActionListener(this);
        button.setFocusable(false);
        button.setForeground(ColourPicker.SIDEBAR);
    }

    // MODIFIES: this
    // EFFECTS: refreshes main frame
    public void refresh() {
        frame.dispose();
        FitnessInterface fitnessInterface = new FitnessInterface(allData);
        fitnessInterface.setVisible(true);
    }

    // EFFECTS: produces true if entry is valid
    public abstract boolean checkValid();

    // MODIFIES: textField
    // EFFECTS: reset text fields
    public abstract void clearTextFields();

    // MODIFIES: this
    // EFFECTS: sets up frame data
    public void start(AllData allData, JFrame frame) {
        this.allData = allData;
        this.frame = frame;
    }
}