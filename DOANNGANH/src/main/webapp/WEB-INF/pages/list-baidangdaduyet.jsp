<%-- 
    Document   : list-baidangdaduyet
    Created on : Sep 27, 2024, 3:18:39 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h1 class="text-center text-success pt-0 mt-0 mb-5">DANH SÁCH </h1>

<div class="row">
    <c:forEach var="baiDang" items="${baiDangList}">
        <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h4 class="card-title mb-2"><strong>${baiDang.tieuDe}</strong></h4>
                    <p class="card-text">
                        Ngày đăng: <fmt:formatDate value="${baiDang.ngayDang}" pattern="dd/MM/yyyy"/>
                    </p>                    
                    <p class="card-text">Danh mục: <strong>${baiDang.danhMucId.tenDanhMuc}</strong></p>
                    <p class="card-text">Giáo Viên Đăng: <strong>GV. ${baiDang.idGVDang.ho} ${baiDang.idGVDang.ten}</strong></p>

                    <!-- Nút bấm xem demo mở bài đăng trên web -->
                    <a href="${pageContext.request.contextPath}/demo/${baiDang.id}" 
                       class="btn btn-primary" target="_blank">Xem Demo</a>                  
                </div>
            </div>
        </div>
    </c:forEach>
</div>