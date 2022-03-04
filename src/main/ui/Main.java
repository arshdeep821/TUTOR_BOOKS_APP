package ui;


import java.io.FileNotFoundException;

// Class level comment
/*
Runs the tutor app, allowing user to make inputs and the app to run in general
 */
public class Main {

    // EFFECTS: Instantiates a Tutorapp, running the application in the console
    public static void main(String[] args) {
        try {
            new TutorApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

}