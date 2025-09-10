<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
    <div class="row">
        <c:forEach var="item" items="${menuList}">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <img src="${item.imageUrl}" class="card-img-top" alt="${item.name}">
                    <div class="card-body">
                        <h5>${item.name}</h5>
                        <p>${item.description}</p>
                        <p>Price: $${item.price}</p>
                        <form action="${pageContext.request.contextPath}/ui/cart/add" method="post">
						    <input type="hidden" name="itemId" value="${item.id}">
						    <input type="number" name="quantity" value="1" min="1">
						    <button type="submit" class="btn btn-primary">Add to Cart</button>
						</form>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
tml>