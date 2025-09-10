<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Store Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Welcome, ${admin.username}!</h2>
        <a href="${pageContext.request.contextPath}/ui/admins/logout?token=${token}" 
           class="btn btn-danger">
           Logout
        </a>
    </div>
    
    <!-- View Profile -->
<a href="${pageContext.request.contextPath}/ui/admins/profile?token=${jwtToken}" 
   class="btn btn-info btn-sm ms-2">View Profile</a>

    <!-- Profile Details -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Your Profile</h5>
        </div>
        <div class="card-body">
            <p><strong>Username:</strong> ${admin.username}</p>
            <p><strong>Email:</strong> ${admin.email}</p>
            <p><strong>Phone:</strong> ${admin.phoneNumber}</p>
            <p><strong>Branch Id:</strong> ${admin.branchId != null ? admin.branchId : 'N/A'}</p>
            <p><strong>Branch Name:</strong> ${admin.branchName != null ? admin.branchName : 'N/A'}</p>
            <p><strong>Role:</strong> ${admin.adminRole}</p>
            <a href="${pageContext.request.contextPath}/ui/admins/update/${admin.id}?token=${jwtToken}" 
               class="btn btn-primary">Edit Profile</a>
        </div>
    </div>
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
    <title>Store Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Welcome, ${admin.username}!</h2>
        <a href="${pageContext.request.contextPath}/ui/admins/logout?token=${token}" 
           class="btn btn-danger">
           Logout
        </a>
    </div>

    <!-- Profile Details -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Your Profile</h5>
        </div>
        <div class="card-body">
            <p><strong>Username:</strong> ${admin.username}</p>
            <p><strong>Email:</strong> ${admin.email}</p>
            <p><strong>Phone:</strong> ${admin.phoneNumber}</p>
            <p><strong>Branch:</strong> ${admin.branch != null ? admin.branch.name : 'N/A'}</p>
            <p><strong>Role:</strong> ${admin.adminRole}</p>
            <a href="${pageContext.request.contextPath}/ui/admin/update/${admin.id}?token=${token}" 
               class="btn btn-primary">Edit Profile</a>
        </div>
    </div>
</div>
</body>
</html>
>>>>>>> 201dda49cb3b15489c6d73ef904cb5fdb7fcbd72
