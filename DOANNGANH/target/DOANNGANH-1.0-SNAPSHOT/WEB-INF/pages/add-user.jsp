<%-- 
    Document   : add-user
    Created on : May 24, 2024, 1:01:50 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<h1 class="text-center text-success mt-1">
    <c:choose>
        <c:when test="${user.id > 0}">CẬP NHẬT THÔNG TIN NGƯỜI DÙNG</c:when>
        <c:otherwise>THÊM TÀI KHOẢN</c:otherwise>
    </c:choose>
</h1>

<div id="errorAlert" class="alert alert-danger" role="alert" style="text-align: center; display: ${not empty errorMessage ? 'block' : 'none'};">
    ${errorMessage}
</div>

<c:url value="/add-user" var="action" />
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data" onsubmit="return validateForm()">
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
        <label for="ten">Tên</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="email" placeholder="Email" path="email" />
        <label for="email">Email</label>
        <div class="invalid-feedback"></div>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="chucVu" name="chucVu" path="chucVuId" onchange="setUserRole()">
            <c:forEach items="${chucVu}" var="chucVuItem">
                <option value="${chucVuItem.id}" <c:if test="${chucVuItem.id == user.chucVuId.id}">selected</c:if>>${chucVuItem.chucVu}</option>
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

<script src="<c:url value='/js/script.js'/>"></script>
<script>
    function validateForm() {
        const errorAlert = document.getElementById("errorAlert");
        if (errorAlert.textContent.trim() !== "") {
            errorAlert.style.display = "block";
            return false; // Ngăn không cho form submit
        }
        errorAlert.style.display = "none"; // Ẩn thông báo nếu không có lỗi
        return true; // Cho phép form submit
    }

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
                role = "ROLE_HV"; 
        }

        userRoleInput.value = role; // Cập nhật giá trị userRole dựa trên chức vụ đã chọn
    }

    window.onload = setUserRole; // Gọi hàm này khi trang được load
</script>


