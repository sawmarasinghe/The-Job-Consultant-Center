package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.DAO.JobSeekerDAO;
import com.Model.JobSeeker;
import service.JobSeekerService;

public class JobSeekerServiceTest {

    @Mock
    private JobSeekerDAO jobSeekerDAO;

    private JobSeekerService jobSeekerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jobSeekerService = new JobSeekerService();
        jobSeekerService.setJobSeekerDAO(jobSeekerDAO);
    }

    @Test
    public void testAddJobSeeker() throws SQLException {
        JobSeeker jobSeeker = new JobSeeker(1,"Thilini", "Marasinghe", "thilini@gamial.com", 719632587, "India", "Manager");

        jobSeekerService.addJobSeeker(jobSeeker);

        verify(jobSeekerDAO).addJobSeeker(jobSeeker);
    }

    @Test
    public void testGetJobSeekerById() {
        int jobSeekerId = 1;
        JobSeeker expectedJobSeeker = new JobSeeker(1,"Thilini", "Marasinghe", "thilini@gamial.com", 719632587, "India", "Manager");
        when(jobSeekerDAO.getJobSeekerById(jobSeekerId)).thenReturn(expectedJobSeeker);

        JobSeeker actualJobSeeker = jobSeekerService.getJobSeekerById(jobSeekerId);

        assertEquals(expectedJobSeeker, actualJobSeeker);
    }

    @Test
    public void testGetAllJobSeekers() {
        List<JobSeeker> expectedJobSeekers = new ArrayList<>();
        when(jobSeekerDAO.selectAllJobSeekers()).thenReturn(expectedJobSeekers);

        List<JobSeeker> actualJobSeekers = jobSeekerService.getAllJobSeekers();

        assertEquals(expectedJobSeekers, actualJobSeekers);
    }

    @Test
    public void testDeleteJobSeeker() throws SQLException {
        int jobSeekerId = 1;

        boolean result = jobSeekerService.deleteJobSeeker(jobSeekerId);

        assertTrue(result);
        verify(jobSeekerDAO).deleteJobSeeker(jobSeekerId);
    }

    @Test
    public void testUpdateJobSeeker() throws SQLException {
        JobSeeker jobSeeker = new JobSeeker(1,"Thilini", "Marasinghe", "thilini@gamial.com", 719632587, "India", "Manager");

        boolean result = jobSeekerService.updateJobSeeker(jobSeeker);

        assertTrue(result);
        verify(jobSeekerDAO).updateJobSeeker(jobSeeker);
    }

}
