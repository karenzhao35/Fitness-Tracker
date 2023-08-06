package ui.panels;

import ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Panel implements ActionListener, Panels {

    public void generateButton(JButton button, int x, int y, int width) {
        button.setBounds(x, y, width, 30);
        button.setFont(new Font("Monospace", Font.PLAIN, 12));
        button.addActionListener(this);
        button.setFocusable(false);
        button.setForeground(Colors.SIDEBAR);
    }




}