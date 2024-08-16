
<%--
  Created by IntelliJ IDEA.
  User: abclon
  Date: 2024-08-12
  Time: 오전 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html></html>
<html>
<head>
    <title>noticeList</title>
</head>
<body>
    <table>
    <tr>
        <th class="bno">번호</th>
        <th class="title">제목</th>
        <th class="writer">이름</th>
        <th class="reg_dt">등록일</th>
        <th class="view_cnt">조회수</th>
    </tr>
    <c:forEach var="noticeDto" items="${list}">
        <tr>
            <td class="bno">${noticeDto.bno}</td>
            <td class="title">${noticeDto.title}</td>
            <td class="writer">${noticeDto.writer}</td>
            <td class="reg_dt">${noticeDto.reg_dt}</td>
            <td class="view_cnt">${noticeDto.view_cnt}</td>
        </tr>

    </c:forEach>
    </table>
    <br>
    <div class="paging-container">
        <div class="paging">
            <c:if test="${totalCnt==null || totalCnt==0}">
                <div> 게시물이 없습니다. </div>
            </c:if>
            <c:if test="${totalCnt!=null && totalCnt!=0}">
                <c:if test="${ph.prevPage}">
                    <a class="page" href="<c:url value="/notice/list${ph.getQueryString(ph.beginPage-1)}"/>">&lt;</a>
                </c:if>
                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <a class="page ${i==ph.page? "paging-active" : ""}" href="<c:url value="/notice/list${ph.getQueryString(i)}"/>">${i}</a>
                </c:forEach>
                <c:if test="${ph.nextPage}">
                    <a class="page" href="<c:url value="/notice/list${ph.getQueryString(ph.endPage+1)}"/>">&gt;</a>
                </c:if>
            </c:if>
        </div>
    </div>
</body>
</html>
