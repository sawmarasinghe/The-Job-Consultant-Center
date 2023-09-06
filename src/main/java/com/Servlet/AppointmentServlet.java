package com.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.Model.Appointment;
import com.Model.Consultants;
import com.Model.JobSeeker;

import service.AppointmentService;


@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    AppointmentService appointmentService;

    public void init() {
        
    	appointmentService = new AppointmentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listAppointments(request, response);
                break;
            case "showAddForm":
                showAddForm(request, response);
                break;
            case "add":
                addAppointment(request, response);
                break;
            case "showUpdateForm":
                showUpdateForm(request, response);
                break;
            case "update":
                updateAppointment(request, response);
                break;
            case "delete":
                deleteAppointment(request, response);
                break;
            case "index":
                showindex(request, response);
                break;
            case "indexSave":
			try {
				showindexSave(request, response);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
                break;
            default:
                listAppointments(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
	
	private void showindexSave(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException, ServletException{
		int consultant_ID = Integer.parseInt(request.getParameter("Consultant_ID"));
        String firstName = request.getParameter("FirstName");
        String LastName = request.getParameter("LastName");
        String email = request.getParameter("email");
        int PhoneNumber =Integer.parseInt( request.getParameter("PhoneNumber"));
        String Desired_Country = request.getParameter("Desired_Country");
        String Desired_Job_Type = request.getParameter("Desired_Job_Type");
        String appointment_DateTimeStr = request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date appointment_DateTime = sdf.parse(appointment_DateTimeStr);
        
        JobSeeker jobSeeker = new JobSeeker(firstName, LastName, email, PhoneNumber, Desired_Country, Desired_Job_Type);
       
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<JobSeeker>> constraintViolations =validator.validate(jobSeeker);
		
		if(!constraintViolations.isEmpty()) {
			String error="";
			for(ConstraintViolation<JobSeeker> constraintViolation: constraintViolations) {
				error += "<li>"+ constraintViolation.getMessage()+"</li>";
			}
			
			request.setAttribute("errors", error);
			RequestDispatcher dispatcher =request.getRequestDispatcher("AppointmentServlet?action=index");
			dispatcher.forward(request, response);
		}
		else {
			try {
				appointmentService.addJobSeeker(jobSeeker);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        
        int jobId = appointmentService.getLastId();
        
        
        
        Appointment appointment = new Appointment(consultant_ID, jobId, appointment_DateTime, "Not Complete", "Online", Desired_Country, Desired_Job_Type);
        
       
		Set<ConstraintViolation<Appointment>> constraintViolations1 =validator.validate(appointment);
		
		if(!constraintViolations1.isEmpty()) {
			String error="";
			for(ConstraintViolation<Appointment> constraintViolation: constraintViolations1) {
				error += "<li>"+ constraintViolation.getMessage()+"</li>";
			}
			
			request.setAttribute("errors", error);
			RequestDispatcher dispatcher =request.getRequestDispatcher("AppointmentServlet?action=index");
			dispatcher.forward(request, response);
		}
		
		else {
			appointmentService.addAppointment(appointment);
			RequestDispatcher dispatcher =request.getRequestDispatcher("AppointmentServlet?action=index");
			dispatcher.forward(request, response);
		}
   
	}
	
	private void showindex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Consultants> consultantsList = appointmentService.selectAllConsultants();
		request.setAttribute("consultantsList", consultantsList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
    private void listAppointments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Appointment> appointmentsList = appointmentService.selectAllAppointments();
        request.setAttribute("appointmentsList", appointmentsList);
        request.getRequestDispatcher("Appointment/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Consultants> consultantsList = appointmentService.selectAllConsultants();
    	List<JobSeeker> jobSeekersList = appointmentService.selectAllJobSeekers();
    	request.setAttribute("consultantsList", consultantsList);
    	request.setAttribute("jobSeekersList", jobSeekersList);
        request.getRequestDispatcher("Appointment/create.jsp").forward(request, response);
    }

    private void addAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	
            int consultant_ID = Integer.parseInt(request.getParameter("Consultant_ID"));
            int jobSeeker_ID = Integer.parseInt(request.getParameter("jobSeeker_ID"));
            String appointment_DateTimeStr = request.getParameter("date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date appointment_DateTime = sdf.parse(appointment_DateTimeStr);
            String status = request.getParameter("Status");
            String type = request.getParameter("type");
            String Country = request.getParameter("countries");
            String job = request.getParameter("jobs");
            
            Appointment newAppointment = new Appointment(consultant_ID, jobSeeker_ID, appointment_DateTime, status, type, Country, job);
           
            
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    		Validator validator = validatorFactory.getValidator();
    		Set<ConstraintViolation<Appointment>> constraintViolations =validator.validate(newAppointment);
    		
    		if(!constraintViolations.isEmpty()) {
    			String error="";
    			for(ConstraintViolation<Appointment> constraintViolation: constraintViolations) {
    				error += "<li>"+ constraintViolation.getMessage()+"</li>";
    			}
    			
    			request.setAttribute("errors", error);
    			RequestDispatcher dispatcher =request.getRequestDispatcher("Appointment/create.jsp");
    			dispatcher.forward(request, response);
    		}
    		else {
    			appointmentService.addAppointment(newAppointment);
    			response.sendRedirect("AppointmentServlet");
    		}
    		
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("id"));
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        List<Consultants> consultantsList = appointmentService.selectAllConsultants();
    	List<JobSeeker> jobSeekersList = appointmentService.selectAllJobSeekers();
    	request.setAttribute("consultantsList", consultantsList);
    	request.setAttribute("jobSeekersList", jobSeekersList);
        request.setAttribute("appointment", appointment);
        request.getRequestDispatcher("Appointment/create.jsp").forward(request, response);
    }

    private void updateAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int appointment_ID = Integer.parseInt(request.getParameter("id"));
            int consultant_ID = Integer.parseInt(request.getParameter("Consultant_ID"));
            int jobSeeker_ID = Integer.parseInt(request.getParameter("jobSeeker_ID"));
            String appointment_DateTimeStr = request.getParameter("date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date appointment_DateTime = sdf.parse(appointment_DateTimeStr);
            String status = request.getParameter("Status");
            String type = request.getParameter("type");
            String Country = request.getParameter("countries");
            String job = request.getParameter("jobs");

            Appointment appointment = new Appointment(appointment_ID, consultant_ID, jobSeeker_ID, appointment_DateTime, status, type, Country, job);
            
            
            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    		Validator validator = validatorFactory.getValidator();
    		Set<ConstraintViolation<Appointment>> constraintViolations =validator.validate(appointment);
    		
    		if(!constraintViolations.isEmpty()) {
    			String error="";
    			for(ConstraintViolation<Appointment> constraintViolation: constraintViolations) {
    				error += "<li>"+ constraintViolation.getMessage()+"</li>";
    			}
    			
    			request.setAttribute("errors", error);
    			RequestDispatcher dispatcher =request.getRequestDispatcher("Appointment/create.jsp");
    			dispatcher.forward(request, response);
    		}
    		else {
    			appointmentService.updateAppointment(appointment);
    			 response.sendRedirect("AppointmentServlet");
    		}
           
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("id"));
        appointmentService.deleteAppointment(appointmentId);
        response.sendRedirect("AppointmentServlet");
    }
}
