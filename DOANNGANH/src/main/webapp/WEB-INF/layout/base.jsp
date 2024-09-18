<%-- 
    Document   : base
    Created on : Apr 3, 2024, 1:08:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

    </head>

    <body>
        <tiles:insertAttribute name="header" />     
        <div class="row">
            <div class="col-md-2" style="margin: 0; padding-right: 10px">
                <tiles:insertAttribute name="sidebar" />
            </div>
            <div class="col-md-10" style="padding-top: 10px; padding-bottom: 50px; padding-left: 50px; padding-right: 50px; margin: 80px 0 0; box-shadow: 0px -5px 10px rgba(0, 0, 0, 0.1);">
                <tiles:insertAttribute name="content" />
            </div>
        </div>
        <tiles:insertAttribute name="footer" />
    </body>
</html>