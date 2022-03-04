package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    public void testWriterInvalidFile() {
        try {
            TutorOrganization tutorOrganization = new TutorOrganization("My tutor organization");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyTutorOrganization() {
        try {
            TutorOrganization tutorOrganization = new TutorOrganization("My tutor organization");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTutorOrganization.json");
            writer.open();
            writer.write(tutorOrganization);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTutorOrganization.json");
            tutorOrganization = reader.read();
            assertEquals("My tutor organization", tutorOrganization.getTutorName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralWorkroom() {
        try {
            TutorOrganization tutorOrganization = new TutorOrganization("My tutor organization");
            tutorOrganization.makeNewTutorSession(new Student("Cole", 8), 8);
            tutorOrganization.makeNewTutorSession(new Student("Billy", 3), 3);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTutorOrganization.json");
            writer.open();
            writer.write(tutorOrganization);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTutorOrganization.json");
            tutorOrganization = reader.read();
            assertEquals("My tutor organization", tutorOrganization.getTutorName());
            List<Student> students = tutorOrganization.getStudentList();
            checkStudent("Cole", 8, students.get(8));
            checkStudent("Billy", 3, students.get(3));
        } catch (IOException e) {
            fail("Exception should not have been thrown");

        }
    }

    @Test
    public void testConstructor() {
        JsonWriter j1 = new JsonWriter("coolio");
        assertEquals("coolio", j1.getDestination());
    }




}
