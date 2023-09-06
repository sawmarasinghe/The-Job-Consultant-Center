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

import com.Model.Receptionist;
import service.ReceptionistService;

@WebServlet("/ReceptionistServlet")
public class ReceptionistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ReceptionistService receptionistService;

    public void init() {
    	receptionistService = new ReceptionistService();
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
                listReceptionists(request, response);
                break;
            case "editFrm":
                showEditForm(request, response);
                break;
            case "update":
                updateReceptionist(request, response);
                break;
            case "delete":
                deleteReceptionist(request, response);
                break;
            case "createFrm":
                createFrm(request, response);
                break;
            case "create":
                createReceptionist(request, response);
                break;
            default:
                listReceptionists(request, response);
        }
    }

    private void createReceptionist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first_Name = request.getParameter("first_Name");
        String last_Name = request.getParameter("last_Name");
        String email = request.getParameter("email");
        int phone_Number = Integer.parseInt(request.getParameter("phone_Number"));

        Receptionist newReceptionist = new Receptionist(first_Name, last_Name, email, phone_Number);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Receptionist>> constraintViolations =validator.validate(newReceptionist);
		
		if(!constraintViolations.isEmpty()) {
			String error="";
			for(ConstraintViolation<Receptionist> constraintViolation: constraintViolations) {
				error += "<li>"+ constraintViolation.getMessage()+"</li>";
			}
			
			request.setAttribute("errors", error);
			RequestDispatcher dispatcher =request.getRequestDispatcher("Receptionnists/create.jsp");
			dispatcher.forward(request, response);
		}
		else {
			try {
				receptionistService.addReceptionist(newReceptionist);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 response.sendRedirect(request.getContextPath() + "/ReceptionistServlet?action=list");
		}

    }

    private void createFrm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("Receptionnists/create.jsp");
    }

    private void listReceptionists(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Receptionist> receptionistsList = receptionistService.getAllReceptionists();
        request.setAttribute("receptionistsList", receptionistsList);
        request.getRequestDispatcher("Receptionnists/list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int receptionistId = Integer.parseInt(request.getParameter("id"));
        Receptionist receptionist = receptionistService.getReceptionistById(receptionistId);
        request.setAttribute("receptionist", receptionist);
        request.getRequestDispatcher("Receptionnists/create.jsp").forward(request, response);
    }

    private void updateReceptionist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int receptionistId = Integer.parseInt(request.getParameter("id"));
        String first_Name = request.getParameter("first_Name");
        String last_Name = request.getParameter("last_Name");
        String email = request.getParameter("email");
        int phone_Number = Integer.parseInt(request.getParameter("phone_Number"));

        Receptionist receptionist = new Receptionist(receptionistId, first_Name, last_Name, email, phone_Number);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Receptionist>> constraintViolations =validator.validate(receptionist);
		
		if(!constraintViolations.isEmpty()) {
			String error="";
			for(ConstraintViolation<Receptionist> constraintViolation: constraintViolations) {
				error += "<li>"+ constraintViolation.getMessage()+"</li>";
			}
			
			request.setAttribute("errors", error);
			RequestDispatcher dispatcher =request.getRequestDispatcher("Receptionnists/create.jsp");
			dispatcher.forward(request, response);
		}
		else {
			try {
				receptionistService.updateReceptionist(receptionist);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect("ReceptionistServlet?action=list");
		}

    }

    private void deleteReceptionist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int receptionistId = Integer.parseInt(request.getParameter("id"));

        try {
        	receptionistService.deleteReceptionist(receptionistId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("ReceptionistServlet?action=list");
    }
}
