<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.qkart.config.AppConfig" %><%--
  Created by IntelliJ IDEA.
  User: anuma
  Date: 29-07-2023
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details</title>
    <!-- Add Bootstrap CSS link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="Navbar.jsp"/>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Order Details</h3>
                    <div class="table-responsive"> <!-- Added responsive wrapper for the table -->
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Date</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Cancel</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="o" items="${sessionScope.ordersList}" >
                                <tr>
                                    <td><c:out value="${o.orderId}"></c:out></td>
                                    <td><c:out value="${o.createdDate}"></c:out></td>
                                    <td><c:out value="${o.name}"></c:out></td>
                                    <td><c:out value="${o.quantity}"></c:out></td>
                                    <td><c:out value="${o.price}"></c:out></td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/api/protected/order/cancel">
                                            <input type="hidden" name="orderId" value="<c:out value="${o.orderId}"></c:out>" >
                                            <button class="btn btn-danger btn-sm" type="submit">Cancel</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Add Bootstrap JS and jQuery script links -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
