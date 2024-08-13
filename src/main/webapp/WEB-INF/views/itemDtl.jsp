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
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<script>
    let msg = "${msg}"
</script>
<form action="" id="form">
    <div class="content" style="margin-left:25%;margin-right:25%">

        <input type="hidden" name="prod_num" id="prod_num" value="1">

        <div class="d-flex">

            <div class="wd50">
                <span class="badge badge-primary mgb-15">
                    판매중
                </span>

                <input type="hidden" value="스몰 클래식 N 볼캡" id="prod_name" name="prod_name">
                <div class="h4">스몰 클래식 N 볼캡</div>
                <hr class="my-4">

                <div class="text-right">
                    <div class="h4 text-danger text-left">
                        <input type="hidden" value="1000" id="price" name="price">
                        <span>1000</span>원
                    </div>
                    <div class="input-group w-50">
                        <div class="input-group-prepend">
                            <span class="input-group-text">수량</span>
                        </div>
                        <input type="number" name="qty" id="qty" class="form-control" value="1" min="1">
                    </div>
                </div>
                <hr class="my-4">

                <div class="text-right mgt-50">
                    <h5>결제 금액</h5>
                    <h3 name="totalPrice" id="totalPrice" class="font-weight-bold"></h3>
                </div>
                <div class="text-right">
                    <button type="button" id="cartBtn" class="btn btn-light border border-primary btn-lg">장바구니 담기</button>
                    <button type="button" id="orderBtn" class="btn btn-light border border-primary btn-lg">주문하기</button>
                </div>

            </div>
        </div>

        <div class="jumbotron jumbotron-fluid mgt-30">
            <div class="container">
                <h4 class="display-5">상품 상세 설명</h4>
                <hr class="my-4">
                <p class="lead">설명: 스몰 클래식 N 볼캡</p>
            </div>
        </div>
    </div>
</form>
<script>
    $(document).ready(function () {
        calculateTotalPrice();

        $("#qty").change( function(){
            calculateTotalPrice();
        });

        $('#orderBtn').on("click", function () {
            if(!confirm("정말로 주문하시겠습니까?")) return;
            let form = $('#form');
            form.attr("action", "<c:url value='/order/ordering?page=${page}&pageSize=${pageSize}'/>");
            form.attr("method", "post");
            form.submit();
        });
    });

    function calculateTotalPrice(){
        var qty = $("#qty").val();
        var price = $("#price").val();
        var totalPrice = price*qty;
        $("#totalPrice").html(totalPrice + '원');
    }
</script>
</body>
</html>