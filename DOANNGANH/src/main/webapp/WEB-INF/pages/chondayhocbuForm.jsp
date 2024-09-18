<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-success pt-0 mt-0 mb-4">Chọn Ngày Học Bù</h1>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
        ${errorMessage}
    </div>
</c:if>

<form:form method="post" action="${pageContext.request.contextPath}/chonngayhocbu/${ngayBuId}" modelAttribute="ngayhocbu" id="ngayHocBuForm">
    <div class="mb-3 row">
        <label for="ngayHocBu" class="col-sm-2 col-form-label">Ngày Học Bù:</label>
        <div class="col-sm-10">
            <form:input path="ngayHocBu" type="date" class="form-control" id="ngayHocBu" required="true" />
        </div>
    </div>

    <div class="mb-3 row">
        <label for="thoiGianBatDau" class="col-sm-2 col-form-label">Thời Gian Bắt Đầu:</label>
        <div class="col-sm-10">
            <form:input path="thoiGianBatDau" id="gioBatDau" type="time" class="form-control" required="true" />
        </div>
    </div>

    <div class="mb-3 row">
        <label for="thoiGianKetThuc" class="col-sm-2 col-form-label">Thời Gian Kết Thúc:</label>
        <div class="col-sm-10">
            <form:input path="thoiGianKetThuc" id="gioKetThuc" type="time" class="form-control" required="true" />
        </div>
    </div>

    <div class="text-center">
        <button type="submit" class="btn btn-primary">Lưu Ngày Học Bù</button>
    </div>
</form:form>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Ẩn thông báo lỗi và thành công khi tải trang
        var errorDiv = document.querySelector(".alert-danger");

        if (errorDiv) {
            errorDiv.style.display = "none";
        }

        // Lắng nghe sự kiện submit form
        document.getElementById("ngayHocBuForm").addEventListener("submit", function (event) {
            event.preventDefault(); // Ngăn chặn hành động submit mặc định

            // Thực hiện xử lý logic kiểm tra ở đây, ví dụ:
            var ngayHocBu = document.getElementById("ngayHocBu").value;
            var thoiGianBatDau = document.getElementById("gioBatDau").value;
            var thoiGianKetThuc = document.getElementById("gioKetThuc").value;

            if (!ngayHocBu || !thoiGianBatDau || !thoiGianKetThuc) {
                // Nếu có lỗi, hiển thị thông báo lỗi
                if (errorDiv) {
                    errorDiv.style.display = "block";
                }
            } else {
                // Nếu không có lỗi, cho phép submit form
                this.submit();
            }
        });
    });
</script>