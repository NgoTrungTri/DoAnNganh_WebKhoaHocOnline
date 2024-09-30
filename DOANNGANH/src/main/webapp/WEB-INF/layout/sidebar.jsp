<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
    body {
        margin: 0;
        font-family: "Lato", sans-serif;
    }

    .sidebar {
        margin: 0;
        padding: 80px 0 0;
        background-color: #f1f1f1;
        position: sticky;
        top: 0;
        left: 0;
        height: 100vh;
        overflow: auto;
        box-shadow: 0px -5px 10px rgba(0, 0, 0, 0.1);
    }

    .sidebar a {
        display: block;
        color: black;
        padding: 16px;
        text-decoration: none;
        white-space: nowrap;
        transition: background-color 0.5s ease;
    }

    .sidebar a.active {
        background-color: #216945;
        color: white;
    }

    .sidebar a:hover:not(.active) {
        background-color: #216945;
        color: purple;
    }

    .dropdown {
        position: relative;
    }

    .dropdown-content {
        display: none;
        background-color: #f9f9f9;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        position: relative;
        width: 100%;
        z-index: 1;
        padding: 0;
        transition: max-height 1s ease, opacity 1s ease;
        max-height: 0;
        overflow: hidden;
        opacity: 0;
    }

    .dropdown-content a {
        color: black;
        padding: 8px 16px;
        text-decoration: none;
        display: block;
        white-space: nowrap;
    }

    .dropdown-content a:hover {
        background-color: #ddd;
    }

    .dropdown:hover .dropdown-content {
        display: block;
        max-height: 300px;
        opacity: 1;
    }

    .dropdown:hover > a {
        background-color: #555;
        color: white;
    }

    div.content {
        margin-left: 200px;
        padding: 1px 16px;
        height: 1000px;
    }

    @media screen and (max-width: 700px) {
        .sidebar {
            width: 100%;
            height: auto;
            position: relative;
        }
        .sidebar a {
            float: left;
        }
        div.content {
            margin-left: 0;
        }
    }

    @media screen and (max-width: 400px) {
        .sidebar a {
            text-align: center;
            float: none;
        }
    }
</style>

<div class="sidebar">
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal.name != null}">
            <div class="dropdown">
                <a class="active" href="#">Quản Lý Người Dùng</a>
                <div class="dropdown-content">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name == 'admin'}">
                            <a href="<c:url value="/?userRole=ROLE_NV"/>">Nhân Viên</a>
                            <a href="<c:url value="/?userRole=ROLE_GV"/>">Giáo Viên</a>
                            <a href="<c:url value="/?userRole=ROLE_HV"/>">Học Viên</a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="/?userRole=ROLE_GV"/>">Giáo Viên</a>
                            <a href="<c:url value="/?userRole=ROLE_HV"/>">Học Viên</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="dropdown">
                <a href="#">Quản lý Khóa Học</a>
                <div class="dropdown-content">
                    <a href="<c:url value="/view-list-course"/>">Xem Khóa Học</a>
                    <a href="<c:url value="/create-course"/>">Tạo Khóa Học</a>
                </div>
            </div>

            <div class="dropdown">
                <a href="#">Quản lý Đơn Hàng</a>
                <div class="dropdown-content">
                    <a href="<c:url value="/donhang/viewpager"/>">Xem Đơn Hàng</a>
                </div>
            </div>

            <div class="dropdown">
                <a href="#">Quản lý Lương</a>
                <div class="dropdown-content">
                    <a href="<c:url value="/hesoluong"/>">Hệ Số Lương Giáo Viên</a>
                    <a href="<c:url value="/bang-luong"/>">Xuất Bảng Lương</a>
                </div>
            </div>

            <div class="dropdown">
                <a href="#">Quản lý Bài Đăng</a>
                <div class="dropdown-content">
                    <a href="<c:url value="/bai-dang-da-duyet"/>">Bài Đăng Public</a>
                    <a href="<c:url value="/bai-dang-chua-duyet"/>">Bài Đăng Chưa Duyệt</a>
                </div>
            </div>

            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name == 'admin'}">
                    <div class="dropdown">
                        <a href="#">Quản lý Doanh Thu</a>
                        <div class="dropdown-content">
                            <a href="<c:url value="/doanh-thu"/>">Doanh Thu Theo Tháng</a> 
                            <a href="<c:url value="/doanh-thu-quy"/>">Doanh Thu Theo Quý</a> 
                        </div>
                    </div>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>

        </c:when>
        <c:otherwise>
            <a class="active" href="#">Quản Lý Người Dùng</a>
            <a href="#">Quản lý Khóa Học</a>
            <a href="#">Quản lý Đơn Hàng</a>
            <a href="#">Quản lý Lương</a>
            <a href="#">Quản lý Bài Đăng</a>
        </c:otherwise>
    </c:choose>
</div>

