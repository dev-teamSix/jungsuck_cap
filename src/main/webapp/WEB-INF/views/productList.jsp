<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>상품 목록</title>
    <style>
        body {
            font-family: 'Helvetica Neue', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #FFF;
        }

        /* 전체 레이아웃 */
        .container {
            display: flex;
            padding: 20px;
            max-width: 1200px;
            margin: 0 auto;
        }

        /* 카테고리 레이아웃 */
        .category-menu {
            width: 220px;
            margin-right: 20px;
            padding: 20px;
            background-color: #fff;
            /*box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);*/
            border-radius: 10px;
            position: relative;
        }

        .category-menu h2 {
            font-size: 24px;
            margin-bottom: 15px;
            color: #333;
            font-weight: bold;
        }

        .category-menu ul {
            list-style: none;
            padding: 0;
        }

        .category-menu li {
            margin-bottom: 15px;
            position: relative;
        }

        .category-menu li.high {
            font-size: 18px;
            font-weight: bold;
            color: #333;
            cursor: pointer;
        }

        .category-menu li.low {
            font-size: 14px;
            margin-left: 15px;
            color: #555;
            display: none;
        }

        .category-menu li a {
            text-decoration: none;
            color: inherit;
            display: block;
            padding: 8px;
            border-radius: 5px;
        }

        .category-menu li a:hover {
            background-color: rgba(14, 5, 5, 0.62);
            color: white;
        }

        .category-menu li:hover .low {
            display: block;
        }

        .category-menu .separator {
            height: 1px;
            background-color: #000000;
            width: 100%;
            margin: 10px 0;
        }

        /* 상품 목록 컨테이너 */
        .product-list-container {
            flex-grow: 1;
        }

        /* 상품 개수 및 정렬 기준 */
        .product-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .product-header p {
            font-size: 18px;
            color: #333;
        }

        .sort-options select {
            padding: 10px;
            font-size: 14px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        /* 검색창 스타일 */
        .search-form {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .search-input {
            padding: 12px;
            font-size: 16px;
            width: 350px;
            border: 2px solid #ddd;
            border-radius: 25px;
            outline: none;
            transition: border-color 0.3s;
        }

        .search-input:focus {
            border-color: rgba(0, 0, 0, 0.62);
        }

        .search-button {
            padding: 12px 20px;
            font-size: 16px;
            border: none;
            background-color: #000000;
            color: white;
            cursor: pointer;
            border-radius: 25px;
            margin-left: 10px;
        }

        .search-button:hover {
            background-color: rgba(0, 0, 0, 0.68);
        }

        /* 상품 목록 스타일 */
        .product-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 30px;
        }

        .product-item {
            background-color: #fff;
            border-radius: 10px;
            overflow: hidden;
            /*box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);*/
            transition: transform 0.3s, box-shadow 0.3s;
            text-align: center;
        }

        .product-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.15);
        }

        .product-img img {
            width: 70%;
            height: auto;
        }

        .product-info {
            padding: 10px;
            text-align: center;
        }

        .product-info h3 {
            font-size: 15px;
            color: #333;
            margin: 10px 0;
        }

        .product-info p.price {
            font-size: 15px;
            font-weight: bold;
            color: #e74c3c;
            margin: 5px 0;
        }

        .product-info .old-price {
            font-size: 14px;
            color: #999;
            text-decoration: line-through;
            margin-bottom: 10px;
        }

        .product-manage-button {
            padding: 10px 10px;
            font-size: 10px;
            border: none;
            background-color: rgba(0, 0, 0, 0.22);
            color: white;
            cursor: pointer;
            border-radius: 25px;
            margin-left: 10px;
        }

        .color-chips {
            display: flex;
            justify-content: center;
            gap: 5px;
        }

        .color-chips span {
            width: 20px;
            height: 20px;
            border-radius: 50%;
            border: 1px solid #ddd;
        }

        /* 페이지네이션 */
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 30px;
        }

        .pagination a {
            color: #000000;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            border: 1px solid #000000;
            margin: 0 5px;
            transition: background-color 0.3s;
        }

        .pagination a:hover {
            background-color: #000000;
            color: white;
        }
    </style>
</head>
<body>
<script>
    let msg = "${msg}";
    if (msg) {
        alert(msg);
    }
</script>

