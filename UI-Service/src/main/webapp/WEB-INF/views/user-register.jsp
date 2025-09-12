<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .registration-card {
            max-width: 450px;
            margin: auto;
            margin-top: 80px;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }
        .registration-card h2 {
            font-weight: bold;
            margin-bottom: 25px;
            text-align: center;
            color: #333333;
        }
        .form-control:focus {
            border-color: #ff4d4d;
            box-shadow: 0 0 5px rgba(255, 77, 77, 0.3);
        }
        .btn-primary {
            width: 100%;
            background-color: #ff4d4d;
            border: none;
        }
        .btn-primary:hover {
            background-color: #e64444;
        }
        .login-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #ff4d4d;
            text-decoration: none;
        }
        .login-link:hover {
            text-decoration: underline;
        }
        .alert {
            margin-top: 15px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
    <div class="registration-card">
        <h2>User Registration</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/ui/users/register" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" name="name" class="form-control" value="${user.name}" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" name="email" class="form-control" value="${user.email}" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" name="password" class="form-control" value="${user.password}" required>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
            <a href="${pageContext.request.contextPath}/ui/users/login" class="login-link">Already have an account? Login</a>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
