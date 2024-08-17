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
<script>
    let msg = "${msg}"
    if(msg == "CANCEL_OK") alert("주문이 취소되었습니다.");
    if(msg == "CANCEL_ERR") alert("주문 취소에 실패했습니다.");
    if(msg == "ORDER_OK") alert("주문 되었습니다.");
    if(msg == "ORDER_ERR") alert("주문에 실패했습니다.");
    if(msg == "CART_ORDER_OK") alert("주문 되었습니다.");
    if(msg == "CART_ORDER_ERR") alert("주문에 실패했습니다.");
</script>
<div  class="content-mg">

    <h2 class="mb-4">
        주문 이력
    </h2>
    <br>

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
            <c:forEach var="orderItem" items="${orderItemsList[status.index]}">
                <div class="card d-flex">
                    <div class="d-flex mb-3">
                        <div class="align-self-center w-75">
                            <span class="fs24 font-weight-bold">${orderItem.prod_name}</span>
                            <div class="fs18 font-weight-light">
                                <span>${orderItem.price}원</span>
                                <span>${orderItem.qty}개</span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:forEach>
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