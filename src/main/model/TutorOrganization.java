package model;

import java.util.ArrayList;
import java.util.List;

public class TutorOrganization {
    private List<Student> bookingTimes;


    // EFFECTS: Construct empty ArrayList with 20 null slots
    public TutorOrganization() {
        bookingTimes = new ArrayList<>();
        for (int n = 0; n <= 19; n++) {
            bookingTimes.add(n, null);
        }
    }

    // MODIFIES: this and Student
    // EFFECTS: gets student booked into requested appointmentTime if it is free
    public boolean makeNewTutorSession(Student s, int appointmentTime) {
        if (appointmentTime >= bookingTimes.size()) {
            return false;
        }
        bookingTimes.set(appointmentTime, s);
        s.setBookedSession(appointmentTime);
        return true;
    }

    // EFFECTS: return true if student is found at the correct appointmentTime
    public boolean affirmAppointment(Student s, int appointmentTime) {
        Student studentBooking;
        studentBooking = bookingTimes.get(appointmentTime);
        if (studentBooking.getStudentName() == s.getStudentName()) {
            return true;
        } else {

            if (studentBooking == null) {
                return false;
            }
        }
        return false;
    }


    // EFFECTS: return true if the student is booked at the correct appointmentTime
    public boolean approveStudentName(String name, int appointmentTime) {
        if (bookingTimes.get(appointmentTime) != null) {
            Student studentBooking;
            studentBooking = bookingTimes.get(appointmentTime);
            String studentBookingName;
            studentBookingName = studentBooking.getStudentName();
            boolean isStudentBooked = (studentBookingName == name);
            return isStudentBooked;
        }

        return false;
    }

    // EFFECTS: check if appointment is already take
    //          return true if taken
    //          else false
    public boolean checkIfTaken(int appointmentTime) {
        if (bookingTimes.get(appointmentTime) == null) {
            return false;
        }
        return true;
    }

    // REQUIRES: current appointment the student has booked
    // MODIFIES: this
    // EFFECTS: cancels appointment of the student
    //          true means appointment got cancelled, else false
    public void cancelAppointment(int currentAppointmentTime) {
        bookingTimes.set(currentAppointmentTime, null);



    }

    // MODIFIES: this
    // EFFECTS: changes the appointment time of student
    public void changeAppointment(Student s, int currentAppointmentTime,int nextAppointmentTime) {
        int appointmentSession;
        appointmentSession = s.getBookedSession();
        bookingTimes.set(currentAppointmentTime, null);
        bookingTimes.set(nextAppointmentTime, s);
        s.setBookedSession(nextAppointmentTime);
    }

}
