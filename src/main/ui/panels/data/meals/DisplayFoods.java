package ui.panels.data.meals;

import model.food.Food;
import model.food.MealType;
import model.food.Meals;
import ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayFoods {
    private JFrame main;
    private Meals meals;

    private JPanel breakfastBase;
    private JPanel lunchBase;
    private JPanel dinnerBase;
    private JPanel snackBase;

    private JPanel breakfastMain;
    private JPanel lunchMain;
    private JPanel dinnerMain;
    private JPanel snackMain;

    public DisplayFoods(Meals meals) {
        this.meals = meals;
        main = new JFrame();

        breakfastBase = new JPanel();
        lunchBase = new JPanel();
        dinnerBase = new JPanel();
        snackBase = new JPanel();

        breakfastMain = new JPanel();
        lunchMain = new JPanel();
        dinnerMain = new JPanel();
        snackMain = new JPanel();

        ArrayList<ArrayList<Food>> separated = separateFoodTypes(meals);


        main.setTitle("Meal on " + meals.getDate());

        main.setLayout(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setMinimumSize(new Dimension(500, 700));
        main.getContentPane().setBackground(Colors.MAIN_COLOUR);

        addMainComponents();
        placeLabels(separated);
        initBase(separated);

        main.pack();
        main.setVisible(true);
    }

    private void addMainComponents() {
        main.add(addPanel(breakfastBase, 65));
        main.add(addPanel(lunchBase, 215));
        main.add(addPanel(dinnerBase, 370));
        main.add(addPanel(snackBase, 530));

        breakfastBase.add(addScroll(breakfastMain), BorderLayout.CENTER);
        lunchBase.add(addScroll(lunchMain), BorderLayout.CENTER);
        dinnerBase.add(addScroll(dinnerMain), BorderLayout.CENTER);
        snackBase.add(addScroll(snackMain), BorderLayout.CENTER);

        main.add(addHeader("breakfast.png", 175, -60, 150, 120));
        main.add(addHeader("lunch.png", 175, 95, 150, 120));
        main.add(addHeader("dinner.png", 175, 250, 150, 120));
        main.add(addHeader("snack.png", 170, 390, 170, 140));
    }

    public JLabel addHeader(String file, int x, int y, int width, int height) {
        ImageIcon unscaled = new ImageIcon("src/main/ui/panels/data/" + file);
        Image image = unscaled.getImage().getScaledInstance(width, height, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);

        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setIcon(icon);
        return label;
    }

    public JPanel addPanel(JPanel panel, int height) {
        panel.setLayout(new BorderLayout());
        panel.setBackground(Colors.SIDEBAR);
        panel.setBounds(10, height, 480, 100);
        return panel;
    }

    public JScrollPane addScroll(JPanel panel) {
        JScrollPane pane = new JScrollPane(panel);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBorder(null);
        return pane;
    }

    public void setUpMainPanels(JPanel panel, ArrayList<Food> foods) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Colors.SIDEBAR);
        panel.setPreferredSize(new Dimension(500, calculateHeight(foods.size())));
    }

    public void initBase(ArrayList<ArrayList<Food>> separated) {
        setUpMainPanels(breakfastMain, separated.get(0));
        setUpMainPanels(lunchMain, separated.get(1));
        setUpMainPanels(dinnerMain, separated.get(2));
        setUpMainPanels(snackMain, separated.get(3));
    }

    // EFFECTS: sorts foods into a list of list
    //          the list will contain all the food in each meal type in order
    //          breakfast, lunch, dinner, snack
    public ArrayList<ArrayList<Food>> separateFoodTypes(Meals meals) {
        ArrayList<ArrayList<Food>> soFar = new ArrayList<>();
        ArrayList<Food> breakfast = new ArrayList<>();
        ArrayList<Food> lunch = new ArrayList<>();
        ArrayList<Food> dinner = new ArrayList<>();
        ArrayList<Food> snack = new ArrayList<>();
        for (Food f : meals.getFoods()) {
            if (f.getType().equals(MealType.BREAKFAST)) {
                breakfast.add(f);
            } else if (f.getType().equals(MealType.LUNCH)) {
                lunch.add(f);
            } else if (f.getType().equals(MealType.DINNER)) {
                dinner.add(f);
            } else {
                snack.add(f);
            }
        }
        soFar.add(breakfast);
        soFar.add(lunch);
        soFar.add(dinner);
        soFar.add(snack);
        return soFar;
    }

    public int calculateHeight(int size) {
        int height = (18 * (size + 1));
        if (height < 105) {
            return 105;
        } else {
            return height;
        }
    }

    public void placeLabels(ArrayList<ArrayList<Food>> separated) {
        printFoodLabels(separated.get(0), breakfastMain);
        printFoodLabels(separated.get(1), lunchMain);
        printFoodLabels(separated.get(2), dinnerMain);
        printFoodLabels(separated.get(3), snackMain);
    }

    public void printFoodLabels(ArrayList<Food> foods, JPanel panel) {
        panel.add(new JLabel(" "));
        for (Food food : foods) {
            String n = food.getName();
            int c = food.getCalories();
            double p = food.getProtein();
            JLabel label = new JLabel();
            label.setText("   - " + n + " which had " + c + " calories and " + p + " grams of protein.");
            label.setForeground(Colors.MAIN_COLOUR);
            label.setFont(new Font("Monospace", Font.PLAIN, 12));
            panel.add(label);
        }
    }


    public static void main(String[] args) {
        new DisplayFoods(new Meals());
    }


}
