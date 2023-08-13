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

<c:choose>
    <c:when test="${sessionScope.username == null}">
        <div>
            <jsp:include page="login-required.jsp"/>
        </div>
    </c:when>
    <c:when test="${sessionScope.cartItemsList.size() == 0}">
        <div class="container mt-4">
            <h2>You have no items in Cart</h2>
        </div>
    </c:when>
    <c:otherwise>
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
                            <td><c:out value="${ci.product.name}"></c:out></td>
                            <td><c:out value="${ci.product.category}"></c:out></td>
                            <td>$<c:out value="${ci.product.price}"></c:out></td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/api/protected/decrease-items">
                                    <input type="hidden" name="productId" value="<c:out value="${ci.product.productId}"></c:out>">
                                    <input type="hidden" name="quantity" value="<c:out value="${ci.quantity}"></c:out>">
                                    <button class="btn btn-secondary btn-sm decrease-quantity">-</button>
                                </form>
                                <span class="mx-2 quantity"><c:out value="${ci.quantity}"></c:out></span>
                                <form action="${pageContext.request.contextPath}/api/protected/increase-items" method="post">
                                    <input type="hidden" name="productId" value="<c:out value="${ci.product.productId}"></c:out>">
                                    <input type="hidden" name="quantity" value="<c:out value="${ci.quantity}"></c:out>">
                                    <button class="btn btn-secondary btn-sm increase-quantity">+</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/api/protected/buy-now" method="post">
                                    <input type="hidden" name="productId" value="<c:out value="${ci.product.productId}"></c:out>">
                                    <input type="hidden" name="quantity" value="<c:out value="${ci.quantity}" ></c:out>">
                                    <button type="submit" class="btn btn-primary">Buy Now</button>
                                </form>
                            </td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/api/protected/cart-items/remove">
                                    <input type="hidden" name="productId" value="<c:out value="${ci.product.productId}"></c:out>">
                                    <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <!-- Add more rows as needed -->
                    </tbody>
                </table>
            </div>
            <div class="text-end">
                <h4>Total Price: $<c:out value="${sessionScope.totalCartPrice}"></c:out></h4>
                <form method="post" action="${pageContext.request.contextPath}/api/protected/checkout-all">
                    <button type="submit" class="btn btn-primary">Checkout</button>
                </form>

            </div>
        </div>
    </c:otherwise>
</c:choose>



<script>
    // console.log("is it working" )
    // const quantityElements = document.querySelectorAll(".quantity");
    // const increaseButtons = document.querySelectorAll(".increase-quantity");
    // const decreaseButton = document.querySelectorAll(".decrease-quantity");
    //
    // for (let i = 0; i < increaseButtons.length; i++) {
    //     increaseButtons[i].addEventListener('click', () => {
    //         let currentQuantity = parseInt(quantityElements[i].textContent);
    //
    //         currentQuantity++;
    //
    //         quantityElements[i].textContent = currentQuantity.toString();
    //     });
    // }
    //
    // for (let i = 0; i < decreaseButton.length; i++) {
    //     decreaseButton[i].addEventListener('click', () => {
    //         let currentQuantity = parseInt(quantityElements[i].textContent);
    //
    //         if (currentQuantity > 1) {
    //             currentQuantity--;
    //
    //             quantityElements[i].textContent = currentQuantity.toString();
    //         }
    //     });
    // }
</script>
</body>
</html>
