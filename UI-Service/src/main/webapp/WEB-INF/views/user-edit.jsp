<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Edit User</h2>

    <form action="${pageContext.request.contextPath}/ui/users/admin/update/${user.id}" method="post">

        
        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" name="name" class="form-control" value="${user.name}" required>
        </div>
        
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" name="email" class="form-control" value="${user.email}" required>
        </div>
        
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" name="password" class="form-control" value="${user.password}" required>
        </div>
        
        <div class="mb-3">
            <label for="phone" class="form-label">Phone:</label>
            <input type="tel" name="phone" class="form-control" value="${user.phone}" pattern="[0-9]{10}" title="Please enter a 10-digit number"  >
        </div>
        
        <div class="mb-3">
            <label for="address" class="form-label">Address:</label>
            <textarea name="address" class="form-control">${user.address}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">Update User</button>
        <a href="${pageContext.request.contextPath}/ui/users/admin/list" class="btn btn-secondary">Back</a>
    </form>
</div>
</body>
</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2>Edit User</h2>
    <form action="${pageContext.request.contextPath}/ui/users/update/${user.id}" method="post">
        <input type="hidden" name="_method" value="patch"/>
        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" name="name" class="form-control" value="${user.name}" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" name="email" class="form-control" value="${user.email}" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" name="password" class="form-control" value="${user.password}" required>
        </div>
        <button type="submit" class="btn btn-primary">Update User</button>
        <a href="${pageContext.request.contextPath}/ui/users/all" class="btn btn-secondary">Back</a>
    </form>
</div>
</body>
</html>
    
>>>>>>> 201dda49cb3b15489c6d73ef904cb5fdb7fcbd72
