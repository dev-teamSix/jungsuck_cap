<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>챗봇</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9; /* 채팅창 배경색을 원래 채팅창 색으로 변경 */
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: flex-end;
            height: 100vh;
        }

        .chat-container {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff; /* 배경색을 흰색으로 변경 */
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            display: flex;
            flex-direction: column;
            gap: 10px;
            overflow-y: auto;
            flex-grow: 1;
            max-height: calc(100vh - 80px); /* 입력창 위로 공간 확보 */
        }

        .message {
            padding: 10px;
            border-radius: 10px;
            max-width: 80%;
            word-wrap: break-word;
            margin-bottom: 10px;
        }

        .message.user {
            background-color: #b3e5fc; /* 사용자 말풍선: 하늘색 */
            align-self: flex-end;
            color: #333;
        }

        .message.assistant {
            background-color: #e0e0e0; /* 챗봇 말풍선: 회색 */
            align-self: flex-start;
            color: #333;
        }

        .message.user strong::before {
            content: "👤 ";
        }

        .message.assistant strong::before {
            content: "🤖 ";
        }

        .input-form {
            display: flex;
            gap: 10px;
            padding: 10px;
            background-color: #fff;
            border-top: 1px solid #ccc;
            position: sticky;
            bottom: 0;
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            box-sizing: border-box;
        }

        .input-form input[type="text"] {
            flex: 1;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .input-form button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #0288d1;
            color: white;
            cursor: pointer;
        }

        .input-form button:hover {
            background-color: #0277bd;
        }
    </style>
</head>
<body>
<div class="chat-container">
    <c:forEach var="message" items="${messages}">
        <div class="message ${message.role_data}">
            <strong>
                <span style="text-transform:uppercase">${message.role_data} :</span>
            </strong>
            <br>
            <p style = "white-space: pre-line; margin-top: 0px;">
                    ${message.content_data}
            </p>
        </div>
    </c:forEach>
</div>

<%--<form class="input-form" action="/chat/sendchat" method="post">--%>
<form class="input-form">
    <input
            type="text"
            name="user_input"
            id="user_input"
            placeholder="채팅을 입력하세요 : "
            value=""
            required
    />
    <button type="button" id="sendBtn">보내기</button>
</form>

<script>
    $(document).ready(function () {
        $('#sendBtn').on("click", function () {
            var url = "/chat/sendchat";
            var user_input = $("#user_input").val();

            // var paramData = new Object();
            // paramData['user_input'] = user_input;
            paramData = {
                "user_input" : user_input
            };

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
                    location.replace('/chat/showchat');
                },
                error : function(jqXHR, status, error){
                    if(jqXHR.status == '401'){
                        alert('로그인 후 이용해주세요');
                        location.href='/login/form';
                    } else{
                        // alert(jqXHR.responseJSON.message);
                        alert("입력이 실패했습니다.");
                    }
                }
            });
        });

        $(function() {
            $('.chat-container').scrollTop($('.chat-container')[0].scrollHeight)
        });
    });
</script>

</body>
</html>