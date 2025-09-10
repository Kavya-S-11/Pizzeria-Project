<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Super Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Welcome, Super Admin!</h2>
        <a href="${pageContext.request.contextPath}/ui/admins/logout?token=${token}" class="btn btn-danger">Logout</a>
    </div>

    <!-- Buttons -->
    <div class="mt-4 mb-4">
<!-- Add New Store Admin -->
<a href="${pageContext.request.contextPath}/ui/admins/add-store-admin-page?token=${jwtToken}" 
   class="btn btn-success">Add New Store Admin</a>

<!-- View Profile -->
<a href="${pageContext.request.contextPath}/ui/admins/profile?token=${jwtToken}" 
   class="btn btn-info btn-sm ms-2">View Profile</a>

<!-- Add / Manage Branches -->
<a href="${pageContext.request.contextPath}/ui/admins/branches/manage?token=${jwtToken}" 
   class="btn btn-info ms-2">Add / Manage Branches</a>

<h5 class="mt-4">Users Management</h5>
    <a href="${pageContext.request.contextPath}/ui/users/admin/list" class="btn btn-primary ms-2">List Users</a>
    </div>

    <!-- List All Admins -->
    <div class="card">
        <div class="card-header">
            <h5>All Admins</h5>
        </div>
        <div class="card-body">
            <table class="table table-bordered table-striped">
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
				        <a href="${pageContext.request.contextPath}/ui/admins/update/${admin.id}?token=${jwtToken}" class="btn btn-primary btn-sm">Edit</a>
				
				        <form action="${pageContext.request.contextPath}/ui/admins/admin/${admin.id}/deactivate?token=${jwtToken}" method="post" style="display:inline;">
				            <input type="hidden" name="token" value="${jwtToken}" />
				            <button type="submit" class="btn btn-warning btn-sm"
				                    onclick="return confirm('Are you sure you want to deactivate this admin?');">
				                Deactivate
				            </button>
				        </form>
				
				        <form action="${pageContext.request.contextPath}/ui/admins/admin/${admin.id}/delete?token=${jwtToken}" method="post" style="display:inline;">
				            <input type="hidden" name="token" value="${jwtToken}" />
				            <button type="submit" class="btn btn-danger btn-sm"
				                    onclick="return confirm('Are you sure you want to delete this admin?');">
				                Delete
				            </button>
				        </form>
				    </c:when>
				
				    <c:otherwise>
				        <button class="btn btn-secondary btn-sm" disabled>Inactive</button>
				
				        <form action="${pageContext.request.contextPath}/ui/admins/admin/${admin.id}/activate?token=${jwtToken}" method="post" style="display:inline;">
				            <input type="hidden" name="token" value="${jwtToken}" />
				            <button type="submit" class="btn btn-success btn-sm"
				                    onclick="return confirm('Are you sure you want to activate this admin?');">
				                Activate
				            </button>
				        </form>
				
				        <form action="${pageContext.request.contextPath}/ui/admins/admin/${admin.id}/delete?token=${jwtToken}" method="post" style="display:inline;">
				            <input type="hidden" name="token" value="${jwtToken}" />
				            <button type="submit" class="btn btn-danger btn-sm"
				                    onclick="return confirm('Are you sure you want to delete this admin?');">
				                Delete
				            </button>
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
</body>
</html>
