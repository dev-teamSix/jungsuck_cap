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
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .main-header {
            background-color: #343a40;
            color: #ffffff;
            padding: 1rem 0;
        }
        .logo {
            color: #ffffff;
            text-decoration: none;
            font-size: 1.5rem;
            font-weight: bold;
        }
        .logo-mini {
            font-size: 1rem;
        }
        .navbar {
            padding: 0;
            margin: 0;
        }
        .navbar-nav .nav-item .nav-link {
            color: #ffffff;
        }
        .navbar-nav .nav-item .nav-link:hover {
            color: #dddddd;
        }
        .login-container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            padding: 2rem;
            width: 100%;
            max-width: 400px;
            margin: 2rem auto;
        }
        .login-container h1 {
            margin-bottom: 1.5rem;
        }
        .form-control {
            border-radius: 5px;
            box-shadow: inset 0 1px 2px rgba(0,0,0,.075);
        }
        .form-check-inline {
            margin-right: 1rem;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }
        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }
        .login-footer {
            margin-top: 1rem;
            text-align: center;
        }
        .login-footer a {
            margin: 0 0.5rem;
            color: #007bff;
        }
        .login-footer a:hover {
            text-decoration: underline;
        }
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

            $("#reg_btn").on("click", function(){
                let form = $("#form");
                let action = "/notice/write";
                form.attr("action", action);
                form.attr("method","post");
                if(formCheck()){
                    form.submit();
                }
            });

            $("#mod_btn").on("click", function(){
                let form = $("#form");
                let action = "/notice/modify";
                form.attr("action", action);
                form.attr("method","post");
                if(formCheck()){
                    form.submit();
                }
            })

            $("#del_btn").on("click", function(){
                if(!confirm("정말로 삭제하시겠습니까?")) return;
                let form = $("#form");
                let action = "/notice/remove";

                form.attr("action", action);
                form.attr("method","post");

                form.submit();
            });

            $("#can_btn").on("click", function(){
                location.href = "/notice/list?page="+page+"&pageSize="+pageSize+"&option_date="+option_date+"&option_key="+option_key+"&keyword="+keyword;
            })

            $("#list_btn").on("click", function(){
                location.href = "/notice/list?page="+page+"&pageSize="+pageSize+"&option_date="+option_date+"&option_key="+option_key+"&keyword="+keyword;
            })

            let is_notice = "${noticeDto.is_notice}";
            console.log("is_notice:"+is_notice);
            if(is_notice =='0'){
                console.log("0이냐");
                $("#radioFalse").prop("checked",true);
            }else{
                $("#radioTrue").prop("checked",true);

            }
        });

    </script>
</head>
<body>

<h3>공지사항</h3>
<form action="" id="form" method="post">
    <table>
        <tbody>
        <tr>
            <th>글번호</th>
            <td>
                <input type="text" name="bno" value="${noticeDto.bno}" readonly>

            </td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>
                <input type="text" name="writer" value="${noticeDto.writer}" readonly>
            </td>

        </tr>
        <tr>
            <th>등록일</th>
            <td>
                ${noticeDto.reg_dt}
                <%--<input type="text" name="reg_dt" value="${noticeDto.reg_dt}" readonly>--%>
            </td>
        </tr>
        <tr>
            <th>제목</th>
            <td>
                <input type="text" name="title" value="${noticeDto.title}" style="width:100%;">
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td>
                <textarea name="content">${noticeDto.content}</textarea>
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
                    <input type="radio" name="is_notice" id="radioFalse" value="0">
                    <label for="radioFalse">미지정</label>
                </div>
            </td>
        </tr>
        </tbody>

    </table>

    <div class="button-group">
        <div class="button-group-left">
            <button type="button" id="list_btn" name="list_btn">목록</button>
        </div>
        <div class="button-group-right">
            <button type="button" id="mod_btn" name="mod_btn">수정</button>
            <button type="button" id="can_btn" name="can_btn">취소</button>
            <button type="button" id="del_btn" name="del_btn">삭제</button>
        </div>
    </div>
</form>

</body>
</html>

