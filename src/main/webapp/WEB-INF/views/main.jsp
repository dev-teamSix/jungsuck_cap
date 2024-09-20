<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JungSuk_Cap - 모자 쇼핑몰</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery, Popper.js, and Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .row {
            display: flex;
            flex-wrap: wrap;
            margin-right: -217px;
        }
        /* 상단 네비게이션 */
        .top-nav {
            display: flex;
            justify-content: flex-end;
            padding: 10px 20px;
            font-size: 0.9rem;
            background-color: #f7f7f7;
        }
        .top-nav a {
            margin-left: 15px;
            color: #333;
            text-decoration: none;
        }
        .top-nav a:hover {
            text-decoration: underline;
        }
        /* 메인 헤더 */
        .main-header {
            background-color: #fff;
            padding: 20px;
            text-align: center;
        }
        .main-header img {
            max-width: 296px;
        }
        /* 네비게이션 바 */
        .navbar {
            justify-content: center;
            padding: 0.5rem 0;
            background-color: #fff;
            border-bottom: 1px solid #ddd;
        }
        .navbar-nav .nav-item .nav-link {
            font-weight: bold;
            padding: 0.5rem 1rem;
            color: #000;
        }
        .navbar-nav .nav-item .nav-link:hover {
            color: #555;
        }
        .navbar-icons {
            font-size: 1.2rem;
            margin-left: 15px;
        }
        .product-banner img {
            width: 100%;
            height: auto;
            display: block;
            margin: 0 auto;
        }
        /* Best 10 스타일 */
        .best10-container {
            padding: 2rem;
            background-color: #fff;
        }
        .best10-container h2 {
            font-size: 1.5rem;
            margin-bottom: 1.5rem;
            margin-left: 131px;
        }
        .best10-item {
            text-align: center;
            margin-bottom: 1rem;
        }
        .best10-item img {
            width: 100%;
            max-width: 180px;
            height: auto;
            display: block;
            margin: 0 auto 1rem;
        }
        .best10-item h3 {
            font-size: 1.2rem;
            margin-bottom: 0.5rem;
        }
        .best10-item p {
            color: #666;
            font-size: 1rem;
        }
        .best10-item .price {
            color: #000;
            font-weight: bold;
        }
        /* Footer 스타일 */
        footer {
            background-color: #fff;
            padding: 2rem;
            font-size: 0.9rem;
            color: #666;
            border-top: 1px solid #ddd;
        }
        footer .footer-section {
            display: flex;
            justify-content: space-between;
        }
        footer h4 {
            font-size: 1.1rem;
            margin-bottom: 1rem;
            color: #000;
        }
        footer a {
            color: #666;
            text-decoration: none;
        }
        footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<!-- 상단 네비게이션 -->
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
    <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="#">BEST</a></li>
        <li class="nav-item"><a class="nav-link" href="#">신상품</a></li>
        <li class="nav-item"><a class="nav-link" href="#">CAP</a></li>
        <li class="nav-item"><a class="nav-link" href="#">HAT</a></li>
        <li class="nav-item"><a class="nav-link" href="#">비니/와치캡</a></li>
        <li class="nav-item"><a class="nav-link" href="#">빅사이즈</a></li>
        <li class="nav-item"><a class="nav-link" href="#">SALE</a></li>
        <li class="nav-item"><a class="nav-link" href="#">BRAND</a></li>
        <li class="nav-item"><a class="nav-link" href="#">COLOR</a></li>
        <li class="nav-item"><a class="nav-link" href="#">기타</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Season</a></li>
        <li class="nav-item"><a class="nav-link" href="#">올마켓</a></li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fa fa-search navbar-icons"></i>
                <i class="fa fa-user navbar-icons"></i>
                <i class="fa fa-shopping-cart navbar-icons"></i>
            </a>
        </li>
    </ul>
</nav>

<!-- 상품 배너 -->
<div class="product-banner">
    <img src="/resources/image/img.png" alt="모자 배너 이미지">
</div>
<jsp:include page="./chat.jsp" flush="true" />
<!-- Best 10 상품 -->
<div class="best10-container">
    <h2>Best 10</h2>
    <div class="row">
        <!-- 1st item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_2.png" alt="모자1">
            <h3>베이직 무지 볼캡</h3>
            <p class="price">₩13,900</p>
        </div>
        <!-- 2nd item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_3.png" alt="모자2">
            <h3>무지 빅사이즈 하드 볼캡</h3>
            <p class="price">₩15,000</p>
        </div>
        <!-- 3rd item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_4.png" alt="모자3">
            <h3>무지 빅사이즈 버킷햇</h3>
            <p class="price">₩16,000</p>
        </div>
        <!-- 4th item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_5.png" alt="모자4">
            <h3>심플 5각 볼캡</h3>
            <p class="price">₩20,000</p>
        </div>
        <!-- 5th item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_6.png" alt="모자5">
            <h3>빅사이즈 밀짚 페도라</h3>
            <p class="price">₩18,900</p>
        </div>
    </div>
    <div class="row">
        <!-- 6th item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_7.png" alt="모자6">
            <h3>스퀘어 빅사이즈 페도라</h3>
            <p class="price">₩14,900</p>
        </div>
        <!-- 7th item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_8.png" alt="Best 10">
            <h3>원톤 섬머 빅사이즈 페도라</h3>
            <p class="price">₩19,000</p>
        </div>
        <!-- 8th item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_9.png" alt="모자8">
            <h3>린넨 심플 헌팅캡</h3>
            <p class="price">₩17,000</p>
        </div>
        <!-- 9th item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_10.png" alt="모자9">
            <h3>버클 컨트롤 헌팅캡</h3>
            <p class="price">₩16,500</p>
        </div>
        <!-- 10th item -->
        <div class="col-md-2 best10-item">
            <img src="/resources/image/img_11.png" alt="모자10">
            <h3>마르코디아즈 벤타르테 헌팅캡</h3>
            <p class="price">₩14,500</p>
        </div>
    </div>
</div>
<!-- Footer -->
<footer>
    <div class="footer-section container">
        <header class="main-header">
            <a href="/">
                <img src="/resources/image/img_1.png" alt="IAMCAP 로고">
            </a>
        </header>
        <div class="row">
            <div class="col-md-4">
                <h4>쇼핑몰 기본정보</h4>
                <p>
                    상호명: 아이엠캡 <br>
                    대표자명: 정지훈 <br>
                    사업장 주소: 서울 중구 퇴계로88길 58 2층 아이엠 <br>
                    대표 전화: 010-9207-5257 <br>
                    사업자 등록번호: 130-37-99238 <br>
                    통신판매업 신고번호: 2009-서울중구-0202 <br>
                    개인정보보호책임자: 정지훈
                </p>
            </div>
            <div class="col-md-4">
                <h4>고객센터 정보</h4>
                <p>
                    상담/주문 전화: 010-9207-5257 <br>
                    상담/주문 이메일: hades0302@naver.com <br>
                    cs 운영시간: 월-금 오전 11시~오후 5시 (문자 상담만 가능)
                </p>
            </div>
            <div class="col-md-4">
                <h4>결제 정보</h4>
                <p>
                    무통장 입금: 우리은행 1005-504-636693 (노유리)
                </p>
                <h4>SNS</h4>
                <p>Instagram</p>
            </div>
        </div>
    </div>
    <div class="text-center mt-4">
        <p>Copyright © 아이엠캡. All Rights Reserved. Hosting by Cafe24 Corp.</p>
    </div>
</footer>
</body>
</html>