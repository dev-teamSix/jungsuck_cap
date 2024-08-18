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
        if(msg == "CART_IN_OK") alert("카트에 넣었습니다.");
        if(msg == "CART_IN_ERR") alert("카트에 넣는데 실패했습니다.");
        if(msg == "CART_DEL_OK") alert("카트에서 삭제했습니다.");
        if(msg == "CART_DEL_ERR") alert("카트에서 삭제하는데 실패했습니다.");
        if(msg == "CART_QTY_OK") alert("수량을 변경했습니다.");
        if(msg == "CART_QTY_ERR") alert("수량을 변경하는데 실패했습니다.");
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

<div class="content-mg">

    <br>
    <h2 class="mb-4">
        장바구니 목록
    </h2>

    <div>
        <table class="table">
            <colgroup>
                <col width="12%"/>
                <col width="76%"/>
                <col width="12%"/>
            </colgroup>
            <thead>
            <tr class="text-center">
                <td>
                    <input type="checkbox" id="checkall" onclick="checkAll()"> 전체선택
                </td>
                <td>상품정보</td>
                <td>상품금액</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cartItem" items="${cartItemList}" varStatus="status">
                <form action="" id="form_${cartItem.cart_item_no}">
                    <tr>
                        <input type="hidden" id="prod_no_${cartItem.cart_item_no}" name="prod_no" value="${cartItem.prod_no}">
                        <c:if test="${status.index == 0}">
                            <input type="hidden" id="from_cart_${cartItem.cart_item_no}" name="from_cart" value="0">
                        </c:if>
                        <c:if test="${status.index > 0}">
                            <input type="hidden" id="from_cart_${cartItem.cart_item_no}" name="from_cart" value="${maxOrdNo}">
                        </c:if>
                        <td class="text-center align-middle">
                            <input type="checkbox" id="cartChkBox_${cartItem.cart_item_no}" name="cartChkBox" value="${cartItem.cart_item_no}">
                        </td>
                        <td class="card d-flex">
                            <div class="d-flex mb-3">
                                <div class="align-self-center w-75">
                                    <input type="hidden" id="prod_name_${cartItem.cart_item_no}"
                                           name="prod_name" value="${cartItem.prod_name}">
                                    <span class="fs24 font-weight-bold">${cartItem.prod_name}</span>
                                    <input type="hidden" id="col_no_${cartItem.cart_item_no}" name="col_no" value="${cartItem.col_no}">
                                    <input type="hidden" id="col_name_${cartItem.cart_item_no}" name="col_name" value="${cartItem.col_name}">
                                    <br>
                                    <span class="fs18 font-weight-bold">${cartItem.col_name}</span>
                                    <div class="fs18 font-weight-light">
                                        <span class="input-group mt-2">
                                            <input type="hidden" id="price_${cartItem.cart_item_no}" name="price" value="${cartItem.price}"
                                                   data-price="${cartItem.price}" class="align-self-center mr-2">
                                                ${cartItem.price}원 &nbsp&nbsp
                                            <input type="number" name="qty" id="qty_${cartItem.cart_item_no}"
                                                   value="${cartItem.qty}" min="1"
                                                   onchange="changeQty(this)"
                                                   class="cartItemQty form-control mr-2" >
                                            <button type="button" id="changeQtyBtn_${cartItem.cart_item_no}" class="btn btn-outline-secondary changeQtyBtn" value="${cartItem.cart_item_no}">변경하기</button>
                                            &nbsp
                                            <button type="button" id="delBtn_${cartItem.cart_item_no}" class="btn btn-outline-secondary itemDeleteBtn" value="${cartItem.cart_item_no}"> &times; </button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="text-center align-middle">
                            <span id="totalPrice_${cartItem.cart_item_no}"
                                  name="totalPrice">
                                    ${cartItem.price * cartItem.qty}원
                            </span>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
        <br>

        <h2 class="text-center">
            총 주문 금액 : <span id="orderTotalPrice" class="text-danger">0원</span>
        </h2>
        <br>

        <div class="text-center mt-3">
            <button type="button" class="btn btn-primary btn-lg" id="orderBtn">주문하기</button>
        </div>

    </div>

</div>

<script>
    $(document).ready(function () {

        $("input[name=cartChkBox]").change( function () {
            getOrderTotalPrice();
        });

        $('.itemDeleteBtn').on("click", (e) => {
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $("#form_" + e.target.value);
            form.attr("action", "<c:url value='/cart/deleteCartItem'/>");
            form.attr("method", "post");
            form.submit();
        });

        $('.changeQtyBtn').on("click", (e) => {
            let form = $("#form_" + e.target.value);
            form.attr("action", "<c:url value='/cart/updateCartItemQty'/>");
            form.attr("method", "post");
            form.submit();
        });

        $('#orderBtn').on("click", function () {
            var url = "/cart/ordering";

            var dataList = new Array();
            var paramData = new Object();

            $("input[name=cartChkBox]:checked").each(function() {
                var cart_item_no = $(this).val();
                var prod_no = $("#prod_no_" + cart_item_no).val();
                var col_no = $("#col_no_" + cart_item_no).val();
                var qty = $("#qty_" + cart_item_no).val();
                var prod_name = $("#prod_name_" + cart_item_no).val();
                var price = $("#price_" + cart_item_no).val();
                var col_name = $("#col_name_" + cart_item_no).val();
                var from_cart = $("#from_cart_" + cart_item_no).val();

                var data = new Object();
                data["cart_item_no"] = cart_item_no;
                data["prod_no"] = prod_no;
                data["col_no"] = col_no;
                data["qty"] = qty;
                data["prod_name"] = prod_name;
                data["price"] = price;
                data["col_name"] = col_name;
                data["from_cart"] = from_cart;
                dataList.push(data);
            });

            if (dataList.length != 0) {
                paramData['cartItemDtoList'] = dataList;

                var param = JSON.stringify(paramData);

                $.ajax({
                    url      : url,
                    type     : "POST",
                    // contentType : "application/json",
                    headers : {"content-type" : "application/json"},
                    data     : param,
                    // dataType : "json",
                    cache   : false,
                    success  : function(result, status){
                        alert("주문이 완료 되었습니다.");
                        location.replace('/order/list');
                    },
                    error : function(jqXHR, status, error){
                        if(jqXHR.status == '401'){
                            alert('로그인 후 이용해주세요');
                            location.href='/login/form';
                        } else{
                            // alert(jqXHR.responseJSON.message);
                            alert("주문이 실패했습니다.");
                        }
                    }
                });
            }
        });

        $(".cartItemQty").on("keyup change click", (e) => {
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
    });

    function getOrderTotalPrice(){
        var orderTotalPrice = 0;
        $("input[name=cartChkBox]:checked").each(function() {
            var cart_item_no = $(this).val();
            var price = $("#price_" + cart_item_no).attr("data-price");
            var qty = $("#qty_" + cart_item_no).val();
            orderTotalPrice += price*qty;
        });

        $("#orderTotalPrice").html(orderTotalPrice+'원');
    }

    function changeQty(obj) {
        var qty = obj.value;
        var cart_item_no = obj.id.split('_')[1];
        var price = $("#price_" + cart_item_no).data("price");
        var totalPrice = qty*price;
        $("#totalPrice_" + cart_item_no).html(totalPrice+"원");
        getOrderTotalPrice();
    }

    function checkAll(){
        if($("#checkall").prop("checked")){
            $("input[name=cartChkBox]").prop("checked",true);
        }else{
            $("input[name=cartChkBox]").prop("checked",false);
        }
        getOrderTotalPrice();
    }

</script>
</body>
</html>