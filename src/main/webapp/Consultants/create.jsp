<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

            <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dash.css">
            <link rel="stylesheet"
                href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
                crossorigin="anonymous">
                </script>

            <title>The Jobs Consultation Centre-Admin</title>
        </head>
        <% String userName=null; Cookie[] cookies=request.getCookies(); if(cookies !=null){ for(Cookie cookie :
            cookies){ if(cookie.getName().equals("user")) userName=cookie.getValue(); } } if(userName==null)
            response.sendRedirect("login.jsp"); %>

            <body class="container" id="body-pd">
                <header class="header" id="header">
                    <div class="header_toggle">
                        <img src="<%=request.getContextPath()%>/img/icons8.png" class='' id="header-toggle">
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

                <div class=" container col-md-5">
                    <div class="row">
                        <div class="col">
                            <div class="card" style="margin-top: 90px; width:100%">
                                <h5 class="">
                                    <c:if test="${consultant != null}">
                                        Update Consultant
                                    </c:if>
                                    <c:if test="${consultant == null}">
                                        Add New Consultant
                                    </c:if>
                                </h5>
                                <div class="card-body" style="width: 100%">
                                    <c:choose>
                                        <c:when test="${consultant != null}">
                                            <form
                                                action="<%=request.getContextPath()%>/ConsultantsServlet?action=update"
                                                method="post">
                                        </c:when>
                                        <c:otherwise>
                                            <form
                                                action="<%=request.getContextPath()%>/ConsultantsServlet?action=create"
                                                method="post">
                                        </c:otherwise>
                                    </c:choose>

                                    <c:if test="${consultant != null}">
                                        <input type="hidden" name="id"
                                            value="<c:out value='${consultant.getConsultant_ID()}' />" />
                                    </c:if>

                                    <fieldset class="form-group">
                                        <label>First Name</label> <input type="text"
                                            value="<c:out value='${consultant.getFirst_Name()}' />" class="form-control"
                                            name="first_Name" required="required">
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Last Name</label> <input type="text"
                                            value="<c:out value='${consultant.getLast_Name()}' />" class="form-control"
                                            name="last_Name">
                                    </fieldset>

                                    <fieldset class="form-group">
                                        <label>Specialization</label> <input type="text"
                                            value="<c:out value='${consultant.getSpecialization()}' />"
                                            class="form-control" name="specialization">
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <label>Email</label> <input type="text"
                                            value="<c:out value='${consultant.getEmail()}' />" class="form-control"
                                            name="email">
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <label>Phone Number</label> <input type="text"
                                            value="<c:out value='${consultant.getPhone_Number()}' />"
                                            class="form-control" name="phone_Number">
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <label>Countries</label> <input type="text"
                                            value="<c:out value='${consultant.getCountries()}' />" class="form-control"
                                            name="countries">
                                    </fieldset>
                                    <fieldset class="form-group">
                                        <label>Jobs</label> <input type="text"
                                            value="<c:out value='${consultant.getJobs()}' />" class="form-control"
                                            name="jobs">
                                    </fieldset>

                                    <br>
                                    <button type="submit" class="btn btn-success">Save</button>
                                    <c:if test="${not empty errors}">
                                        <ul class="error text text-danger">${errors}</ul>
                                    </c:if>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </body>
            <script src="../js/script.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"></script>
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        </html>