package ui;

import model.AllData;
import persistence.JsonWriter;
import ui.panels.LoadPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class FitnessInterface extends JFrame implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int SIDEBAR_WIDTH = 250;
    public static final int WINDOW_WIDTH = WIDTH - SIDEBAR_WIDTH;

    private JPanel sideBar;
    private JPanel dataPanel;
    private CardLayout card;
    private AllData allData;
    private MainPanel mainPanel;

    private JButton loadButton;
    private JButton workoutButton;
    private JButton mealButton;
    private JButton mealSearchButton;
    private JButton workoutSearchButton;

    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem save;
    private JMenuItem exit;
    private JMenuItem refresh;

    private JsonWriter jsonWriter;

    public FitnessInterface(AllData allData) {
        this.allData = allData;
        sideBar = new JPanel();
        mainPanel = new MainPanel(allData.getAllWorkouts(), allData.getAllMeals());
        jsonWriter = new JsonWriter(LoadPanel.JSON_STORE);
        loadButton = new JButton("MAIN MENU          ");
        workoutButton = new JButton("ADD WORKOUT     ");
        mealButton = new JButton("LOG FOOD            ");
        mealSearchButton = new JButton("SEARCH FOOD      ");
        workoutSearchButton = new JButton("SEARCH WORKOUT");
        menuBar = new JMenuBar();
        file = new JMenu("File");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        refresh = new JMenuItem("Refresh");
        setUpMenuBar();
        createSideBar();
        setUpFrame();
    }

    private void setUpFrame() {
        setTitle("Your Fitness Buddy");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        setJMenuBar(menuBar);
        addMainPanels();
        setBackground(Colors.SIDEBAR);

        pack();
        setResizable(false);
        setVisible(true);
        getContentPane().setBackground(Colors.MAIN_COLOUR);
    }

    private void setUpMenuBar() {
        menuBar.setFont(new Font("Monospace", Font.PLAIN, 10));
        save.addActionListener(this);
        exit.addActionListener(this);
        refresh.addActionListener(this);
        file.add(save);
        file.add(refresh);
        file.add(exit);
        menuBar.add(file);
    }

    public void addMainPanels() {
        dataPanel = mainPanel.getPanel();
        card = mainPanel.getMainCard();
        add(dataPanel, BorderLayout.EAST);
        add(sideBar, BorderLayout.WEST);
    }


    public void createSideBar() {
//        sideBarButtons("menu.png", loadButton, 0);
//        sideBarButtons("gym.png", workoutSearchButton, 388);
        sideBarButtons("workout.png", workoutButton, 0);
        sideBarButtons("noodle.png", mealButton, 97);
        sideBarButtons("search-food.png", mealSearchButton, 194);
        sideBarButtons("gym.png", workoutSearchButton, 291);


        sideBar.setLayout(null);
        sideBar.setBackground(Colors.SIDEBAR);
        sideBar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, HEIGHT));
        sideBar.add(workoutSearchButton);
        sideBar.add(mealSearchButton);
        sideBar.add(mealButton);
        sideBar.add(workoutButton);
        sideBar.add(loadButton);
    }


    private void sideBarButtons(String file, JButton button, int height) {
        ImageIcon unscaled = new ImageIcon("src/main/ui/" + file);
        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setBounds(0, height, SIDEBAR_WIDTH + 3, 100);
        button.setIcon(icon);
        initializeSideBarButton(button);
    }

//    public void createWorkoutButton() {
//        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("sport.png"));
//        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
//        ImageIcon workoutIcon = new ImageIcon(image);
//
//        workoutButton.setVerticalTextPosition(JButton.BOTTOM);
//        workoutButton.setBounds(0, 97, SIDEBAR_WIDTH + 3, 100);
//
//        workoutButton.setIcon(workoutIcon);
//        initializeSideBarButton(workoutButton);
//    }

//    public void createMealButton() {
//        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("healthy-food.png"));
//        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
//        ImageIcon mealIcon = new ImageIcon(image);
//
//        mealButton.setVerticalTextPosition(JButton.BOTTOM);
//        mealButton.setBounds(0, 194, SIDEBAR_WIDTH + 3, 100);
//
//        mealButton.setIcon(mealIcon);
//        initializeSideBarButton(mealButton);
//    }

//    public void createSearchMealButton() {
//        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("search-food.png"));
//        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
//        ImageIcon searchIcon = new ImageIcon(image);
//
//        mealSearchButton.setBounds(0, 291, SIDEBAR_WIDTH + 3, 100);
//
//        mealSearchButton.setIcon(searchIcon);
//        initializeSideBarButton(mealSearchButton);
//
//    }

//    public void createSearchWorkoutButton() {
//        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("calendar.png"));
//        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
//        ImageIcon searchIcon = new ImageIcon(image);
//
//        workoutSearchButton.setBounds(0, 388, SIDEBAR_WIDTH + 3, 100);
//
//        workoutSearchButton.setIcon(searchIcon);
//        initializeSideBarButton(workoutSearchButton);
//
//    }


    public void initializeSideBarButton(JButton button) {
        button.setFont(new Font("Monospace", Font.ITALIC, 20));
        button.setIconTextGap(10);
        button.setForeground(Colors.MAIN_COLOUR);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createLineBorder(Colors.MAIN_COLOUR, 3, true));
    }


    public static void main(String[] args) {
        new FitnessInterface(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mealButton) {
            card.show(dataPanel, "food");
        }
        if (e.getSource() == workoutButton) {
            card.show(dataPanel, "workout");
        }
        if (e.getSource() == mealSearchButton) {
            card.show(dataPanel, "search food");
        }
        if (e.getSource() == save) {
            try {
                jsonWriter.open();
                jsonWriter.write(allData);
                jsonWriter.close();
                // System.out.println("Saved " + allData.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException error) {
                // System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == refresh) {
            new FitnessInterface(allData);
        }
    }

}