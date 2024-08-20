<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-08-12
  Time: 오후 6:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        table tr td.no{
            width: 50px;
        }

        table tr td.name{
            overflow:hidden;
            white-space:nowrap;
            text-overflow:ellipsis;
            max-width:150px;
        }

        table tr td.color {
            padding: 1px 1px;
            margin-right: 5px;
        }

        table tr td img {
            width: 80px;
        }

    </style>
</head>
<body>
<div style="text-align:center">
    <p>총 ${totalCnt}개의 상품이 있습니다.</p>
    <div id="sortOptions">
        <select id="sort" onchange="onChangeSort();">
            <option value="">- 정렬 방식 -</option>
            <option value="firstRegDt"  ${sortBy == 'firstRegDt' ? 'selected': ''}>상품등록순</option>
            <option value="lowPrice"  ${sortBy == 'lowPrice' ? 'selected': ''}>낮은가격순</option>
            <option value="highPrice"  ${sortBy == 'highPrice' ? 'selected': ''}>높은가격순</option>
            <option value="totalSales" ${sortBy == 'totalSales' ? 'selected': ''}>판매량순</option>
        </select>
    </div>
    <table border="1">
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
            <tr onclick="window.location='<c:url value="/products/read?prodNo=${p.prodNo}"/>';" style="cursor:pointer;">
                <td class="no">${p.prodNo}</td>
                <td class="no">${p.product.prodCatgNo}</td>
                <td class="main_img">
                    <img src="<c:url value='${p.mainImg.url}${p.mainImg.fileName}.${p.mainImg.fileExt}'/>">
                </td>
                <td class="name">${p.product.name}</td>
                <td>${p.product.price}</td>
                <td>
                    <c:forEach var="color" items="${p.prodColList}">
                        <span class="color" style="background-color: ${color.colRgbCd}; border-style: solid;">&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    </c:forEach>
                </td>
                <td>${p.product.avgRatg}</td>
                <td>${p.product.totalSales}</td>
                <td>${p.product.viewCnt}</td>
                <td>${p.product.firstRegDt}</td>
            </tr>

        </c:forEach>
    </table>


    <c:if test="${ph.prevPage}">
        <a href="<c:url value='/products?pageNo=${ph.beginPage-1}&pageSize=${ph.pageSize}&prodCatgNo=${prodCatgNo}&sortBy=${sortBy}'/>"+>&lt</a>
    </c:if>
    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
        <a href="<c:url value='/products?pageNo=${i}&pageSize=${ph.pageSize}&prodCatgNo=${prodCatgNo}&sortBy=${sortBy}'/>">${i}</a>
    </c:forEach>
    <c:if test="${ph.nextPage}">
        <a href="<c:url value='/products?pageNo=${ph.endPage+1}&pageSize=${ph.pageSize}&prodCatgNo=${prodCatgNo}&sortBy=${sortBy}'/>">&gt;</a>
    </c:if>
</div>
<script>
    function onChangeSort() {
        var selectElement = document.getElementById("sort");
        var selectedValue = selectElement.value;
        console.log(selectedValue);
        window.location='<c:url value="/products?pageNo=${ph.page}&pageSize=${pageSize}&prodCatgNo=${prodCatgNo}"/>'+"&sortBy="+selectedValue;
    }
</script>
</body>
</html>
