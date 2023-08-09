<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: anuma--%>
<%--  Date: 29-07-2023--%>
<%--  Time: 14:57--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--&lt;%&ndash;<%@ include file="Navbar.jsp" %>&ndash;%&gt;--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Products</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">--%>
<%--</head>--%>
<%--<body>--%>
<%--    <c:set var="a" value="10"></c:set>--%>
<%--&lt;%&ndash;    <h1>This is products page <c:out value="${productList.size()}"></c:out></h1>&ndash;%&gt;--%>
<%--    <jsp:include page="Navbar.jsp"/>--%>
<%--    <%%>--%>
<%--    <div class="card mt-3">--%>
<%--        <div class="card-body">--%>
<%--            All Products--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="container mt-4">--%>
<%--        <div class="row">--%>
<%--            <!-- Product Card -->--%>
<%--            <c:forEach items="${productList}" var="p">--%>
<%--                <div class="col-md-3">--%>
<%--                        <div class="card">--%>
<%--                            <img src="product_image.jpg" class="card-img-top" alt="Product Image">--%>
<%--                            <div class="card-body">--%>
<%--                                <h3 class="card-title"><c:out value="${p.name}"></c:out></h3>--%>
<%--                                <p class="card-text">Price: <c:out value="$ ${p.price}"></c:out></p>--%>
<%--                                <p class="catd-text">Category: null</p>--%>
<%--                                <div class="d-flex justify-content-evenly">--%>
<%--                                    <a href="#" class="btn btn-dark text-center">Add to Cart</a>--%>
<%--                                    <a href="#" class="btn btn-primary text-center">Buy Now</a>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</body>--%>
<%--</html>--%>
<%--
  Created by IntelliJ IDEA.
  User: anuma
  Date: 29-07-2023
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ include file="Navbar.jsp" %>--%>
<html>
<head>
    <title>Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .product-card {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<c:set var="a" value="10"></c:set>
<%--    <h1>This is products page <c:out value="${productList.size()}"></c:out></h1>--%>
<jsp:include page="Navbar.jsp"/>
<!-- ... Your previous HTML code ... -->

<div class="container mt-3">
    <div class="card">
        <div class="card-body">
            All Products
        </div>
    </div>
    <div class="row mt-4 d-flex flex-wrap">
        <!-- Product Card -->

        <c:forEach items="${productList}" var="p">
            <div class="col-md-6 col-lg-3 product-card mt-auto">
                <div class="card h-100">
                    <img src="<c:out value="${p.imageURL}"></c:out>" class="card-img-top" alt="Product Image">
                    <div class="card-body text-center">

                        <h3 class="card-title"><c:out value="${p.name}"></c:out></h3>
                        <div class="product-price">$<c:out value="${p.price}"></c:out></div>
                        <p class="card-text">Category: null</p>
                        <div class="d-flex justify-content-between">
                            <form action="<%=request.getContextPath()%>/api/protected/addToCart" method="post">
                                <input type="hidden" name="username" value="<c:out value="${requestScope.username}"></c:out>">
                                <input type="hidden" name="productId" value="<c:out value="${p.productId}"></c:out>">
                                <input type="hidden" name="quantity" value=1>
                                <button type="submit" class="btn btn-dark">Add to Cart</button>
                            </form>

                            <form action="addToCart" method="post">
<%--                                <input type="hidden" name="productId" value="<c:out value="${p.id}"/>">--%>
                                <button type="submit" class="btn btn-primary">Buy Now</button>
                            </form>
<%--                            <a href="#" class="btn btn-dark">Add to Cart</a>--%>
<%--                            <a href="#" class="btn btn-primary">Buy Now</a>--%>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>

</body>
</html>
