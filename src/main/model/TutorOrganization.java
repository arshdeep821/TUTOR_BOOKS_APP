package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Class level comment:

/*
- This class represents a list of Students, which translates to a list of bookings of which student has booked which
session
- It sets up a list of 20 null slots, and it will set the slot number to the student name who requests that time
  unless it is already booked
 */
public class TutorOrganization implements Writable {
    private List<Student> bookingTimes;
    private String name;


    // EFFECTS: Construct empty ArrayList with 20 null slots
    public TutorOrganization(String name) {
        bookingTimes = new ArrayList<>();
        for (int n = 0; n <= 19; n++) {
            bookingTimes.add(n, null);
        }

        this.name = name;
    }

    // REQUIRES: An appointmentTime between [0,19]
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



    // EFFECTS: return true if the student is booked at the correct appointmentTime of their choice
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
    //           and has atleast 1 session booked
    // MODIFIES: this
    // EFFECTS: cancels appointment of the student
    //          true means appointment got cancelled, else false
    public void cancelAppointment(int currentAppointmentTime) {
        bookingTimes.set(currentAppointmentTime, null);
    }

    // REQUIRES: A Student that already has a booked an appointment at a
    //           time where another different Student has already booked one
    // MODIFIES: this and Student
    // EFFECTS: changes the appointment time of student
    public void changeAppointment(Student s, int currentAppointmentTime,int nextAppointmentTime) {
        bookingTimes.set(currentAppointmentTime, null);
        bookingTimes.set(nextAppointmentTime, s);
        s.setBookedSession(nextAppointmentTime);
    }

    // getters
    public int getBookingTimes() {
        return bookingTimes.size();
    }

    public List<Student> getStudentList() {
        return bookingTimes;
    }

    public String getTutorName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("students", studentsToJson());
        return json;
    }

    // EFFECTS: returns things in this tutorOrganization as a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student student : bookingTimes) {
            jsonArray.put(student.toJson());
        }
        return jsonArray;
    }
}
