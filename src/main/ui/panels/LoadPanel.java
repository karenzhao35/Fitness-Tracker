package ui.panels;

import model.AllData;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.Colors;
import ui.FitnessInterface;
import ui.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class LoadPanel extends JFrame implements ActionListener {

    private JButton load;
    private JButton startNew;
    private JButton save;
    private JLabel loadMessage;
    private JLabel welcomeMessage;
    private JLabel saveMessage;

    public static final String JSON_STORE = "./data/log.json";

    private AllData allData;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public LoadPanel() {
        allData = new AllData("My data", "bro");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        load = new JButton();
//        save = new JButton();
        startNew = new JButton();

//        generateSaveButton();
        generateMainButton(startNew,"S T A R T  N E W", 440);
        generateSaveMessage();
        generateLoadMessage();

        generateMainButton(load,"L O A D  D A T A", 380);
        generateWelcome();


        setLayout(null);
        setBackground(Colors.MAIN_COLOUR);
        setSize(MainPanel.DIMENSION);
        // setPreferredSize(MainPanel.DIMENSION);
        add(welcomeMessage);
        add(saveMessage);
//        add(save);
        add(load);
        add(loadMessage);
        add(startNew);
        setVisible(true);

//        JFrame frame = new JFrame();
//        frame.setBackground(Colors.SIDEBAR);
//
//        JMenuBar menuBar = new JMenuBar();
//        menuBar.setOpaque(false);
//        menuBar.setFont(new Font("Monospace", Font.PLAIN, 10));
//        menuBar.setBackground(Colors.SIDEBAR);
//        menuBar.setBorderPainted(false);
//        menuBar.setForeground(Colors.MAIN_COLOUR);
//        JMenu file = new JMenu("File");
//        file.setOpaque(false);
//        JMenuItem save = new JMenu("Save");
//        save.setOpaque(false);
//        try {
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (UnsupportedLookAndFeelException e) {
//            throw new RuntimeException(e);
//        }
//
//        file.add(save);
//        menuBar.add(file);
//        frame.setJMenuBar(menuBar);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,500);
//        frame.setLayout(new BorderLayout());
//        frame.add(this);
//        frame.setVisible(true);
//
//
//        frame.pack();

    }



//    public LoadPanel getPanel() {
//        return this;
//    }

    public void generateLoadMessage() {
        ImageIcon unscaled = new ImageIcon(LoadPanel.class.getResource("welcome.png"));
        Image image = unscaled.getImage().getScaledInstance(300, 300, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon welcomeIcon = new ImageIcon(image);

        loadMessage = new JLabel("TO YOUR FITNESS CENTRE");
        loadMessage.setIcon(welcomeIcon);
        loadMessage.setIconTextGap(-10);
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
        welcomeMessage.setBounds(MainPanel.WIDTH / 2 - 100, MainPanel.HEIGHT / 2 + 20, 300, 200);
        welcomeMessage.setVisible(false);
    }

    public void generateSaveMessage() {
        saveMessage = new JLabel("Your data has been saved.");
        saveMessage.setForeground(Colors.SIDEBAR);
        saveMessage.setFont(new Font("Monospace", Font.BOLD, 15));
        saveMessage.setBounds(MainPanel.WIDTH / 2 - 100, MainPanel.HEIGHT / 2 - 30, 300, 200);
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


//    public void generateSaveButton() {
//        save = new JButton();
//        save.setText("SAVE");
//        save.setBounds(440, 5, 100, 30);
//        save.setFont(new Font("Monospace", Font.BOLD, 15)); // font of text
//        save.addActionListener(this);
//        save.setFocusable(false);
//        save.setOpaque(true);
//        save.setContentAreaFilled(true);
//        save.setBorderPainted(true);
//        save.setFocusPainted(false);
//        save.setBackground(Colors.SIDEBAR);
//        save.setForeground(Colors.MAIN_COLOUR);
//        save.setBorder(BorderFactory.createBevelBorder(0));
//    }


    // TODO: implement error messages?
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            try {
                allData = jsonReader.read();
                welcomeMessage.setVisible(true);
                setVisible(false);
                new FitnessInterface(allData);
                // System.out.println("Loaded " + allData.getName() + " from " + JSON_STORE);
            } catch (IOException error) {
                // System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
        if (e.getSource() == startNew) {
            allData = new AllData("My data", "bro");
            setVisible(false);
            new FitnessInterface(allData);
        }
//        if (e.getSource() == save) {
//            try {
//                jsonWriter.open();
//                jsonWriter.write(allData);
//                jsonWriter.close();
//                saveMessage.setVisible(true);
//                // System.out.println("Saved " + allData.getName() + " to " + JSON_STORE);
//            } catch (FileNotFoundException error) {
//                // System.out.println("Unable to write to file: " + JSON_STORE);
//            }
//        }

    }

    public AllData getData() {
        return allData;
    }

    public static void main(String[] args) {
        new LoadPanel();
    }
}