<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom SCSS-like styling */
        body {
            background-color: #f8f9fa;
        }

        .dashboard-container {
            margin-top: 60px;
            margin-bottom: 80px; /* space for sticky footer */
        }

        .welcome-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            padding: 30px;
            text-align: center;
        }

        .welcome-card h2 {
            color: #dc3545; /* red theme */
            margin-bottom: 20px;
        }

        .dashboard-buttons .btn {
            min-width: 150px;
            margin: 10px;
            font-weight: 600;
            transition: transform 0.2s ease;
        }

        .dashboard-buttons .btn:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body>

<!-- Header -->
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container dashboard-container">
    <div class="welcome-card">
        <h2>Welcome, ${user.name}!</h2>
        <p class="mb-4">Manage your orders and explore our menu</p>

        <div class="dashboard-buttons">
            <a href="${pageContext.request.contextPath}/ui/menus" class="btn btn-primary btn-lg">View Menu</a>
            <a href="${pageContext.request.contextPath}/ui/cart" class="btn btn-success btn-lg">View My Cart</a>
            <a href="${pageContext.request.contextPath}/ui/orders/my" class="btn btn-primary btn-lg">View My Orders</a>
            <a href="${pageContext.request.contextPath}/ui/users/logout" class="btn btn-danger btn-lg">Logout</a>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
