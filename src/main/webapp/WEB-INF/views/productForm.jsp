<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-09-02
  Time: 오후 4:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
</head>
<body>
<script>
    let msg = "${msg}";
    if(msg) {
        alert(msg);
    }
</script>
    <h2>상품 ${mode=="new" ? "등록" : ""}</h2>
    <form id="form">
        <div>
            <p>상품 번호: ${product.prodNo}</p>
            <input type="number" hidden name="prodNo" value="${product.prodNo}"/>
        </div>
        <div>
            <label>상품 카테고리</label>
            <input type="number" name="prodCatgNo" value="${product.prodCatgNo}"  ${mode != "new" ? 'readonly': ''}/>
        </div>
        <div>
            <label>상품명</label>
            <input id="name" type="text" name="name" value="${product.name}" placeholder="50자 이내 작성"   ${mode != "new" ? 'readonly': ''}/>
        </div>
        <button type="button" ${mode == "new" ? "" : "hidden"}>중복 확인</button>
        <div>
            <label>상품 바코드</label>
            <input type="text" name="barc" value="${product.barc}" placeholder="숫자 20자 이내"  ${mode != "new" ? 'readonly': ''}/>
        </div>
        <div>
            <label>상품 가격</label>
            <input type="number" name="price" value="${product.price}"  ${mode != "new" ? 'readonly': ''}/>
        </div>
        <div>
            <label>상품 간략 소개</label>
            <input type="text" name="shortDet" value="${product.shortDet}" placeholder="50자 이내 작성"  ${mode != "new" ? 'readonly': ''}/>
        </div>
        <div>
            <label>상품 설명</label>
            <textarea rows=20 name="longDet"  ${mode != "new" ? 'readonly': ''}>${product.longDet}</textarea>
        </div>
        <div>
            <button type="button" id="registerBtn"  ${mode != "new" ? 'hidden' : ''}>등록</button>
            <button type="button" id="modifyBtn"  ${mode == "new" ? 'hidden' : ''}>수정</button>
            <button type="button" id="removeBtn"  ${mode == "new" ? 'hidden' : ''}>삭제</button>
        </div>

    </form>
<script>

    $(document).ready(function () {
        $('#registerBtn').on("click", function () {
            let form = $('#form');
            form.attr("action", "<c:url value='/product/register${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        })

        $('#modifyBtn').on("click", function() {
            // 1. 읽기 상태면 수정 상태로 변경
            let form = $('#form');
            let readOnly = $('#name').attr('readonly');

            if(readOnly == 'readonly') {
                // 번호 필드를 제외한 모든 입력 필드와 텍스트 영역의 5 readonly 속성을 해제
                $('#form').find('input:not([name="prodNo"]), textarea').removeAttr('readonly');


                // 상위 카테고리 select 가능하도록 하기
                // $('#highCatg').prop('disabled', false);

                $('h2').html("상품 수정");
                $('#modifyBtn').html("수정 완료");
                return;
            }

            // 2. 수정 상태면 수정된 내용을 서버에 전송
            form.attr("action", "<c:url value='/product/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        })

        $('#removeBtn').on("click", function() {
            let form = $('#form');

            form.attr("action", "<c:url value='/product/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        })
    })
</script>
</body>
</html>
