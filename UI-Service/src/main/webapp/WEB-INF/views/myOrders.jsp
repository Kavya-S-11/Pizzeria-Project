<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>My Orders - Pizzeria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container mt-5">
    <h2 class="mb-4">My Orders</h2>
<a href="${pageContext.request.contextPath}/ui/users/dashboard" class="btn btn-secondary mb-3">Back to Dashboard</a>
    <c:if test="${empty orders}">
        <p>You haven’t placed any orders yet.</p>
    </c:if>

    <c:if test="${not empty orders}">
        <table class="table table-striped">
            <thead class="table-dark">
            <tr>
                <th>Order ID</th>
                <th>Date</th>
                <th>Total</th>
                <th>Delivery Mode</th>
                <th>Status</th>
                <th>Items</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="o" items="${orders}">
                <tr>
                    <td>${o.orderId}</td>
                    <td>${o.orderDate}</td>
                    <td>₹${o.totalAmount}</td>
                    <td>${o.deliveryMode}</td>
                    <td>
                        <span class="badge bg-info">${o.status}</span>
                    </td>
                    <td>
                        <ul>
                            <c:forEach var="item" items="${o.items}">
                                <li>${item.name} x ${item.quantity} (₹${item.subtotal})</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>
                        <!-- Cancel button for PENDING orders -->
                        <c:if test="${o.status == 'PENDING'}">
                            <form action="${pageContext.request.contextPath}/ui/orders/cancel/${o.orderId}" method="post">
                                <button type="submit" class="btn btn-danger btn-sm">Cancel</button>
                            </form>
                        </c:if>

                        <!-- Pay Now button for ACCEPTED orders -->
                        <c:if test="${o.status == 'ACCEPTED'}">
                            <form action="${pageContext.request.contextPath}/ui/orders/pay/${o.orderId}" method="post">
                                <button type="submit" class="btn btn-success btn-sm">Pay Now</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
