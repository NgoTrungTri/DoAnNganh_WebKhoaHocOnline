<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<style>
    #doanhThuChart {
        max-width: 900px;
        max-height: 400px;
        margin: auto;
    }
</style>

<!-- Title -->
<h1 class="text-center text-success mb-4">Doanh Thu Theo Tháng</h1>

<form action="${pageContext.request.contextPath}/doanh-thu" method="post" class="row g-3 mb-5 justify-content-center">
    <div class="col-md-3">
        <label for="thang" class="form-label">Tháng:</label>
        <!-- Gán giá trị mặc định là tháng hiện tại -->
        <input type="number" id="thang" name="thang" class="form-control" min="1" max="12" value="${currentMonth}" required>
    </div>
    <div class="col-md-3">
        <label for="nam" class="form-label">Năm:</label>
        <!-- Gán giá trị mặc định là năm hiện tại -->
        <input type="number" id="nam" name="nam" class="form-control" min="2000" max="2100" value="${currentYear}" required>
    </div>
    <div class="col-md-2 align-self-end">
        <button type="submit" class="btn btn-primary w-100">Xem Doanh Thu</button>
    </div>
</form>

<c:if test="${!empty danhSachKhoaHoc}">
    <h2 class="text-center">Doanh Thu Các Khóa Học Trong Tháng ${param.thang}/${param.nam}</h2>
    <!-- Vùng hiển thị biểu đồ doanh thu -->
    <canvas id="doanhThuChart" width="400" height="200"></canvas>
    </c:if>

<c:if test="${empty danhSachKhoaHoc}">
    <h3 class="text-center text-danger">Chưa có dữ liệu</h3>
</c:if>


<c:if test="${!empty danhSachKhoaHoc}">
    <script>
        var ctx = document.getElementById('doanhThuChart').getContext('2d');

        var labels = [<c:forEach var="khoaHoc" items="${danhSachKhoaHoc}">
        '${khoaHoc.tenKhoaHoc}',
        </c:forEach>
        ];

        var data = [<c:forEach var="doanhThu" items="${doanhThuList}">
            ${doanhThu},
        </c:forEach>
        ];

        var chart = new Chart(ctx, {
            type: 'bar', // Dạng biểu đồ cột
            data: {
                labels: labels, // Tên các khóa học
                datasets: [{
                        label: 'Doanh thu',
                        data: data, // Doanh thu của các khóa học
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true // Trục Y bắt đầu từ 0
                    }
                }
            }
        });
    </script>
</c:if>
