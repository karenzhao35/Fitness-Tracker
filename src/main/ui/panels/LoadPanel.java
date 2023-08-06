package ui.panels;

import model.AllData;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.Colors;
import ui.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class LoadPanel extends JPanel implements ActionListener, Panels {

    private JButton load;
    private JButton startNew;
    private JButton save;
    private JLabel loadMessage;
    private JLabel welcomeMessage;
    private JLabel saveMessage;

    private static final String JSON_STORE = "./data/log.json";

    private AllData allData;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public LoadPanel() {
        allData = new AllData("My data", "bro");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        generateSaveButton();
        generateSaveMessage();
        generateLoadMessage();
        generateNewButton();
        generateLoadButton();
        generateWelcome();

        setLayout(null);
        setBackground(Colors.MAIN_COLOUR);
        setPreferredSize(MainPanel.DIMENSION);
        add(welcomeMessage);
        add(saveMessage);
        add(save);
        add(load);
        add(loadMessage);
        add(startNew);

    }


    public LoadPanel getPanel() {
        return this;
    }

    public void generateLoadMessage() {
        ImageIcon unscaled = new ImageIcon(LoadPanel.class.getResource("welcome.png"));
        Image image = unscaled.getImage().getScaledInstance(300, 300, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon welcomeIcon = new ImageIcon(image);

        loadMessage = new JLabel("CLICK TO LOAD DATA:");
        loadMessage.setIcon(welcomeIcon);
        loadMessage.setIconTextGap(-20);
        loadMessage.setForeground(Colors.SIDEBAR);
        loadMessage.setFont(new Font("Monospace", Font.BOLD, 15));
        loadMessage.setBounds(MainPanel.WIDTH / 2 - 150, MainPanel.HEIGHT / 2 - 430, 500, 500);
        loadMessage.setHorizontalTextPosition(JLabel.CENTER);
        loadMessage.setVerticalTextPosition(JLabel.BOTTOM);
    }

    public void generateWelcome() {
        welcomeMessage = new JLabel("Nice to see you again bro!");
        welcomeMessage.setForeground(Colors.SIDEBAR);
        welcomeMessage.setFont(new Font("Monospace", Font.BOLD, 15));
        welcomeMessage.setBounds(MainPanel.WIDTH / 2 - 100, MainPanel.HEIGHT / 2 - 50, 300, 200);
        welcomeMessage.setVisible(false);
    }

    public void generateSaveMessage() {
        saveMessage = new JLabel("Your data has been saved.");
        saveMessage.setForeground(Colors.SIDEBAR);
        saveMessage.setFont(new Font("Monospace", Font.BOLD, 15));
        saveMessage.setBounds(MainPanel.WIDTH / 2 - 100, MainPanel.HEIGHT / 2 - 30, 300, 200);
        saveMessage.setVisible(false);
    }


    public void generateLoadButton() {
        load = new JButton();
        load.setText("L O A D  D A T A");
        load.setBounds(MainPanel.WIDTH / 2 - 200, MainPanel.HEIGHT / 2 - 20, 400, 50);
        load.setFont(new Font("Monospace", Font.BOLD, 15)); // font of text
        load.addActionListener(this);
        load.setFocusable(false);
        load.setOpaque(true);
        load.setContentAreaFilled(true);
        load.setBorderPainted(true);
        load.setFocusPainted(false);
        load.setBackground(Colors.SIDEBAR);
        load.setForeground(Colors.MAIN_COLOUR);
        load.setBorder(BorderFactory.createBevelBorder(0));
    }

    public void generateNewButton() {
        startNew = new JButton();
        startNew.setText("S T A R T  N E W");
        startNew.setBounds(MainPanel.WIDTH / 2 - 200, MainPanel.HEIGHT / 2 + 40, 400, 50);
        startNew.setFont(new Font("Monospace", Font.BOLD, 15)); // font of text
        startNew.addActionListener(this);
        startNew.setFocusable(false);
        startNew.setOpaque(true);
        startNew.setContentAreaFilled(true);
        startNew.setBorderPainted(true);
        startNew.setFocusPainted(false);
        startNew.setBackground(Colors.SIDEBAR);
        startNew.setForeground(Colors.MAIN_COLOUR);
        startNew.setBorder(BorderFactory.createBevelBorder(0));
    }

    public void generateSaveButton() {
        save = new JButton();
        save.setText("SAVE");
        save.setBounds(440, 5, 100, 30);
        save.setFont(new Font("Monospace", Font.BOLD, 15)); // font of text
        save.addActionListener(this);
        save.setFocusable(false);
        save.setOpaque(true);
        save.setContentAreaFilled(true);
        save.setBorderPainted(true);
        save.setFocusPainted(false);
        save.setBackground(Colors.SIDEBAR);
        save.setForeground(Colors.MAIN_COLOUR);
        save.setBorder(BorderFactory.createBevelBorder(0));
    }


    // TODO: implement error messages?
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            try {
                allData = jsonReader.read();
                welcomeMessage.setVisible(true);

                // System.out.println("Loaded " + allData.getName() + " from " + JSON_STORE);
            } catch (IOException error) {
                // System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
        if (e.getSource() == save) {
            try {
                jsonWriter.open();
                jsonWriter.write(allData);
                jsonWriter.close();
                saveMessage.setVisible(true);
                // System.out.println("Saved " + allData.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException error) {
                // System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }

    }

    public AllData getData() {
        return allData;
    }

}