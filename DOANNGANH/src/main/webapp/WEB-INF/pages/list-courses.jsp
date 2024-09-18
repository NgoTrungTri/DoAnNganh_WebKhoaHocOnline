<%-- 
    Document   : list-courses
    Created on : Sep 3, 2024, 1:12:17 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-success pt-0 mt-0 mb-3">DANH SÁCH KHÓA HỌC</h1>

<div class="float-start mb-3 mt-3">
    <form id="filterForm" action="<c:url value='/view-list-course' />" method="get" class="d-flex">
        <div class="me-3">
            <select name="category" id="category" class="form-select" style="width: 350px;">
                <option value="" ${param.category == null || param.category == '' ? 'selected' : ''}>Tất cả</option>
                <option value="Ngoại Ngữ" ${param.category == 'Ngoại Ngữ' ? 'selected' : ''}>Ngoại Ngữ</option>
                <option value="Tin Học" ${param.category == 'Tin Học' ? 'selected' : ''}>Tin Học</option>
            </select>
        </div>
    </form>
</div>

<table class="table table-striped mt-1">
    <tr>
        <th>ID</th>
        <th>Tên Khóa Học</th>
        <th>Thời Gian</th>
        <th>Giáo Viên Phụ Trách</th>
        <th>Giá Tiền</th>
        <th></th>
    </tr>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.id}</td>
            <td>${course.tenKhoaHoc}</td>
            <td> ${fn:substring(course.ngayBatDau, 0, 10)} -> ${fn:substring(course.ngayKetThuc, 0, 10)}</td>
            <td>GV.${course.idGVPhuTrach.ho} ${course.idGVPhuTrach.ten}</td>
            <td>${course.giaTien}</td>
            <td style="width: 300px">
                <a class="btn btn-info" href="<c:url value="/view-list-course/${course.id}" />">Cập nhật</a>
                 <a class="btn btn-info"  href="<c:url value='/view-schedule/${course.id}' />">Xem Lịch Học</a>
                <a class="btn btn-info"  href="<c:url value='/outline/${course.id}' />">Xem đề cương</a>
                <a class="btn btn-info mt-1" href="<c:url value="/list-ngayhocbu/${course.id}" />">Lịch Học Bù</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    document.getElementById('category').addEventListener('change', function () {
        if (this.value === '') {
            window.location.href = '<c:url value="/view-list-course" />';
        } else {
            document.getElementById('filterForm').submit();
        }
    });
</script>