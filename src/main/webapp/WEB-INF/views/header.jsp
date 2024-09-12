<!-- header.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<header id="header-section">
    <div class="top-nav">
        <c:choose>
            <c:when test="${!empty sessionUser}">
                <a href="#">관심상품 0개</a>
                <a href="#">좋아요 0개</a>
            </c:when>
        </c:choose>
        <a href="<c:url value='/user/register'/>">회원가입</a>
        <c:choose>
            <c:when test="${empty sessionUser}">
                <a href="<c:url value='/login/form'/>">로그인</a>
            </c:when>
            <c:otherwise>
                <a href="<c:url value='/login/out'/>">로그아웃</a>
            </c:otherwise>
        </c:choose>
        <a href="#">주문조회</a>
        <a href="#">최근 본 상품</a>
        <a href="#">고객센터</a>
    </div>

    <!-- 메인 헤더 -->
    <header class="main-header">
        <a href="/">
            <img src="/resources/image/img_1.png" alt="IAMCAP 로고">
        </a>
    </header>

    <!-- 네비게이션 바 -->
    <nav class="navbar navbar-expand-lg">
        <ul class="navbar-nav" id="navbar">
            <li class="nav-item"><a class="nav-link" href="<c:url value="/product/list?sortBy=totalSales"/>">BEST</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value="/product/list?sortBy=firstRegDt"/>">신상품</a></li>
<%--            <li class="nav-item"><a class="nav-link" href="#">CAP</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">HAT</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">비니/와치캡</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">빅사이즈</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">SALE</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">BRAND</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">COLOR</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">기타</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">Season</a></li>--%>
<%--            <li class="nav-item"><a class="nav-link" href="#">올마켓</a></li>--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="#">--%>
<%--                    <i class="fa fa-search navbar-icons"></i>--%>
<%--                    <i class="fa fa-user navbar-icons"></i>--%>
<%--                    <i class="fa fa-shopping-cart navbar-icons"></i>--%>
<%--                </a>--%>
<%--            </li>--%>
        </ul>
    </nav>

    <script>
        document.addEventListener('DOMContentLoaded', function (){
            fetch('<c:url value="/products/catgs/high"/>')
                .then(res => {
                    if(!res.ok) {
                        throw new Error(res.json().msg);
                    }
                    return res.json();
                })
                .then(res => {
                    console.log("json데이터:", res)
                    // 상위 카테고리 목록 동적 렌더링
                    render_high_catg_list(res.res, res.url);
                })
        })

        function render_high_catg_list(catgList, url) {
            const navbar = document.getElementById('navbar');

            catgList.forEach(catg => {
                // <li className="nav-item"><a className="nav-link" href="#">올마켓</a></li>
                const item = document.createElement('li');
                item.classList.add('nav-item');

                const link = document.createElement('a');
                link.classList.add('nav-link');
                link.href = url+catg.catgNo;
                link.textContent = catg.name;

                item.appendChild(link);
                navbar.appendChild(item);
            })
        }
    </script>
</header>