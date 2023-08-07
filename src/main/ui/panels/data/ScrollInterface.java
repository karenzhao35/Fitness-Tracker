package ui.panels.data;

import model.food.AllMeals;
import model.workout.AllWorkouts;
import ui.Colors;
import ui.panels.Panels;

import javax.swing.*;
import java.awt.*;

public abstract class ScrollInterface implements Panels {
    protected AllWorkouts allWorkouts;
    protected AllMeals allMeals;
    protected JPanel basePanel;
    protected JPanel mainPanel;
    protected JScrollPane scroll;

    public ScrollInterface(AllWorkouts allWorkouts, AllMeals allMeals) {
        this.allWorkouts = allWorkouts;
        this.allMeals = allMeals;
        basePanel = new JPanel();
        mainPanel = new JPanel();
        scroll = new JScrollPane(mainPanel);

        basePanel.setBounds(45, 200, 465, 545);
        basePanel.setBackground(Colors.SIDEBAR);
        basePanel.setLayout(new BorderLayout());

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);

        mainPanel.setLayout(null);
        mainPanel.setBackground(Colors.SIDEBAR);
        mainPanel.setPreferredSize(new Dimension(450, calculateHeight()));
        placeItems();


        basePanel.add(scroll, BorderLayout.CENTER);
    }

    public abstract void placeItems();


    public abstract int calculateHeight();

    @Override
    public JPanel getPanel() {
        return basePanel;
    }





}