<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login - Pizzeria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #ffe5e5; /* light minimal red tint */
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .login-card {
            max-width: 400px;
            margin: 100px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }
        .login-card h4 {
            text-align: center;
            font-weight: bold;
            margin-bottom: 25px;
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
        .alert {
            margin-top: 10px;
            font-size: 0.9rem;
        }
        .card-footer {
            text-align: center;
            font-size: 0.9rem;
            color: #777;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
    <div class="login-card card">
        <div class="card-body">
            <h4>Admin Login</h4>
            <form method="post" action="${pageContext.request.contextPath}/ui/admins/login">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <button type="submit" class="btn btn-primary mt-3">Login</button>
            </form>
        </div>
        <div class="card-footer">
            Pizzeria Admin Panel
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
