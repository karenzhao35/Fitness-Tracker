package ui.panels;

import model.food.AllMeals;
import ui.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SearchFoodPanel extends MainPanels {
    private ScrollSetUp scrollSetUpPanel;
    private JPanel display;


    public SearchFoodPanel(AllMeals allMeals) {
        super(null, allMeals);
        scrollSetUpPanel = new ScrollSetUp(allMeals);
        display = scrollSetUpPanel.getPanel();
        addComponents();
    }

    public void addComponents() {
        super.addComponents();
        mainPanel.add(display);
        mainPanel.add(generateHeading("SEARCH PREVIOUS MEALS", 105));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitDateButton) {
            String date = dateTextField.getText();
            if (date.equals("today")) {
                error.setVisible(false);

            } else if (MainPanel.getInstance().dateFormat(date)) {

                error.setVisible(false);

            } else {
                error.setVisible(true);
            }
        }
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}