<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container mt-3">
    <h1 class="text-center text-success pt-0 mb-4">Tạo Buổi Học - <c:out value="${course.tenKhoaHoc}"/></h1>

    <!-- Form tạo buổi học -->
    <div id="lichHocForm" class="card mb-4">
        <div class="card-body">
            <!-- Form để nhập số lượng buổi học -->
            <form id="soBuoiForm" action="${pageContext.request.contextPath}/schedule/${courseId}" method="post" class="mb-3">
                <div class="row mb-3">
                    <label for="soBuoi" class="col-sm-2 col-form-label">Số buổi học trên tuần:</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="number" class="form-control" id="soBuoi" name="soBuoi" min="1" required />
                            <button id="taoFormBtn" type="submit" class="btn btn-secondary ms-2">Tạo form</button>
                        </div>
                    </div>
                </div>
            </form>

            <!-- Form để nhập thông tin các buổi học -->
            <form:form action="${pageContext.request.contextPath}/schedule/preview/${courseId}" method="post" modelAttribute="buoiHocList">
                <div id="buoiHocContainer">
                    <c:forEach var="buoiHoc" items="${buoiHocList.buoiHocList}" varStatus="status">
                        <div class="mb-3 border p-3 rounded">
                            <div class="row mb-3">
                                <label for="thu${status.index}" class="col-sm-2 col-form-label">Chọn Thứ Buổi ${status.index + 1}:</label>
                                <div class="col-sm-10">
                                    <form:select path="buoiHocList[${status.index}].tenThuTrongTuan" id="thu${status.index}" class="form-select" required="true">
                                        <option value="Monday" ${buoiHoc.tenThuTrongTuan == 'Monday' ? 'selected' : ''}>Thứ 2</option>
                                        <option value="Tuesday" ${buoiHoc.tenThuTrongTuan == 'Tuesday' ? 'selected' : ''}>Thứ 3</option>
                                        <option value="Wednesday" ${buoiHoc.tenThuTrongTuan == 'Wednesday' ? 'selected' : ''}>Thứ 4</option>
                                        <option value="Thursday" ${buoiHoc.tenThuTrongTuan == 'Thursday' ? 'selected' : ''}>Thứ 5</option>
                                        <option value="Friday" ${buoiHoc.tenThuTrongTuan == 'Friday' ? 'selected' : ''}>Thứ 6</option>
                                        <option value="Saturday" ${buoiHoc.tenThuTrongTuan == 'Saturday' ? 'selected' : ''}>Thứ 7</option>
                                        <option value="Sunday" ${buoiHoc.tenThuTrongTuan == 'Sunday' ? 'selected' : ''}>Chủ Nhật</option>
                                    </form:select>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label for="gioBatDau${status.index}" class="col-sm-2 col-form-label">Giờ Bắt Đầu:</label>
                                <div class="col-sm-4">
                                    <form:input path="buoiHocList[${status.index}].thoiGianBatDau" id="gioBatDau${status.index}" type="time" class="form-control" required="true" />
                                </div>

                                <label for="gioKetThuc${status.index}" class="col-sm-2 col-form-label">Giờ Kết Thúc:</label>
                                <div class="col-sm-4">
                                    <form:input path="buoiHocList[${status.index}].thoiGianKetThuc" id="gioKetThuc${status.index}" type="time" class="form-control" required="true" />
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="d-flex justify-content-end">
                    <button type="submit" name="action" value="preview" class="btn btn-primary me-2">Xem Lịch Học Dự Kiến</button>
                    <button type="submit" name="action" value="save" class="btn btn-success">Lưu Lịch Học</button>
                </div>
            </form:form>
        </div>
    </div>

    <!-- Hiển thị lịch học dự kiến nếu có -->
    <c:if test="${not empty previewSchedule}">
        <div class="card mt-4">
            <div class="card-body">
                <h5 class="card-title">Lịch Học Dự Kiến</h5>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Ngày học</th>
                            <th>Thời gian bắt đầu</th>
                            <th>Thời gian kết thúc</th>
                            <th>Ngày trong tuần</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="buoiHoc" items="${previewSchedule}">
                            <tr>
                                <td><c:out value="${buoiHoc.ngayHoc}"/></td>
                                <td>
                                    <fmt:formatDate value="${buoiHoc.thoiGianBatDau}" pattern="HH:mm:ss" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${buoiHoc.thoiGianKetThuc}" pattern="HH:mm:ss" />
                                </td>
                                <td><c:out value="${buoiHoc.tenThuTrongTuan}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</div>

