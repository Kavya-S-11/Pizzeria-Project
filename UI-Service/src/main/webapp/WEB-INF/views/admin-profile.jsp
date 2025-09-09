<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #74ebd5, #ACB6E5);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .profile-card {
            max-width: 650px;
            margin: auto;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }
        .card-header { border-bottom: 0; }
        input.form-control { border-radius: 10px; padding: 10px; }
        button, a.btn { border-radius: 10px; padding: 10px 20px; }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="card profile-card">
        <div class="card-header text-center bg-primary text-white">
            <h2>Admin Profile</h2>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/ui/admins/profile/update?token=${jwtToken}" method="post">
                <input type="hidden" name="id" value="${admin.id}"/>

                <div class="mb-3">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" class="form-control" value="${admin.username}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" value="${admin.email}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Phone Number</label>
                    <input type="text" name="phoneNumber" class="form-control" value="${admin.phoneNumber}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Branch</label>
                    <input type="text" class="form-control" value="${branchName}" disabled>
                </div>

                <div class="mb-3">
                    <label class="form-label">Role</label>
                    <input type="text" class="form-control" value="${admin.adminRole}" disabled>
                </div>

                <div class="mb-3">
                    <label class="form-label">New Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Enter new password if changing">
                </div>

                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-success">Update Profile</button>
                    <a href="${pageContext.request.contextPath}/ui/admins/dashboard?token=${jwtToken}" class="btn btn-secondary">Back to Dashboard</a>
                </div>
            </form>
        </div>
        <div class="card-footer text-center">
            <c:if test="${not empty success}">
                <p class="text-success fw-bold">${success}</p>
            </c:if>
            <c:if test="${not empty error}">
                <p class="text-danger fw-bold">${error}</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
