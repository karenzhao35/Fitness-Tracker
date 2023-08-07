package ui;

import model.AllData;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class MainPanel extends JFrame implements ActionListener {
    public static final String JSON_STORE = "./data/log.json";
    private JButton load;
    private JButton startNew;
    private JLabel loadMessage;
    private JLabel welcomeMessage;
    private JLabel saveMessage;
    private AllData allData;
    private JsonReader jsonReader;

    public MainPanel() {
        allData = new AllData("My data", "bro");
        jsonReader = new JsonReader(JSON_STORE);
        load = new JButton();
        startNew = new JButton();

        generateMainButton(startNew,"S T A R T  N E W", 440);
        generateSaveMessage();
        generateLoadMessage();

        generateMainButton(load,"L O A D  D A T A", 380);
        generateWelcome();
        setLayout(null);
        setSize(CardPanel.DIMENSION);
        add(welcomeMessage);
        add(saveMessage);
        add(load);
        add(loadMessage);
        add(startNew);

        getContentPane().setBackground(Colors.MAIN_COLOUR);
        setVisible(true);

    }


    public void generateLoadMessage() {
        ImageIcon unscaled = new ImageIcon(MainPanel.class.getResource("welcome.png"));
        Image image = unscaled.getImage().getScaledInstance(300, 300, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon welcomeIcon = new ImageIcon(image);

        loadMessage = new JLabel("TO YOUR FITNESS CENTRE");
        loadMessage.setIcon(welcomeIcon);
        loadMessage.setIconTextGap(-10);
        loadMessage.setForeground(Colors.SIDEBAR);
        loadMessage.setFont(new Font("Monospace", Font.BOLD, 15));
        loadMessage.setBounds(CardPanel.WIDTH / 2 - 150, CardPanel.HEIGHT / 2 - 430, 500, 500);
        loadMessage.setHorizontalTextPosition(JLabel.CENTER);
        loadMessage.setVerticalTextPosition(JLabel.BOTTOM);
    }

    public void generateWelcome() {
        welcomeMessage = new JLabel("Nice to see you again bro!");
        welcomeMessage.setForeground(Colors.SIDEBAR);
        welcomeMessage.setFont(new Font("Monospace", Font.BOLD, 15));
        welcomeMessage.setBounds(CardPanel.WIDTH / 2 - 100, CardPanel.HEIGHT / 2 + 20, 300, 200);
        welcomeMessage.setVisible(false);
    }

    public void generateSaveMessage() {
        saveMessage = new JLabel("Your data has been saved.");
        saveMessage.setForeground(Colors.SIDEBAR);
        saveMessage.setFont(new Font("Monospace", Font.BOLD, 15));
        saveMessage.setBounds(CardPanel.WIDTH / 2 - 100, CardPanel.HEIGHT / 2 - 30, 300, 200);
        saveMessage.setVisible(false);
    }


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
        button.setBackground(Colors.SIDEBAR);
        button.setForeground(Colors.MAIN_COLOUR);
        button.setBorder(BorderFactory.createBevelBorder(0));
    }



    // TODO: implement error messages?
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            try {
                allData = jsonReader.read();
                welcomeMessage.setVisible(true);
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

    public AllData getData() {
        return allData;
    }

    public static void main(String[] args) {
        new MainPanel();
    }
}