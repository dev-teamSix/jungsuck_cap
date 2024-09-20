<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 이력</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/footer.css">
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
    <!-- jQuery, Popper.js, and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/order.css">

    <script>
        let msg = "${msg}"
        if(msg == "CANCEL_OK") alert("주문이 취소되었습니다.");
        if(msg == "CANCEL_ERR") alert("주문 취소에 실패했습니다.");
        if(msg == "ORDER_OK") alert("주문 되었습니다.");
        if(msg == "ORDER_ERR") alert("주문에 실패했습니다.");
        if(msg == "CART_ORDER_OK") alert("주문 되었습니다.");
        if(msg == "CART_ORDER_ERR") alert("주문에 실패했습니다.");
    </script>
</head>

<body>
<jsp:include page="./header.jsp" flush="true" />

<div  class="content-mg">

    <br>
    <h2 class="mb-4">
        주문 이력
    </h2>

    <form action="" id="form">
        <c:forEach var="orderDto" items="${orders}" varStatus="status">
            <div class="d-flex mb-3 align-self-center">
                <h4>${orderDto.ord_dt} 주문</h4>
                <div class="ml-3">
                    <c:if test="${orderDto.ord_st_cd == 'O'}">
                        <input type="hidden" name="ord_no" id="ord_no" value="${orderDto.ord_no}">
                        <button type="button" class="btn btn-outline-secondary cancelBtn" value="${orderDto.ord_no}">주문취소</button>
                    </c:if>
                    <c:if test="${orderDto.ord_st_cd == 'C'}">
                        <h4>(취소 완료)</h4>
                    </c:if>
                </div>
            </div>
            <div id="one-order">
                <c:forEach var="orderItem" items="${orderItemsList[status.index]}">
                    <div class="card d-flex">
                        <div class="d-flex mb-3">
                            <div class="align-self-center w-75">
                                <span class="fs24 font-weight-bold">${orderItem.prod_name}</span>
                                <br>
                                <span class="fs18 font-weight-bold">${orderItem.col_name}</span>
                                <div class="fs18 font-weight-light">
                                    <span>${orderItem.price}원</span>
                                    <span>${orderItem.qty}개</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        <br><br><br>
    </form>
    <br>
    <div style="text-align:center">
        <c:if test="${ph.prevPage}">
            <a href="<c:url value='/order/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
            <a href="<c:url value='/order/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
        </c:forEach>
        <c:if test="${ph.nextPage}">
            <a href="<c:url value='/order/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
        </c:if>
    </div>
</div>

<jsp:include page="./footer.jsp" flush="true" />

<script>
    $(document).ready(function () {    // main()
        $('.cancelBtn').on("click", (e) => {
            if(!confirm("정말로 취소하시겠습니까?")) return;
            $('#ord_no').val(e.target.value);
            let form = $('#form');
            form.attr("action", "<c:url value='/order/cancel?page=${page}&pageSize=${pageSize}'/>");
            form.attr("method", "post");
            form.submit();
        });
    });
</script>
</body>
</html>
