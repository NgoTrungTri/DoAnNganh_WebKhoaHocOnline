<%-- 
    Document   : list-baidangchuaduyet
    Created on : Sep 26, 2024, 5:07:45 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h1 class="text-center text-success pt-0 mt-0 mb-5">Danh sách bài đăng chưa duyệt</h1>

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

                    <form action="${pageContext.request.contextPath}/duyet-bai/${baiDang.id}" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-success">Duyệt</button>
                    </form>

                    <!-- Nút Góp Ý sẽ mở modal -->
                    <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#gopYModal-${baiDang.id}">Góp Ý</button>
                    
                    <!-- Modal Góp Ý -->
                    <div class="modal fade" id="gopYModal-${baiDang.id}" tabindex="-1" aria-labelledby="gopYModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="gopYModalLabel">Nhập lời nhắc cho bài đăng</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <form id="gopYForm-${baiDang.id}">
                                        <div class="form-group">
                                            <label for="noiDungPhanHoi-${baiDang.id}">Nội dung góp ý:</label>
                                            <textarea class="form-control" id="noiDungPhanHoi-${baiDang.id}" rows="3"></textarea>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <button type="button" class="btn btn-primary" onclick="submitGopY(${baiDang.id})">Gửi</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<script>
    function submitGopY(baiDangId) {
        var noiDungPhanHoi = document.getElementById('noiDungPhanHoi-' + baiDangId).value;

        // Gửi yêu cầu AJAX POST tới backend để cập nhật bài viết
        fetch('${pageContext.request.contextPath}/gop-y-bai/' + baiDangId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ noiDungPhanHoi: noiDungPhanHoi, trangThai: 'KHONG_DAT' })
        })
        .then(response => {
            if (response.ok) {
                alert('Góp ý đã được gửi thành công!');
                window.location.reload(); // Tải lại trang sau khi gửi thành công
            } else {
                alert('Có lỗi xảy ra khi gửi góp ý.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Lỗi kết nối.');
        });
    }
</script>
