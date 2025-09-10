<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login - Pizza Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-header text-center">
                    <h4>Admin Login</h4>
                </div>
                <div class="card-body">
                    <!-- Change form action to UI-Service controller -->
                    <form method="post" action="${pageContext.request.contextPath}/ui/admins/login">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>

                        <!-- Display error message if login fails -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger mt-2">${error}</div>
                        </c:if>

                        <button type="submit" class="btn btn-primary w-100">Login</button>
                    </form>
                </div>
                <div class="card-footer text-center text-muted">
                    Pizza Store Admin Panel
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login - Pizza Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-header text-center">
                    <h4>Admin Login</h4>
                </div>
                <div class="card-body">
                    <!-- Change form action to UI-Service controller -->
                    <form method="post" action="${pageContext.request.contextPath}/ui/admins/login">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>

                        <!-- Display error message if login fails -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger mt-2">${error}</div>
                        </c:if>

                        <button type="submit" class="btn btn-primary w-100">Login</button>
                    </form>
                </div>
                <div class="card-footer text-center text-muted">
                    Pizza Store Admin Panel
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
>>>>>>> 201dda49cb3b15489c6d73ef904cb5fdb7fcbd72
