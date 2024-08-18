<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-08-15
  Time: 오후 7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <style>
        .low-category-list {
            display: none;
            margin-left: 20px;
        }
        .high-category {
            cursor: pointer;
            font-weight: bold;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<script>
    let msg = "${msg}";

    if(msg == "OK_REG") {
        alert("등록 성공 했습니다!");
    }


    if(msg == "OK_MOD") {
        alert("수정 성공 했습니다!");
    }
</script>

<h3>전체 카테고리 목록</h3>
   <a href="<c:url value="/categorys/register"/>"><button type="button">등록</button> </a>
    <table border="1">
        <tr>
            <th>카테고리 번호</th>
            <th>카테고리 이름</th>
            <th>표시 여부</th>
            <th>사용 여부</th>
            <th>상위 카테고리 번호</th>
        </tr>
        <c:forEach var="c" items="${categories}">
            <tr onclick="window.location='<c:url value="/categorys/read?catgNo=${c.catgNo}"/>';" style="cursor:pointer;">
                <td>${c.catgNo}</td>
                <td>${c.name}</td>
                <td> <input type="checkbox" ${c.isUsed=='Y' || c.isUsed =='y' ? 'checked' : ''} onclick="return false"></td>
                <td> <input type="checkbox" ${c.isDisp=='Y' || c.isDisp =='y' ? 'checked' : ''} onclick="return false"></td>
                <td>${c.highCatgNo}</td>
            </tr>

        </c:forEach>
    </table>
<script>
</script>


</body>
</html>
