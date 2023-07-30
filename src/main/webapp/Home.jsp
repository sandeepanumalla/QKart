<%--
  Created by IntelliJ IDEA.
  User: anuma
  Date: 29-07-2023
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>E-commerce Home</title>
  <!-- Add Bootstrap CSS link -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!-- Add custom CSS for styling -->
  <style>
    /* Add your custom CSS styles here */
    body {
      background-color: #f9f9f9;
    }
    .product-card {
      border: 1px solid #e0e0e0;
      border-radius: 5px;
      padding: 20px;
      margin-bottom: 20px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .product-image {
      width: 100%;
      height: 200px;
      object-fit: cover;
      border-radius: 5px;
      margin-bottom: 10px;
    }
    .product-title {
      font-size: 18px;
      font-weight: bold;
      margin-bottom: 10px;
    }
    .product-price {
      font-size: 16px;
      font-weight: bold;
      color: #007bff;
      margin-bottom: 10px;
    }
    .btn-add-to-cart {
      background-color: #007bff;
      color: #fff;
      border: none;
    }
    .btn-add-to-cart:hover {
      background-color: #0056b3;
    }
    .jumbotron {
      background-image: url('banner.jpg');
      background-size: cover;
      color: #fff;
      text-align: center;
      padding: 100px 0;
    }
    .jumbotron h1 {
      font-size: 48px;
      font-weight: bold;
      margin-bottom: 30px;
    }
    .jumbotron p {
      font-size: 18px;
    }
  </style>
</head>
<body>

<!-- Navbar -->
<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--  <div class="container">--%>
<%--    <a class="navbar-brand" href="#">E-commerce Store</a>--%>
<%--    <!-- Add other navigation links as needed -->--%>
<%--    <div class="collapse navbar-collapse" id="navbarSupportedContent">--%>
<%--      <ul class="navbar-nav ml-auto">--%>
<%--        <li class="nav-item active">--%>
<%--          <a class="nav-link" href="#">Home</a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--          <a class="nav-link" href="#">Products</a>--%>
<%--        </li>--%>
<%--&lt;%&ndash;        <li class="nav-item">&ndash;%&gt;--%>
<%--&lt;%&ndash;          <a class="nav-link" href="#">Cart</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </li>&ndash;%&gt;--%>
<%--        <li class="nav-item">--%>
<%--          <a class="nav-link" href="#">Account</a>--%>
<%--        </li>--%>
<%--      </ul>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</nav>--%>
<jsp:include page="Navbar.jsp" />
<!-- Banner Section -->
<section class="jumbotron">
  <div class="container text-dark">
    <h1>Welcome to Our E-commerce Store</h1>
    <p>Shop the best products with amazing deals!</p>
    <a href="<%=request.getContextPath()%>/products" class="btn btn-primary">Explore Now</a>
  </div>
</section>

<!-- Product Listing Section -->
<div class="container">
  <div class="row">
    <!-- Product Card -->
    <div class="col-md-4">
      <div class="product-card">
        <img src="product_image.jpg" alt="Product Image" class="product-image">
        <div class="product-details">
          <div class="product-title">Product 1</div>
          <div class="product-price">$50.00</div>
          <button class="btn btn-add-to-cart">Add to Cart</button>
        </div>
      </div>
    </div>

    <!-- Add more product cards here -->
<%--     For example:--%>
    <div class="col-md-4">
        <div class="product-card">
            <img src="product_image.jpg" alt="Product Image" class="product-image">
            <div class="product-details">
                <div class="product-title">Product 2</div>
                <div class="product-price">$30.00</div>
                <button class="btn btn-add-to-cart">Add to Cart</button>
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
