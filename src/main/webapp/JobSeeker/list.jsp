<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
                crossorigin="anonymous">
            <link rel="stylesheet" href="css/style.css">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
                crossorigin="anonymous">
                </script>
            <link rel="stylesheet" href="css/dash.css">
            <link rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

            <title>The Jobs Consultation Centre-Admin</title>
        </head>

        <body id="body-pd">
            <% String userName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie :
                cookies){ if(cookie.getName().equals("user")) userName=cookie.getValue(); } } if(userName==null)
                response.sendRedirect("./login.jsp"); %>

                <header class="header" id="header">
                    <div class="header_toggle">
                        <img src="img/icons8.png" class='' id="header-toggle">
                    </div>

                    <div class="float-right">
                        <h3>
                            User: <%=userName %>
                        </h3>
                    </div>

                </header>
                <div class="l-navbar" id="nav-bar">
                    <nav class="nav">
                        <div>
                            <a href="#" class="nav_logo">
                                <i class="fa fa-handshake-o" aria-hidden="true"></i>
                                <span class="nav_logo-name">ADMIN PANEL</span>
                            </a>

                            <div class="nav_list">
                                <a href="<%=request.getContextPath()%>/Dashboard.jsp" class="nav_link active"><i
                                        class="fa fa-tachometer" aria-hidden="true"></i>
                                    <span class="nav_name ">Dashboard</span>
                                </a>
                                <% if (userName.equals("admin")) { %>
                                    <a href="<%= request.getContextPath() %>/UsersServlet?action=list" class="nav_link">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                        <span class="nav_name">Users</span>
                                    </a>
                                    <% } %>

                                        <% if (userName.equals("admin")) { %>
                                            <a href="<%=request.getContextPath()%>/ConsultantsServlet?action=list"
                                                class="nav_link">
                                                <i class="fa fa-address-card" aria-hidden="true"></i>
                                                <span class="nav_name">Consultant</span>
                                            </a>
                                            <% } %>

                                                <% if (userName.equals("admin")) { %>
                                                    <a href="<%=request.getContextPath()%>/ReceptionistServlet?action=list"
                                                        class="nav_link">
                                                        <i class="fa fa-users" aria-hidden="true"></i>
                                                        <span class="nav_name">Reception</span>
                                                    </a>
                                                    <% } %>

                                                        <a href="<%=request.getContextPath()%>/AppointmentServlet?action=list"
                                                            class="nav_link">
                                                            <i class="fa fa-list-alt" aria-hidden="true"></i>
                                                            <span class="nav_name">Appointment</span>
                                                        </a>

                                                        <a href="<%=request.getContextPath()%>/JobSeekerServlet?action=list"
                                                            class="nav_link">
                                                            <i class="fa fa-ils" aria-hidden="true"></i>
                                                            <span class="nav_name">Job Seekers</span>
                                                        </a>


                            </div>
                        </div>
                        <a href="<%=request.getContextPath()%>/UsersServlet?action=logout" class="nav_link"> <i
                                class="fa fa-sign-out" aria-hidden="true"></i>
                            <span class="nav_name">SignOut</span>
                        </a>
                    </nav>
                </div>

                <div class="height-100 bg-light">
                    <div class="container">
                        <h3 class="text-center">List of Job Seekers</h3>
                        <hr>
                        <div class="container text-left">
                            <a href="<%=request.getContextPath()%>/JobSeekerServlet?action=createFrm"
                                class="btn btn-success">Add New Seeker</a>
                        </div>
                        <br>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email</th>
                                    <th>Phone Number</th>
                                    <th>Country</th>
                                    <th>Job</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="jobSeekers" items="${jobSeekers}">
                                    <tr>
                                        <td>
                                            <c:out value="${jobSeekers.getJobSeeker_ID()}" />
                                        </td>
                                        <td>
                                            <c:out value="${jobSeekers.getFirst_Name()}" />
                                        </td>
                                        <td>
                                            <c:out value="${jobSeekers.getLast_Name()}" />
                                        </td>
                                        <td>
                                            <c:out value="${jobSeekers.getEmail()}" />
                                        </td>
                                        <td>
                                            <c:out value="${jobSeekers.getPhone_Number()}" />
                                        </td>
                                        <td>
                                            <c:out value="${jobSeekers.getDesired_Country()}" />
                                        </td>
                                        <td>
                                            <c:out value="${jobSeekers.getDesired_Job_Type()}" />
                                        </td>

                                        <td><a class="btn btn-primary"
                                                href="JobSeekerServlet?action=editFrm&id=<c:out value='${jobSeekers.getJobSeeker_ID()}' />">Edit</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-danger"
                                                href="JobSeekerServlet?id=<c:out value='${jobSeekers.getJobSeeker_ID()}' />">Delete</a>

                                        </td>
                                    </tr>
                                </c:forEach>
                                <!-- } -->
                            </tbody>

                        </table>
                    </div>
                </div>

        </body>
        <script src="js/script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        </html>