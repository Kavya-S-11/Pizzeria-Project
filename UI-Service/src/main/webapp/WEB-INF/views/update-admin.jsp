<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Admin Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Update Admin Profile</h2>
    
    <!-- Success / Error Messages -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Update Form -->
    <form action="${pageContext.request.contextPath}/ui/admins/profile/update?token=${jwtToken}" method="post">
        <!-- Hidden field to pass admin ID -->
        <input type="hidden" name="id" value="${admin.id}" />

        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" class="form-control" value="${admin.username}" disabled>
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" value="${admin.email}" disabled>
        </div>

        <div class="mb-3">
            <label class="form-label">Phone Number</label>
            <input type="text" class="form-control" name="phoneNumber" value="${admin.phoneNumber}" required>
        </div>

        <!-- Branch dropdown only if role = ADMIN -->
        <c:if test="${admin.adminRole == 'ADMIN'}">
            <div class="mb-3">
                <label class="form-label">Branch</label>
                <select class="form-control" name="branchId">
                    <c:forEach var="b" items="${branches}">
                        <option value="${b.id}" 
                            <c:if test="${admin.branchId != null && admin.branchId == b.id}">selected</c:if>>
                            ${b.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </c:if>

        <button type="submit" class="btn btn-primary">Update Profile</button>
    </form>

    <!-- Back link -->
    <a href="${pageContext.request.contextPath}/ui/admins/dashboard?token=${jwtToken}" class="btn btn-secondary mt-3">
        Back to Dashboard
    </a>
</div>
</body>
</html>
