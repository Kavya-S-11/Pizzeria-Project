<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Menu - Healthify</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container mt-5">

    <h2 class="mb-4">Our Menu</h2>
    <a href="${pageContext.request.contextPath}/ui/users/dashboard" class="btn btn-secondary mb-3">Back to Dashboard</a>

    <div class="row">
        <c:forEach var="item" items="${menuList}">
            <div class="col-md-4 mb-4">
                <div class="card h-60">
                    <c:if test="${not empty item.imageUrl}">
                        <img src="${item.imageUrl}" class="card-img-top img-fluid" alt="${item.name}" style="height:200px; object-fit:cover;">
                    </c:if>
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${item.name}</h5>
                        <p class="card-text">${item.description}</p>
                        <p class="card-text"><strong>Price: ₹${item.price}</strong></p>

                        <form action="${pageContext.request.contextPath}/ui/cart/add" method="post" class="mt-auto">
                            <input type="hidden" name="itemId" value="${item.id}">
                            <div class="mb-2">
                                <label for="quantity-${item.id}" class="form-label">Quantity:</label>
                                <input type="number" id="quantity-${item.id}" name="quantity" value="1" min="1" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Add to Cart</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>
