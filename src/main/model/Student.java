package model;

// Class level comment:

import org.json.JSONObject;
import persistence.Writable;


/*
- The student class represents an instance of a student with a name and their most recent booking session
- The name of the student is represented via a string and their most recent booked session
  is represented by an int, which would be the 24 hour clock representation of their most
  recent booked session
 */
public class Student implements Writable {
    private String studentName;
    private int bookedSession;

    //EFFECTS: Construct Student with student name and a requested booked session
    public Student(String studentName, int bookedSession) {
        this.studentName = studentName;
        this.bookedSession = bookedSession;
    }
/*
    // REQUIRES: booked session between [0, 19]
    // EFFECTS:
    public Student(String name, int bookedSession) {
        this.studentName = name;
        this.bookedSession = bookedSession;
    }

 */

    //getters
    public String getStudentName() {
        return studentName;
    }

    public int getBookedSession() {
        return bookedSession;
    }

    // setters
    public void setBookedSession(int time) {
        this.bookedSession = time;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", studentName);
        json.put("int", bookedSession);
        return json;
    }


}