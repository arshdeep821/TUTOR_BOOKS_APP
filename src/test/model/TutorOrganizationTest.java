package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TutorOrganizationTest {

    Student billy;
    Student sarah;
    Student dil;
    Student kidda;
    Student josh;
    TutorOrganization outsideOfTheBoxTutoring;

    @BeforeEach
    void runBefore() {
        billy = new Student();
        billy.setStudentName("Billy");
        sarah = new Student();
        sarah.setStudentName("Sarah");
        dil = new Student();
        dil.setStudentName("Dil");
        outsideOfTheBoxTutoring = new TutorOrganization();
        kidda = new Student();
        kidda.setStudentName("Kidda");
        josh = new Student();
        josh.setStudentName("Josh");

    }

    @Test
    void testBookAtAvailableTime() {
        // make the booking
        assertTrue(outsideOfTheBoxTutoring.makeNewTutorSession(billy, 3));

        //check if booking is at correct time
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Billy", 3));
        assertEquals("Billy", billy.getStudentName());
     //   assertTrue(outsideOfTheBoxTutoring.affirmAppointment(billy, 3));

        //check if student name is at correct time
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Billy", 3));
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(3));
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Billy", 3));
        assertFalse(outsideOfTheBoxTutoring.approveStudentName("Billy", 5));
        assertEquals(3,billy.getBookedSession());

        // change appointment time
        outsideOfTheBoxTutoring.changeAppointment(billy, 3,4);
        //check if it is empty at 3
        assertFalse(outsideOfTheBoxTutoring.checkIfTaken(3));
        assertFalse(outsideOfTheBoxTutoring.approveStudentName("Billy", 3));
        // check if taken at 4
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(4));
        assertEquals(4, billy.getBookedSession());
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Billy", 4));

   //    assertTrue(outsideOfTheBoxTutoring.affirmAppointment(billy, 4));
       //assertFalse(outsideOfTheBoxTutoring.affirmAppointment(billy, 3));




        // check if new time is correct
     //   assertTrue(outsideOfTheBoxTutoring.affirmAppointment(billy, 4));


        // add new person to old booking time
        outsideOfTheBoxTutoring.makeNewTutorSession(sarah, 3);
        assertEquals("Sarah", sarah.getStudentName());
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(3));
        assertEquals(3, sarah.getBookedSession());
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Sarah", 3));

        //change booking
        outsideOfTheBoxTutoring.changeAppointment(sarah, 3, 5);
        //check if old is empty
        assertFalse(outsideOfTheBoxTutoring.checkIfTaken(3));
        assertFalse(outsideOfTheBoxTutoring.approveStudentName("Sarah", 3));
        //check if new is taken
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(5));
        assertEquals(5, sarah.getBookedSession());
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Sarah", 5));
        // cancel appointment
        outsideOfTheBoxTutoring.cancelAppointment(5);
        assertFalse(outsideOfTheBoxTutoring.checkIfTaken(5));
        assertFalse(outsideOfTheBoxTutoring.approveStudentName("Sarah", 5));

    }

    @Test
    public void testEdgeCases() {
        // make new person


        //book at edge cases
        assertTrue(outsideOfTheBoxTutoring.makeNewTutorSession(kidda, 0));
        assertEquals("Kidda", kidda.getStudentName());
        assertTrue(outsideOfTheBoxTutoring.makeNewTutorSession(dil, 19));
        assertEquals("Dil", dil.getStudentName());

        //check if taken
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(0));
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(19));
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Dil", 19));
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Kidda", 0));
        assertEquals(19, dil.getBookedSession());
        assertEquals(0, kidda.getBookedSession());

        //test you can't make booking higher than 19
        assertFalse(outsideOfTheBoxTutoring.makeNewTutorSession(josh, 20));
    }

}