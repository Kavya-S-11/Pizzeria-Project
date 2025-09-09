<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Store Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
    <h2>Add Store Admin</h2>

    <div class="card mt-4 mb-4">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/ui/admins/store-admin?token=${jwtToken}" method="post">
                <!-- Include the JWT token as a hidden input -->
                <input type="hidden" name="token" value="${jwtToken}" />

                <div class="mb-3">
                    <input type="text" name="username" class="form-control" placeholder="Username" required/>
                </div>
                <div class="mb-3">
                    <input type="email" name="email" class="form-control" placeholder="Email" required/>
                </div>
                <div class="mb-3">
                    <input type="password" name="password" class="form-control" placeholder="Password" required/>
                </div>
                <div class="mb-3">
                    <input type="text" name="phoneNumber" class="form-control" placeholder="Phone Number"/>
                </div>
                <div class="mb-3">
                    <select name="branchId" class="form-select" required>
                        <option value="">Select Branch</option>
                        <c:forEach var="branch" items="${branches}">
                            <option value="${branch.id}">${branch.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-success">Add Store Admin</button>
                <a href="${pageContext.request.contextPath}/ui/admins/dashboard?token=${jwtToken}" class="btn btn-secondary">Back</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
