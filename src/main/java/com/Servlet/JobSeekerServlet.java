package com.Servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import com.Model.JobSeeker;

import service.JobSeekerService;

@WebServlet("/JobSeekerServlet")
public class JobSeekerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private JobSeekerService jobSeekerService;

    public void init() {
    	jobSeekerService = new JobSeekerService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listJobSeekers(request, response);
                break;
            case "editFrm":
                showEditForm(request, response);
                break;
            case "update":
                updateJobSeeker(request, response);
                break;
            case "delete":
                deleteJobSeeker(request, response);
                break;
            case "createFrm":
                createFrm(request, response);
                break;
            case "create":
                createJobSeeker(request, response);
                break;
            default:
                listJobSeekers(request, response);
        }
    }

    private void createJobSeeker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("first_Name");
        String lastName = request.getParameter("last_Name");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phone_Number"));
        String desiredCountry = request.getParameter("desired_Country");
        String desiredJobType = request.getParameter("desired_Job_Type");
        
        
        JobSeeker newJobSeeker = new JobSeeker(firstName, lastName, email, phoneNumber, desiredCountry, desiredJobType);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<JobSeeker>> constraintViolations = validator.validate(newJobSeeker);

        if (!constraintViolations.isEmpty()) {
            String error = "";
            for (ConstraintViolation<JobSeeker> constraintViolation : constraintViolations) {
                error += "<li>" + constraintViolation.getMessage() + "</li>";
            }

            request.setAttribute("errors", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("JobSeeker/create.jsp");
            dispatcher.forward(request, response);
        } else {
            try {
            	jobSeekerService.addJobSeeker(newJobSeeker);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("JobSeekerServlet?action=list");
        }
    }

    private void createFrm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("JobSeeker/create.jsp");
    }

    private void listJobSeekers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<JobSeeker> jobSeekers = jobSeekerService.getAllJobSeekers();
        request.setAttribute("jobSeekers", jobSeekers);
        request.getRequestDispatcher("JobSeeker/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobSeekerId = Integer.parseInt(request.getParameter("id"));
        JobSeeker jobSeeker = jobSeekerService.getJobSeekerById(jobSeekerId);
        request.setAttribute("jobSeeker", jobSeeker);
        request.getRequestDispatcher("JobSeeker/create.jsp").forward(request, response);
    }

    private void updateJobSeeker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobSeekerId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_Name");
        String lastName = request.getParameter("last_Name");
        String email = request.getParameter("email");
        int phoneNumber = Integer.parseInt(request.getParameter("phone_Number"));
        String desiredCountry = request.getParameter("desired_Country");
        String desiredJobType = request.getParameter("desired_Job_Type");
        
        

        JobSeeker updatedJobSeeker = new JobSeeker(jobSeekerId, firstName, lastName, email, phoneNumber, desiredCountry, desiredJobType);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<JobSeeker>> constraintViolations = validator.validate(updatedJobSeeker);

        if (!constraintViolations.isEmpty()) {
            String error = "";
            for (ConstraintViolation<JobSeeker> constraintViolation : constraintViolations) {
                error += "<li>" + constraintViolation.getMessage() + "</li>";
            }

            request.setAttribute("errors", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("JobSeeker/edit.jsp");
            dispatcher.forward(request, response);
        } else {
            try {
            	jobSeekerService.updateJobSeeker(updatedJobSeeker);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("JobSeekerServlet?action=list");
        }
    }

    private void deleteJobSeeker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobSeekerId = Integer.parseInt(request.getParameter("id"));

        try {
        	jobSeekerService.deleteJobSeeker(jobSeekerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("JobSeekerServlet?action=list");
    }
}
