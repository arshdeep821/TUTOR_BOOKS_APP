package persistence;

import model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkStudent(String name, int bookedSession, Student student) {
        assertEquals(name, student.getStudentName());
        assertEquals(bookedSession, student.getBookedSession());
    }

}
