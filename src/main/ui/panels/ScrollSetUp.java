package ui.panels;

import model.food.AllMeals;
import ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ScrollSetUp extends Panel {
    private AllMeals allMeals;
    private JPanel basePanel;
    private JPanel mainPanel;
    private JScrollPane scroll;
    private JButton button;
    private JPanel testPanel;
    private DisplayMeal displayMeal;

    public ScrollSetUp(AllMeals allMeals) {
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


//        JPanel testPanel2 = new JPanel();
//        testPanel2.setBackground(Colors.SIDEBAR);
//        testPanel2.setBounds(15, 120, 420, 90);
//        testPanel2.setBorder(BorderFactory.createLineBorder(Colors.MAIN_COLOUR, 5));
//
//        button = new JButton("Submit");
//        generateButton(button, 400, 5, 10);
        //mainPanel.add(testPanel);
//        mainPanel.add(testPanel2);
//        testPanel.add(button);
        placeMeals();
        basePanel.add(scroll, BorderLayout.CENTER);
    }

    public void placeMeals() {
        for (int i = 0; i < allMeals.getAllMeals().size(); i++) {
            DisplayMeal displayMeal = new DisplayMeal(allMeals, allMeals.getAllMeals().get(i), i);
            mainPanel.add(displayMeal.getPanel());
        }
    }



    public int calculateHeight() {
        int height = 15 + (105 * allMeals.getAllMeals().size());
        if (height > 550) {
            return height;
        } else {
            return 550;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == button)
    }

    @Override
    public JPanel getPanel() {
        return basePanel;
    }


}