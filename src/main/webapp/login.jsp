<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous">
            </script>
        <title>The Jobs Consultation Centre-Admin</title>
    </head>

    <body>
        <div class="container-fluid ">
            <div class="row">
                <nav class="navbar navbar-expand-lg ">
                    <a class="navbar-brand" href="#"><img src="img/logo.gif" width="50rem" alt="" srcset=""></a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse fw-bolder justify-content-end" id="navbarNav">
                        <ul class="navbar-nav ">
                            <li class="nav-item me-2 ">
                                <a class="nav-link btn button " href="/UsersServlet?action=admin">LOGIN</a>
                            </li>
                            <li class="nav-item me-2">
                                <a class="nav-link btn button" href="#">REGISTER</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="row">
                <div class="content" style="padding: 70px;">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6">
                                <img src="img/undraw_remotely_2j6y.svg" alt="Image" class="img-fluid">
                            </div>
                            <div class="col-md-6 contents">
                                <div class="row justify-content-center">
                                    <div class="col-md-8">
                                        <div class="mb-4">
                                            <h1 class="text-color-pur text-weight">Welcome to The Jobs Consultation
                                                Centre-Admin Panel!
                                            </h1>
                                            <p class="mb-4 text-color-pur"> As a member of our Admin Panel, you'll gain
                                                access to a wide range of powerful tools that will help you manage job
                                                listings, candidate applications, and more with ease.</p>
                                        </div>
                                        <form action="UsersServlet" method="post">
                                            <input type="hidden" name="action" value="login">
                                            <div class="form-group first">
                                                <label class="text-color-pur" for="username">User Name</label>
                                                <input type="text" class="form-control" id="username" name="username">
                                            </div>
                                            <div class="form-group last mb-4">
                                                <label class="text-color-pur" for="password">Password</label>
                                                <input type="password" class="form-control" id="password"
                                                    name="password">

                                            </div>
                                            <c:if test="${not empty error}">
                                                <p class="error text text-danger">${error}</p>
                                            </c:if>
                                            <div class="d-flex mb-5 align-items-center">
                                                <label class="control control--checkbox mb-0 text-color-pur"><span
                                                        class="caption">Remember
                                                        me</span>
                                                    <input type="checkbox" checked="checked" />
                                                    <div class="control__indicator"></div>
                                                </label>
                                                <span class="ml-auto "><a href="#"
                                                        class="forgot-pass text-color-pur">Forgot
                                                        Password</a></span>
                                            </div>
                                            <button type="submit" class="btn button" style="width: 100%;">Log
                                                In</button>
                                            <span class="d-block text-left my-4 text-muted text-color-pur">&mdash; or
                                                login
                                                with
                                                &mdash;</span>
                                            <div class="col">
                                                <a href="#" class="col-4 m-3">
                                                    <img src="img/icons8-facebook-48.png" width="40" alt="" srcset="">
                                                </a>
                                                <a href="#" class="col-4s  m-3">
                                                    <img src="img/icons8-twitter-48.png" width="40" alt="" srcset="">
                                                </a>
                                                <a href="#" class="col-4 m-3">
                                                    <img src="img/icons8-google-48.png" width="40" alt="" srcset="">
                                                </a>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </html>