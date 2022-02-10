package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TutorOrganizationTest {

    Student billy;
    Student sarah;
    Student dil;
    TutorOrganization outsideOfTheBoxTutoring;

    @BeforeEach
    void runBefore() {
        billy = new Student();
        billy.setStudentName("Billy");
        sarah = new Student();
        dil = new Student();
        outsideOfTheBoxTutoring = new TutorOrganization();
    }

    @Test
    void testBookAtAvailableTime() {
        // make the booking
        assertTrue(outsideOfTheBoxTutoring.makeNewTutorSession(billy, 3));

        //check if booking is at correct time
        assertTrue(outsideOfTheBoxTutoring.affirmAppointment(billy, 3));

        //check if student name is at correct time
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Billy", 3));
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(3));
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Billy", 3));
        assertFalse(outsideOfTheBoxTutoring.approveStudentName("Billy", 5));
        assertEquals(3,billy.getBookedSession());

        // change appointment time
        outsideOfTheBoxTutoring.changeAppointment(billy, 3,4);
        assertFalse(outsideOfTheBoxTutoring.checkIfTaken(3));
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(4));
       assertTrue(outsideOfTheBoxTutoring.affirmAppointment(billy, 4));
       assertFalse(outsideOfTheBoxTutoring.affirmAppointment(billy, 3));
       assertEquals(4, billy.getBookedSession());

        // check if new time is correct
        assertTrue(outsideOfTheBoxTutoring.affirmAppointment(billy, 4));
        assertTrue(outsideOfTheBoxTutoring.approveStudentName("Billy", 4));

        // add new person to old booking time
        outsideOfTheBoxTutoring.makeNewTutorSession(sarah, 3);
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(3));
        outsideOfTheBoxTutoring.changeAppointment(sarah, 3, 5);
        assertFalse(outsideOfTheBoxTutoring.checkIfTaken(3));
        assertTrue(outsideOfTheBoxTutoring.checkIfTaken(5));
        outsideOfTheBoxTutoring.cancelAppointment(5);
        assertFalse(outsideOfTheBoxTutoring.checkIfTaken(5));

        // make new person
        Student kidda = new Student();
        kidda.setStudentName("Kidda");
        assertEquals("Kidda", kidda.getStudentName());
        assertFalse(outsideOfTheBoxTutoring.makeNewTutorSession(kidda, 30));

    }

}