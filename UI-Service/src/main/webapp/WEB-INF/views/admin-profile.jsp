<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: linear-gradient(135deg, #74ebd5, #ACB6E5); font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        .profile-card { max-width: 720px; margin: auto; border-radius: 12px; overflow: hidden; box-shadow: 0 10px 25px rgba(0,0,0,0.15); }
        .card-header { border-bottom: 0; }
        input.form-control, select.form-control { border-radius: 10px; padding: 10px; }
        button, a.btn { border-radius: 10px; padding: 8px 18px; }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container mt-5">
    <div class="card profile-card">
        <div class="card-header text-center bg-primary text-white">
            <h2>Admin Profile</h2>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/ui/admins/profile/update?token=${jwtToken}" method="post">
                <input type="hidden" name="id" value="${admin.id}">


                <div class="mb-3">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" class="form-control" value="${admin.username}" disabled>
                </div>

				<div class="mb-3">
				    <label class="form-label">Email</label>
				    <input type="email" name="email" class="form-control" value="${admin.email}" disabled>
				</div>
				
				<div class="mb-3">
				    <label class="form-label">Branch</label>
				    <select name="branchId" class="form-control" disabled>
				        <c:forEach var="b" items="${branches}">
				            <option value="${b.id}" <c:if test="${b.id == admin.branchId}">selected</c:if>>${b.name}</option>
				        </c:forEach>
				    </select>
				</div>
				

                <div class="mb-3">
                    <label class="form-label">Phone Number</label>
                    <input type="text" name="phoneNumber" class="form-control" value="${admin.phoneNumber}" disabled>
                </div>

                <div class="mb-3">
                    <label class="form-label">Role</label>
                    <input type="text" class="form-control" value="${roleName}" disabled>
                </div>

                <div class="mb-3">
                    <label class="form-label">New Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Enter new password if changing" disabled>
                </div>

                <div class="d-flex justify-content-between">
                    <button type="button" class="btn btn-warning" id="editBtn" onclick="enableEditing()">Edit Profile</button>
                    <button type="submit" class="btn btn-success d-none" id="updateBtn">Update Profile</button>
                    <a href="${pageContext.request.contextPath}/ui/admins/dashboard?token=${jwtToken}" class="btn btn-secondary">Back to Dashboard</a>
                </div>
            </form>
        </div>

        <div class="card-footer text-center">
            <c:if test="${not empty success}"><p class="text-success fw-bold">${success}</p></c:if>
            <c:if test="${not empty error}"><p class="text-danger fw-bold">${error}</p></c:if>
        </div>
    </div>
</div>

<script>
function enableEditing() {
    // enable inputs that are not role or id
    document.querySelectorAll('input.form-control, select.form-control').forEach(function(el) {
        // keep role field disabled
        if (el.getAttribute('name') === null || el.getAttribute('name') === 'id') return;
        el.removeAttribute('disabled');
    });

    document.getElementById('editBtn').classList.add('d-none');
    document.getElementById('updateBtn').classList.remove('d-none');
}
</script>

</body>

</html>
