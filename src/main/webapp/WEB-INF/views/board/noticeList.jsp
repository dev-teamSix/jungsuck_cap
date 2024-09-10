<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abclon
  Date: 2024-08-12
  Time: 오전 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>noticeList</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f8f9fa;
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
    .main-header {
        background-color: #343a40;
        color: #ffffff;
        padding: 1rem 0;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f4f4f4;
        font-weight: bold;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
    .paging-container {
        text-align: center;
        margin-top: 20px;
    }
    .paging a {
        margin: 0 5px;
        padding: 8px 16px;
        text-decoration: none;
        color: #333;
        border: 1px solid #ddd;
        border-radius: 4px;
    }
    .paging-active {
        background-color: #007bff;
        color: #fff;
    }
    .search-container {
        display: flex;
        justify-content: space-between;
        margin-bottom: 20px;
    }
    .search-form select, .search-form input[type="text"] {
        padding: 8px;
        margin-right: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }
    .search-button {
        padding: 8px 16px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .btn-write {
        padding: 8px 16px;
        background-color: #28a745;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .btn-write i {
        margin-right: 5px;
    }
</style>
<script>
    let msg = "${msg}";
    if(msg=="DEL_OK") alert("게시글이 정상적으로 삭제되었습니다.");
    if(msg=="WRT_ERR") alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
    if(msg=="MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
    if(msg=="ADMIN_NO") alert("관리자가 아니면 공지사항 작성이 불가능 합니다.");
</script>

<body>
<jsp:include page="../header.jsp" flush="true" />
    <table>
    <tr>
        <th class="bno">번호</th>
        <th class="title">제목</th>
        <th class="writer">이름</th>
        <th class="reg_dt">등록일</th>
        <th class="view_cnt">조회수</th>
    </tr>
        <c:forEach var="noticeDto" items="${noticeList}">
            <tr>
                <td class="notice_bno"><b>공지</b></td>
                <td class="notice_title"><a href="<c:url value="/notice/read${ph.queryString}&bno=${noticeDto.bno}"/>"><b>${noticeDto.title}</b></a></td>
                <td class="notice_writer">${noticeDto.writer}</td>
                <td class="notice_reg_dt">${noticeDto.reg_dt}</td>
                <td class="notice_view_cnt">${noticeDto.view_cnt}</td>
            </tr>
        </c:forEach>

    <c:forEach var="noticeDto" items="${list}">
        <tr>
            <td class="bno">${noticeDto.bno}</td>
            <td class="title"><a href="<c:url value="/notice/read${ph.queryString}&bno=${noticeDto.bno}"/>">${noticeDto.title}</a></td>
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

    <div class="search-container">
        <form action="<c:url value="/notice/list"/>" class="search-form" method="get">
            <select class="option_date" name="option_date">
                <option value="week" ${sc.option_date=='week' ? "selected": ""}>일주일</option>
                <option value="month" ${sc.option_date=='month' ? "selected" : ""}>한달</option>
                <option value="month3" ${sc.option_date=='month3' ? "selected" : ""}>세달</option>
                <option value="all" ${sc.option_date=='all' || sc.option_date=='' ? "selected"  : ""}>전체</option>
            </select>
            <select class="option_key" name="option_key">
                <option value="titleContent" ${sc.option_key=='titleContent' || sc.option_key=='' ? "selected" : ""}>제목+내용</option>
                <option value="title" ${sc.option_key=='title' ? "selected" : ""}>제목만</option>
                <option value="writer" ${sc.option_key=='writer' ? "selected" : ""}>작성자</option>
            </select>

            <input type="text" name="keyword" class="search-input" type="text" value="${sc.keyword}" placeholder="검색어를 입력해주세요">
            <input type="submit" class="search-button" value="검색">
        </form>
        <button id="writeBtn" class="btn-write" onclick="location.href='<c:url value="/notice/write"/>'"><i class="fa fa-pencil"></i> 글쓰기</button>
    </div>
<jsp:include page="../footer.jsp" flush="true" />
</body>
</html>
