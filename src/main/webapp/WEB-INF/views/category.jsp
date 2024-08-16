<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-08-16
  Time: 오전 7:47
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

    if(msg == "ERR_REG") {
        alert("등록 실패하였습니다. ");
    }

    if(msg == "ERR_MOD") {
        alert("수정 실패하였습니다. ");
    }
</script>

<h2>카테고리 ${mode == "new" ? "등록" : ''}</h2>
<form action="" id="form">
    <div>
        <input type="${mode == "new" ? 'hidden' : 'text'}" name="catgNo" value="${category.catgNo}" readonly />
    </div>

    <div>
        <label>상위 카테고리 번호</label>
        <select id="highCatg" name="highCatgNo" ${mode == "new" ? '': 'disabled'}>
            <option  value="${category.highCatgNo}">${category.highCatgNo==null ? '없음' : category.highCatgNo}</option>
            <c:forEach var="catg" items="${highCatgList}">
                <option value="${catg.catgNo}">[${catg.catgNo}] ${catg.name}</option>
            </c:forEach>
        </select>

    </div>
    <div>
        <label>카테고리 이름</label>
        <input id = "catg_name" type="text" name="name" value="${category.name}" ${mode == "new"? '' : 'readonly'}/>
    </div>

    <div>
      <label>카테고리 설명</label>
      <textarea name="detail" cols="30" rows="10"  ${mode == "new"? '' : 'readonly'}>${category.detail}</textarea>
    </div>
    <div>
       <label>사용 여부</label>
        <input type="radio" name="isUsed" value="Y" ${category.isUsed == 'Y' || category.isUsed == 'y' ? 'checked': ''} ${mode == "new"? '' : 'onclick="return false"'} /> Y
        <input type="radio" name="isUsed" value="N" ${category.isUsed == 'N' || category.isUsed == 'n' ? 'checked': ''} ${mode == "new"? '' : 'onclick="return false"'} /> N
    </div>
    <div>
        <label>표시 여부</label>
       <input type="radio" name="isDisp" value="Y" ${category.isDisp == 'Y' || category.isDisp == 'y' ? 'checked': ''}  ${mode == "new"? '' : 'onclick="return false"'} /> Y
       <input type="radio" name="isDisp"  value="N" ${category.isDisp == 'N' || category.isDisp == 'n' ? 'checked': ''}  ${mode == "new"? '' : 'onclick="return false"'} /> N
     </div>
    ${mode != "new" ? '<button type="button" id="modifyBtn" class="btn">수정</button>' : null}
    ${mode != "new" ? ' <button type="button" id="removeBtn" class="btn">삭제</button>' :null}
    ${mode != "new" ? ' <button type="button" id="listBtn" class="btn">목록</button>' : null}
    ${mode == "new" ? '<button type="button" id="registerBtn" class="btn">등록</button>' :null}
</form>

<script>


    $(document).ready(function() {

        // 목록 버튼 onclick 이벤트 정의
        $('#listBtn').on("click", function() {
            // 목록 버튼을 누르면 GET /categorys 요청 (목록 페이지로 이동)
            location.href = "<c:url value='/categorys'/>";
        })

        // 삭제 버튼 onclick 이벤트 정의
        $('#removeBtn').on("click", function() {
            // 삭제 경고에 "아니요"를 누른 경우 삭제 X
            if(!confirm("정말로 삭제하겠습니까?")) return;

            let form = $('#form'); // form id를 가진 엘리먼트의 참조를 얻어옴
            form.attr("action", "<c:url value='/categorys'/>");
            form.attr("method", "delete");
            form.submit();
        })

        // 등록 버튼 onclick 이벤트 정의
        $('#registerBtn').on("click", function() {

            let form = $('#form'); // form id를 가진 엘리먼트의 참조를 얻어옴
            form.attr("action", "<c:url value='/categorys'/>");
            form.attr("method", "post");
            form.submit();  // form에 있는 input, textarea 태그의 각종 value값들이 body에 담겨 전송됨
        })

        // 수정 버튼 onclick 이벤트 정의
        $('#modifyBtn').on("click", function() {
            // 1. 읽기 상태면 수정 상태로 변경
            let form = $('#form');
            let readOnly = $('#catg_name').attr('readonly');

            if(readOnly == 'readonly') {
                // 번호 필드를 제외한 모든 입력 필드와 텍스트 영역의 5 readonly 속성을 해제
                $('#form').find('input:not([name="catgNo"]), textarea').removeAttr('readonly');

                // 체크박스의 클릭 가능하도록 onclick 속성 해제
                $('input[type="radio"]').attr('onclick', '').unbind('click');

                // 상위 카테고리 select 가능하도록 하기
                $('#highCatg').prop('disabled', false);

                $('h2').html("카테고리 수정");
                $('#modifyBtn').html("수정 완료");
                return;
            }

            // 2. 수정 상태면 수정된 내용을 서버에 전송

            form.attr("action", "<c:url value='/categorys/modify'/>");
            form.attr("method", "post");
            form.submit();
        })

    })
</script>
</body>
</html>
