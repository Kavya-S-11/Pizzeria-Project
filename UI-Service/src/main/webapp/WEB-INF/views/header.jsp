<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String currentPath = request.getRequestURI();
%>

<style>
    .navbar-custom {
        background-color: #dc3545; 
    }
</style>

<nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/">
            <img src="https://static.vecteezy.com/system/resources/previews/011/157/905/original/pizzeria-emblem-on-blackboard-pizza-logo-template-emblem-for-cafe-restaurant-or-food-delivery-service-free-vector.jpg" alt="Pizzeria Logo" height="50" class="me-2">
            <span class="fw-bold">Pizzeria</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link ${currentPath.contains('/') ? 'active' : ''}" href="${pageContext.request.contextPath}/">Home</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
