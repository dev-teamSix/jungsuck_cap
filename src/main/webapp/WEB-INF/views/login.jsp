<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="/css/login.css">
    <script>
        let errorMsg = "${errorMsg}";

        function validateForm(event) {
            const id = document.getElementById('inputId').value.trim();
            const pwd = document.getElementById('inputPw').value.trim();

            if (id === '' || pwd === '') {
                event.preventDefault(); // 폼 제출을 막음
                $('#warningModal').modal('show'); // 모달 창 띄우기
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            document.getElementById('loginForm').addEventListener('submit', validateForm);

            // Enter 키를 누르면 모달의 확인 버튼 클릭
            document.addEventListener('keyup', function(event) {
                if (event.key === 'Enter') {
                    // 경고 모달이 표시되고 있을 때만
                    if ($('#warningModal').is(':visible')) {
                        document.getElementById('confirmButton').click();
                    }
                }
            });

            if (errorMsg) {
                $('#warningModal').modal('show');
            }
        });
    </script>
</head>
<body>

<header class="main-header">
    <div class="container">
        <a href="/login/form" class="logo">
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

<div class="login-container">
    <h1 class="text-center">로그인</h1>
    <form action="/login/in" method="POST" id="loginForm">
        <div class="form-group">
            <label for="inputId">아이디</label>
            <input type="text" class="form-control" id="inputId" name="id" value="${cookie['id'] != null ? cookie['id'].value : userDto.id}"  maxlength="20" placeholder="아이디를 입력해주세요">
            <div class="alert-container">
                <c:if test="${not empty valid_id}">
                    <span class="error-message">${valid_id}</span>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPw">비밀번호</label>
            <input type="password" class="form-control" id="inputPw" name="pwd" value="${userDto.pwd}" placeholder="비밀번호를 입력해주세요">
            <div class="alert-container">
                <c:if test="${not empty valid_pwd}">
                    <span class="error-message">${valid_pwd}</span>
                </c:if>
            </div>
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="saveIdBox" name="rememberId" ${cookie['id'] != null ? 'checked' : ''}>
            <label class="form-check-label" for="saveIdBox">ID 저장</label>
        </div>
        <button type="submit" class="btn btn-primary btn-block">로그인</button>
    </form>
    <div class="login-footer">
        <a href="#" data-toggle="modal" data-target="#userIdFindModel">아이디 찾기</a>
        <a href="#" data-toggle="modal" data-target="#userPwFindModel">비밀번호 찾기</a>
    </div>
</div>

<!-- 경고 모달 -->
<div class="modal fade" id="warningModal" tabindex="-1" role="dialog" aria-labelledby="warningModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="warningModalLabel">입력 오류</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <c:if test="${not empty errorMsg}">
                    ${errorMsg}
                </c:if>
                <c:if test="${empty errorMsg}">
                    아이디와 비밀번호를 모두 입력해주세요.
                </c:if>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="confirmButton">확인</button>
            </div>
        </div>
    </div>
</div>

<!-- 아이디 찾기 모달 -->
<div class="modal fade" id="userIdFindModel" tabindex="-1" role="dialog" aria-labelledby="userIdFindLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="userIdFindLabel">아이디 찾기</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" id="user_name" placeholder="이름을 입력해주세요.">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" id="user_email" placeholder="이메일을 입력해주세요.">
                    </div>
                    <div class="form-group" id="answerLine"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary" id="findIdButton">검색</button>
            </div>
        </div>
    </div>
</div>