<div class="container">
    <!-- 왼쪽 카테고리 메뉴 -->
    <div class="category-menu">
        <h2>카테고리</h2>
        <div class="separator"></div>
        <ul>
            <li class="high">
                <a href="<c:url value="/product/list"/>">전체</a>
            </li>
            <div class="separator"></div>
            <c:forEach var="catg" items="${catgList}">
                <li class="high">
                    <a href="<c:url value="/product/list?catgNo=${catg.highCatgNo}"/>">${catg.highName}</a>
                    <ul>
                        <c:forEach var="low" items="${catg.lowCategoryList}">
                            <li class="low"><a href="<c:url value="/product/list?catgNo=${low.catgNo}"/>">${low.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <div class="separator"></div>
            </c:forEach>
        </ul>
    </div>

    <!-- 오른쪽 상품 목록 -->
    <div class="product-list-container">
        <!-- 검색 창 -->
        <div class="search-form">
            <form action="<c:url value="/product/list"/>" method="get">
                <input type="text" name="keyword" class="search-input" value="${sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="submit" class="search-button" value="검색">
            </form>
        </div>

        <!-- 상품 개수 및 정렬 기준 -->
        <div class="product-header">
            <p>총 <strong>${totalCnt}</strong>개의 상품이 있습니다.</p>
            <div class="sort-options">
                <select id="sort" class="sort" onchange="onChangeSort();">
                    <option value="">- 정렬 방식 -</option>
                    <option value="firstRegDt" ${sc.sortBy == 'firstRegDt' ? 'selected' : ''}>상품등록순</option>
                    <option value="lowPrice" ${sc.sortBy == 'lowPrice' ? 'selected' : ''}>낮은가격순</option>
                    <option value="highPrice" ${sc.sortBy == 'highPrice' ? 'selected' : ''}>높은가격순</option>
                    <option value="totalSales" ${sc.sortBy == 'totalSales' ? 'selected' : ''}>판매량순</option>
                </select>
            </div>
        </div>

        <!-- 상품 목록 -->
        <div class="product-list">
            <c:forEach var="p" items="${productList}">
                <div class="product-item">
                    <div class="product-img">
                        <a href="<c:url value="/product/read?prodNo=${p.prodNo}"/>">
                            <!-- 이미지가 있으면 해당 이미지 표시, 없으면 기본 이미지 표시 -->
                            <img src=<c:choose>
                                 <c:when test="${p.mainImg.url != null && p.mainImg.fileName != null}">
                                         "<c:url value="${p.mainImg.url}${p.mainImg.fileName}.${p.mainImg.fileExt}"/>"
                            </c:when>
                            <c:otherwise>
                                "<c:url value='/resources/images/product/default.png' />"
                            </c:otherwise>
                            </c:choose>
                            alt="${p.product.name}"
                            >
                        </a>
                    </div>
                    <div class="product-info">
                        <h3>${p.product.name}</h3>
                        <p class="price">${p.product.price}원</p>
                        <div class="color-chips">
                            <c:forEach var="color" items="${p.prodColList}">
                                <span style="background-color: ${color.colRgbCd};"></span>
                            </c:forEach>
                        </div>
                    </div>
                    <a href="<c:url value="/product/admin/read?prodNo=${p.prodNo}"/>">
                        <button type="button" class="product-manage-button">관리</button>
                    </a>
                </div>
            </c:forEach>
        </div>

        <!-- 페이지네이션 -->
        <div class="pagination">
            <c:if test="${ph.prevPage}">
                <a href="<c:url value='/product/list${sc.getQueryString(ph.beginPage-1)}&sortBy=${sc.sortBy}'/>'">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="<c:url value='/product/list${sc.getQueryString(i)}&sortBy=${sc.sortBy}'/>'">${i}</a>
            </c:forEach>
            <c:if test="${ph.nextPage}">
                <a href="<c:url value='/product/list${sc.getQueryString(ph.endPage+1)}&sortBy=${sc.sortBy}'/>'">&gt;</a>
            </c:if>
        </div>
    </div>
</div>

<script>
    function onChangeSort() {
        var selectElement = document.getElementById("sort");
        var selectedValue = selectElement.value;
        window.location = '<c:url value="/product/list${sc.queryString}"/>' + "&sortBy=" + selectedValue;
    }
</script>

</body>
</html>
