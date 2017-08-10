<%-- 
    Document   : login
    Created on : 02-ago-2017, 23:52:43
    Author     : VakSF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
            
        <!-- Stylesheet -->
        <link href="lib/semantic/semantic.min.css" rel="stylesheet">
            
        <title>.: Iniciar sesión :.</title>
            
        <style>
            
            body { 
                background-image: url("img/chocolate.jpg");
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-position: right bottom; 
            }
            
            body > .grid {
                height: 100%;
            }
            .image {
                margin-top: -100px;
            }
            .column {
                max-width: 450px;
            }
                
        </style>
    </head>
    <body>
        
        <%
            Object user = request.getSession().getAttribute("loggedUser");

            if (user != null && !user.toString().isEmpty()) {

                response.sendRedirect("home.jsp");

            }
        %>
        
        <div class="ui middle aligned center aligned grid">
            <div class="column">
                <h1 class="ui image header">
                    <div class="content">
                        .: Inicia sesión :.
                    </div>
                </h1>
                <form action="LoginController" method="POST" class="ui large form">
                    <div class="ui stacked secondary segment">
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="user icon"></i>
                                <input type="text" name="user" placeholder="E-mail address">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="password" name="password" placeholder="Password">
                            </div>
                        </div>
                        <div class="ui fluid large teal submit button">Entrar!</div>
                    </div>
                        
                    <div class="ui error message"></div>
                        
                </form>
                    
                <div class="ui message">
                    No tienes una cuenta? <a href="/register.jsp">Regístrate</a>
                </div>
            </div>
        </div>
        
        <script src="lib/jquery/jquery-3.2.1.min.js"></script>
        <script src="lib/angular/angular.min.js"></script>
        <script src="lib/semantic/semantic.min.js"></script>
        <script src="js/login.js"></script>
    </body>
</html>
