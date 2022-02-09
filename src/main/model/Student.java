package model;

public class Student {
    // delete or rename this class!
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
