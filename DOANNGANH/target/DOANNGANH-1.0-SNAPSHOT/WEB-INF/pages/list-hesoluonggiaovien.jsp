<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <h1 class="text-center text-success pt-0 mb-4">DANH SÁCH HỆ SỐ LƯƠNG GIÁO VIÊN</h1>

    <!-- Form chọn loại giáo viên, tự động submit khi thay đổi -->
    <form method="get" action="<c:url value='/hesoluong' />">
        <div class="form-group">
            <select name="LoaiGV" id="LoaiGV" class="form-control" onchange="this.form.submit()">
                <option value="Cơ Hữu" ${LoaiGV == 'Cơ Hữu' ? 'selected' : ''}>Cơ Hữu</option>
                <option value="Thỉnh Giảng" ${LoaiGV == 'Thỉnh Giảng' ? 'selected' : ''}>Thỉnh Giảng</option>
            </select>
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

    <!-- Form chỉnh sửa hệ số lương -->
    <div id="editFormContainer" style="display: none;">
        <h3 class="text-primary">Chỉnh sửa hệ số lương</h3>
        <form id="editForm" method="post" action="<c:url value='/updateLuongGiaoVien' />">
            <input type="hidden" id="idGiaoVien" name="id" value=""/>
            <div class="form-group">
                <label for="heSo">Hệ Số Lương:</label>
                <input type="text" id="heSo" name="heSo" class="form-control" required/>
            </div>
            <div class="form-group mt-2">
                <label for="tienLuongThemTheoGio">Tiền Lương Thêm Theo Giờ:</label>
                <input type="text" id="tienLuongThemTheoGio" name="tienLuongThemTheoGio" class="form-control" required/>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Cập nhật</button>
        </form>
    </div>
</div>

<!-- JavaScript để hiển thị form chỉnh sửa -->
<script type="text/javascript">
    function showEditForm(id, heSo, tienLuongThemTheoGio) {
        // Hiển thị form
        document.getElementById('editFormContainer').style.display = 'block';

        // Đặt giá trị vào các input
        document.getElementById('idGiaoVien').value = id;
        document.getElementById('heSo').value = heSo;
        document.getElementById('tienLuongThemTheoGio').value = tienLuongThemTheoGio;

        // Cuộn xuống form
        window.scrollTo(0, document.getElementById('editFormContainer').offsetTop);
    }
</script>
