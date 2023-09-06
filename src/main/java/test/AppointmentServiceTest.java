package test;

import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.DAO.AppointmentDAO;
import com.DAO.ConsultantsDAO;
import com.DAO.JobSeekerDAO;
import com.Model.Appointment;
import com.Model.Consultants;
import com.Model.JobSeeker;
import service.AppointmentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentServiceTest {
    private AppointmentDAO appointmentDAOMock;
    private ConsultantsDAO consultantsDAOMock;
    private JobSeekerDAO jobSeekerDAOMock;
    private AppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        appointmentDAOMock = mock(AppointmentDAO.class);
        consultantsDAOMock = mock(ConsultantsDAO.class);
        jobSeekerDAOMock = mock(JobSeekerDAO.class);
        appointmentService = new AppointmentService(appointmentDAOMock, consultantsDAOMock, jobSeekerDAOMock);
    }

    @Test
    public void testAddJobSeeker() throws SQLException {
        JobSeeker jobSeeker = new JobSeeker("John", "Doe", "john@example.com", 123456789, "USA", "Developer");
        doNothing().when(jobSeekerDAOMock).addJobSeeker(jobSeeker);

        appointmentService.addJobSeeker(jobSeeker);

        verify(jobSeekerDAOMock).addJobSeeker(jobSeeker);
    }

    @Test
    public void testGetLastId() {
        when(jobSeekerDAOMock.getLastId()).thenReturn(123);

        int lastId = appointmentService.getLastId();

        assertEquals(123, lastId);
    }

    @Test
    public void testAddAppointment() {
        Appointment appointment = new Appointment(0, 0, null, null, null, null, null);
        doNothing().when(appointmentDAOMock).addAppointment(appointment);

        appointmentService.addAppointment(appointment);

        verify(appointmentDAOMock).addAppointment(appointment);
    }

    @Test
    public void testSelectAllConsultants() {
        List<Consultants> consultantsList = new ArrayList<>();
        when(consultantsDAOMock.selectAllConsultants()).thenReturn(consultantsList);

        List<Consultants> result = appointmentService.selectAllConsultants();

        assertEquals(consultantsList, result);
    }
    
    @Test
    public void testSelectAllAppointments() {
        List<Appointment> appointmentsList = new ArrayList<>();
        when(appointmentDAOMock.selectAllAppointments()).thenReturn(appointmentsList);

        List<Appointment> result = appointmentService.selectAllAppointments();

        assertEquals(appointmentsList, result);
    }

    @Test
    public void testSelectAllJobSeekers() {
        List<JobSeeker> jobSeekersList = new ArrayList<>();
        when(jobSeekerDAOMock.selectAllJobSeekers()).thenReturn(jobSeekersList);

        List<JobSeeker> result = appointmentService.selectAllJobSeekers();

        assertEquals(jobSeekersList, result);
    }

    @Test
    public void testGetAppointmentById() throws ParseException {
    	Date date = Date.valueOf("2023-08-26") ;
        Appointment appointment = new Appointment(1, 1, "Thilini", 1, "Marasinghe",date, "NotComplete", "Online", "Sri Lanka", "Developer");
        when(appointmentDAOMock.getAppointmentById(1)).thenReturn(appointment);

        assertEquals(appointmentDAOMock.getAppointmentById(1).getConsultant_name(), "Thilini");
    }

}
