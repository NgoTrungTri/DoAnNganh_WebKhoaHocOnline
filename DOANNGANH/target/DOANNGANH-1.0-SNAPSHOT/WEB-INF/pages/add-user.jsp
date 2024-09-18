<%-- 
    Document   : add-user
    Created on : May 24, 2024, 1:01:50 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>
    <style>
        .is-invalid {
            border-color: #dc3545; /* Đổi màu viền khi có lỗi */
        }

        .invalid-feedback {
            color: #dc3545; /* Màu chữ cho thông báo lỗi */
            display: block; /* Hiển thị thông báo lỗi */
        }
    </style>
</head>
<h1 class="text-center text-success mt-1">
    <c:choose>
        <c:when test="${user.id > 0}">CẬP NHẬT THÔNG TIN NGƯỜI DÙNG</c:when>
        <c:otherwise>THÊM TÀI KHOẢN</c:otherwise>
    </c:choose>
</h1>


<c:url value="/add-user" var="action" />
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="username" placeholder="Tên đăng nhập" path="username" />
        <label for="username">Tên đăng nhập</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="password" placeholder="Mật khẩu" path="password" />
        <label for="password">Mật khẩu</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="name" placeholder="Nhập họ" path="ho" />
        <label for="name">Họ</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="ten" placeholder="Nhập tên" path="ten" />
        <label for="name">Tên</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="email" placeholder="Email" path="email" />
        <label for="email">Email</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="chucVu" name="chucVu" path="chucVuId"  onchange="setUserRole()">
            <c:forEach items="${chucVu}" var="chucVu">
                <c:choose>
                    <c:when test="${chucVu.id==user.chucVuId.id}">
                        <option value="${chucVu.id}" selected>${chucVu.chucVu}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${chucVu.id}">${chucVu.chucVu}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="chucVu">Chức Vụ</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="birth" placeholder="Ngày sinh" path="ngaySinh" type="date" />
        <label for="birth">Ngày sinh</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="sex" placeholder="Giới tính" path="gioiTinh" />
        <label for="sex">Giới tính</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" id="avatar" path="file"/>
        <label for="avatar">Avatar</label>
        <div class="invalid-feedback"></div>

        <c:if test="${user.id > 0}">
            <img src="${user.avatar}" width="100" class="img-fluid"/>
        </c:if>
    </div>

    <form:hidden id="userRole" path="userRole" />

    <form:hidden path="ngayTao" />    
    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${user.id > 0 }">Cập nhật</c:when>
                <c:otherwise>Thêm</c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id"></form:hidden>
        </div>
</form:form>
<script src="<c:url value="/js/script.js"/>"></script>
<script>
    function setUserRole() {
        const chucVuSelect = document.getElementById("chucVu");
        const userRoleInput = document.getElementById("userRole");
        const selectedChucVu = chucVuSelect.options[chucVuSelect.selectedIndex].text;

        console.log("Selected Chuc Vu: " + selectedChucVu);
        
        let role;
        switch (selectedChucVu) {
            case "Quản Trị Viên":
                role = "ROLE_ADMIN";
                break;
            case "Nhân Viên":
                role = "ROLE_NV";
                break;
            case "Giáo Viên":
                role = "ROLE_GV";
                break;
            case "Học Viên":
                role = "ROLE_HV";
                break;
            default:
                role = "ROLE_HV"; // Vai trò mặc định
        }

        // Cập nhật giá trị userRole dựa trên chức vụ đã chọn
        userRoleInput.value = role;
    }

    // Gọi hàm này khi trang được load để set giá trị đúng khi cập nhật
    window.onload = setUserRole;
</script>
