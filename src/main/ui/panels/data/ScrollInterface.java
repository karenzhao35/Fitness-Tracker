package ui.panels.data;

import model.AllData;
import model.food.AllMeals;
import model.workout.AllWorkouts;
import ui.ColourPicker;
import ui.panels.Panels;
import javax.swing.*;
import java.awt.*;

// sets ups scroll interface for data
public abstract class ScrollInterface implements Panels {
    protected AllWorkouts allWorkouts;
    protected AllMeals allMeals;
    protected AllData allData;
    protected JPanel basePanel;
    protected JPanel mainPanel;
    protected JScrollPane scroll;
    protected JFrame frame;

    // EFFECTS: constructs a new scroll interface with given data
    public ScrollInterface(AllData allData, JFrame frame) {
        this.allData = allData;
        this.frame = frame;
        this.allWorkouts = allData.getAllWorkouts();
        this.allMeals = allData.getAllMeals();
        basePanel = new JPanel();
        mainPanel = new JPanel();
        scroll = new JScrollPane(mainPanel);
        setUpBase();
        setUpScroll();
        setUpMain();
        placeItems();
        basePanel.add(scroll, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: set up main panel
    private void setUpMain() {
        mainPanel.setLayout(null);
        mainPanel.setBackground(ColourPicker.SIDEBAR);
        mainPanel.setPreferredSize(new Dimension(450, calculateHeight()));
    }

    // MODIFIES: this
    // EFFECTS: sets up scroll panel
    private void setUpScroll() {
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
    }

    // MODIFIES: this
    // EFFECTS: sets up base panel
    private void setUpBase() {
        basePanel.setBounds(45, 200, 465, 545);
        basePanel.setBackground(ColourPicker.SIDEBAR);
        basePanel.setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: place individual items on scroll
    public abstract void placeItems();


    // EFFECTS: calculate new height of main panel
    public abstract int calculateHeight();

    // EFFECTS: returns base panel
    @Override
    public JPanel getPanel() {
        return basePanel;
    }





}