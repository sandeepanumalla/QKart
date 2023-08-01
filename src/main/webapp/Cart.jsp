<%--
  Created by IntelliJ IDEA.
  User: anuma
  Date: 29-07-2023
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<jsp:include page="Navbar.jsp"/>
    <c:if test="${user == null}">
        <div>
            <jsp:include page="login-required.jsp"/>
        </div>
    </c:if>

<%--  <h3>This is Cart Page</h3>--%>


</body>
</html>
