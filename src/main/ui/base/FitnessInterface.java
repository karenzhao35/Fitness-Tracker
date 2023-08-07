package ui.base;

import model.AllData;
import persistence.JsonWriter;
import ui.ColourPicker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static ui.MainPanel.JSON_STORE;

// main fitness interface frame
public class FitnessInterface extends JFrame implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int SIDEBAR_WIDTH = 250;
    public static final int WINDOW_WIDTH = WIDTH - SIDEBAR_WIDTH;

    private JPanel sideBar;
    private JPanel dataPanel;
    private CardLayout card;
    private AllData allData;
    private CardPanel cardPanel;

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

    // EFFECTS: constructs a FitnessInterface with given allData
    public FitnessInterface(AllData allData) {
        this.allData = allData;
        init();
        setUpMenuBar();
        createSideBar();
        setUpFrame();
    }

    // MODIFIES: this
    // EFFECTS: initializes frame components
    private void init() {
        sideBar = new JPanel();
        cardPanel = new CardPanel(allData.getAllWorkouts(), allData.getAllMeals());
        jsonWriter = new JsonWriter(JSON_STORE);
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
    }

    // MODIFIES: this
    // EFFECTS: sets up main frame
    private void setUpFrame() {
        setTitle("Your Fitness Buddy");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        setJMenuBar(menuBar);
        addMainPanels();
        setBackground(ColourPicker.SIDEBAR);

        pack();
        setResizable(false);
        setVisible(true);
        getContentPane().setBackground(ColourPicker.MAIN_COLOUR);
    }

    // MODIFIES: this
    // EFFECTS: sets up menu bar
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

    // MODIFIES: this
    // EFFECTS: add main panels to the frame
    public void addMainPanels() {
        dataPanel = cardPanel.getPanel();
        card = cardPanel.getMainCard();
        add(dataPanel, BorderLayout.EAST);
        add(sideBar, BorderLayout.WEST);
    }


    // MODIFIES: this
    // EFFECTS: creates sidebar and adds components
    public void createSideBar() {
        sideBarButtons("workout.png", workoutButton, 0);
        sideBarButtons("noodle.png", mealButton, 97);
        sideBarButtons("search-food.png", mealSearchButton, 194);
        sideBarButtons("gym.png", workoutSearchButton, 291);

        sideBar.setLayout(null);
        sideBar.setBackground(ColourPicker.SIDEBAR);
        sideBar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, HEIGHT));
        sideBar.add(workoutSearchButton);
        sideBar.add(mealSearchButton);
        sideBar.add(mealButton);
        sideBar.add(workoutButton);
        sideBar.add(loadButton);
    }

//    IMAGE ICON CREDITS:
//    WORKOUT:
//    <a target="_blank" href="https://icons8.com/icon/20288/gymnastics">Workout</a> icon by <a target="_blank" href
//    ="https://icons8.com">Icons8</a>
//    FOOD:
//    <a target="_blank" href="https://icons8.com/icon/AdSgGRnON0R9/kawaii-noodle">Kawaii Noodle</a> icon by <a target
//    ="_blank" href="https://icons8.com">Icons8</a>
//    SEARCH FOOD:
//    <a target="_blank" href="https://icons8.com/icon/123417/search-in-list">Search in List</a> icon by <a target
//    ="_blank" href="https://icons8.com">Icons8</a>
//    SEARCH WORKOUT:
//    <a target="_blank" href="https://icons8.com/icon/l5Br0fxxE35Q/gym">Gym</a> icon by <a target="_blank" href
//    ="https://icons8.com">Icons8</a>


    // MODIFIES: button
    // EFFECTS: generates button with given file and height
    private void sideBarButtons(String file, JButton button, int height) {
        ImageIcon unscaled = new ImageIcon("src/main/ui/sideBarImages/" + file);
        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setBounds(0, height, SIDEBAR_WIDTH + 3, 100);
        button.setIcon(icon);
        initializeSideBarButton(button);
    }

    // MODIFIES: button
    // EFFECTS: generates sidebar buttons
    public void initializeSideBarButton(JButton button) {
        button.setFont(new Font("Monospace", Font.ITALIC, 20));
        button.setIconTextGap(10);
        button.setForeground(ColourPicker.MAIN_COLOUR);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createLineBorder(ColourPicker.MAIN_COLOUR, 3, true));
    }

    // MODIFIES: this
    // EFFECTS: parses user input for sidebar and menu bar
    @Override
    public void actionPerformed(ActionEvent e) {
        sideBarActions(e);
        menuBarActions(e);
    }

    // MODIFIES: this
    // EFFECTS: parses user input for the menu bar
    private void menuBarActions(ActionEvent e) {
        if (e.getSource() == save) {
            try {
                jsonWriter.open();
                jsonWriter.write(allData);
                jsonWriter.close();
                System.out.println("Saved " + allData.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException error) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == refresh) {
            this.dispose();
            FitnessInterface main = new FitnessInterface(allData);
            main.setVisible(true);

        }
    }

    // MODIFIES: this
    // EFFECTS: parses user input for the sidebar
    private void sideBarActions(ActionEvent e) {
        if (e.getSource() == mealButton) {
            card.show(dataPanel, "food");
        }
        if (e.getSource() == workoutButton) {
            card.show(dataPanel, "workout");
        }
        if (e.getSource() == mealSearchButton) {
            card.show(dataPanel, "search food");
        }
        if (e.getSource() == workoutSearchButton) {
            card.show(dataPanel, "search workout");
        }
    }

}