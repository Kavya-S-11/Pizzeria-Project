<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-5">

    <div class="alert alert-danger">
        <h4>⚠️ Error</h4>
        <p>${errorMessage}</p>
    </div>

    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/ui/admins/login" class="btn btn-primary">Go to Login</a>
    </div>
</body>
</html>
