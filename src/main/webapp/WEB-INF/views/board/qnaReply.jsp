<%--
  Created by IntelliJ IDEA.
  User: abclon
  Date: 2024-08-15
  Time: 오후 5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QnA</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        textarea {
            width: 100%;
            height: 100px;
        }
        .button-group {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }
        .button-group-left,
        .button-group-right {
            display: flex;
        }
        .button-group button {
            padding: 10px 15px;
            margin-left: 10px;
            font-size: 16px;
            cursor: pointer;
        }
        .button-group-right button:first-child {
            margin-left: 0;
        }
    </style>
    <script>
        $(document).ready(function() {
            //원글의 정보를 가져와서 렌더링 시킨다.

            let page = "${page}";
            let pageSize="${pageSize}";
            let option_key ="${option_key}";
            let option_date = "${option_date}";
            let keyword = "${keyword}";

            console.log("page:"+page);
            console.log("pageSize:"+pageSize);
            console.log("option_key:"+option_key);
            console.log("option_date:"+option_date);
            console.log("keyword:"+keyword);

            let formCheck = function() {
                let form = document.getElementById("form");
                if(form.title.value=="") {
                    alert("제목을 입력해 주세요.");
                    form.title.focus();
                    return false;
                }

                if(form.content.value=="") {
                    alert("내용을 입력해 주세요.");
                    form.content.focus();
                    return false;
                }
                return true;
            }


            $("#reply_btn").on("click", function(){
                let form = $("#form");
                let action = "/qna/reply";
                form.attr("action", action);
                form.attr("method","post");
                if(formCheck()){
                    form.submit();
                }
            });

            $("#can_btn").on("click", function(){
                location.href = "/qna/list?page="+page+"&pageSize="+pageSize+"&option_date="+option_date+"&option_key="+option_key+"&keyword="+keyword;
            })

            $("#list_btn").on("click", function(){
                location.href = "/qna/list?page="+page+"&pageSize="+pageSize+"&option_date="+option_date+"&option_key="+option_key+"&keyword="+keyword;
            })

        });

    </script>
</head>
<body>

<h3>QnA</h3>
<form action="" id="form" method="post">
    <table>
        <tbody>
        <tr>
            <th>글번호</th>
            <td>
                <input type="text" name="bno" value="${qnaDto.bno}" readonly>

            </td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>
                <input type="text" name="writer" value="${qnaDto.writer}" readonly>
            </td>

        </tr>
        <tr>
            <th>등록일</th>
            <td>
                ${qnaDto.reg_dt}
                <%--<input type="text" name="reg_dt" value="${qnaDto.reg_dt}" readonly>--%>
            </td>
        </tr>
        <tr>
            <th>제목</th>
            <td>
                <input type="text" name="title" value="Re:${qnaDto.title}" style="width:100%;">
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td>
                <textarea name="content">
                -----------------------------원글----------------------------
                    ${qnaDto.content}
                </textarea>
            </td>
        </tr>
        </tbody>

    </table>

    <div class="button-group">
        <div class="button-group-left">
            <button type="button" id="list_btn" name="list_btn">목록</button>
        </div>
        <div class="button-group-right">
            <button type="button" id="mod_btn" name="mod_btn">답변등록</button>
            <button type="button" id="can_btn" name="can_btn">취소</button>
        </div>
    </div>
</form>

</body>
</html>
