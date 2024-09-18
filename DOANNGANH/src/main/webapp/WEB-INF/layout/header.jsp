<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-white navbar-whitesmoke fixed-top shadow-navbar" style="padding: 0; margin: 0">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <a href="/">
                <img class="ft-logo" src="https://res.cloudinary.com/dhrkxbsmh/image/upload/v1724748091/BannerDAN_uidhrp.jpg" alt loading="eager" style="width: 40px; height: 40px">
            </a>
        </a>
        <div class="ms-1" style="width: 200px; height: 60px">
            <h6 style="text-align: center; font-weight: bold">TRUNG TÂM NGOẠI NGỮ & TIN HỌC EJ GROUP</h6>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        
            <div id="Login" class="ms-auto"">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <li class="nav-item">
                            <a class="btn btn-primary" href="<c:url value="/login" />">Đăng nhập</a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a href="<c:url value="/" />">Xin Chào ${pageContext.request.userPrincipal.name}!</a>
                            <a class="btn btn-primary" href="<c:url value="/logout" />">Đăng xuất</a>
                        </li>
                    </c:when>
                </c:choose>
            </div> 
      
    </div>
</nav>

<style>
    .shadow-navbar {
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.3); /* Thêm shadow cho phần dưới của header */
    }
</style>