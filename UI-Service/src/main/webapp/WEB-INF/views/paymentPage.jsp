<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Payment</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <style>
        body {
            background: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .navbar {
            background-color: #343a40;
        }
        .navbar-brand, .nav-link {
            color: #fff !important;
        }
        .payment-card {
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 12px;
            background: #fff;
            box-shadow: 0px 4px 12px rgba(0,0,0,0.1);
        }
        .payment-card h2 {
            color: #343a40;
            margin-bottom: 20px;
        }
        .btn-success {
            width: 100%;
            font-size: 18px;
            padding: 12px;
            border-radius: 8px;
        }
        .form-select {
            border-radius: 8px;
        }
    </style>
</head>
<body>

<!-- Header/Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/ui/home">Pizza Store</a>
        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ui/menu">Menu</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ui/orders">My Orders</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Payment Form -->
<div class="payment-card">
    <h2 class="text-center">Payment for Order #${order.orderId}</h2>
    <p class="text-center fs-5 mb-4">Total Amount: <strong>₹${order.totalAmount}</strong></p>

    <form action="${pageContext.request.contextPath}/ui/orders/confirmPayment/${order.orderId}" method="post">
        <div class="mb-3">
            <label class="form-label">Select Payment Mode</label>
            <select class="form-select" name="paymentMode" required>
                <option value="CARD">💳 Credit/Debit Card</option>
                <option value="UPI">📱 UPI</option>
                <option value="COD">💵 Cash on Delivery</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Confirm Payment</button>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
