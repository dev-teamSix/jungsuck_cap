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

<body>
<div class="container">
    <div class="product-images">
        <img id="main-image" src="<c:url value='${pr.mainImg.url}${pr.mainImg.fileName}.${pr.mainImg.fileExt}'/>" alt="Main Product Image">
        <div class="thumbnail-images">
            <c:forEach var="pc" items="${pr.prodColList}">
                <img src="<c:url value='${pc.image.url}${pc.image.fileName}.${pc.image.fileExt}'/>" onclick="changeImage(this.src)">
            </c:forEach>
        </div>
    </div>
    <div class="product-details">
        <h1>${pr.product.name}</h1>
        <p class="rating">⭐ ${pr.product.avgRatg} (${pr.product.reviewCnt}) </p>
        <p class="price">
            ${pr.product.price}
        </p>
        <p>간략 소개: ${pr.product.shortDet}</p>
        <p>색상:</p>
        <select name="color">
            <option value="">-- 필수 색상 선택 --</option>
            <c:forEach var="pc" items="${pr.prodColList}">
                <option value="${pc.productColor.colNo}">${pc.productColor.colName}</option>
            </c:forEach>
        </select>
        <p>최소 ${pr.product.minOrdCnt} 이상 최대 ${pr.product.maxOrdCnt} 이하 주문 가능</p>
        <p>TOTAL (QUANTITY)</p>
        <!-- 상품 선택하면 동적으로 추가되어야 함-->

        <button>BUY IT NOW</button>
        <button>CART</button>
        <button>WISH LIST</button>
    </div>
</div>
<script>
    function changeImage(src) {
        document.getElementById('main-image').src = src;
    }
</script>

</body>
</html>
