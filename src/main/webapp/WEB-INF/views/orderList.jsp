<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
<header class="main-header">
    <div class="container">
        <a href="/" class="logo">
            <span class="logo-lg"><b>모자의 정석</b></span>
        </a>
        <nav class="navbar navbar-expand-lg navbar-dark">
            <a href="#" class="navbar-toggler" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <c:choose>
                        <c:when test="${!empty sessionUser}">
                            <button type="button" id="toCart" onclick="location.href='/cart/cartList'">장바구니</button>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${sessionUser.name}
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <p class="dropdown-item">
                                        가입일자 : <fmt:formatDate value="${sessionUser.regDt}" pattern="yyyy-MM-dd"/> <br>
                                        최근 로그인일자 : <fmt:formatDate value="${sessionUser.recentLogin}" pattern="yyyy-MM-dd"/>
                                    </p>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">게시글</a>
                                    <a class="dropdown-item" href="#">공지사항</a>
                                    <a class="dropdown-item" href="#">Q&A</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/login/out" id="logoutButton">
                                        <i class="glyphicon glyphicon-log-out"></i> 로그아웃
                                    </a>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    회원가입 또는 로그인
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <p class="dropdown-item">회원가입 또는 로그인해주세요</p>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="<c:url value='/user/register'/>">
                                        <i class="fa fa-user-plus"></i> 회원가입
                                    </a>
                                    <a class="dropdown-item" href="<c:url value='/login/form'/>">
                                        <i class="glyphicon glyphicon-log-in"></i> 로그인
                                    </a>
                                </div>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
    </div>
</header>

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