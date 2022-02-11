package model;

// Class level comment:

/*
- The student class represents an instance of a student with a name and their most recent booking session
- The name of the student is represented via a string and their most recent booked session
  is represented by an int, which would be the 24 hour clock representation of their most
  recent booked session
 */
public class Student {
    private String studentName;
    private int bookedSession;

    //EFFECTS: Construct Student with student name
    //         Set bookedsession to zero
    public Student() {
        this.studentName = "";
        bookedSession = 0;
    }

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
}