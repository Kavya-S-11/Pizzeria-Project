<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order Placed - Pizzeria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container mt-5 text-center">
    <h2 class="text-success mb-4">🎉 Order Placed Successfully!</h2>

    <c:if test="${not empty order}">
        <p><strong>Order ID:</strong> ${order.orderId}</p>
        <p><strong>Total Amount:</strong> ₹${order.totalAmount}</p>
        <p><strong>Delivery Mode:</strong> ${order.deliveryMode}</p>
        <p><strong>Status:</strong> ${order.status}</p>
    </c:if>

    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/ui/orders/my" class="btn btn-primary">View My Orders</a>
        <a href="${pageContext.request.contextPath}/ui/menus" class="btn btn-secondary">Continue Shopping</a>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
