<%--
  Created by IntelliJ IDEA.
  User: abclon
  Date: 2024-08-12
  Time: 오전 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
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
</head>
<script>
    $(document).ready(function() {
        console.log("noticeWrite");
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

        $("#reg_btn").on("click", function(){

            let isNoticeValue= document.querySelector('input[name="is_notice"]:checked').value
            console.log("radio값:"+isNoticeValue);

            let form = $("#form");

            if(formCheck()){
                form.submit();
            }

        });


    });
</script>
<body>

<h3>공지사항</h3>
<form id="form" action="/notice/write" method="post">
    <table>
        <tbody>
        <tr>
            <th>제목</th>
            <td>
                <input type="text" id="title" name="title" value="" style="width:100%;">
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td>
                <textarea id="content" name="content"></textarea>
            </td>
        </tr>
        <tr>
            <th>상단고정(공지글)</th>
            <td>
                <div>
                    <input type="radio" name="is_notice" id="radioTrue" value="1">
                    <label for="radioTrue">지정</label>
                </div>
                <div>
                    <input type="radio" name="is_notice" id="radioFalse" value="0" checked>
                    <label for="radioFalse">미지정</label>
                </div>
            </td>
        </tr>
        </tbody>

    </table>

    <div class="button-group">
        <div class="button-group-left">
            <button type="button" onclick="location.href='/notice/list'">목록</button>
        </div>
        <div class="button-group-right">
            <button type="button" name="reg_btn" id="reg_btn">등록</button>
            <button type="button" name="can_btn" id="can_btn" onclick="location.href='/notice/list'">취소</button>
        </div>
    </div>
</form>

</body>
</html>

