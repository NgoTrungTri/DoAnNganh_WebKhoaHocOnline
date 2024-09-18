<%-- 
    Document   : outline
    Created on : Sep 4, 2024, 1:04:52 AM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-5">
    <h1 class="text-center text-success">Đề Cương Khóa Học</h1>
    <div class="row justify-content-center">
        <div class="col-md-8">        
            <!-- Danh sách Đề Cương -->
            <c:choose>
                <c:when test="${not empty decuong}">
                    <c:forEach items="${decuong}" var="deCuong">
                        <div class="card mb-3 shadow-sm mt-3">
                            <div class="card-body">
                                <h5 class="card-title">${deCuong.tenDeCuong}</h5>
                                <p class="card-text">
                                    <strong>File URL:</strong> 
                                    <a href="${deCuong.urlFile}" target="_blank" style="color: blue">Xem ở đây</a>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-warning text-center">
                        Không tìm thấy đề cương cho khóa học này!!
                    </div>
                </c:otherwise>
            </c:choose>
            <!-- Nút Thêm Đề Cương -->
            <button class="btn btn-success w-100 mb-3" type="button" id="toggleFormButton" onclick="toggleForm()">Thêm Đề Cương</button>

            <!-- Form Tải Đề Cương -->
            <div id="uploadFormContainer" style="display:none;">
                <h2 class="mt-4">Upload Đề Cương Mới</h2>
                <c:url value="/outline/${courseId}" var="action" />
                <form:form id="uploadForm" method="post" enctype="multipart/form-data" action="${action}" modelAttribute="outline">
                    <div class="form-floating mb-3 mt-3">
                        <form:input type="text" name="tenDeCuong" id="tenDeCuong" path="tenDeCuong" class="form-control" placeholder="Tên Đề Cương"/>
                        <label for="tenDeCuong">Tên Đề Cương</label>
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="mb-3">
                        <label for="fileUpload" class="form-label">Chọn tệp đề cương</label>
                        <form:input type="file" name="file" id="fileUpload" path="file" class="form-control" />
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Upload</button>
                </form:form>              
            </div>

        </div>
    </div>
</div>

<!-- JavaScript để hiển thị hoặc ẩn form -->
<script>
    function toggleForm() {
        var formContainer = document.getElementById('uploadFormContainer');
        var toggleButton = document.getElementById('toggleFormButton');

        if (formContainer.style.display === "none") {
            formContainer.style.display = "block";
            toggleButton.innerText = "Thu lại";
        } else {
            formContainer.style.display = "none";
            toggleButton.innerText = "Thêm Đề Cương";
        }
    }
</script>
