package ui;

import model.Student;
import model.TutorOrganization;

import java.util.Scanner;


// Class level comment
/*
- Runs the tutor app through the Java console
- Allows user to type in inputs in order to book sessions at specific times
- methods and fields represents actions that the user inputed into the console
 */
public class TutorApp {

    boolean cont = true;
    Scanner scanner = new Scanner(System.in);
    TutorOrganization tutorOrganization = new TutorOrganization();
    Student student = new Student();
    int option;


    // EFFECTS: runs the tutor app
    public TutorApp() {
        runTutor();
    }

    // MODIFIES: this
    // EFFECTS: sets up the console for the UI, allowing user to enter commands/inputs in relation to booking sessions
    //          processes user input
    private void runTutor() {

        option = 1;
        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        student.setStudentName(name);
        System.out.println("Hello " + name);

        while (option == 1) {

            display1();
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
}
