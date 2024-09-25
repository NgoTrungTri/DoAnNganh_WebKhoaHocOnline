<%-- 
    Document   : xuatbangluongpdf
    Created on : Sep 20, 2024, 3:57:10 PM
    Author     : DELL
--%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    LocalDate today = LocalDate.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String currentMonth = today.format(DateTimeFormatter.ofPattern("MM-yyyy"));

    request.setAttribute("currentMonth", currentMonth);
%>


<h1 class="text-center text-success pt-0 mt-0 mb-4">Xem Thông Tin Bảng Lương</h1>

<!-- Combo box form for teacher selection -->
<form id="salaryForm" method="get" action="<c:url value='/bang-luong/thongtin' />" class="mb-4">
    <div class="mb-3">
        <label for="teacher" class="form-label">Chọn Giáo Viên:</label>
        <select name="teacherId" id="teacher" class="form-select">
            <c:forEach var="teacher" items="${teachers}">
                <option value="${teacher.id}">GV. ${teacher.ho} ${teacher.ten}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Xem Bảng Lương</button>
</form>

<!-- Display teacher information if available -->
<c:if test="${not empty teacher}">
    <div class="mt-4">
        <h2 class="mb-4">Thông Tin Giáo Viên</h2>
        <p><strong>Tên Giáo Viên: </strong>GV. ${teacher.ho} ${teacher.ten}</p>
        <p><strong>Email Giáo Viên: </strong> ${teacher.email}</p>

        <h2 class="mt-4 text-center mb-4">Thông Tin Bảng Lương Giáo Viên Tháng ${currentMonth}</h2>

        <!-- Salary Information Table -->
        <table class="table table-bordered mt-3">
            <thead class="table-light">
                <tr>
                    <th scope="col">Thông Tin</th>
                    <th scope="col">Giá Trị</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><strong>Tổng Số Giờ Dạy</strong></td>
                    <td>${tongGioDay} giờ</td>
                </tr>
                <tr>
                    <td><strong>Hệ Số Lương</strong></td>
                    <td>${heSoLuong}</td>
                </tr>
                <tr>
                    <td><strong>Tiền Lương Theo Giờ</strong></td>
                    <td>${luongTheoGio}</td>
                </tr>
                <tr>
                    <td><strong>Lương Cơ Sở</strong></td>
                    <td>${luongCoSo} VND</td>
                </tr>
                <tr>
                    <td><strong>Lương Thưởng</strong></td>
                    <td>${luongThuong}</td>
                </tr>
                <tr>
                    <td><strong>Lương Trừ</strong></td>
                    <td>${luongTru}</td>
                </tr>
                <tr>
                    <td><strong>Tổng Lương</strong></td>
                    <td>${tongLuong}</td>
                </tr>
            </tbody>
        </table>

        <form action="<c:url value='/api/bang-luong/export-pdf/${teacher.id}' />" method="get">
            <button type="submit" class="btn btn-success mt-3">
                Xuất PDF Bảng Lương
            </button>
        </form>
    </div>
</c:if>


