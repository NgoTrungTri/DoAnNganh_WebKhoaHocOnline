<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-success pt-0 mb-4">DANH SÁCH HỆ SỐ LƯƠNG GIÁO VIÊN</h1>

    <!-- Form chọn loại giáo viên, tự động submit khi thay đổi -->
    <form method="get" action="<c:url value='/hesoluong' />">
        <div class="form-group d-flex align-items-center">
            <select name="LoaiGV" id="LoaiGV" class="form-control me-2" onchange="this.form.submit()">
                <option value="Cơ Hữu" ${LoaiGV == 'Cơ Hữu' ? 'selected' : ''}>Cơ Hữu</option>
                <option value="Thỉnh Giảng" ${LoaiGV == 'Thỉnh Giảng' ? 'selected' : ''}>Thỉnh Giảng</option>
            </select>
            <button type="button" class="btn btn-success" onclick="showAddForm()">Thêm</button>
        </div>
    </form>

    <br>

    <!-- Bảng hiển thị hệ số lương giáo viên -->
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>STT</th>
                <th>Tên Giáo Viên</th>
                <th>Hệ Số Lương</th>
                <th>Tiền Lương Thêm Theo Giờ</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty heSo}">
                <c:forEach var="gv" items="${heSo}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>GV. ${gv.userId.ho} ${gv.userId.ten}</td>
                        <td>${gv.heSo}</td>
                        <td>${gv.tienLuongThemTheoGio}</td>
                        <td>
                            <button type="button" class="btn btn-warning" onclick="showEditForm(${gv.id}, '${gv.heSo}', '${gv.tienLuongThemTheoGio}')">Chỉnh sửa</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty heSo}">
                <tr>
                    <td colspan="4" class="text-center">Không có dữ liệu</td>
                </tr>
            </c:if>
        </tbody>
    </table>

    <!-- Form tạo lương giáo viên - Ẩn lúc đầu -->
    <div id="createFormContainer" style="display: none;">
        <h3 class="text-primary">Tạo lương giáo viên</h3>
        <form id="createForm" method="post" action="<c:url value='/createLuongGiaoVien' />">
            <div class="form-group">
                <label for="userId">Giáo Viên:</label>
                <select id="userId" name="userId" class="form-control" required>
                    <option value="">Chọn giáo viên</option>
                    <c:forEach var="gv" items="${listGiaoVien}">
                        <option value="${gv.id}">GV. ${gv.ho} ${gv.ten}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="heSo">Hệ Số Lương:</label>
                <input type="text" id="heSo" name="heSo" class="form-control" required/>
            </div>
            <div class="form-group mt-2">
                <label for="tienLuongThemTheoGio">Tiền Lương Thêm Theo Giờ:</label>
                <input type="text" id="tienLuongThemTheoGio" name="tienLuongThemTheoGio" class="form-control" required/>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Tạo</button>
        </form>
    </div>

    <!-- Form chỉnh sửa hệ số lương - Ẩn lúc đầu -->
    <div id="editFormContainer" style="display: none;">
        <h3 class="text-primary">Chỉnh sửa hệ số lương</h3>
        <form id="editForm" method="post" action="<c:url value='/updateLuongGiaoVien' />">
            <input type="hidden" id="editIdGiaoVien" name="id" value=""/>
            <div class="form-group">
                <label for="heSo">Hệ Số Lương:</label>
                <input type="text" id="editHeSo" name="heSo" class="form-control" required/>
            </div>
            <div class="form-group mt-2">
                <label for="tienLuongThemTheoGio">Tiền Lương Thêm Theo Giờ:</label>
                <input type="text" id="editTienLuongThemTheoGio" name="tienLuongThemTheoGio" class="form-control" required/>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Cập nhật</button>
        </form>
    </div>
</div>

<!-- JavaScript để hiển thị form -->
<script type="text/javascript">
    function showAddForm() {
        // Ẩn form chỉnh sửa nếu đang mở
        document.getElementById('editFormContainer').style.display = 'none';
        
        // Hiển thị form thêm mới
        document.getElementById('createFormContainer').style.display = 'block';

        // Cuộn xuống form
        window.scrollTo(0, document.getElementById('createFormContainer').offsetTop);
    }

    function showEditForm(id, heSo, tienLuongThemTheoGio) {
        // Ẩn form tạo nếu đang mở
        document.getElementById('createFormContainer').style.display = 'none';

        // Hiển thị form chỉnh sửa
        document.getElementById('editFormContainer').style.display = 'block';

        // Đặt giá trị vào các input
        document.getElementById('editIdGiaoVien').value = id;
        document.getElementById('editHeSo').value = heSo;
        document.getElementById('editTienLuongThemTheoGio').value = tienLuongThemTheoGio;

        // Cuộn xuống form
        window.scrollTo(0, document.getElementById('editFormContainer').offsetTop);
    }
</script>