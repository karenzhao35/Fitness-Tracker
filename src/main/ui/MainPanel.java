package ui;

import model.AllData;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Main load panel for fitness interface
public class MainPanel extends JFrame implements ActionListener {
    public static final String JSON_STORE = "./data/log.json";
    private JButton load;
    private JButton startNew;
    private JLabel loadMessage;
    private AllData allData;
    private JsonReader jsonReader;

    // EFFECTS: constructs a main panel
    public MainPanel() {
        allData = new AllData("My data", "bro");
        jsonReader = new JsonReader(JSON_STORE);
        load = new JButton();
        startNew = new JButton();
        generateMainButton(startNew,"S T A R T  N E W", 440);
        generateMainButton(load,"L O A D  D A T A", 380);
        setUpMain();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up main frame and add components
    private void setUpMain() {
        setLayout(null);
        setSize(CardPanel.DIMENSION);
        getContentPane().setBackground(ColourPicker.MAIN_COLOUR);
        add(load);
        add(generateLoadMessage());
        add(startNew);
    }

    // EFFECTS: generates and returns load message
    public JLabel generateLoadMessage() {
        ImageIcon unscaled = new ImageIcon(MainPanel.class.getResource("welcome.png"));
        Image image = unscaled.getImage().getScaledInstance(300, 300, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon welcomeIcon = new ImageIcon(image);

        loadMessage = new JLabel("TO YOUR FITNESS CENTRE");
        loadMessage.setIcon(welcomeIcon);
        loadMessage.setIconTextGap(-10);
        loadMessage.setForeground(ColourPicker.SIDEBAR);
        loadMessage.setFont(new Font("Monospace", Font.BOLD, 15));
        loadMessage.setBounds(CardPanel.WIDTH / 2 - 150, CardPanel.HEIGHT / 2 - 430, 500, 500);
        loadMessage.setHorizontalTextPosition(JLabel.CENTER);
        loadMessage.setVerticalTextPosition(JLabel.BOTTOM);
        return loadMessage;
    }

    // MODIFIES: button
    // EFFECTS: generates button with given label and height
    public void generateMainButton(JButton button, String label, int height) {
        button.setText(label);
        button.setBounds(75, height, 400, 50);
        button.setFont(new Font("Monospace", Font.BOLD, 15));
        button.addActionListener(this);
        button.setFocusable(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setBackground(ColourPicker.SIDEBAR);
        button.setForeground(ColourPicker.MAIN_COLOUR);
        button.setBorder(BorderFactory.createBevelBorder(0));
    }



    // MODIFIES: this
    // EFFECTS: parses user input, either loading data are starting a new data file
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            try {
                allData = jsonReader.read();
                setVisible(false);
                new FitnessInterface(allData);
                System.out.println("Loaded " + allData.getName() + " from " + JSON_STORE);
            } catch (IOException error) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
        if (e.getSource() == startNew) {
            allData = new AllData("My data", "bro");
            setVisible(false);
            new FitnessInterface(allData);
        }
    }

    // EFFECTS: runs program
    public static void main(String[] args) {
        new MainPanel();
    }
}