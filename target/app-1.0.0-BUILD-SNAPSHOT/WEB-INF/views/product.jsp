<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-08-12
  Time: 오후 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/product.css'/>"/>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<form action="" id="form">
<div class="container">
    <input type="hidden" name="prod_no" id="prod_no" value=${pr.product.prodNo}>
    <input type="hidden" id="from_cart" name="from_cart" value="0">
<%--    <input type="hidden" id="from_cart" name="from_cart" value="${maxOrdNo}">--%>
    <div class="product-images">
        <img id="main-image" src="<c:url value='${pr.mainImg.url}${pr.mainImg.fileName}.${pr.mainImg.fileExt}'/>" alt="Main Product Image">
        <div class="thumbnail-images">
            <c:forEach var="pc" items="${pr.prodColList}">
                <img src="<c:url value='${pc.image.url}${pc.image.fileName}.${pc.image.fileExt}'/>" onclick="changeImage(this.src)">
            </c:forEach>
        </div>
    </div>
    <div class="product-details">
        <input type="hidden" value="${pr.product.name}" id="prod_name" name="prod_name">
        <h1>${pr.product.name}</h1>
        <p class="rating">⭐ ${pr.product.avgRatg} (${pr.product.reviewCnt}) </p>
        <input type="hidden" value="${pr.product.price}" id="price" name="price">
        <p class="price">
            ${pr.product.price}
        </p>
        <p>간략 소개: ${pr.product.shortDet}</p>
        <p>색상:</p>
        <select id="color" name="color">
            <option value="">-- 필수 색상 선택 --</option>
            <c:forEach var="pc" items="${pr.prodColList}">
                <option value="${pc.productColor.colNo}">${pc.productColor.colName}</option>
            </c:forEach>
        </select>
        <p>최소 ${pr.product.minOrdCnt} 이상 최대 ${pr.product.maxOrdCnt} 이하 주문 가능</p>
        <input type="number" name="qty" id="qty" class="form-control" value="1"
               min="${pr.product.minOrdCnt}" max="${pr.product.maxOrdCnt}" onsubmit="return false">
        <p>TOTAL (QUANTITY)</p>
        <!-- 상품 선택하면 동적으로 추가되어야 함-->

        <button type="button" id="orderBtn">BUY IT NOW</button>
        <button type="button" id="cartBtn">CART</button>
        <button>WISH LIST</button>
    </div>
</div>
</form>
<script>
    $(document).ready(function () {

        $("#qty").on("keyup change click", (e) => {
            if (e.target.value >= 0) {
                if (e.target.value.length > 5) {
                    if (e.target.value > 10000) {
                        alert("최대 9,999까지 입력가능");
                    }
                    e.target.value = e.target.value.slice(0, 5);
                }
            } else {
                e.target.value = 0;
                alert("음수값을 입력할 수 없습니다.");
            }
        });

        $('#orderBtn').on("click", function () {
            var colorValue = document.getElementById("color").value;
            if(colorValue != "") {
                if(!confirm("정말로 주문하시겠습니까?")) return;
                let form = $('#form');
                form.attr("action", "<c:url value='/order/ordering?page=${page}&pageSize=${pageSize}'/>");
                form.attr("method", "post");
                form.submit();
            } else {
                alert("색상을 선택하세요.");
            }
        });

        $('#cartBtn').on("click", function () {
            var colorValue = document.getElementById("color").value;
            if(colorValue != "") {
                if(!confirm("카트에 넣으시겠습니까?")) return;
                let form = $('#form');
                form.attr("action", "<c:url value='/cart/insertCartItem'/>");
                form.attr("method", "post");
                form.submit();
            } else {
                alert("색상을 선택하세요.");
            }
        });
    });

    function changeImage(src) {
        document.getElementById('main-image').src = src;
    }
</script>
</body>
</html>
