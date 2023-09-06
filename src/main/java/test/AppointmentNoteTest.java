package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.Model.Appointment_Note;

public class AppointmentNoteTest {

    private Appointment_Note appointmentNote;

    @Before
    public void setUp() {
        int appointmentId = 123;
        Date noteDate = new Date(2000-10-01); // You can use an appropriate date here
        String noteText = "Sample note text";
        appointmentNote = new Appointment_Note(appointmentId, noteDate, noteText);
    }

    @Test
    public void testGetAppointmentId() {
        assertEquals(123, appointmentNote.getAppointment_ID());
    }

    @Test
    public void testGetNoteDate() {
        assertNotNull(appointmentNote.getNote_Date());
        // Add additional assertions or comparisons for the Date if needed
    }

    @Test
    public void testGetNoteText() {
        assertEquals("Sample note text", appointmentNote.getNote_Text());
    }

    @Test
    public void testConstructorWithId() {
        assertEquals(123, appointmentNote.getAppointment_ID());
        assertNotNull(appointmentNote.getNote_Date());
        assertEquals("Sample note text", appointmentNote.getNote_Text());
    }

    @Test
    public void testSetters() {
        appointmentNote.setAppointment_ID(456);
        assertEquals(456, appointmentNote.getAppointment_ID());

        Date newNoteDate = new Date(2000-8-26); // You can use an appropriate date here
        appointmentNote.setNote_Date(newNoteDate);
        assertEquals(newNoteDate, appointmentNote.getNote_Date());

        appointmentNote.setNote_Text("Updated note text");
        assertEquals("Updated note text", appointmentNote.getNote_Text());
    }

}
