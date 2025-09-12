<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Pending Orders | Pizzeria</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container { max-width: 1100px; }
        .back-btn { margin-bottom: 20px; }
        .table th, .table td { vertical-align: middle; }
        .form-control-sm { max-width: 200px; }
        .badge-status { font-size: 0.85rem; }
    </style>
</head>
<body>

<!-- Header -->
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Pending Orders</h2>
       <form action="${pageContext.request.contextPath}/ui/admins/dashboard" method="get">
    <button type="submit" class="btn btn-secondary btn-sm back-btn">Back to Dashboard</button>
</form>

    </div>

    <c:if test="${empty orders}">
        <p class="text-muted">No pending orders at the moment.</p>
    </c:if>

    <c:if test="${not empty orders}">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Total Amount</th>
                    <th>Items</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <td>${o.orderId}</td>
                        <td>${o.userId}</td>
                        <td>₹${o.totalAmount}</td>
                        <td>
                            <ul class="mb-0">
                                <c:forEach var="item" items="${o.items}">
                                    <li>${item.name} x ${item.quantity}</li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td>
                            <span class="badge ${o.status == 'PENDING' ? 'bg-warning' : (o.status == 'ACCEPTED' ? 'bg-success' : 'bg-danger')} badge-status">
                                ${o.status}
                            </span>
                        </td>
                        <td>
                            <c:if test="${o.status == 'PENDING'}">
                                <form action="${pageContext.request.contextPath}/ui/admin/orders/status/${o.orderId}" method="post" class="d-flex flex-column gap-1">
                                    <input type="text" name="message" placeholder="Admin note (optional)" class="form-control form-control-sm">
                                    <div class="d-flex gap-2 mt-1">
                                        <button type="submit" name="status" value="ACCEPTED" class="btn btn-success btn-sm flex-fill">Accept</button>
                                        <button type="submit" name="status" value="REJECTED" class="btn btn-danger btn-sm flex-fill">Reject</button>
                                    </div>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.querySelectorAll('form').forEach(form => {
    form.addEventListener('submit', function(e) {
        e.preventDefault();

        // get which button was clicked
        const clickedButton = e.submitter; 
        const status = clickedButton.value; 
        const message = form.querySelector('input[name="message"]').value;

        fetch(form.action, {
            method: 'POST', // still hits AdminService controller
            body: new URLSearchParams({ status, message })
        })
        .then(response => {
            if (!response.ok) throw new Error('Failed to update order status');
            return response.text();
        })
        .then(() => {
            alert('Order status updated successfully!');
            location.reload();
        })
        .catch(err => alert(err.message));
    });
});


</script>

</body>
</html>
