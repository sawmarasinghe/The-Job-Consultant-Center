package com.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.Model.Users;
import service.UsersService;


@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsersService usersService;

    public void init() {
    	usersService = new UsersService();
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
                listUsers(request, response);
                break;
            case "login":
                login(request, response);
                break;
            case "admin":
            	admin(request,response);
            	break;
            case "addUserFrm":
            	addUserFrm(request,response);
            	break;
            case "update":
            	update(request,response);
            	break;
            case "updateFrm":
            	updateFrm(request,response);
            	break;
            case "addUser":
            	addUser(request,response);
            	break;
            case "delete":
            	delete(request,response);
            	break;
            case "logout":
            	logout(request,response);
            	break;
            default:
            	listUsers(request, response);
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/html");
    	Cookie loginCookie = null;
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		if(cookie.getName().equals("user")){
    			loginCookie = cookie;
    			break;
    		}
    	}
    	}
    	if(loginCookie != null){
    		loginCookie.setMaxAge(0);
        	response.addCookie(loginCookie);
    	}
    	response.sendRedirect("login.jsp");
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	private void updateFrm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id =  Integer.parseInt(request.getParameter("id"));
    	
    	 Users existingUser =usersService.getUserById(id);
    	 
    	 request.setAttribute("user", existingUser);
 		 request.getRequestDispatcher("AddUser.jsp").forward(request, response);

	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username =  request.getParameter("username");
		String password =  request.getParameter("password");
		String role =  request.getParameter("Role");
		
		Users newUser = new Users(username, password, role);
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Users>> constraintViolations =validator.validate(newUser);
		
		if(!constraintViolations.isEmpty()) {
			String error="";
			for(ConstraintViolation<Users> constraintViolation: constraintViolations) {
				error += "<li>"+ constraintViolation.getMessage()+"</li>";
			}
			
			request.setAttribute("errors", error);
			RequestDispatcher dispatcher =request.getRequestDispatcher("AddUser.jsp");
			dispatcher.forward(request, response);
		}
		else {
			try {
				usersService.addUser(newUser);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath() + "//UsersServlet?action=list");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id =  Integer.parseInt(request.getParameter("id"));
		String username =  request.getParameter("username");
		String password =  request.getParameter("password");
		String role =  request.getParameter("Role");
		
		Users users = new Users(id, username, password, role);
		
		try {
			usersService.updateUser(users);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "//UsersServlet?action=list");
		
	}

	private void addUserFrm(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.sendRedirect("AddUser.jsp");
	}
    
    private void admin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.sendRedirect("login.jsp");
	}

    private void listUsers(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List<Users> usersList;
        usersList = usersService.getAllUsers();
		request.setAttribute("usersList", usersList);
		request.getRequestDispatcher("User_list.jsp").forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String isValidUser = usersService.loginCheck(username, password);

        if (isValidUser != null) {
        	Cookie loginCookie = new Cookie("user",isValidUser);
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
            response.sendRedirect("Dashboard.jsp");
        } else {
        	request.setAttribute("error", "Invalid username or password");
	        request.getRequestDispatcher("login.jsp").forward(request, response); 
        }
    }
}
