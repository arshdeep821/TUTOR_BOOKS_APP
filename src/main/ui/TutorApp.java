package ui;

import model.Student;
import model.TutorOrganization;

import java.util.Scanner;


public class TutorApp {

    boolean cont = true;
    Scanner scanner = new Scanner(System.in);
    TutorOrganization tutorOrganization = new TutorOrganization();
    Student student = new Student();
    int option;


    public TutorApp() {
        runTutor();
    }

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

    private void display1() {
        System.out.println("Select a time from 0-19(24hr clock) for your tutoring session");
    }


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
