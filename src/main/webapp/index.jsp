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
            <title>The Jobs Consultation Centre</title>
        </head>

        <body>
            <nav class="navbar navbar-expand-lg ">
                <a class="navbar-brand" href="#"><img src="img/logo.gif" width="50rem" alt="" srcset=""></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse fw-bolder" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">HOME</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">ABOUT</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">SERVICE</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">DOCTORS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">FACILITIES</a>
                        </li>

                    </ul>
                </div>
            </nav>
            <div class="container-fluid background-image-div">
                <div class="row ">
                    <div class="col-6 ms-5 mt-4 mb-3 ">
                        <h1 class="fw-bolder text text-light">The Jobs Consultation Centre</h1>
                    </div>
                    <div class="row justify-content-center align-items-center">
                        <div
                            class="col-lg-3 col-md-12 ms-4 bg-white shadow p-3 mb-5 bg-body rounded fw-normal text-color-pur">
                            <form action="<%=request.getContextPath()%>/AppointmentServlet?action=indexSave"
                                method="POST">
                                <div class="mb-3 mt-2 ms-3 me-3">
                                    <label for="exampleFormControlInput1" class="form-label text-weight">First
                                        Name</label>
                                    <input type="text" class="form-control" id="MobileNumber" name="FirstName">
                                </div>
                                <div class="mb-3 ms-3 me-3">
                                    <label for="exampleFormControlInput1" class="form-label text-weight">Last
                                        Name</label>
                                    <input type="text" class="form-control " id="FirstName" name="LastName">
                                </div>
                                <div class="mb-3 ms-3 me-3">
                                    <label for="exampleFormControlInput1" class="form-label text-weight">Email</label>
                                    <input type="email" class="form-control" id="LastName" name="email">
                                </div>
                                <div class="mb-3 ms-3 me-3">
                                    <label for="exampleFormControlInput1" class="form-label text-weight">Phone
                                        Number</label>
                                    <input type="text" class="form-control" id="Address" name="PhoneNumber">
                                </div>
                                <div class="mb-3 ms-3 me-3">
                                    <label for="exampleFormControlInput1" class="form-label text-weight">Desired
                                        Country</label>
                                    <input type="text" class="form-control" id="Age" name="Desired_Country">
                                </div>
                                <div class="mb-3 ms-3 me-3">
                                    <label for="exampleFormControlInput1" class="form-label text-weight">Desired Job
                                        Type</label>
                                    <input type="text" class="form-control" id="Age" name="Desired_Job_Type">
                                </div>
                                <div class="mb-3 ms-3 me-3">
                                    <label for="exampleFormControlInput1" class="form-label text-weight">Select
                                        Consultant</label>
                                    <select class="form-select " name="Consultant_ID"
                                        aria-label="Default select example">

                                        <c:forEach var="consultant" items="${consultantsList}">
                                            <option value="${consultant.getConsultant_ID()}">
                                                <table class="table table-bordered table-responsive">
                                                    <tr>
                                                        <td>${consultant.getFirst_Name()}
                                                            ${consultant.getLast_Name()} |</td>
                                                        <td>${consultant.getCountries()} |</td>
                                                        <td>${consultant.getJobs()}|</td>
                                                    </tr>
                                                </table>
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3 ms-3 me-3">
                                    <label for="exampleFormControlInput1" class="form-label text-weight">Appointment
                                        Date</label>
                                    <input type="date" class="form-control" id="Age" name="date">
                                </div>
                                <div class="row">
                                    <div class="col text-center">
                                        <button type="submit" class="btn text-center button">Submit</button>
                                    </div>
                                </div>
                                <div class="row">
                                	<div class="col text-center">
	                                    <c:if test="${not empty errors}">
	                                        <ul class="error text text-danger">${errors}</ul>
	                                    </c:if>
	                                </div>
                                </div>
                            </form>
                        </div>
                        <div class=" col-lg-6 col-md-12 ms-4 bg-white shadow p-3 mb-5 bg-body rounded custom ">
                            <div class="row">
                                <h4 class="fw-bolder text-color-pur">Excellence. Every Patient. Every Time.</h4>
                            </div>
                            <div class="row mt-5 text-center justify-content-md-center" style="margin-bottom: 50px;">
                                <div class="col-lg-2 col-md-3 ">
                                    <img width="64" height="64" src="img/icons8-free-64.png" />
                                    <h6 class="fw-bolder">Free Consultation Services</h6>
                                </div>
                                <div class="col-lg-2 col-md-3">
                                    <img width="64" height="64" src="img/icons8-permanent-job-64.png" />
                                    <h6 class="fw-bolder">Expert Advice on International Job Markets</h6>
                                </div>
                                <div class="col-lg-2 col-md-3">
                                    <img width="64" height="64" src="img/icons8-search-client-64.png" />
                                    <h6 class="fw-bolder">Job Search Assistance</h6>
                                </div>
                                <div class="col-lg-2 col-md-3">
                                    <img width="64" height="64" src="img/icons8-interview-64.png" />
                                    <h6 class="fw-bolder">Interview Preparation</h6>
                                </div>
                                <div class="col-lg-2 col-md-3">
                                    <img width="64" height="64" src="img/icons8-customer-support-64.png" />
                                    <h6 class="fw-bolder">Follow-up Support</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <div class="col-8">
                        <h1 class="text-center mb-5" style="color: #58086c; font-weight: 900;">SERVICES</h1>
                        <div class="row ">
                            <div class="col-lg-4 col-sm-12">
                                <div class="card" style="width: 18rem; height: 35rem;">
                                    <img src="img/Consultative sales-amico.svg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h3 class="text-color-pur">Free Consultation Services</h3>
                                        <p class="card-text text-justify">The centre provides free consultation services
                                            to job
                                            seekers.
                                            Their team of expert job consultants specializes in various countries and
                                            different
                                            types of jobs, guiding job seekers towards the right job opportunities based
                                            on
                                            their qualifications and preferences.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-sm-12">
                                <div class="card" style="width: 18rem; height: 35rem;">
                                    <img src="img/Consulting-amico.svg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h3 class="text-color-pur">Expert Advice on International Job Markets</h3>
                                        <p class="card-text text-justify">The consultants at the centre possess in-depth
                                            knowledge and
                                            experience in international job markets. They provide valuable insights and
                                            advice
                                            on job trends, demand for skills, and potential job opportunities in
                                            different
                                            countries.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-sm-12">
                                <div class="card" style="width: 18rem; height: 35rem;">
                                    <img src="img/Job hunt-amico.svg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h3 class="text-color-pur">Job Search Assistance</h3>
                                        <p class="card-text text-justify"> The centre helps job seekers in their search
                                            for
                                            suitable job
                                            openings abroad. They may provide information on job portals, recruitment
                                            agencies,
                                            and other resources that can aid in finding job opportunities.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-3 justify-content-center align-items-center">
                            <div class="col-lg-4 col-sm-12">
                                <div class="card" style="width: 18rem; height: 35rem;">
                                    <img src="img/Job hunt-amico.svg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h3 class="text-color-pur">Interview Preparation</h3>
                                        <p class="card-text text-justify">The consultants may conduct mock interviews
                                            and
                                            provide tips to job
                                            seekers on how to perform well during job interviews. They may offer
                                            guidance on
                                            answering common interview questions and presenting oneself confidently.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-sm-12">
                                <div class="card" style="width: 18rem; height: 35rem;">
                                    <img src="img/Business support-pana.svg" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h3 class="text-color-pur">Follow-up Support</h3>
                                        <p class="card-text text-justify">The centre may offer post-placement support to
                                            candidates who
                                            secure jobs through their guidance. This support may include assistance in
                                            resolving
                                            work-related issues or challenges faced by the candidates in their new
                                            roles.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        </body>

        </html>