<%--
  Created by IntelliJ IDEA.
  User: anuma
  Date: 29-07-2023
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar navbar-light bg-light">--%>
<%--&lt;%&ndash;  <div class="container-fluid">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <a class="navbar-brand" href="#">QKart</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;      <div class="col-md-3 d-flex justify-content-between">&ndash;%&gt;--%>
<%--&lt;%&ndash;          <a href="#" class="navbar-text">Home</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;          <a href="#" class="navbar-text">Login</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;          <a href="<%=request.getContextPath()%>/Cart.jsp" class="navbar-text">Cart</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;      </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;  </div>&ndash;%&gt;--%>
<%--</nav>--%>
<%--</body>--%>
<%--</html>--%>
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
        <a class="navbar-brand" href="#">Shopping Website</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/Home.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/Cart.jsp">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                            <path d="M0 1.053A.053.053 0 0 1 .053 1h1.397l1.007 7.051A2 2 0 0 0 4.441 10H14a1 1 0 0 1 1 1 1 1 0 0 1-1 1H4.441a2 2 0 1 0-1.964 1.549l-.504 3.529A.5.5 0 0 1 2 16h12a.5.5 0 0 1 .481.374l1 4A.5.5 0 0 1 15 21H1a.5.5 0 0 1-.481-.626l1-4A.5.5 0 0 1 2 16h1.344a2.5 2.5 0 0 1 4.908 0h3.296a2.5 2.5 0 0 1 4.908 0H16a1 1 0 0 1-1-1 1 1 0 0 1 1-1h1a.053.053 0 0 1 .053.053V1.053A.053.053 0 0 1 16 1h-4.952a2.5 2.5 0 1 1-4.096 0H0a.053.053 0 0 1 .053.053z"/>
                        </svg>
                        Cart <span class="badge badge-secondary">3</span>
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
