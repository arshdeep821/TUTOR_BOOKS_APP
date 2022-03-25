package ui;

import model.Student;
import model.TutorOrganization;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class TutorAppV2 {

    private static final String JSON_STORE = "./data/tutororganization.json";
    TutorOrganization tutorOrganization;
    Student student;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public TutorAppV2() {
        runTeller2();
    }

    private void runTeller2() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye. See you at your tutoring session");
    }

    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        tutorOrganization = new TutorOrganization();
        student = new Student(null, 0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tm -> make Booking");
        //System.out.println("\tc1 -> change Booking");
        //System.out.println("\tc2 -> cancel appointment");
        System.out.println("\ts -> save bookings");
        System.out.println("\tl -> load bookings");
        System.out.println("\te -> exit tutoring app");
    }

    private void processCommand(String command) {
        if (command.equals("m")) {
            enterInfo();
            makebooking();
        } else if (command.equals("c1")) {
            changeBooking();
        } else if (command.equals("c2")) {
            cancelBooking();
        } else if (command.equals("s")) {
            saveTutorSession();
        } else if (command.equals("l")) {
            loadTutorSession();
            displayBookings();
        } else if (command.equals("c")) {
            enterInfo();
        }
    }

    private void enterInfo() {
        System.out.println("Enter Name:");
        String name = input.next();

        student.setStudentName(name);
        System.out.println("Hello " + name);
    }

    private void makebooking() {

        System.out.println("Select a time from 0-19(24hr clock) for your tutoring session");
        //int time = Integer.parseInt(input.nextLine());
        int time = input.nextInt();

        if (!tutorOrganization.checkIfTaken(time)) {
            tutorOrganization.makeNewTutorSession(student, time);
            System.out.println("Your session is available, appointment is made for " + time);
        } else {
            System.out.println("Your requested session at " + time + " is taken please select another time");
            makebooking();
        }
    }

    private void changeBooking() {
        System.out.println("Please enter your name:");
        String name = input.next();

        System.out.println("Please enter your current appointment time that you would like to change:");
        int oldTime = input.nextInt();

        System.out.println("Please enter the time you would like to change to:");
        int newTime = input.nextInt();

        Student student1 = new Student(name, oldTime);

        if (tutorOrganization.getStudentList().contains(student1)) {
            tutorOrganization.getStudentList().remove(oldTime - 1);
            Student student2 = new Student(name, newTime);
            tutorOrganization.makeNewTutorSession(student2, newTime);
            System.out.println("Booking has been changed");
        }
    }

    private void cancelBooking() {
        System.out.println("enter your name:");
        String name = input.next();

        System.out.println("Select the time you would like to cancel");
        int time = input.nextInt();

        for (Student s : tutorOrganization.getStudentList()) {
            if (s.getBookedSession() == time) {
                tutorOrganization.getStudentList().remove(s);
                System.out.println("Appointment cancelled successfully");
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



    // EFFECTS: displays all saved Tutor booking times
    private void displayBookings() {
        System.out.println("\nThe current bookings are:");
        for (Student s : tutorOrganization.getStudentList()) {
            if (s != null) {
                System.out.println(s.getStudentName() + " " + s.getBookedSession());
            }
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
