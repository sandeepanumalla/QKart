<%--
  Created by IntelliJ IDEA.
  User: anuma
  Date: 29-07-2023
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Website</title>
    <!-- Add Bootstrap CSS link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/Home.jsp">Shopping Website</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
                <li class="nav-item">
                    <%-- Check if the username attribute is present in the session --%>
                    <% String username = (String) session.getAttribute("username"); %>
                        <c:choose>
                            <c:when test="${username != null}">
                                <a class="nav-link" href="#"><c:out value="${username}" /></a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" href="<%=request.getContextPath()%>/login">Login</a>
                            </c:otherwise>
                        </c:choose>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/api/protected/cart-items">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                            <path d="M0 1.053A.053.053 0 0 1 .053 1h1.397l1.007 7.051A2 2 0 0 0 4.441 10H14a1 1 0 0 1 1 1 1 1 0 0 1-1 1H4.441a2 2 0 1 0-1.964 1.549l-.504 3.529A.5.5 0 0 1 2 16h12a.5.5 0 0 1 .481.374l1 4A.5.5 0 0 1 15 21H1a.5.5 0 0 1-.481-.626l1-4A.5.5 0 0 1 2 16h1.344a2.5 2.5 0 0 1 4.908 0h3.296a2.5 2.5 0 0 1 4.908 0H16a1 1 0 0 1-1-1 1 1 0 0 1 1-1h1a.053.053 0 0 1 .053.053V1.053A.053.053 0 0 1 16 1h-4.952a2.5 2.5 0 1 1-4.096 0H0a.053.053 0 0 1 .053.053z"/>
                        </svg>
                        Cart <span class="badge badge-danger">
                        <c:choose>
                            <c:when test="${not empty sessionScope.cartSize}">
                                ${sessionScope.cartSize}
                            </c:when>
                            <c:otherwise>
                                0
                            </c:otherwise>
                        </c:choose>
                    </span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Add Bootstrap JS and jQuery script links -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
