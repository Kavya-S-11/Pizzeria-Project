<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>My Cart - Pizzeria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container mt-5">

    <h2 class="mb-4">My Cart</h2>
    <a href="${pageContext.request.contextPath}/ui/menus" class="btn btn-secondary mb-3">Back to Menu</a>

    <c:if test="${cart.items == null || cart.items.isEmpty()}">
        <p>Your cart is empty.</p>
    </c:if>

    <c:if test="${not empty cart.items}">
        <table class="table table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Item</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Subtotal</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>${item.name}</td>
                    <td>₹${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>₹${item.subtotal}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/ui/cart/remove" method="post">
                            <input type="hidden" name="itemId" value="${item.itemId}">
                            <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <h4>Total: ₹${cart.totalAmount}</h4>

        <div class="mt-3">
            <form action="${pageContext.request.contextPath}/ui/cart/clear" method="post">
                <button type="submit" class="btn btn-warning">Clear Cart</button>
            </form>
            <form action="${pageContext.request.contextPath}/ui/orders/place" method="post">
			    <input type="hidden" name="userId" value="${user.userId}">
			    <button type="submit" class="btn btn-success">Proceed to Checkout</button>
			</form>
        </div>
    </c:if>

</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
