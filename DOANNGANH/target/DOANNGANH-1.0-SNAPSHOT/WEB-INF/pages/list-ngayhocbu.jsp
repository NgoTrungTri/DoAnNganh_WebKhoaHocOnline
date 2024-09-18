<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-3">
    <h1 class="text-center text-success pt-0 mt-0 mb-4">Danh Sách Ngày Học Bù</h1>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>STT</th>
                    <th>Ngày Học Bù</th>
                    <th>Thời Gian Bắt Đầu</th>
                    <th>Thời Gian Kết Thúc</th>
                    <th>Ngày Bù</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ngayhocbu" items="${ngayhocbuList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>
                            <spring:eval expression="ngayhocbu.ngayHocBu" />
                        </td>
                        <td>
                            <spring:eval expression="ngayhocbu.thoiGianBatDau" />
                        </td>
                        <td>
                            <spring:eval expression="ngayhocbu.thoiGianKetThuc" />
                        </td>
                        <td>
                            <spring:eval expression="ngayhocbu.ngayBu" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <a class="btn btn-primary" href="<c:url value="/view-list-course"/>">Trở về khóa học</a>
        </div>
    </div>
</div>

