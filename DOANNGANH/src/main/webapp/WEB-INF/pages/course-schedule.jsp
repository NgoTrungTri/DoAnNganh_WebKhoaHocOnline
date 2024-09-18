<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<div class="container mt-3">
    <h1 class="mb-4 text-center text-success pt-0">Thời Khóa Biểu - <c:out value="${course.tenKhoaHoc}"/></h1>

    <!-- Hiển thị thông tin khóa học -->
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title mb-3">Thông tin khóa học</h5>
            <p><strong>Tên khóa học:</strong> <c:out value="${course.tenKhoaHoc}"/></p>
            <p><strong>Ngày bắt đầu:</strong> <c:out value="${startdate}"/></p>
            <p><strong>Ngày kết thúc:</strong> <c:out value="${enddate}"/></p>
            <p><strong>Số tuần:</strong> <c:out value="${soTuan}"/></p>
        </div>
    </div>

    <!-- Hiển thị lịch học hiện tại -->
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">Lịch học hiện tại</h5>
            <c:if test="${not empty scheduleList}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Ngày học</th>
                            <th>Thời gian bắt đầu</th>
                            <th>Thời gian kết thúc</th>
                            <th>Ngày trong tuần</th>
                            <th>Ngày Học Bù</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${scheduleList}">
                            <tr>
                                <td><c:out value="${item.ngayHoc}"/></td>
                                <td><c:out value="${item.thoiGianBatDau}"/></td>
                                <td><c:out value="${item.thoiGianKetThuc}"/></td>
                                <td><c:out value="${item.tenThuTrongTuan}"/></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/chonngayhocbu/${item.id}" class="btn btn-warning">Chọn Ngày Học Bù</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty scheduleList}">
                <p>Chưa có lịch học.</p>
            </c:if>
        </div>
    </div>

    <!-- Nút tạo buổi học -->
    <a href="${pageContext.request.contextPath}/schedule/${courseId}" class="btn btn-primary mb-4">Tạo Buổi Học</a>






