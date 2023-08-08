<%--
  Created by IntelliJ IDEA.
  User: anuma
  Date: 29-07-2023
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Cart Page</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="Navbar.jsp"/>--%>
<%--    <c:if test="${sessionScope.username == null}">--%>
<%--        <div>--%>
<%--            <jsp:include page="login-required.jsp"/>--%>
<%--        </div>--%>
<%--    </c:if>--%>

<%--&lt;%&ndash;  <h3>This is Cart Page</h3>&ndash;%&gt;--%>


<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<jsp:include page="Navbar.jsp"/>

<c:if test="${sessionScope.username == null}">
    <div>
        <jsp:include page="login-required.jsp"/>
    </div>
</c:if>

<div class="container mt-4">
    <h2>Your Cart</h2>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.cartItemsList}" var="ci">
                <tr>
                    <td><c:out value="${ci.name}"></c:out></td>
                    <td><c:out value="${ci.category}"></c:out></td>
                    <td>$<c:out value="${ci.price}"></c:out></td>
                    <td>
                        <button class="btn btn-secondary btn-sm">-</button>
                        <span class="mx-2"><c:out value="${ci.quantity}"></c:out></span>
                        <button class="btn btn-secondary btn-sm">+</button>
                    </td>
                    <td>
                        <button class="btn btn-danger btn-sm">Cancel</button>
                    </td>
                </tr>
            </c:forEach>
            <!-- Add more rows as needed -->
            </tbody>
        </table>
    </div>
    <div class="text-end">
        <h4>Total Price: $10.00</h4>
        <button class="btn btn-primary">Checkout</button>
    </div>
</div>

</body>
</html>
