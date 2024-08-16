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
    if(msg == "CART_IN_OK") alert("카트에 넣었습니다.");
    if(msg == "CART_IN_ERR") alert("카트에 넣는데 실패했습니다.");
    if(msg == "CART_DEL_OK") alert("카트에서 삭제했습니다.");
    if(msg == "CART_DEL_ERR") alert("카트에서 삭제하는데 실패했습니다.");
    if(msg == "CART_QTY_OK") alert("수량을 변경했습니다.");
    if(msg == "CART_QTY_ERR") alert("수량을 변경하는데 실패했습니다.");
</script>
<div class="content" class="content-mg">

    <h3 name="experiment" id="experiment" class="font-weight-bold"></h3>

    <h2 class="mb-4">
        장바구니 목록
    </h2>

    <div>
        <table class="table">
            <colgroup>
                <col width="15%"/>
                <col width="70%"/>
                <col width="15%"/>
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
                        <input type="hidden" id="prod_num_${cartItem.cart_item_no}" name="prod_num" value="${cartItem.prod_num}">
                        <c:if test="${status.index == 0}">
                            <input type="hidden" id="from_cart_${status.index}" name="from_cart" value="0">
                        </c:if>
                        <c:if test="${status.index > 0}">
                            <input type="hidden" id="from_cart_${status.index}" name="from_cart" value="${maxOrdNo}">
                        </c:if>
                        <td class="text-center align-middle">

                            <input type="checkbox" id="cartChkBox_${cartItem.cart_item_no}" name="cartChkBox" value="${cartItem.cart_item_no}">
                        </td>
                        <td class="d-flex">
                            <div class="align-self-center">
                                <input type="hidden" id="prod_name_${cartItem.cart_item_no}" name="prod_name" value="${cartItem.prod_name}">
                                        ${cartItem.prod_name}
                                </input>
                                <div class="fs18 font-weight-light">
                            <span class="input-group mt-2">
                                <input type="hidden" id="price_${cartItem.cart_item_no}" name="price" value="${cartItem.price}"
                                        data-price="${cartItem.price}" class="align-self-center mr-2">
                                    ${cartItem.price}원
                                </input>
                                <input type="number" name="qty" id="qty_${cartItem.cart_item_no}"
                                       value="${cartItem.qty}" min="1"
                                       onchange="changeQty(this)"
                                       class="cartItemQty form-control mr-2" >
                                <button type="button" id="changeQtyBtn_${cartItem.cart_item_no}" class="btn btn-outline-secondary changeQtyBtn" value="${cartItem.cart_item_no}">변경하기</button>
                                <button type="button" id="delBtn_${cartItem.cart_item_no}" class="btn btn-outline-secondary itemDeleteBtn" value="${cartItem.cart_item_no}"> &times; </button>
                            </span>
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
            <%--let params = Test.--%>
            <%--const query = 'input[name="cartChkBox"]:checked';--%>
            <%--const selectedEls = document.querySelectorAll(query);--%>
            <%--if(selectedEls == null) return;--%>
            <%--if (!confirm("정말로 주문하시겠습니까?")) return;--%>
            <%--let cnt = 0;--%>
            <%--selectedEls.forEach((el) => {--%>
            <%--    if ($("#from_cart_" + cnt).val() == 0) {--%>
            <%--        let form = $('#form_' + el.value);--%>
            <%--        form.attr("action", "<c:url value='/cart/ordering'/>");--%>
            <%--        form.attr("method", "post");--%>
            <%--        form.submit();--%>
            <%--    }--%>
            <%--    cnt = cnt + 1;--%>
            <%--});--%>
            <%--$("input[name=cartChkBox]:checked").each(function() {--%>
            <%--    // var cartItemId = $(this).val();--%>
            <%--    let form = $('#form_' + $(this).val());--%>
            <%--    form.attr("action", "<c:url value='/cart/ordering'/>");--%>
            <%--    form.attr("method", "post");--%>
            <%--    form.submit();--%>
            <%--});--%>
            <%--var cartItemListSize = $("#cartItemListSize").val();--%>
            <%--for(let i = 0; i < cartItemListSize; i++) { id="from_cart_${status.index}"--%>
            <%--    let form = $('#form_' + $(this).val());--%>
            <%--    form.attr("action", "<c:url value='/cart/ordering'/>");--%>
            <%--    form.attr("method", "post");--%>
            <%--    form.submit();--%>
            <%--}--%>
<%--            <c:forEach var="i" begin="0" end=>--%>
<%--            ${i },--%>
<%--            </c:forEach>--%>
            var url = "/cart/ordering";

            var dataList = new Array();
            var paramData = new Object();
            var data = new Object();

            $("input[name=cartChkBox]:checked").each(function() {
                var cart_item_no = $(this).val();
                var prod_num = $("#prod_num_" + cart_item_no).val();
                var qty = $("#qty_" + cart_item_no).val();
                var prod_name = $("#prod_name_" + cart_item_no).val();
                var price = $("#price_" + cart_item_no).val();
                var from_cart = $("#from_cart_" + cart_item_no).val();

                data["cart_item_no"] = cart_item_no;
                data["prod_num"] = prod_num;
                data["qty"] = qty;
                data["prod_name"] = prod_name;
                data["price"] = price;
                data["from_cart"] = from_cart;
                dataList.push(data);
            });

            paramData['cartItemDtoList'] = dataList;

            var param = JSON.stringify(paramData);

            $.ajax({
                url      : url,
                type     : "POST",
                contentType : "application/json",
                data     : param,
                dataType : "json",
                cache   : false,
                success  : function(result, status){
                    alert("주문이 완료 되었습니다.");
                    // location.href='/order/list';
                    location.replace('/order/list');
                },
                error : function(jqXHR, status, error){

                    if(jqXHR.status == '401'){
                        alert('로그인 후 이용해주세요');
                        location.href='/cart/tempSignUpPage';
                    } else{
                        alert(jqXHR.responseJSON.message);
                    }

                }
            });
        });

        $(".cartItemQty").on("keyup change click", (e) => {
            // $("#experiment").html(e.target.value + '개');
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
        // $("#experiment").html(qty + '원');
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