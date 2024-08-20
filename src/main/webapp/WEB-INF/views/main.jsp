<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
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
    </style>
</head>

<body>
<header class="main-header">
    <div class="container">
        <a href="/" class="logo">
            <span class="logo-lg"><b>모자의 정석</b></span>
        </a>
        <nav class="navbar navbar-expand-lg navbar-dark">
            <a href="#" class="navbar-toggler" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <c:choose>
                        <c:when test="${!empty sessionUser}">
                            <button type="button" style="background-color: #343a40; color: #ffffff; border: none; width: 80px" onclick="location.href='/cart/cartList'">장바구니</button>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${sessionUser.name}
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <p class="dropdown-item">
                                        가입일자 : <fmt:formatDate value="${sessionUser.regDt}" pattern="yyyy-MM-dd"/> <br>
                                        최근 로그인일자 : <fmt:formatDate value="${sessionUser.recentLogin}" pattern="yyyy-MM-dd"/>
                                    </p>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">게시글</a>
                                    <a class="dropdown-item" href="#">공지사항</a>
                                    <a class="dropdown-item" href="#">Q&A</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/login/out" id="logoutButton">
                                        <i class="glyphicon glyphicon-log-out"></i> 로그아웃
                                    </a>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    회원가입 또는 로그인
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <p class="dropdown-item">회원가입 또는 로그인해주세요</p>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="<c:url value='/user/register'/>">
                                        <i class="fa fa-user-plus"></i> 회원가입
                                    </a>
                                    <a class="dropdown-item" href="<c:url value='/login/form'/>">
                                        <i class="glyphicon glyphicon-log-in"></i> 로그인
                                    </a>
                                </div>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
    </div>
</header>
</body>
</html>
