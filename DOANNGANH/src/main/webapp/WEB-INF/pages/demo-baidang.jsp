<%-- 
    Document   : demo-baidang
    Created on : Sep 26, 2024, 6:25:24 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!--<head>
    <link href="https://cdn.ckeditor.com/4.16.0/standard-all/contents.css" rel="stylesheet">
</head>-->

<h1 class="text-center mb-4">${baiDang.tieuDe}</h1>
<!--Nội Dung-->
<c:out value="${baiDang.noiDung}" escapeXml="false" />

<p class="mt-4">Ngày đăng: <fmt:formatDate value="${baiDang.ngayDang}" pattern="dd/MM/yyyy"/></p>


