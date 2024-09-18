<%-- 
    Document   : users
    Created on : May 22, 2024, 3:10:37 PM
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<h1 class="text-center text-success pt-0 mt-0 mb-3">QUẢN TRỊ NGƯỜI DÙNG</h1>

<div class="float-start mb-3 mt-3">
    <form id="filterForm" action="<c:url value='/' />" method="get" class="d-flex">
        <div class="me-3">
            <select name="userRole" id="userRole" class="form-select" style="width: 350px;">
                 <option value="" ${param.userRole == null || param.userRole == '' ? 'selected' : ''}>Tất cả</option>
    <option value="ROLE_NV" ${param.userRole == 'ROLE_NV' ? 'selected' : ''}>Nhân Viên</option>
    <option value="ROLE_GV" ${param.userRole == 'ROLE_GV' ? 'selected' : ''}>Giáo Viên</option>
    <option value="ROLE_HV" ${param.userRole == 'ROLE_HV' ? 'selected' : ''}>Học Viên</option>       
            </select>
        </div>
    </form>
</div>

<div class="float-end mb-3">
    <a class="btn btn-success mt-3" href="<c:url value='/add-user' />">Thêm tài khoản</a>
</div>


<table class="table table-striped mt-1">
    <tr>
        <th>ID</th>
        <th>Avatar</th>
        <th>Username</th>
        <th>Email</th>
        <th>Họ tên</th>
        <th>Ngày sinh</th>
        <th></th>
    </tr>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.id}</td>
            <td> <img class="card-img-top" src="${u.avatar}" alt="${u.username}" style="width:50px;"></td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <td>${u.ho} ${u.ten} </td>
            <td>${u.ngaySinh}</td> 
            <td>
                <a class="btn btn-info" href="<c:url value="/${u.id}" />">Cập nhật</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    document.getElementById('userRole').addEventListener('change', function() {
        var selectedUserRole = document.getElementById('userRole').value;
        document.getElementById('filterForm').submit();
        
        if (selectedUserRole === "" ) {
            event.preventDefault();
            window.location.href = '<c:url value="/" />';   
        }
    });
    
</script>