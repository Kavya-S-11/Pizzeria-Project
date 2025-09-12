<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .dashboard-container {
            margin-top: 40px;
            margin-bottom: 80px;
            text-align: center;
        }

        .welcome-card {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            padding: 40px 20px;
            margin-bottom: 40px;
        }

        .welcome-card h2 {
            color: #dc3545; /* red theme */
            margin-bottom: 10px;
        }

        .dashboard-actions {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .action-card {
            background: #dcdcdc;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            padding: 25px 30px;
            width: 220px;
            transition: transform 0.2s, box-shadow 0.2s;
            cursor: pointer;
            text-decoration: none;
            color: inherit;
        }

        .action-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
        }

        .action-card h4 {
            margin-top: 15px;
            font-weight: 600;
        }

        .action-card i {
            font-size: 40px;
            color: #dc3545;
        }

        /* Adjust header logout button if needed */
        .header-logout {
            margin-left: auto;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<!-- Existing Header -->
<jsp:include page="/WEB-INF/views/header.jsp"/>

<!-- Add Logout button inside header (if header allows extra content) -->
<div class="d-flex justify-content-end me-3 mt-2">
    <a href="${pageContext.request.contextPath}/ui/users/logout" class="btn btn-danger btn-sm">
        Logout <i class="fas fa-sign-out-alt"></i>
    </a>
</div>

<!-- Main Content -->
<div class="container dashboard-container">
    <div class="welcome-card">
        <h2>Welcome, ${user.name}!</h2>
        <p class="mb-4">Manage your orders and explore our menu</p>

        <div class="dashboard-actions">
            <a href="${pageContext.request.contextPath}/ui/menus" class="action-card">
                <i class="fas fa-utensils"></i>
                <h4>View Menu</h4>
            </a>

            <a href="${pageContext.request.contextPath}/ui/cart" class="action-card">
                <i class="fas fa-shopping-cart"></i>
                <h4>My Cart</h4>
            </a>

            <a href="${pageContext.request.contextPath}/ui/orders/my" class="action-card">
                <i class="fas fa-box"></i>
                <h4>My Orders</h4>
            </a>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
