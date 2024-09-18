<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <style>
        fieldset {
            border: 2px solid #008000;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
            position: relative;
            padding-top: 1.5em;
        }

        legend {
            font-size: 1rem;
            font-weight: bold;
            color: #008000; /* Màu chữ */
            padding: 0 10px;
            width: auto;
            position: absolute;
            top: -0.8em; /* Di chuyển legend lên trên */
            left: 10px; /* Căn lề trái */
            background-color: white; /* Đặt nền màu trắng để che border */
            padding: 0 5px; /* Thêm khoảng cách xung quanh legend */
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<h1 class="text-center text-success mt-1 mb-4">
    <c:choose>
        <c:when test="${khoahoc.id > 0}">CẬP NHẬT KHÓA HỌC</c:when>
        <c:otherwise>TẠO KHÓA HỌC MỚI</c:otherwise>
    </c:choose>
</h1>

<c:url value="/create-course" var="action" />
<form:form method="post" id="formCourse" action="${action}" modelAttribute="khoahoc">
    <fieldset>
        <legend>Nhập Thông Tin Khóa Học</legend>
        <div class="form-floating mb-3 mt-3">
            <form:input class="form-control" id="tenKhoaHoc" placeholder="Tên Khóa Học" path="tenKhoaHoc" />
            <label for="tenKhoaHoc">Tên Khóa Học</label>
            <div class="invalid-feedback"></div>
        </div>

        <div class="form-floating mb-3 mt-3">
            <form:input class="form-control" id="startDate" placeholder="Ngày Bắt Đầu" path="ngayBatDau" type="date" />
            <label for="startDate">Ngày Bắt Đầu</label>
            <div class="invalid-feedback"></div>
        </div>

        <div class="form-floating mb-3 mt-3">
            <form:input class="form-control" id="endDate" placeholder="Ngày Kết Thúc" path="ngayKetThuc" type="date" />
            <label for="endDate">Ngày Kết Thúc</label>
            <div class="invalid-feedback"></div>
        </div>

        <div class="form-floating mb-3 mt-3">
            <form:input class="form-control" id="giaTien" placeholder="Giá Tiền Khóa Học" path="giaTien" type="number" />
            <label for="giaTien">Giá Tiền Khóa Học</label>
            <div class="invalid-feedback"></div>
        </div>

        <div class="form-floating mb-3 mt-3">
            <form:input class="form-control" id="tienLuongPhuTroi" placeholder="Tiền Lương Phụ Trội" path="tienLuongPhuTroi" type="number"/>
            <label for="tienLuongPhuTroi">Tiền Lương Phụ Trội</label>
            <div class="invalid-feedback"></div>
        </div>

        <div class="form-floating mb-3 mt-3">
            <form:select class="form-select" id="danhMuc" path="danhMucId">
                <c:forEach items="${danhMuc}" var="danhMuc">
                    <option value="${danhMuc.id}">${danhMuc.tenDanhMuc}</option>
                </c:forEach>
            </form:select>
            <label for="danhMuc">Danh Mục</label>
        </div>

        <div class="form-floating mb-3 mt-3">
            <form:select class="form-select" id="idGVPhuTrach" path="idGVPhuTrach">
                <c:forEach items="${giaoVien}" var="giaoVien">
                    <option value="${giaoVien.id}">GV. ${giaoVien.ho} ${giaoVien.ten}</option>
                </c:forEach>
            </form:select>
            <label for="idGVPhuTrach">Giáo Viên Phụ Trách</label>
        </div> 
        
        <form:hidden path="ngayTao"/> 
        <form:hidden path="idNVTao"/> 
        <form:hidden path="trangThai" /> 
        <form:hidden path="id"/>  
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${khoahoc.id > 0}">Cập Nhật</c:when>
                <c:otherwise>Xác Nhận</c:otherwise>
            </c:choose>
        </button>
    </fieldset>
</form:form>
    