<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Welcome, ${user.name}!</h2>
    <p>Email: ${user.email}</p>
    
    
    <a href="${pageContext.request.contextPath}/ui/users/logout" class="btn btn-danger">Logout</a>
</div>
</body>
</html>
