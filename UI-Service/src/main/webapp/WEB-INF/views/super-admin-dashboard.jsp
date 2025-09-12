<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Super Admin Dashboard - Pizza Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
    body {
        background-color: #f8f9fa;
        min-height: 100vh;
        font-family: 'Segoe UI', sans-serif;
    }

    /* Header Section */
    .dashboard-header {
        background: linear-gradient(90deg, #ff4d4d, #ff944d);
        color: #fff;
        padding: 20px;
        border-radius: 12px;
        margin-bottom: 30px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    }
    .dashboard-header h2 {
        font-weight: 600;
    }

    /* Cards */
    .card {
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.08);
        margin-bottom: 20px;
    }
    .card-header {
        background-color: #0d6efd;
        color: #fff;
        font-weight: 600;
        border-top-left-radius: 12px;
        border-top-right-radius: 12px;
    }

    /* Dashboard Actions */
    .dashboard-actions {
        margin-bottom: 25px;
    }
    .dashboard-actions .btn {
        margin: 6px;
        border-radius: 8px;
        transition: transform 0.2s ease, box-shadow 0.2s ease;
    }
    .dashboard-actions .btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 12px rgba(0,0,0,0.2);
    }

    /* Table */
    .table {
        border-radius: 12px;
        overflow: hidden;
    }
    .table thead {
        background-color: #343a40;
        color: #fff;
    }
    .table-striped tbody tr:hover {
        background-color: #f1f1f1;
    }

    /* Button Colors */
    .btn-primary {
        background-color: #ff4d4d;
        border: none;
    }
    .btn-primary:hover {
        background-color: #e64444;
    }
    .btn-info {
        background-color: #17a2b8;
        border: none;
    }
    .btn-info:hover {
        background-color: #138496;
    }
    .btn-success {
        background-color: #198754;
        border: none;
    }
    .btn-success:hover {
        background-color: #157347;
    }
    .btn-warning {
        background-color: #ffc107;
        border: none;
        color: #212529;
    }
    .btn-warning:hover {
        background-color: #e0a800;
    }
    .btn-danger {
        background-color: #dc3545;
        border: none;
    }
    .btn-danger:hover {
        background-color: #c82333;
    }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container mt-4">

    <!-- Header Section -->
    <div class="dashboard-header d-flex justify-content-between align-items-center">
        <h2>Welcome, Super Admin!</h2>
        <a href="${pageContext.request.contextPath}/ui/admins/logout?token=${token}" class="btn btn-light btn-sm fw-bold">Logout</a>
    </div>

    <!-- Dashboard Actions -->
    <div class="card p-3">
        <h5 class="mb-3">Quick Actions</h5>
        <div class="dashboard-actions">
            <a href="${pageContext.request.contextPath}/ui/admins/profile?token=${jwtToken}" 
               class="btn btn-success">View Profile</a>
            <a href="${pageContext.request.contextPath}/ui/admins/add-store-admin-page?token=${jwtToken}" 
               class="btn btn-success">Add New Store Admin</a>
            <a href="${pageContext.request.contextPath}/ui/admins/branches/manage?token=${jwtToken}" 
               class="btn btn-success">Manage Branches</a>
            <a href="${pageContext.request.contextPath}/ui/admin/menu" class="btn btn-success">Manage Menu Items</a>
            <a href="${pageContext.request.contextPath}/ui/admin/menu/add" class="btn btn-success">Add Menu Item</a>
            <a href="${pageContext.request.contextPath}/ui/users/admin/list" class="btn btn-success">List Users</a>
            <a href="${pageContext.request.contextPath}/ui/admin/orders/pending" class="btn btn-success">View Pending Orders</a>
        </div>
    </div>

    <!-- Admins Table -->
    <div class="card mt-4">
        <div class="card-header">
            All Admins
        </div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-striped table-bordered mb-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Branch ID</th>
                            <th>Branch Name</th>
                            <th>Branch Address</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="admin" items="${allAdmins}">
                            <tr <c:if test="${!admin.active}">class="table-secondary"</c:if>>
                                <td>${admin.id}</td>
                                <td>${admin.username}</td>
                                <td>${admin.email}</td>
                                <td>${admin.phoneNumber}</td>
                                <td>${admin.branchId != null ? admin.branchId : 'N/A'}</td>
                                <td>${admin.branchName != null ? admin.branchName : 'N/A'}</td>
                                <td>${admin.branchAddress != null ? admin.branchAddress : 'N/A'}</td>
                                <td>${admin.adminRole}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${admin.active}">
                                            <a href="${pageContext.request.contextPath}/ui/admins/update/${admin.id}?token=${jwtToken}" 
                                               class="btn btn-primary btn-sm">Edit</a>
                                            <form action="${pageContext.request.contextPath}/ui/admins/admin/${admin.id}/deactivate?token=${jwtToken}" method="post" style="display:inline;">
                                                <button type="submit" class="btn btn-warning btn-sm" onclick="return confirm('Deactivate this admin?');">Deactivate</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/ui/admins/admin/${admin.id}/delete?token=${jwtToken}" method="post" style="display:inline;">
                                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Delete this admin?');">Delete</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-secondary btn-sm" disabled>Inactive</button>
                                            <form action="${pageContext.request.contextPath}/ui/admins/admin/${admin.id}/activate?token=${jwtToken}" method="post" style="display:inline;">
                                                <button type="submit" class="btn btn-success btn-sm" onclick="return confirm('Activate this admin?');">Activate</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/ui/admins/admin/${admin.id}/delete?token=${jwtToken}" method="post" style="display:inline;">
                                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Delete this admin?');">Delete</button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
