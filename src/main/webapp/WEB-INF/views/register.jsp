<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="/css/register.css">
</head>
<body>
<div class="contnet-wrapper">
    <section class="content container-fluid">
        <div class="register-box-body">
            <p class="login-box-msg">회원가입 페이지</p>
            <form action="<c:url value='/user/save'/>" id="insertForm" method="post">
                <div class="row mt-2">
                    <div class="row mt-2">
                        <div class="col-ls-5">
                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="joinIdInput">아이디</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="text" id="joinIdInput" class="form-control" name="id" placeholder="아이디를 입력하세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertId"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="changePassword">비밀번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="password" id="changePassword" class="form-control" name="pwd" placeholder="비밀번호를 입력하세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertPassword"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="confirmPassword">비밀번호 확인</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="password" id="confirmPassword" class="form-control" name="pwd" placeholder="비밀번호를 한번 더 입력해주세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertPassword2"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userName">이름</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <input type="text" id="userName" class="form-control" name="name" placeholder="이름을 입력해주세요">
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertName"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userGender">성별</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="radio" id="userGender" name="gender" value="M">남
                                    <input type="radio" id="userGender" name="gender" value="Y">여
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertGender"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userBirth">생년월일</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="date" id="userBirth" class="form-control" name="birth" placeholder="생년월일을 선택해주세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertBirth"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userPhone">휴대폰번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="text" id="userPhone" class="form-control" name="birth" placeholder="휴대폰번호를 입력해주세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertPhone"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userEmail">이메일</label>
                                </div>
                            </div>

                            <div class="row mt-1 email-container">
                                <input type="text" id="userEmail" class="form-control" name="email" placeholder="이메일 주소를 입력해주세요">
                                <button type="button" id="checkEmailButton" class="btn btn-primary" style="height: 36px" disabled='disabled'>인증번호 발송</button>
                            </div>

                            <div class="row mt-1">
                                <div class="col" id="alertEmail"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col bi bi-exclamation-square-fill deepblue">
                                    인증번호 발송은 서버 상황에 따라 5초에서 10초 정도 시간이 걸릴 수 있습니다.
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input class="form-control" id="checkEmail" type="text" placeholder="인증번호를 입력해주세요." aria-label="default input example" disabled="disabled">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertCertified"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <a type="button" class="btn btn-default pull-left" href="home.jsp">취소</a>
                                    <button type="button" class="btn btn-primary infoModBtn pull-right" id="joinButton">가입하기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    window.addEventListener("DOMContentLoaded",function (){
        $("#joinIdInput").keyup(function () {
            var value = $(event.target).val();
            var num = value.search(/[0-9]/g);
            var eng = value.search(/[a-z]/ig);

            if(value.length < 5 || value.length > 10) {
                $("#alertId").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertId").text("! 아이디는 5자리이상 10자리 이하여야 합니다.")
            }else if(value.replace(/\s| /gi, "").length == 0) {
                $("#alertId").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertId").text("! 아이디에 공백은 사용할 수 없습니다.")
            }else if(num <0 || eng <0) {
                $("#alertId").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertId").text("! 아이디는 영어+숫자로 이루어져야 합니다.")
            }else {
                $.ajax({
                    type: "post",
                    url : "/isExistId",
                    data: {
                        id : $("#joinIdInput").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        if(data.result == "fail") {
                            $("#alertId").css({
                                "color":"red"
                            });
                            $("#alertId").text("! 이미 사용중인 아이디입니다.")
                        }else {
                            $("#alertId").css({
                                "color":"black"
                            });
                            $("#alertId").text("✔️ 사용 가능한 아이디입니다.")
                        }
                    }
                });
            }
        });

        $("#changePassword").keyup(function () {
            var value = $(event.target).val();

            var num = value.search(/[0-9]/g);
            var eng = value.search(/[a-z]/ig);
            var spe = value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

            if(value.length < 8 || value.length > 30) {
                $("#alertPassword").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertPassword").text("! 비밀번호는 8자리이상 30자리 이하여야 합니다.")
            }else if(value.replace(/\s| /gi, "").length == 0) {
                $("#alertPassword").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertPassword").text("! 비밀번호에 공백은 사용할 수 없습니다.")
            }else if(num <0 || eng <0 || spe < 0) {
                $("#alertPassword").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertPassword").text("! 비밀번호는 영어+숫자+특수문자로 이루어져야 합니다.")
            }else {
                $("#alertPassword").css({
                    "color":"black",
                    "font-size" : "10px"
                });
                $("#alertPassword").text("✔️ 사용 가능한 비밀번호입니다.")
            }
        });

        $("#confirmPassword").keyup(function () {
            var value = $("#confirmPassword").val();

            if(value != $("#changePassword").val()) {
                $("#alertPassword2").css({
                    "color":"red",
                    "font-size":"12px"
                });
                $("#alertPassword2").text("! 비밀번호가 일치하지 않습니다.")
                return;
            }
            $("#alertPassword2").css({
                "color":"black",
                "font-size":"10px"
            });
            $("#alertPassword2").text("✔️ 비밀번호가 일치합니다.")
        });

        $("#userName").keyup(function () {
            var value = $(event.target).val();
            var txt = value.search(/[가-힣]/g);

            if(value.length < 1 || value.length > 10) {
                $("#alertName").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertName").text("! 이름은 1자리이상 10자리 이하여야 합니다.")
            }else if(value.replace(/\s| /gi, "").length == 0) {
                $("#alertName").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertName").text("! 이름은 공백을 사용할 수 없습니다.")
            }else if(txt < 0) {
                $("#alertName").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertName").text("! 이름은 한글만 입력 가능합니다.")
            }else {
                $("#alertName").css({
                    "color":"black",
                    "font-size" : "10px"
                });
                $("#alertName").text("✔️ 사용 가능한 이름입니다.")
            }
        });

        $("#userPhone").keyup(function () {
            var value = $(event.target).val();
            var phone = $("#userPhone").val();
            var regex = new RegExp("^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$");

            if(value.length < 13 || value.length > 13) {
                $("#alertPhone").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertPhone").text("! 휴대폰번호는 하이폰포함 13글자여야 됩니다.")
            }else if(!regex.test(phone)) {
                $("#alertPhone").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertPhone").text("! 휴대폰번호 정규식에 맞게끔 작성해주세요.")
            }else {
                $("#alertPhone").css({
                    "color":"black",
                    "font-size" : "10px"
                });
                $("#alertPhone").text("✔️ 사용 가능한 휴대폰번호입니다.")
            }
        });

        $("#userEmail").keyup(function () {
            var value = $(event.target).val();
            var email = $("#userEmail").val();
            var regex = new RegExp("^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$");

            if(!regex.test(email)) {
                $("#alertEmail").css({
                    "color":"red",
                    "font-size" : "10px"
                });
                $("#alertEmail").text("! 이메일의 정규식에 맞게끔 작성 부탁드립니다. abc@gmail.com");
            }else {
                $.ajax({
                    type: "post",
                    url : "/isExistEmail",
                    data: {
                        email : email
                    },
                    dataType: "json",
                    success: function (data) {
                        if(data.result == "fail") {
                            $("#alertEmail").css({
                                "color":"red",
                                "font-size":"10px"
                            });
                            $("#alertEmail").text("!  이메일이 이미 사용중입니다.");
                        }else {
                            $("#alertEmail").css({
                                "color":"black",
                                "font-size":"10px"
                            });
                            $("#alertEmail").text("✔️ 사용 가능한 이메일주소입니다.");
                            $("#checkEmailButton").attr("disabled",false);
                        }
                    }
                });
            }
        });

        var code = "";
        $("#checkEmailButton").click(function () {
            $.ajax({
                type: "post",
                url : "/checkEmail",
                data: {
                    email : $("#userEmail").val()
                },
                dataType: "json",
                success: function (data) {
                    if(data.result == "error") {
                        alert("서버와 통신중 에러가 발생했습니다.");
                        $("#alertCertified").css({
                            "color":"red",
                            "font-size":"10px"
                        });
                        $("#alertCertified").text("! 서버와 통신중 에러가 발생했습니다.");
                    }else {
                        alert("인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
                        $("#alertCertified").text("! 인증번호를 입력해주세요.")
                        $("#alertCertified").css({
                            "color":"red",
                            "font-size":"10px"
                        });
                        code = data.code;
                        $("#checkEmail").attr("disabled",false);
                        $("#checkEmailButton").attr("disabled",true);
                    }
                }
            });
        });

        $("#checkEmail").keyup(function () {
            if($("#checkEmail").val().length != 6) {
                $("#alertCertified").text("! 인증번호가 일치하지 않습니다. 다시 확인해주시기 바랍니다.")
                $("#alertCertified").css({
                    "color":"red",
                    "font-size" : "10px"
                });
            }else if($("#checkEmail").val() == code) {
                $("#alertCertified").text("✔️ 메일인증이 완료되었습니다.")
                $("#alertCertified").css({
                    "color":"green",
                    "font-size" : "10px"
                });
                $("#checkEmail").attr("disabled",true);
                $("#joinButton").attr("disabled", false);
                $("#userEmail").attr("disabled", false);
                $("#updateInfo").attr("disabled", false);
            }
        });

        $("#joinButton").click(function () {
            if($("#alertId").text() != "✔️ 사용 가능한 아이디입니다.") {
                alert("아이디 중복 확인을 해주세요.");
                return;
            }

            if($("#alertName").text() != "✔️ 사용 가능한 이름입니다.") {
                alert("이름을 다시 작성해주세요.");
                return;
            }

            if($("#alertPhone").text() != "✔️ 사용 가능한 휴대폰번호입니다.") {
                alert("휴대폰번호를 확인을 해주세요.");
                return;
            }

            if($("#alertEmail").text() != "✔️ 사용 가능한 이메일주소입니다.") {
                alert("이메일 중복 확인을 해주세요.");
                return;
            }

            if($("#alertCertified").text() != "✔️ 메일인증이 완료되었습니다.") {
                alert("메일 인증을 해주세요.");
                return;
            }

            $("#insertForm").submit();

            alert("회원가입이 완료 되었습니다.");
        });
    });
</script>
</body>
</html>
