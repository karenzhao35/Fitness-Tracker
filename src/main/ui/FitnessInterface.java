package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FitnessInterface extends JFrame implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int SIDEBAR_WIDTH = 250;
    public static final int WINDOW_WIDTH = WIDTH - SIDEBAR_WIDTH;

    private JPanel sideBar;
    private JPanel mainPanel;
    private CardLayout card;

    private JButton loadButton;
    private JButton workoutButton;
    private JButton mealButton;
    private JButton mealSearchButton;
    private JButton workoutSearchButton;

    public FitnessInterface() {
        createSideBar();



        setTitle("Your Fitness Buddy");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        addMainPanels();

        pack();
        setResizable(false);
        setVisible(true);
        getContentPane().setBackground(Colors.MAIN_COLOUR);
    }

    public void addMainPanels() {
        mainPanel = MainPanel.getInstance().getPanel();
        card = MainPanel.getInstance().getMainCard();
        add(mainPanel, BorderLayout.EAST);
        add(sideBar, BorderLayout.WEST);
    }


    public void createSideBar() {
        createLoadButton();
        createWorkoutButton();
        createMealButton();
        createSearchMealButton();
        createSearchWorkoutButton();

        sideBar = new JPanel();
        sideBar.setLayout(null);
        sideBar.setBackground(Colors.SIDEBAR);
        sideBar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, HEIGHT));

        sideBar.add(workoutSearchButton);
        sideBar.add(mealSearchButton);
        sideBar.add(mealButton);
        sideBar.add(workoutButton);
        sideBar.add(loadButton);
    }

    public void createLoadButton() {
        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("menu-book.png"));
        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon menuIcon = new ImageIcon(image);

        loadButton = new JButton("MAIN MENU          ");
        loadButton.setVerticalTextPosition(JButton.BOTTOM);
        loadButton.setBounds(0, 0, SIDEBAR_WIDTH + 3, 100);

        loadButton.setIcon(menuIcon);
        initializeSideBarButton(loadButton);
    }

    public void createWorkoutButton() {
        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("sport.png"));
        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon workoutIcon = new ImageIcon(image);

        workoutButton = new JButton("ADD WORKOUT     ");
        workoutButton.setVerticalTextPosition(JButton.BOTTOM);
        workoutButton.setBounds(0, 97, SIDEBAR_WIDTH + 3, 100);

        workoutButton.setIcon(workoutIcon);
        initializeSideBarButton(workoutButton);
    }

    public void createMealButton() {
        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("healthy-food.png"));
        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon mealIcon = new ImageIcon(image);

        mealButton = new JButton("LOG FOOD            ");
        mealButton.setVerticalTextPosition(JButton.BOTTOM);
        mealButton.setBounds(0, 194, SIDEBAR_WIDTH + 3, 100);

        mealButton.setIcon(mealIcon);
        initializeSideBarButton(mealButton);
    }

    public void createSearchMealButton() {
        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("search-food.png"));
        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon searchIcon = new ImageIcon(image);

        mealSearchButton = new JButton("SEARCH FOOD      ");
        mealSearchButton.setBounds(0, 291, SIDEBAR_WIDTH + 3, 100);

        mealSearchButton.setIcon(searchIcon);
        initializeSideBarButton(mealSearchButton);

    }

    public void createSearchWorkoutButton() {
        ImageIcon unscaled = new ImageIcon(FitnessInterface.class.getResource("calendar.png"));
        Image image = unscaled.getImage().getScaledInstance(35, 35, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon searchIcon = new ImageIcon(image);

        workoutSearchButton = new JButton("SEARCH WORKOUT");
        workoutSearchButton.setBounds(0, 388, SIDEBAR_WIDTH + 3, 100);

        workoutSearchButton.setIcon(searchIcon);
        initializeSideBarButton(workoutSearchButton);

    }


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
        new FitnessInterface();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            card.show(mainPanel, "load");
        }
        if (e.getSource() == mealButton) {
            card.show(mainPanel, "food");
        }
        if (e.getSource() == workoutButton) {
            card.show(mainPanel, "workout");
        }
        if (e.getSource() == mealSearchButton) {
            card.show(mainPanel, "search food");
        }
    }

}