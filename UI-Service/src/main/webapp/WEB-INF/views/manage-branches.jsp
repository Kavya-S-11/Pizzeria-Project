<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Branches</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
    <h2>Manage Branches</h2>

    <!-- Add New / Edit Branch -->
    <div class="card mt-4 mb-4">
        <div class="card-header">
            <h5>Add New / Edit Branch</h5>
        </div>
        <div class="card-body">
		        <c:choose>
		    <c:when test="${branchToEdit != null and branchToEdit.id != null}">
		        <c:set var="formAction" value="${pageContext.request.contextPath}/ui/admins/branches/update?token=${token}" />
		    </c:when>
		    <c:otherwise>
		        <c:set var="formAction" value="${pageContext.request.contextPath}/ui/admins/branches/add?token=${token}" />
		    </c:otherwise>
		</c:choose>
		        
          <form action="${formAction}" method="post">

                <input type="hidden" name="id" value="${branchToEdit.id}"/>
                
                <div class="mb-3">
                    <input type="text" name="name" class="form-control" placeholder="Branch Name" 
                           value="${branchToEdit.name}" required/>
                </div>
                <div class="mb-3">
                    <input type="text" name="address" class="form-control" placeholder="Address" 
                           value="${branchToEdit.address}"/>
                </div>
                <div class="mb-3">
                    <input type="text" name="city" class="form-control" placeholder="City" 
                           value="${branchToEdit.city}"/>
                </div>
                <div class="mb-3">
                    <input type="text" name="state" class="form-control" placeholder="State" 
                           value="${branchToEdit.state}"/>
                </div>
                <div class="mb-3">
                    <input type="text" name="phoneNumber" class="form-control" placeholder="Phone Number" 
                           value="${branchToEdit.phoneNumber}"/>
                </div>
                
                <button type="submit" class="btn btn-success">
                    <c:choose>
                        <c:when test="${branchToEdit.id != null}">Update Branch</c:when>
                        <c:otherwise>Add Branch</c:otherwise>
                    </c:choose>
                </button>
            </form>
        </div>
    </div>

    <!-- List All Branches -->
    <div class="card">
        <div class="card-header">
            <h5>All Branches</h5>
        </div>
        <div class="card-body">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Phone</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="branch" items="${branches}">
                        <tr>
                            <td>${branch.id}</td>
                            <td>${branch.name}</td>
                            <td>${branch.address}</td>
                            <td>${branch.city}</td>
                            <td>${branch.state}</td>
                            <td>${branch.phoneNumber}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/ui/admins/branches/edit/${branch.id}?token=${token}" 
                                   class="btn btn-primary btn-sm">Edit</a>

                                <c:choose>
                                    <c:when test="${branchAdminMap[branch.id]}">
                                        <span title="Cannot deactivate: Admin(s) assigned to this branch" 
                                              data-bs-toggle="tooltip" data-bs-placement="top">
                                            <button class="btn btn-warning btn-sm" disabled>Deactivate</button>
                                        </span>
                                        <span title="Cannot delete: Admin(s) assigned to this branch" 
                                              data-bs-toggle="tooltip" data-bs-placement="top">
                                            <button class="btn btn-danger btn-sm" disabled>Delete</button>
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/ui/admins/branches/delete/${branch.id}?token=${token}" 
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Are you sure you want to delete this branch?');">Delete</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/ui/admins/dashboard?token=${jwtToken}" class="btn btn-secondary mt-4">Back to Dashboard</a>
</div>
</body>
</html>
