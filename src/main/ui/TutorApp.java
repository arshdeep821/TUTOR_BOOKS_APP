package ui;

import model.Student;
import model.TutorOrganization;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


// Class level comment
/*
- Runs the tutor app through the Java console
- Allows user to type in inputs in order to book sessions at specific times
- methods and fields represents actions that the user inputed into the console
 */
public class TutorApp {

    private static final String JSON_STORE = "./data/tutororganization.json";
    boolean cont = true;
    Scanner scanner = new Scanner(System.in);
    TutorOrganization tutorOrganization = new TutorOrganization("tutorOrganization");
    Student student = new Student("student", 0);
    int option;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    // EFFECTS: runs the tutor app
    public TutorApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTutor();
    }

    // MODIFIES: this
    // EFFECTS: sets up the console for the UI, allowing user to enter commands/inputs in relation to booking sessions
    //          processes user input
    @SuppressWarnings("methodlength")
    private void runTutor() {
        /*
        System.out.println("would you like to load your work click 1 for yes, 2 for no");
        int nextint1 = Integer.parseInt(scanner.nextLine());
        if (nextint1 == 1) {
            loadTutorSession();
        }

         */

        option = 1;
        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        student.setStudentName(name);
        System.out.println("Hello " + name);

        while (option == 1) {

            display1();
            int time = Integer.parseInt(scanner.nextLine());
            if (!tutorOrganization.checkIfTaken(time)) {
                tutorOrganization.makeNewTutorSession(student, time);
                System.out.println("Your session is available, appointment is made for " + time);
            } else {
                System.out.println("Your requested session is taken please select another time");
                changeBooking();
            }
            System.out.println("Would you like to book another appointment?");
            System.out.println("type 1 to book again, else 2");
            option = Integer.parseInt(scanner.nextLine());
            if (option != 1) {
                System.out.println("Thank you for booking, see you at your session");
            }
        }

        System.out.println("Would you like to save your session? Click 1 to save click 2 to exit");
        int nextInt = Integer.parseInt(scanner.nextLine());
        if (nextInt == 1) {
            saveTutorSession();
        } else {
            System.out.println("Goodbye! Work will not be saved");
        }


    }

    // EFFECTS: Prints string onto the console for user to see when they can book their session
    private void display1() {
        System.out.println("Select a time from 0-19(24hr clock) for your tutoring session");
    }


    // MODIFIES: this
    // EFFECTS: It allows the student to request a new session that is not taken
    private void changeBooking() {
        while (cont) {
            display1();

            while (option == 1) {

                int time = scanner.nextInt();
                if (!tutorOrganization.checkIfTaken(time)) {
                    tutorOrganization.makeNewTutorSession(student, time);
                    System.out.println("Your session is available, appointment is made for " + time);
                } else {
                    System.out.println("Your requested session is taken please select another time");
                    changeBooking();
                }
                System.out.println("Would you like to book another appointment?");
                System.out.println("type 1 to book again, else 2");
                option = scanner.nextInt();
                if (option != 1) {
                    System.out.println("Thank you for booking, see you at your session");
                }
            }
        }
    }

    // EFFECTS: saves the tutorOrganization to file
    private void saveTutorSession() {
        try {
            jsonWriter.open();
            jsonWriter.write(tutorOrganization);
            jsonWriter.close();
            System.out.println("Saved tutorOrganization to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tutorsession from file
    private void loadTutorSession() {
        try {
            tutorOrganization = jsonReader.read();
            System.out.println("Loaded Tutorsession to from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
