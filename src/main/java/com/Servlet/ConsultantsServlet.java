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

import com.Model.Consultants;
import service.ConsultantsService;


@WebServlet("/ConsultantsServlet")
public class ConsultantsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConsultantsService consultantsService;

    public void init() {
        consultantsService = new ConsultantsService();
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
                listConsultants(request, response);
                break;
            case "editFrm":
                showEditForm(request, response);
                break;
            case "update":
                updateConsultant(request, response);
                break;
            case "delete":
                deleteConsultant(request, response);
                break;
            case "createFrm":
            	createFrm(request, response);
                break;
            case "create":
            	create(request, response);
                break;
            default:
                listConsultants(request, response);
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	
        String first_Name = request.getParameter("first_Name");
        String last_Name = request.getParameter("last_Name");
        String specialization = request.getParameter("specialization");
        String email = request.getParameter("email");
        int phone_Number = Integer.parseInt(request.getParameter("phone_Number"));
        String countries = request.getParameter("countries");
        String jobs = request.getParameter("jobs");
        
        Consultants newConsultants =new Consultants(first_Name, last_Name, specialization, email, phone_Number, countries, jobs);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Consultants>> constraintViolations =validator.validate(newConsultants);
		
		if(!constraintViolations.isEmpty()) {
			String error="";
			for(ConstraintViolation<Consultants> constraintViolation: constraintViolations) {
				error += "<li>"+ constraintViolation.getMessage()+"</li>";
			}
			
			request.setAttribute("errors", error);
			RequestDispatcher dispatcher =request.getRequestDispatcher("Consultants/create.jsp");
			dispatcher.forward(request, response);
		}
		else {
			try {
				consultantsService.addConsultant(newConsultants);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath() + "/ConsultantsServlet?action=list");
		}
	}

	private void createFrm(HttpServletRequest request, HttpServletResponse response) throws IOException  {
    	response.sendRedirect("Consultants/create.jsp");
	}

	private void listConsultants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Consultants> consultantsList = consultantsService.selectAllConsultants();
        request.setAttribute("consultantsList", consultantsList);
        request.getRequestDispatcher("Consultants/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int consultantId = Integer.parseInt(request.getParameter("id"));
        Consultants consultant = consultantsService.getConsultantById(consultantId);
        request.setAttribute("consultant", consultant);
        request.getRequestDispatcher("Consultants/create.jsp").forward(request, response);
    }

    private void updateConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int consultantId = Integer.parseInt(request.getParameter("id"));
        String first_Name = request.getParameter("first_Name");
        String last_Name = request.getParameter("last_Name");
        String specialization = request.getParameter("specialization");
        String email = request.getParameter("email");
        int phone_Number = Integer.parseInt(request.getParameter("phone_Number"));
        String countries = request.getParameter("countries");
        String jobs = request.getParameter("jobs");

        Consultants consultant = new Consultants(consultantId, first_Name, last_Name, specialization, email, phone_Number, countries, jobs);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Consultants>> constraintViolations =validator.validate(consultant);
		
		if(!constraintViolations.isEmpty()) {
			String error="";
			for(ConstraintViolation<Consultants> constraintViolation: constraintViolations) {
				error += "<li>"+ constraintViolation.getMessage()+"</li>";
			}
			
			request.setAttribute("errors", error);
			RequestDispatcher dispatcher =request.getRequestDispatcher("Consultants/create.jsp");
			dispatcher.forward(request, response);
		}
		else {
			try {
				consultantsService.updateConsultant(consultant);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 response.sendRedirect("ConsultantsServlet?action=list");
		}
    }

    private void deleteConsultant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int consultantId = Integer.parseInt(request.getParameter("id"));

        try {
        	consultantsService.deleteConsultant(consultantId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("ConsultantsServlet?action=list");
    }
}
