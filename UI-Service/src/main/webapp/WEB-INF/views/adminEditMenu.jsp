<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Menu Item - Healthify</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container mt-5">
    <h2 class="mb-4">Edit Menu Item</h2>
    <form action="${pageContext.request.contextPath}/ui/admin/menu/edit/${menuItem.id}" method="post">
        <input type="hidden" name="id" value="${menuItem.id}">
       <!-- Name -->
        <div class="mb-3">
            <label for="name" class="form-label">Item Name</label>
            <input type="text" class="form-control" id="name" name="name" 
                   value="${menuItem.name}" required>
        </div>

        <!-- Description -->
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3" required>
                ${menuItem.description}
            </textarea>
        </div>

        <!-- Price -->
        <div class="mb-3">
            <label for="price" class="form-label">Price (₹)</label>
            <input type="number" class="form-control" id="price" name="price" 
                   value="${menuItem.price}" required step="0.01" min="0">
        </div>

        <!-- Image URL -->
        <div class="mb-3">
            <label for="imageUrl" class="form-label">Image URL</label>
            <input type="text" class="form-control" id="imageUrl" name="imageUrl" 
                   value="${menuItem.imageUrl}">
            <c:if test="${not empty menuItem.imageUrl}">
                <div class="mt-2">
                    <img src="${menuItem.imageUrl}" alt="Menu Image" width="120" height="120" class="img-thumbnail"/>
                </div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">Update Item</button>
        <a href="${pageContext.request.contextPath}ui/admin/menu" class="btn btn-secondary">Cancel</a>
    </form>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
