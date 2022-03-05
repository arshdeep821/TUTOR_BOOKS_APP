package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    TutorOrganization tutorOrganization;

    @BeforeEach
    public void runBefore() {
        tutorOrganization = new TutorOrganization();

    }
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TutorOrganization tutorOrganization = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTutorOrganization.json");
        try {
            TutorOrganization tutorOrganization = reader.read();
            //assertEquals("My tutor organization", tutorOrganization.getTutorName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralTutorOrganization() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTutorOrganization.json");
        try {
            TutorOrganization tutorOrganization = reader.read();
            //assertEquals("My tutor organization",tutorOrganization.getTutorName());
            List<Student> students = tutorOrganization.getStudentList();
            checkStudent("Josh", 5, students.get(5));
            checkStudent("Grace", 8, students.get(8));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    public void testConstructor() {
        JsonReader j1 = new JsonReader("Hello");
        assertEquals("Hello", j1.getSource());
    }

/*
    @Test
    public void testreadFile() {
    }

 */


}





