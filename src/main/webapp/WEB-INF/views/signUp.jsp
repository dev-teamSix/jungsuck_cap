<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/cart/cartList'/>">Cart</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<form action="" id="form">
    <div style="text-align:center">
        <h1>This is BOARD</h1>
        <h1>This is BOARD</h1>
        <h1>This is BOARD</h1>
        <h1>This is BOARD</h1>
        <h1>This is BOARD</h1>
        <button type="button" id="joinBtn" class="btn btn-light border border-primary btn-lg">회원가입</button>
    </div>
</form>
<script>
    $(document).ready(function () {
        $("#joinBtn").on("click", function () {
            if(!confirm("확인")) return;
            let form = $('#form');
            form.attr("action", "<c:url value='/cart/createCart'/>");
            form.attr("method", "post");
            form.submit();
        });
    });
</script>
</body>
</html>