<!-- 비밀번호 찾기 모달 -->
<div class="modal fade" id="userPwFindModel" tabindex="-1" role="dialog" aria-labelledby="userPwFindLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="userPwFindLabel">비밀번호 찾기</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <input type="text" class="form-control" name="id" id="findIdInput" placeholder="아이디를 입력해주세요.">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" id="findEmailInput" placeholder="이메일을 입력해주세요.">
                        <button type="button" id="checkEmailButton" class="btn btn-primary mt-2">인증번호 발송</button>
                    </div>

                    <!-- AJAX 성공 시 표시되는 부분 -->
                    <div class="form-group" id="emailInfo" style="display: none;">
                        <span class="text-info">인증번호 발송은 서버 상황에 따라 5초에서 10초 정도 시간이 걸릴 수 있습니다.</span>
                    </div>
                    <div class="form-group" id="emailCodeInput" style="display: none;">
                        <input class="form-control" id="checkEmail" type="text" placeholder="인증번호를 입력해주세요." aria-label="default input example">
                        <div class="error-message" id="alertCertified"></div>
                    </div>
                    <!-- AJAX 실패 시 표시되는 부분 -->
                    <div class="form-group" id="alertEmail" style="display: none;">
                        <span class="error-message">가입 시 입력하신 회원 정보가 맞는지 다시 한번 확인해 주세요.</span>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" name="password" id="findPwInput" placeholder="새 비밀번호를 입력해주세요." disabled="disabled">
                        <div class="error-message" id="alertPassword"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary" id="updatePW" disabled="disabled">수정</button>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        let code = '';
        $('#checkEmailButton').click(function() {
            let email = $('#findEmailInput').val(); // email 가져오기
            let id = $('#findIdInput').val(); // id 가져오기

            // 본인인증 (id, email 전달)
            $.ajax({
                type: 'POST',
                url: '/find/authentication',
                data: { email: email, id: id },
                success: function(data) {
                    if (data.result == 'fail') {
                        // 오류 메시지 표시
                        $('#alertEmail').show();
                        $('#emailInfo').hide();
                        $('#emailCodeInput').hide();
                    } else {
                        alert("인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
                        // 인증번호 입력 필드 및 정보 메시지 표시
                        $('#emailInfo').show();
                        $('#emailCodeInput').show();
                        $('#alertEmail').hide(); // 오류 메시지 숨김
                        $("#alertCertified").text("! 인증번호를 입력해주세요.");
                        $("#alertCertified").css({
                            "color": "red",
                            "font-size": "15px"
                        });

                        code = data.code; // 인증번호 저장

                        $('#checkEmail').prop('disabled', false);
                        $('#findPwInput').prop('disabled', true); // 인증 완료 전까지 비밀번호 입력 비활성화
                    }
                }
            });
        });

        $("#checkEmail").keyup(function () {
            if ($("#checkEmail").val().length != 6) {
                $("#alertCertified").text("! 인증번호가 일치하지 않습니다. 다시 확인해주시기 바랍니다.")
                $("#alertCertified").css({
                    "color": "red",
                    "font-size": "15px"
                });
                // 비밀번호 입력창 비활성화
                $('#findPwInput').prop('disabled', true);
            } else if ($("#checkEmail").val() == code) {
                $("#alertCertified").text("✔️ 메일인증이 완료되었습니다.")
                $("#alertCertified").css({
                    "color": "green",
                    "font-size": "15px"
                });
                // 비밀번호 입력창 활성화
                $('#findPwInput').prop('disabled', false);
            }
        });

        // 비밀번호 유효성 검사
        $("#findPwInput").keyup(function (event) {
            let value = $(event.target).val();
            let id = $('#findIdInput').val(); // id 가져오기

            let num = value.search(/[0-9]/g);
            let eng = value.search(/[a-z]/ig);
            let spe = value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

            // 연속적인 4자리 숫자 제한 검사 함수
            function hasSequentialNumbers(value) {
                for (let i = 0; i < value.length - 3; i++) {
                    let a = parseInt(value.charAt(i));
                    let b = parseInt(value.charAt(i + 1));
                    let c = parseInt(value.charAt(i + 2));
                    let d = parseInt(value.charAt(i + 3));

                    if (!isNaN(a) && !isNaN(b) && !isNaN(c) && !isNaN(d)) {
                        if (a + 1 === b && b + 1 === c && c + 1 === d) {
                            return true;
                        }
                    }
                }
                return false;
            }

            // ID 포함 여부 검사
            if (value.includes(id)) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "15px"
                });
                $("#alertPassword").text("! 비밀번호에 ID를 포함할 수 없습니다.");
                $('#updatePW').prop('disabled', true);
            } else if (value.length < 8 || value.length > 30) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "15px"
                });
                $("#alertPassword").text("! 비밀번호는 8자리 이상 30자리 이하여야 합니다.");
                $('#updatePW').prop('disabled', true);
            } else if (value.replace(/\s| /gi, "").length == 0) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "15px"
                });
                $("#alertPassword").text("! 비밀번호에 공백은 사용할 수 없습니다.");
                $('#updatePW').prop('disabled', true);
            } else if (num < 0 || eng < 0 || spe < 0) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "15px"
                });
                $("#alertPassword").text("! 비밀번호는 영어+숫자+특수문자로 이루어져야 합니다.");
                $('#updatePW').prop('disabled', true);
            } else if (hasSequentialNumbers(value)) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "15px"
                });
                $("#alertPassword").text("! 비밀번호에 4자리 이상의 연속적인 숫자를 사용할 수 없습니다.");
                $('#updatePW').prop('disabled', true);
            } else {
                $("#alertPassword").css({
                    "color": "black",
                    "font-size": "15px"
                });
                $("#alertPassword").text("✔️ 사용 가능한 비밀번호입니다.");
                $('#updatePW').prop('disabled', false);
            }
        });

        // 비밀번호 업데이트
        $("#updatePW").click(function () {
            $.ajax({
                type: "post",
                url: "/find/pwd",
                data: {
                    id: $('#findIdInput').val(),
                    pwd: $("#findPwInput").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data.result == 'fail') {
                        alert("해당하는 아이디가 존재하지 않습니다");
                    } else {
                        alert("비밀번호 변경에 성공하였습니다.");
                        location.reload();
                    }
                }
            });
        });
    });
</script>
</body>
</html>
