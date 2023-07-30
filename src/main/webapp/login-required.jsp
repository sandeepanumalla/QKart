<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Please Login to View Cart</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container text-center mt-5">
    <div class="card mx-auto">
        <div class="card-body">
            <h1 class="card-title mb-4">Please Login to View Cart</h1>
            <p class="card-text lead">You need to be logged in to view your cart contents. Please login or create an account.</p>
            <a href="<%=request.getContextPath()%>/Login.jsp" class="btn btn-primary">Login</a>
            <a href="<%=request.getContextPath()%>/Register.jsp" class="btn btn-success">Create Account</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS and jQuery (optional for Bootstrap features) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
