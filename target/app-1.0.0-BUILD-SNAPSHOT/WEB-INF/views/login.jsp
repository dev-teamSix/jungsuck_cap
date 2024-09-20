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
    <!-- jQuery (필수) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Bootstrap JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <!-- Bootstrap CSS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/footer.css">
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
<jsp:include page="/WEB-INF/views/header.jsp" />
<div class="login-container">
    <h1 class="text-center">로그인</h1>
    <form action="/login/in" method="POST" id="loginForm">
        <div class="form-group">
            <label for="inputId">아이디</label>
            <input type="text" class="form-control" id="inputId" name="id" value="${cookie['id'] != null ? cookie['id'].value : userDto.id}"  maxlength="20" placeholder="아이디를 입력해주세요">
            <div class="alert-container">
                <span style="color: red; font-size: 10px;">${valid_id}</span>
            </div>
        </div>
        <div class="form-group">
            <label for="inputPw">비밀번호</label>
            <input type="password" class="form-control" id="inputPw" name="pwd" value="${userDto.pwd}" placeholder="비밀번호를 입력해주세요">
            <div class="alert-container">
                <c:if test="${not empty valid_pwd}"><span style="color: red; font-size: 10px;">${valid_pwd}</span></c:if>
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
<jsp:include page="/WEB-INF/views/footer.jsp" />
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
                        <button type="button" id="checkEmailButton" class="btn btn-primary mt-2">임시 비밀번호 발송</button>
                    </div>

                    <!-- AJAX 성공 시 표시되는 부분 -->
                    <div class="form-group" id="emailInfo" style="display: none;">
                        <span class="text-info">임시 비밀번호 발송은 서버 상황에 따라 5초에서 10초 정도 시간이 걸릴 수 있습니다.</span>
                    </div>
                    <div class="form-group" id="emailCodeInput" style="display: none;">
                        <input class="form-control" id="checkEmail" type="text" placeholder="임시 비밀번호를 입력해주세요." aria-label="default input example">
                        <div class="error-message" id="alertCertified"></div>
                    </div>
                    <!-- AJAX 실패 시 표시되는 부분 -->
                    <div class="form-group" id="alertEmail" style="display: none;">
                        <span class="error-message">가입 시 입력하신 회원 정보가 맞는지 다시 한번 확인해 주세요.</span>
                    </div>

                    <%--                    <div class="form-group">--%>
                    <%--                        <input type="password" class="form-control" name="password" id="findPwInput" placeholder="새 비밀번호를 입력해주세요." disabled="disabled">--%>
                    <%--                        <div class="error-message" id="alertPassword"></div>--%>
                    <%--                    </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary" id="updatePW" disabled="disabled">확인</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        // 아이디 찾기
        $("#findIdButton").click(function () {
            var name = $("#user_name").val().trim();
            var email = $("#user_email").val().trim();

            // 이름과 이메일이 입력되었는지 확인
            if (name === "" || email === "") {
                $("#answerLine").css({
                    "color": "red",
                    "text-align": "center",
                    "text-size": "10px"
                });
                $("#answerLine").text("아이디와 이메일을 모두 입력해주세요.");
                return;  // 유효성 검사를 통과하지 못하면 AJAX 요청을 보내지 않음
            }

            $.ajax({
                type: "post",
                url: "/find/id",
                data: {
                    name: name,
                    email: email
                },
                dataType: "json",
                success: function (data) {
                    if (data.header.message === 'SUCCESS') {
                        $("#answerLine").css({
                            "color": "green",
                            "text-align": "center",
                            "text-size": "10px"
                        });
                        $("#answerLine").text(data.msg); // 서버에서 반환한 메시지를 사용
                    }
                },
                error: function (xhr, status, error) {
                    const response = xhr.responseJSON;

                    // 예외 메시지를 표시
                    let errorMessage = "알 수 없는 오류가 발생했습니다.";
                    if (response && response.message) {
                        errorMessage = response.message;
                    }

                    $("#answerLine").css({
                        "color": "red",
                        "text-align": "center",
                        "font-size": "10px"
                    });
                    $("#answerLine").text(errorMessage);
                }
            });
        });


        let code = '';
        $('#checkEmailButton').click(function() {
            let email = $('#findEmailInput').val(); // email 가져오기
            let id = $('#findIdInput').val(); // id 가져오기

            // 본인인증 (id, email 전달)
            $.ajax({
                type: 'POST',
                url: '/find/pwd',
                data: { email: email, id: id },
                success: function(data) {
                    if (data.header.message === 'SUCCESS') {
                        alert(data.msg);
                        // 인증번호 입력 필드 및 정보 메시지 표시
                        $('#emailInfo').show();
                        $('#emailCodeInput').show();
                        $('#alertEmail').hide(); // 오류 메시지 숨김
                        $("#alertCertified").text("! 비밀번호를 입력해주세요.");
                        $("#alertCertified").css({
                            "color": "red",
                            "font-size": "15px"
                        });

                        code = data.data; // 인증번호 저장

                        $('#checkEmail').prop('disabled', false);
                    }
                },
                error: function(xhr, status, error) {
                    // JSON 응답을 파싱하여 오류 메시지를 추출
                    const response = xhr.responseJSON;

                    if (response && response.header && response.header.message) {
                        // 서버에서 전달된 메시지를 #alertEmail 요소에 표시
                        $('#alertEmail').show();
                        $('#alertEmail').text(response.header.message);
                        $('#emailInfo').hide();
                        $('#emailCodeInput').hide();
                    } else {
                        // JSON 응답이 예상대로 오지 않았을 경우 기본 오류 메시지 표시
                        alert("알 수 없는 오류가 발생했습니다.");
                    }
                }
            });
        });

        $("#checkEmail").keyup(function () {
            if ($("#checkEmail").val().length != 10) {
                $("#alertCertified").text("! 비밀번호가 일치하지 않습니다. 다시 확인해주시기 바랍니다.")
                $("#alertCertified").css({
                    "color": "red",
                    "font-size": "15px"
                });
            } else if ($("#checkEmail").val() == code) {
                $("#alertCertified").text("✔️ 비밀번호가 일치합니다")
                $("#alertCertified").css({
                    "color": "green",
                    "font-size": "15px"
                });
                $('#updatePW').prop('disabled', false);
            }
        });


        // 비밀번호 업데이트
        $("#updatePW").click(function () {
            $.ajax({
                type: "post",
                url: "/find/updatePw",
                data: {
                    id : $('#findIdInput').val(),
                    pwd: $("#checkEmail").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data.header.message === 'SUCCESS') {
                        alert(data.msg);
                        location.reload();
                    }
                },
                error: function(xhr, status, error) {
                    const response = xhr.responseJSON;
                    // AJAX 요청 실패 시
                    if (response && response.header && response.header.message) {
                        alert(response.header.message);
                    } else {
                        alert("알 수 없는 오류가 발생했습니다.");
                    }
                }
            });
        });
    });
</script>
</body>
</html>