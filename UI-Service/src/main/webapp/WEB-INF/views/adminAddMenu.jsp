<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Menu Item - Healthify</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container mt-5">
    <h2 class="mb-4">Add New Menu Item</h2>
    <form action="${pageContext.request.contextPath}/ui/admin/menu/add" method="post">
        <div class="mb-3">
    <label for="name" class="form-label">Item Name</label>
    <input type="text" class="form-control" id="name" name="name" placeholder="Enter item name" required>
</div>

<div class="mb-3">
    <label for="description" class="form-label">Description</label>
    <textarea class="form-control" id="description" name="description" placeholder="Enter description" required></textarea>
</div>

<div class="mb-3">
    <label for="category" class="form-label">Category</label>
    <select class="form-select" id="category" name="category" required>
        <option value="" selected disabled>Select category</option>
        <option value="Pizzas">Pizzas</option>
        <option value="Beverages">Beverages</option>
        <option value="Breads">Breads</option>
        <option value="Desserts">Desserts</option>
    </select>
</div>


<div class="mb-3">
    <label for="price" class="form-label">Price</label>
    <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Enter price" required>
</div>

<div class="mb-3">
    <label for="imageUrl" class="form-label">Image URL</label>
    <input type="text" class="form-control" id="imageUrl" name="imageUrl" placeholder="Enter image URL">
</div>

        <button type="submit" class="btn btn-success">Add Item</button>
        <a href="${pageContext.request.contextPath}/ui/admin/menu" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
