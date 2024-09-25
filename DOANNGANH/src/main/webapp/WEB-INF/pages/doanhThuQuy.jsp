<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<style>
    .chart-container {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
    }
    .chart {
        width: 45%; /* Mỗi biểu đồ chiếm khoảng 45% chiều rộng */
        margin: 20px 0;
    }
</style>

<!-- Title -->
<h1 class="text-center text-success mb-4">Doanh Thu Theo Quý</h1>

<form action="${pageContext.request.contextPath}/doanh-thu-quy" method="post" class="row g-3 justify-content-center">
    <div class="col-md-4">
        <label for="quy" class="form-label">Chọn Quý:</label>
        <input type="number" id="quy" name="quy" class="form-control" min="1" max="4" placeholder="1 - 4" required>
    </div>
    <div class="col-md-4">
        <label for="nam" class="form-label">Chọn Năm:</label>
        <input type="number" id="nam" name="nam" class="form-control" min="2000" max="2100" placeholder="2024" value="${nam}" required>
    </div>
    <div class="col-md-2 align-self-end">
        <button type="submit" class="btn btn-primary w-100">Xem Doanh Thu</button>
    </div>
</form>

<c:if test="${!empty danhSachKhoaHoc}">
    <div class="chart-container">
        <!-- Vùng hiển thị biểu đồ doanh thu theo khóa học -->
        <div class="chart">
            <canvas id="doanhThuQuyChart" width="600" height="600"></canvas>
        </div>

        <!-- Vùng hiển thị biểu đồ doanh thu theo tháng -->
        <div class="chart">
            <canvas id="doanhThuThangChart" width="600" height="400"></canvas>
        </div>
    </div>
</c:if>

<c:if test="${empty danhSachKhoaHoc}">
    <h3 class="text-center text-danger">Chưa có dữ liệu</h3>
</c:if>

<c:if test="${!empty danhSachKhoaHoc}">
    <script>
        var ctx1 = document.getElementById('doanhThuQuyChart').getContext('2d');
        var ctx2 = document.getElementById('doanhThuThangChart').getContext('2d');

        var labels = [<c:forEach var="khoaHoc" items="${danhSachKhoaHoc}">
        '${khoaHoc.tenKhoaHoc}',
        </c:forEach>
];

        var data = [<c:forEach var="doanhThu" items="${doanhThuList}">
            ${doanhThu},
        </c:forEach>
];

        var doanhThuQuyChart = new Chart(ctx1, {
            type: 'bar', // Dạng biểu đồ cột
            data: {
                labels: labels, // Tên các khóa học
                datasets: [{
                        label: 'Doanh thu',
                        data: data, // Doanh thu của các khóa học
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 1)',
                        borderWidth: 1
                    }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true // Trục Y bắt đầu từ 0
                    }
                },
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Doanh thu các khóa học trong quý'
                    }
                }
                
                
            }
        });

        var thangLabels = [<c:forEach var="thang" items="${thangLabels}">
        '${thang}',
        </c:forEach>
];

        var thangData = [<c:forEach var="doanhThuThang" items="${doanhThuThangList}">
            ${doanhThuThang},
        </c:forEach>
];

        var doanhThuThangChart = new Chart(ctx2, {
            type: 'pie', // Dạng biểu đồ tròn
            data: {
                labels: thangLabels, // Tên các tháng
                datasets: [{
                        label: 'Doanh thu theo tháng',
                        data: thangData, // Doanh thu của các tháng
                        backgroundColor: ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)'],
                        borderColor: ['rgba(255, 99, 132, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)'],
                        borderWidth: 1
                    }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Doanh thu các tháng trong quý'
                    }
                }
            }
        });
    </script>
</c:if>
