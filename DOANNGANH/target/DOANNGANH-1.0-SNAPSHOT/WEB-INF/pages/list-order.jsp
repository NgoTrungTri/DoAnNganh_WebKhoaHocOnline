<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedToday = today.format(formatter);
    String currentMonth = today.format(DateTimeFormatter.ofPattern("MM-yyyy"));
    request.setAttribute("currentMonth", currentMonth);
    request.setAttribute("today", formattedToday);
%>

<h1 class="text-center text-success pt-0 mt-0 mb-4">Danh Sách Đơn Hàng</h1>

<!-- Tabs navigation -->
<ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item" role="presentation">
        <button class="nav-link active" id="ngay-tab" data-bs-toggle="tab" data-bs-target="#ngay" type="button" role="tab">Theo Ngày</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="thang-tab" data-bs-toggle="tab" data-bs-target="#thang" type="button" role="tab">Theo Tháng</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="khoahoc-tab" data-bs-toggle="tab" data-bs-target="#khoahoc" type="button" role="tab">Theo Khóa Học</button>
    </li>
</ul>

<div class="tab-content" id="myTabContent">

    <!-- Tab 1: Đơn hàng theo ngày -->
    <div class="tab-pane fade show active" id="ngay" role="tabpanel" aria-labelledby="ngay-tab">
        <h5 class="mt-3 mb-3 text-center" style="font-style: italic; color: blue">${today}</h5>
        <div class="row">
            <c:choose>
                <c:when test="${not empty donhangsByDay}">
                    <c:forEach items="${donhangsByDay}" var="donhang">
                        <div class="col-12">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h5 class="card-title">MÃ ĐƠN HÀNG: ${donhang.id}</h5>
                                    <p class="card-text">
                                        <strong>Ngày tạo:</strong> <fmt:formatDate value="${donhang.ngayTao}" pattern="dd-MM-yyyy" /><br>
                                        <strong>Tổng tiền:</strong> ${donhang.tongTien} VND<br>
                                        <strong>Thông tin thanh toán:</strong> ${donhang.thongTinThanhToan}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="container mt-3">
                        <ul class="pagination justify-content-center">
                            <c:if test="${page > 0}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${page - 1}&activeTab=ngay">Previous</a>
                                </li>
                            </c:if>
                            <c:forEach begin="0" end="${totalPagesByDay - 1}" var="i">
                                <li class="page-item <c:if test='${i == page}'>active</c:if>">
                                    <a class="page-link" href="?page=${i}&activeTab=ngay">${i + 1}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${page < totalPagesByDay - 1}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${page + 1}&activeTab=ngay">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="text-center text-muted">Chưa có đơn hàng nào trong ngày hôm nay.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Tab 2: Đơn hàng theo tháng -->
    <div class="tab-pane fade" id="thang" role="tabpanel" aria-labelledby="thang-tab">
        <h5 class="mt-3 mb-3 text-center" style="font-style: italic; color: blue">Tháng ${currentMonth}</h5>
        <div class="row">
            <c:choose>
                <c:when test="${not empty donhangsByMonth}">
                    <c:forEach items="${donhangsByMonth}" var="donhang">
                        <div class="col-12">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h5 class="card-title">MÃ ĐƠN HÀNG: ${donhang.id}</h5>
                                    <p class="card-text">
                                        <strong>Ngày tạo:</strong> <fmt:formatDate value="${donhang.ngayTao}" pattern="dd-MM-yyyy" /><br>
                                        <strong>Tổng tiền:</strong> ${donhang.tongTien} VND<br>
                                        <strong>Thông tin thanh toán:</strong> ${donhang.thongTinThanhToan}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="container mt-3">
                        <ul class="pagination justify-content-center">
                            <c:if test="${page > 0}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${page - 1}&activeTab=thang">Previous</a>
                                </li>
                            </c:if>
                            <c:forEach begin="0" end="${totalPagesByMonth - 1}" var="i">
                                <li class="page-item <c:if test='${i == page}'>active</c:if>">
                                    <a class="page-link" href="?page=${i}&activeTab=thang">${i + 1}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${page < totalPagesByMonth - 1}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${page + 1}&activeTab=thang">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="text-center text-muted">Chưa có đơn hàng nào trong tháng này.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Tab 3: Đơn hàng theo khóa học -->
    <div class="tab-pane fade" id="khoahoc" role="tabpanel" aria-labelledby="khoahoc-tab">
        <form action="<c:url value='/donhang/viewpager' />" method="get" class="mt-4 mb-4">
            <input type="hidden" name="activeTab" value="khoahoc"/>
            <label for="tenKhoaHoc">Tìm Kiếm Khóa Học: </label>
            <input type="text" id="tenKhoaHoc" name="tenKhoaHoc" value="${tenKhoaHoc}">
            <button type="submit" class="btn btn-primary btn-sm">Tìm kiếm</button>
        </form>
        <div class="row">
            <c:choose>
                <c:when test="${not empty donhangsByKhoaHoc}">
                    <c:forEach items="${donhangsByKhoaHoc}" var="donhang">
                        <div class="col-12">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h5 class="card-title">MÃ ĐƠN HÀNG: ${donhang.id}</h5>
                                    <p class="card-text">
                                        <strong>Ngày tạo:</strong> <fmt:formatDate value="${donhang.ngayTao}" pattern="dd-MM-yyyy" /><br>
                                        <strong>Tổng tiền:</strong> ${donhang.tongTien} VND<br>
                                        <strong>Thông tin thanh toán:</strong> ${donhang.thongTinThanhToan}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="text-center text-muted">Không tìm thấy đơn hàng nào.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</div>

<!-- Input ẩn để lưu active tab - đặt ngoài tab-content -->
<input type="hidden" id="activeTabInput" value="${activeTab}"/>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Restore active tab từ server
        var activeTabInput = document.getElementById("activeTabInput");
        if (activeTabInput) {
            var activeTab = activeTabInput.value;
            if (activeTab) {
                var tab = document.querySelector("#" + activeTab + "-tab");
                if (tab) {
                    var tabTrigger = new bootstrap.Tab(tab);
                    tabTrigger.show();
                }
            }
        }

        // Cập nhật active tab khi người dùng click
        var tabs = document.querySelectorAll('.nav-link');
        tabs.forEach(function (tab) {
            tab.addEventListener('click', function () {
                var tabId = this.getAttribute('data-bs-target').substring(1);
                var input = document.getElementById('activeTabInput');
                if (input) {
                    input.value = tabId;
                }
            });
        });
    });
</script>
