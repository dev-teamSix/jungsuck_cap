<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니</title>
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
        if(msg == "CART_IN_OK") alert("카트에 넣었습니다.");
        if(msg == "CART_IN_ERR") alert("카트에 넣는데 실패했습니다.");
        if(msg == "CART_DEL_OK") alert("카트에서 삭제했습니다.");
        if(msg == "CART_DEL_ERR") alert("카트에서 삭제하는데 실패했습니다.");
        if(msg == "CART_QTY_OK") alert("수량을 변경했습니다.");
        if(msg == "CART_QTY_ERR") alert("수량을 변경하는데 실패했습니다.");
    </script>
</head>

<body>
<jsp:include page="./header.jsp" flush="true" />

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
                        <input type="hidden" id="from_cart_${cartItem.cart_item_no}" name="from_cart" value="${maxOrdNo}">
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

<jsp:include page="./footer.jsp" flush="true" />

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
