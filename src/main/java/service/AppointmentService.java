package service;

import java.sql.SQLException;
import java.util.List;

import com.DAO.AppointmentDAO;
import com.DAO.ConsultantsDAO;
import com.DAO.JobSeekerDAO;
import com.Model.Appointment;
import com.Model.Consultants;
import com.Model.JobSeeker;

public class AppointmentService {
	
	private AppointmentDAO appointmentDAO;
    private ConsultantsDAO consultantsDAO;
    private JobSeekerDAO jobSeekerDAO;

	public AppointmentService() {
		appointmentDAO = new  AppointmentDAO();
		consultantsDAO = new ConsultantsDAO();
		jobSeekerDAO = new JobSeekerDAO();
	}

	public AppointmentService(AppointmentDAO appointmentDAOMock, ConsultantsDAO consultantsDAOMock,
			JobSeekerDAO jobSeekerDAOMock) {
		appointmentDAO = appointmentDAOMock;
		consultantsDAO = consultantsDAOMock;
		jobSeekerDAO = jobSeekerDAOMock;
	}

	public void addJobSeeker(JobSeeker jobSeeker) throws SQLException {
    	jobSeekerDAO.addJobSeeker(jobSeeker);
	}
    
    public  int  getLastId() {
    	int id =jobSeekerDAO.getLastId();
    	return id;
	}
    
    public void addAppointment(Appointment appointment) {
    	appointmentDAO.addAppointment(appointment);
	}
    
    public List<Consultants> selectAllConsultants() {
    	List <Consultants> list = consultantsDAO.selectAllConsultants();
    	return list;
	}
    
    public List<Appointment> selectAllAppointments() {
    	List<Appointment> list = appointmentDAO.selectAllAppointments();
    	return list;
	}
    
    public List<JobSeeker> selectAllJobSeekers() {
    	List<JobSeeker> list = jobSeekerDAO.selectAllJobSeekers();
    	return list;
	}
    
    public Appointment getAppointmentById(int id) {
    	 
    	Appointment appointment = appointmentDAO.getAppointmentById(id);
    	return appointment;
	}
    
    public void updateAppointment(Appointment appointment) {
    	
    	appointmentDAO.updateAppointment(appointment);
		
	}
    
    public void deleteAppointment(int id) {
   	 
    	appointmentDAO.deleteAppointment(id);
	}


}