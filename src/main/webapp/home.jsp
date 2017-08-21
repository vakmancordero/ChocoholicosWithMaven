<%-- 
    Document   : login
    Created on : 02-ago-2017, 23:52:43
    Author     : VakSF
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
            
        <!-- Stylesheet -->
        <link href="<c:url value = "/lib/semantic/semantic.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value = "/css/home.css"/>" rel="stylesheet" rel="stylesheet" type="text/css">
        <link href="<c:url value = "/lib/sweet/sweetalert2.css"/>" rel="stylesheet" type="text/css">
        
        <title>.: Chocoholicos :.</title>
    </head>
    <body class="grey-background">
        
        <%
            
            Object user = request.getSession().getAttribute("loggedUser");
            
            if (user == null) 
                response.sendRedirect("login.jsp");
        %>
        <div ng-app="chocoApp"  ng-controller="ChocoController">
    
            <!-- Sidebar Menu -->
            <div class="ui vertical inverted sidebar menu">
                <a class="active header item">Chocoholicos</a>
                <a class="item" href="<c:url value = "/home.jsp"/>" class="item">
                    <i class="home icon"></i>
                    Inicio
                </a>
                <a class="item" ng-click="openListConsultationsMl()">
                    <i class="list icon"></i>
                    Ver consultas
                </a>
                <a class="item" href="UserController?action=closeSession">
                    <i class="sign out icon"></i>
                    Cerrar sesión
                </a>
            </div>
    
            <div class="pusher" id="myContainer">
                
                <div class="ui inverted vertical masthead center aligned segment">
                    <div class="ui center aligned container">
                        <div class="ui large secondary inverted pointing menu">
                            <a class="toc item">
                                <i class="sidebar icon"></i>
                            </a>
                            <a class="active header item">Chocoholicos</a>
                            <a class="active item" href="<c:url value = "/home.jsp"/>" class="item">Inicio</a>
                            <div class="ui dropdown item">
                                <i class="database icon"></i>Consultas <i class="dropdown icon"></i>
                                <div class="menu">
                                    <a class="item" href="#">Ver consultas</a>
                                    <div class="divider"></div>
                                    <a class="item" href="#">Eliminar consultas</a>
                                </div>
                            </div>
                            <div class="right menu">
                                <div class="ui dropdown inverted item">
                                    
                                    <% if (user != null) { %>
                                
                                        <i class="database icon"></i><%= user.toString() %> <i class="dropdown icon"></i>
                                    
                                    <% } %>
                                
                                    <div class="menu">
                                        <a class="item" href="UserController?action=closeSession">
                                            <i class="sign out icon"></i>
                                            Cerrar sesión
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="ui vertical stripe segment">

                    <h1 style="font-size: 3em;" class="ui header container main">Bienvenido a <strong>Chocoholicos</strong>!</h1>

                    <div class="ui middle aligned piled segment container">
                        <div class="ui inverted segment" style="overflow-x: scroll;">
                            <h4>Listado de herramientas</h4>
                            <div class="ui inverted labeled icon menu">
                                <a class="item" ng-click="openListUsersMl()">
                                    <i class="address book icon"></i>
                                    Listar usuarios
                                </a>
                                <a class="item" ng-click="openListConsultationsMl()">
                                    <i class="doctor icon"></i>
                                    Listar consultas
                                </a>
                                <a class="item" ng-click="openFindMemberMl()">
                                    <i class="search icon"></i>
                                    Buscar membresía
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="ui middle aligned piled segment container">
                        <div class="ui three column centered stackable grid segment special cards">
                            <div class="card">
                                <div class="blurring dimmable image">
                                    <div class="ui dimmer">
                                        <div class="content">
                                            <div class="center">
                                                <button class="ui inverted button" ng-click="openAddUserMl()">
                                                    Añadir usuario
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <img src="<c:url value = "/img/matthew.png"/>">
                                </div>
                                <div class="content">
                                    <a class="header">Administrar usuarios</a>
                                    <div class="meta">
                                        <span class="date">Agregar nuevos miembros o proveedores</span>
                                    </div>
                                </div>
                                <div class="extra content">
                                    <a>
                                        <i class="user circle icon"></i>
                                        {{providers.length + members.length}} miembros registrados
                                    </a>
                                </div>
                            </div>
                            <div class="card">
                                <div class="blurring dimmable image">
                                    <div class="ui dimmer">
                                        <div class="content">
                                            <div class="center">
                                                <button class="ui inverted button" ng-click="openAddConsultationMl()">
                                                    Nueva consulta
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <img src="<c:url value = "/img/consulta.png"/>">
                                </div>
                                <div class="content">
                                    <a class="header">Generar consultas</a>
                                    <div class="meta">
                                        <span class="date">Crear nuevas consultas para miembros</span>
                                    </div>
                                </div>
                                <div class="extra content">
                                    <a>
                                        <i class="doctor icon"></i>
                                        {{consultations.length}} Consultas registradas
                                    </a>
                                </div>
                            </div>
                            <div class="card">
                                <div class="blurring dimmable image">
                                    <div class="ui dimmer">
                                        <div class="content">
                                            <div class="center">
                                                <button class="ui inverted button" ng-click="openListUserMlReport()">
                                                    Nuevo reporte
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <img src="<c:url value = "/img/report.png"/>">
                                </div>
                                <div class="content">
                                    <a class="header">Generar reportes</a>
                                    <div class="meta">
                                        <span class="date">Exportar información de registros</span>
                                    </div>
                                </div>
                                <div class="extra content">
                                    <a><i class="print icon"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                                        
            <!-- Crear usuario -->
            <div id="addUserMl" class="ui large modal" >
                <i class="close icon"></i>
                <div class="header">
                    Nuevo usuario
                </div>
                <div class="image content">
                    <div class="description">
                        <form class="ui form" ng-submit="saveUser()" id="addUserForm">
                            <h4 class="ui dividing header">Información del usuario</h4>
                            <div class="field">
                                <label>Tipo de usuario: </label>
                                <select class="ui fluid dropdown" ng-model="user.type" required>
                                    <option value="member" selected>Miembro</option>
                                    <option value="provider">Proveedor</option>
                                </select>
                            </div>
                            <div ng-switch on="user.type">
                                <div ng-switch-when="member">
                                    <div class="field">
                                        <label>Nombre:</label>
                                        <input type="text" placeholder="Nombre" ng-model="user.name" required>
                                    </div>
                                    <div class="field">
                                        <label>Dirección:</label>
                                        <input type="text" placeholder="Dirección" ng-model="user.address" required>
                                    </div>
                                    <div class="two fields">
                                        <div class="field">
                                            <label>Código postal:</label>
                                            <input type="text"  placeholder="CP" ng-model="user.cp" required>
                                        </div>
                                        <div class="field">
                                            <label>Ciudad</label>
                                            <select ng-model="user.city" 
                                                    ng-options="city for city in cities"
                                                    ng-change="changeCity()"
                                                    ng-init="user.city = initCity()" required>
                                                <option value="">--</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <div class="eight wide field">
                                            <label>Estado</label>
                                            <select ng-model="user.state" 
                                                    ng-options="state for state in states"
                                                    ng-change="changeState()"
                                                    ng-init="user.state = initState()" required>
                                                <option value="">--</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button class="ui button" type="submit">Finalizar</button>
                                </div>
                                <div ng-switch-when="provider">
                                    <div class="field">
                                        <label>Nombre de usuario:</label>
                                        <input type="text" placeholder="Usuario" ng-model="user.user" required>
                                    </div>
                                    <div class="field">
                                        <label>Contraseña:</label>
                                        <input type="password" placeholder="Contraseña" ng-model="user.password" required>
                                    </div>
                                    <div class="field">
                                        <label>Repetir contraseña:</label>
                                        <input type="password" placeholder="Repetir contraseña" ng-model="user.rePassword" required>
                                    </div>
                                    <div class="field">
                                        <label>Nombre:</label>
                                        <input type="text" placeholder="Nombre" ng-model="user.name" required>
                                    </div>
                                    <div class="field">
                                        <label>Dirección:</label>
                                        <input type="text" placeholder="Dirección" ng-model="user.address" required>
                                    </div>
                                    <div class="two fields">
                                        <div class="field">
                                            <label>Código postal:</label>
                                            <input type="text"  placeholder="CP" ng-model="user.cp" required>
                                        </div>
                                        <div class="field">
                                            <label>Ciudad</label>
                                            <select ng-model="user.city" 
                                                    ng-options="city for city in cities"
                                                    ng-change="changeCity()"
                                                    ng-init="user.city = initCity()" required>
                                                <option value="">--</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <div class="eight wide field">
                                            <label>Estado</label>
                                            <select ng-model="user.state" 
                                                    ng-options="state for state in states"
                                                    ng-change="changeState()"
                                                    ng-init="user.state = initState()" required>
                                                <option value="">--</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button class="ui button" type="submit">Finalizar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
            
            <!-- Editar miembro -->
            <div id="editMemberMl" class="ui large modal" >
                <i class="close icon"></i>
                <div class="header">
                    Editar miembro
                </div>
                <div class="image content">
                    <div class="description">
                        <form class="ui form" ng-submit="editMember()">
                            <h4 class="ui dividing header">Información del miembro</h4>
                            <div class="field">
                                <label>ID:</label>
                                <input type="text" placeholder="Nombre" ng-model="user.id" readonly>
                            </div>
                            <div class="field">
                                <label>Nombre:</label>
                                <input type="text" placeholder="Nombre" ng-model="user.name" required>
                            </div>
                            <div class="field">
                                <label>Dirección:</label>
                                <input type="text" placeholder="Dirección" ng-model="user.address" required>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <label>Código postal:</label>
                                    <input type="text"  placeholder="CP" ng-model="user.cp" required>
                                </div>
                                <div class="field">
                                    <label>Ciudad</label>
                                    <select ng-model="user.city" 
                                            ng-options="city for city in cities"
                                            ng-change="changeCity()"
                                            ng-init="user.city = initCity()">
                                        <option value="">--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="field">
                                <div class="eight wide field">
                                    <label>Estado</label>
                                    <select ng-model="user.state" 
                                            ng-options="state for state in states"
                                            ng-change="changeState()"
                                            ng-init="user.state = initState()">
                                        <option value="">--</option>
                                    </select>
                                </div>
                            </div>
                            <button class="ui button" type="submit">Finalizar</button>
                        </form>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
            
            <!-- Editar proveedor -->
            <div id="editProviderMl" class="ui large modal" >
                <i class="close icon"></i>
                <div class="header">
                    Editar proveedor
                </div>
                <div class="image content">
                    <div class="description">
                        <form class="ui form" ng-submit="editProvider()">
                            <h4 class="ui dividing header">Información del proveedor</h4>
                            <div class="field">
                                <label>ID:</label>
                                <input type="text" placeholder="Nombre" ng-model="user.id" readonly>
                            </div>
                            <div class="field">
                                <label>Nombre:</label>
                                <input type="text" placeholder="Nombre" ng-model="user.name" required>
                            </div>
                            <div class="field">
                                <label>Dirección:</label>
                                <input type="text" placeholder="Dirección" ng-model="user.address" required>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <label>Código postal:</label>
                                    <input type="text"  placeholder="CP" ng-model="user.cp" required>
                                </div>
                                <div class="field">
                                    <label>Ciudad</label>
                                    <select ng-model="user.city" 
                                            ng-options="city for city in cities"
                                            ng-change="changeCity()"
                                            ng-init="user.city = initCity()">
                                        <option value="">--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="field">
                                <div class="eight wide field">
                                    <label>Estado</label>
                                    <select ng-model="user.state" 
                                            ng-options="state for state in states"
                                            ng-change="changeState()"
                                            ng-init="user.state = initState()">
                                        <option value="">--</option>
                                    </select>
                                </div>
                            </div>
                            <button class="ui button" type="submit">Finalizar</button>
                        </form>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
            
            <!-- Crear consultas -->
            <div id="addConsultationMl" class="ui large modal" >
                <i class="close icon"></i>
                <div class="header">
                    Nueva consulta
                </div>
                <div class="image content">
                    <div class="description">
                        <form class="ui form" ng-submit="saveConsultation()" >
                            <h4 class="ui dividing header">Información de la consulta</h4>
                            <div class="field">
                                <label>Fecha:</label>
                                <input type="date" placeholder="Fecha" ng-model="consultation.date" required>
                            </div>
                            <div class="field">
                                <label>Servicio:</label>
                                <div class="ui search" id="search_service">
                                    <div class="ui action left icon input">
                                        <i class="search icon"></i>
                                        <input class="prompt" type="text" placeholder="Buscar servicio..."
                                                ng-model="consultation.service" required>
                                        <div class="ui teal button">Search</div>
                                    </div>
                                    <div class="results"></div>
                                </div>
                            </div>
                            <div class="field">
                                <label>Proveedor:</label>
                                <div class="ui search" id="search_provider">
                                    <div class="ui action left icon input">
                                        <i class="search icon"></i>
                                        <input class="prompt" type="text" placeholder="Buscar proveedor..."
                                               ng-model="consultation.provider" required>
                                        <div class="ui grey button">Search</div>
                                    </div>
                                    <div class="results"></div>
                                </div>
                            </div>
                            <div class="field">
                                <label>Miembro:</label>
                                <div class="ui search" id="search_member">
                                    <div class="ui action left icon input">
                                        <i class="search icon"></i>
                                        <input class="prompt" type="text" placeholder="Buscar miembro..."
                                               ng-model="consultation.member" required>
                                        <div class="ui grey button">Search</div>
                                    </div>
                                    <div class="results"></div>
                                </div>
                            </div>
                            <div class="field">
                                <label>Descripción del servicio:</label>
                                <textarea rows="3" ng-model="consultation.description"></textarea>
                            </div>
                            <div class="field">
                                <label>Comentarios:</label>
                                <textarea rows="3" ng-model="consultation.comment"></textarea>
                            </div>
                            <button class="ui button" type="submit">Finalizar</button>
                        </form>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
            
            <!-- Usuarios -->
            <div id="listUsersMl" class="ui large modal" >
                <i class="close icon"></i>
                <div class="header">
                    Tabla de usuarios
                </div>
                <div class="content">
                    <div class="description ">
                        
                        <h1>Proveedores</h1>
                        <table class="ui inverted selectable celled tablet stackable table">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Dirección</th>
                                    <th>Ciudad</th>
                                    <th>Estado</th>
                                    <th>Editar</th>
                                    <th>Eliminar</th>
                                    <th>Reporte</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="provider in providers track by $index">
                                    <td>{{provider.name}}</td>
                                    <td>{{provider.address}}</td>
                                    <td>{{provider.city}}</td>
                                    <td>{{provider.state}}</td>
                                    <td>
                                        <button class="ui blue button small" ng-click="openEditProviderMl($index)">
                                            <i class="icon edit"></i>
                                            Editar
                                        </button>
                                    </td>
                                    <td>
                                        <button class="ui red button small"  ng-click="deleteProvider($index)">
                                            <i class="icon delete"></i>
                                            Eliminar
                                        </button>
                                    </td>
                                    <td>
                                        <button class="ui grey button small" ng-click="generateReport($index)">
                                            <i class="icon print"></i>
                                            Generar report
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                                    
                        <h1>Miembros</h1>
                        <table class="ui inverted selectable celled tablet stackable table">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Dirección</th>
                                    <th>Ciudad</th>
                                    <th>Estado</th>
                                    <th>Editar</th>
                                    <th>Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="member in members track by $index">
                                    <td>{{member.name}}</td>
                                    <td>{{member.address}}</td>
                                    <td>{{member.city}}</td>
                                    <td>{{member.state}}</td>
                                    <td><button class="ui blue button" ng-click="openEditMemberMl($index)">Editar</button></td>
                                    <td><button class="ui red button"  ng-click="deleteMember($index)">Eliminar</button></td>
                                </tr>
                            </tbody>
                        </table>
                        <button class="green ui button" ng-click="openAddUserMl()">Crear un miembro</button>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
            
            <!-- Buscar miembro -->
            <div id="findMemberMl" class="ui large modal" >
                <i class="close icon"></i>
                <div class="header">
                    Buscar membresía
                </div>
                <div class="image content">
                    <div class="description">
                        <h4 class="ui dividing header">Membresía del usuario</h4>
                        <div class="field">
                            <label>Miembro:</label>
                            <div class="ui form">
                                <div class="ui search" id="search_member_find">
                                    <div class="ui action left icon input">
                                        <i class="search icon"></i>
                                        <input class="prompt" type="text" placeholder="Buscar miembro..."
                                               ng-model="finder.search" required>
                                        <div class="ui grey button">Search</div>
                                    </div>
                                    <div class="results"></div>
                                </div>
                                <div class="field">
                                    <label>Nombre:</label>
                                    <input type="text" placeholder="Nombre" ng-model="finder.name" readonly>
                                </div>
                                <div class="field">
                                    <label>Dirección:</label>
                                    <input type="text" placeholder="Dirección" ng-model="finder.address" readonly>
                                </div>
                                <div class="two fields">
                                    <div class="field">
                                        <label>Código postal:</label>
                                        <input type="text"  placeholder="CP" ng-model="finder.cp" readonly>
                                    </div>
                                    <div class="field">
                                        <label>Ciudad</label>
                                        <input type="text" ng-model="finder.city" readonly>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>Estado</label>
                                    <input type="text" ng-model="finder.state" readonly>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
            
            <!-- Tabla de consultas -->
            <div id="listConsultationsMl" class="ui large modal" >
                <i class="close icon"></i>
                <div class="header">
                    Tabla de consultas
                </div>
                <div class="image content">
                    <div class="description">
                        <table class="ui teal table selectable celled right aligned tablet stackable table">
                            <thead>
                                <tr>
                                    <th>Proveedor</th>
                                    <th>Miembro</th>
                                    <th>Servicio</th>
                                    <th>Fecha creación</th>
                                    <th>Fecha consulta</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="consultation in consultations track by $index">
                                    <td>{{consultation.provider.name}}</td>
                                    <td>{{consultation.member.name}}</td>
                                    <td>{{consultation.service.name}}</td>
                                    <td>{{consultation.currentDate}}</td>
                                    <td>{{consultation.date}}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
            
        </div>
                                    
        <script src="lib/jquery/jquery-3.2.1.min.js"></script>
        <script src="lib/angular/angular.min.js"></script>
        <script src="lib/semantic/semantic.min.js"></script>
        <script src="lib/sweet/sweetalert2.min.js"></script>
        <script src="js/home.js"></script>
    </body>
</html>
