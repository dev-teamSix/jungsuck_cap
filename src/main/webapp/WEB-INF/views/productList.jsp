<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>상품 목록</title>
    <style>
        /* 전체 레이아웃 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        /* 레이아웃 구성: 왼쪽 카테고리, 오른쪽 상품 목록 */
        .container {
            display: flex;
            padding: 20px;
        }

        /* 카테고리 레이아웃 */
        .category-menu {
            width: 200px;
            margin-right: 20px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
            margin-bottom: 10px;
        }

        .category-menu li.high {
            font-size: 18px;
            font-weight: bold;
            color: #000;
        }

        .category-menu li.low {
            font-size: 14px;
            margin-left: 20px;
            color: #666;
        }

        .category-menu li a {
            text-decoration: none;
            color: inherit;
        }

        .category-menu li.low a:hover {
            text-decoration: underline;
            color: #3498db;
        }

        .category-menu .separator {
            height: 2px;
            background-color: #e74c3c;
            width: 20px;
            margin: 10px 0;
        }

        /* 상품 목록 레이아웃 */
        .product-list {
            flex-grow: 1;
        }

        h1 {
            text-align: center;
            color: #333;
            padding: 20px;
        }

        /* 테이블 스타일 */
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:hover {
            background-color: #f2f2f2;
        }

        .product-list img {
            width: 80px;
        }

        /* 검색 폼 */
        .search-form {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .search-input {
            padding: 10px;
            font-size: 16px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .search-button {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            background-color: #3498db;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            margin-left: 10px;
        }

        .search-button:hover {
            background-color: #2980b9;
        }

        /* 정렬 옵션 */
        .sort-options {
            text-align: center;
            margin-bottom: 20px;
        }

        .sort-options select {
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        /* 페이지네이션 스타일 */
        .pagination {
            display: flex;
            justify-content: center;
            padding: 20px 0;
        }

        .pagination a {
            color: #3498db;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            border: 1px solid #3498db;
            margin: 0 5px;
            transition: background-color 0.3s;
        }

        .pagination a:hover {
            background-color: #3498db;
            color: white;
        }
    </style>
</head>
<body>
<script>
    let msg = "${msg}"
    if(msg) {
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
                    <a href="<c:url value="/product/list?catgNo=${catg.highCatgNo}"/>">
                            ${catg.highName}
                    </a>
                </li>
                <div class="separator"></div>
                <c:forEach var="low" items="${catg.lowCategoryList}">
                    <li class="low"><a href="<c:url value="/product/list?catgNo=${low.catgNo}"/>">${low.name}</a></li>
                </c:forEach>
            </c:forEach>
        </ul>
    </div>

    <!-- 오른쪽 상품 목록 -->
    <div class="product-list">
        <h1>상품 목록</h1>

        <!-- 검색 창 -->
        <div>
            <form action="<c:url value="/product/list"/>" class="search-form" method="get">
                <input type="text" name="keyword" class="search-input" value="${sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="submit" class="search-button" value="검색">
            </form>
        </div>

        <!-- 등록 버튼 -->
        <a href="<c:url value="/product/register"/>"><button type="button">등록</button></a>

        <p>총 ${totalCnt}개의 상품이 있습니다.</p>

        <!-- 정렬 옵션 -->
        <div id="sort-options" class="sort-options">
            <select id="sort" class="sort" onchange="onChangeSort();">
                <option value="">- 정렬 방식 -</option>
                <option value="firstRegDt"  ${sc.sortBy == 'firstRegDt' ? 'selected': ''}>상품등록순</option>
                <option value="lowPrice"  ${sc.sortBy == 'lowPrice' ? 'selected': ''}>낮은가격순</option>
                <option value="highPrice"  ${sc.sortBy == 'highPrice' ? 'selected': ''}>높은가격순</option>
                <option value="totalSales" ${sc.sortBy == 'totalSales' ? 'selected': ''}>판매량순</option>
            </select>
        </div>


        <table>
            <tr>
                <th>상품 번호</th>
                <th>상품 카테고리 번호</th>
                <th>대표이미지</th>
                <th>상품명</th>
                <th>가격</th>
                <th>색상표</th>
                <th>별점</th>
                <th>판매량</th>
                <th>조회수</th>
                <th>등록날짜</th>
            </tr>
            <c:forEach var="p" items="${productList}">
                <tr onclick="window.location='<c:url value='/product/read?prodNo=${p.prodNo}'/>'" style="cursor: pointer;">
                    <td>${p.prodNo}</td>
                    <td>${p.product.prodCatgNo}</td>
                    <td><img src="<c:url value='${p.mainImg.url}${p.mainImg.fileName}.${p.mainImg.fileExt}'/>" alt="상품 이미지"></td>
                    <td>${p.product.name}</td>
                    <td>${p.product.price}</td>
                    <td>
                        <c:forEach var="color" items="${p.prodColList}">
                            <span class="color" style="background-color: ${color.colRgbCd};"></span>
                        </c:forEach>
                    </td>
                    <td>${p.product.avgRatg}</td>
                    <td>${p.product.totalSales}</td>
                    <td>${p.product.viewCnt}</td>
                    <td>${p.product.firstRegDt}</td>
                </tr>
                <td>
                    <button type="button" onclick="window.location='<c:url value="/product/admin/read${sc.queryString}&prodNo=${p.prodNo}"/>'">관리</button>
                </td>
            </c:forEach>
        </table>

        <!-- 페이지네이션 -->
        <div class="pagination">
            <c:if test="${ph.prevPage}">
                <a href="<c:url value='/product/list${sc.getQueryString(ph.beginPage-1)}'/>">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="<c:url value='/product/list${sc.getQueryString(i)}'/>">${i}</a>
            </c:forEach>
            <c:if test="${ph.nextPage}">
                <a href="<c:url value='/product/list${sc.getQueryString(ph.endPage+1)}'/>">&gt;</a>
            </c:if>
        </div>
    </div>
</div>

<script>
    function onChangeSort() {
        var selectElement = document.getElementById("sort");
        var selectedValue = selectElement.value;
        window.location='<c:url value="/product/list${sc.queryString}"/>'+"&sortBy="+selectedValue;
    }

</script>

</body>
</html>
