package service;

import java.sql.SQLException;
import java.util.List;

import com.DAO.JobSeekerDAO;
import com.Model.JobSeeker;

public class JobSeekerService {

    private JobSeekerDAO jobSeekerDAO;

    public JobSeekerService() {
        jobSeekerDAO = new JobSeekerDAO();
    }

    public void addJobSeeker(JobSeeker jobSeeker) throws SQLException {
        jobSeekerDAO.addJobSeeker(jobSeeker);
    }

    public JobSeeker getJobSeekerById(int jobSeekerId) {
        return jobSeekerDAO.getJobSeekerById(jobSeekerId);
    }

    public List<JobSeeker> getAllJobSeekers() {
        return jobSeekerDAO.selectAllJobSeekers();
    }

    public boolean deleteJobSeeker(int jobSeekerId) throws SQLException {
        return jobSeekerDAO.deleteJobSeeker(jobSeekerId);
    }

    public boolean updateJobSeeker(JobSeeker jobSeeker) throws SQLException {
        return jobSeekerDAO.updateJobSeeker(jobSeeker);
    }

	public void setJobSeekerDAO(JobSeekerDAO jobSeekerDAO2) {
		jobSeekerDAO = jobSeekerDAO2;
		
	}
}
