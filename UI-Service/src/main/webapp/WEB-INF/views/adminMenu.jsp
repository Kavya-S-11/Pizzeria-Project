<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Menu - Healthify</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container mt-5">
    <h2 class="mb-4">Menu Items</h2>
    <a href="${pageContext.request.contextPath}/ui/admin/menu/add" class="btn btn-success mb-3">Add New Item</a>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Image</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${menuList}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.category}</td>
                <td>₹ ${item.price}</td>
                <td>
                    <c:if test="${not empty item.imageUrl}">
                        <img src="${item.imageUrl}" alt="Menu Image" width="80" height="80" class="img-thumbnail"/>
                    </c:if>
                    <c:if test="${empty item.imageUrl}">
                        <span class="text-muted">No Image</span>
                    </c:if>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/ui/admin/menu/edit/${item.id}" 
                       class="btn btn-primary btn-sm">Edit</a>
                    <form action="${pageContext.request.contextPath}/ui/admin/menu/delete/${item.id}" 
      method="post" style="display:inline;">
    <button type="submit" class="btn btn-danger btn-sm"
            onclick="return confirm('Are you sure you want to delete this item?');">
        Delete
    </button>
</form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="javascript:history.back()" class="btn btn-secondary">Back</a>
    
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
