package ui.console;

import ui.console.FitnessApp;

import java.io.FileNotFoundException;

public class Main {

    // main method
    public static void main(String[] args) {
        try {
            new FitnessApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

}

