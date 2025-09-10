<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>My Cart</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${cart}">
            <tr>
                <td>${item.menuItem.name}</td>
                <td>$${item.menuItem.price}</td>
                <td>${item.quantity}</td>
                <td>$${item.total}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/cart/remove/${item.menuItem.id}" class="btn btn-danger btn-sm">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="mt-3">
        <c:set var="grandTotal" value="0"/>
        <c:forEach var="item" items="${cart}">
            <c:set var="grandTotal" value="${grandTotal + item.total}"/>
        </c:forEach>
        <h4>Grand Total: $${grandTotal}</h4>
        <a href="/order/checkout" class="btn btn-success">Proceed to Checkout</a>
    </div>
</div>
</body>
</html>
