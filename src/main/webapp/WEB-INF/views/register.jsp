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
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<div class="contnet-wrapper">
    <section class="content container-fluid">
        <div class="register-box-body">
            <p class="login-box-msg">회원가입 페이지</p>
            <p>*필수입력 항목</p>
            <p class="errorMsg">
                <c:if test="${not empty errorMsg}">
                    ${errorMsg}
                </c:if>
            </p>
            <form:form  action="/user/save" id="insertForm" method="post">
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
                                    <input type="text" id="joinIdInput" value="${userDto.id}" class="form-control" name="id" placeholder="아이디를 입력하세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertId"><form:errors path="id" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="changePassword">비밀번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="password" id="changePassword"  class="form-control" name="pwd" placeholder="비밀번호를 입력하세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertPassword"><form:errors path="pwd" id="error_message"/></div>
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
                                <input type="text" id="userName" value="${userDto.name}" class="form-control" name="name" placeholder="이름을 입력해주세요">
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertName"><form:errors path="name" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userGender">성별</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="radio" id="userGender" name="gender" <c:if test="${userDto.gender == 'M'}">checked</c:if> value="M">남
                                    <input type="radio" id="userGender" name="gender" <c:if test="${userDto.gender == 'Y'}">checked</c:if> value="Y">여
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertGender"><form:errors path="gender" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userBirth">생년월일</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="date" id="userBirth" class="form-control" value="${userDto.birth}" name="birth" placeholder="생년월일을 선택해주세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertBirth" style="color: red; font-size: 10px;"><form:errors path="birth" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" style="display: flex; align-items: center;">
                                    <label for="searchAddressButton">주소</label>
                                    <button type="button" id="searchAddressButton" class="btn btn-primary" style="margin-left: 22px; padding: 5px 10px;">주소 검색</button>
                                </div>
                            </div>

                            <!-- 주소 검색 후 동적으로 생성될 input 필드들 -->
                            <div id="addressFields"></div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userPhone">휴대폰번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="text" id="userPhone" class="form-control" value="${userDto.phNum}" name="phNum" placeholder="휴대폰번호를 입력해주세요">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertPhone"><form:errors path="phNum" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userEmail">이메일</label>
                                </div>
                            </div>

                            <div class="row mt-1 email-container">
                                <input type="text" id="userEmail" class="form-control" value="${userDto.email}" name="email" placeholder="이메일 주소를 입력해주세요">
                                <button type="button" id="checkEmailButton" class="btn btn-primary" style="height: 36px" disabled='disabled'>인증번호 발송</button>
                            </div>

                            <div class="row mt-1">
                                <div class="col" id="alertEmail"><form:errors path="email" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col bi bi-exclamation-square-fill deepblue">
                                    인증번호 발송은 서버 상황에 따라 5초에서 10초 정도 시간이 걸릴 수 있습니다.
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input class="form-control"  id="checkEmail" type="text" value="${userDto.mailKey}" placeholder="인증번호를 입력해주세요." aria-label="default input example" disabled="disabled">
                                    <input type="hidden" name="mailKey" id="mailKeyHidden">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertCertified"><form:errors path="mailKey" id="error_message"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <a type="button" class="btn btn-default pull-left" href="home.jsp" style="margin-top: 4px;">취소</a>
                                    <button type="button" class="btn btn-primary infoModBtn pull-right" id="joinButton">가입하기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
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
                    url : "/user/checkDuplicatedId",
                    data: {
                        id : $("#joinIdInput").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        if(data.result == "fail") {
                            $("#alertId").css({
                                "color":"red",
                                "font-size" : "10px"
                            });
                            $("#alertId").text("! 이미 사용중인 아이디입니다.")
                        }else {
                            $("#alertId").css({
                                "color":"black",
                                "font-size" : "10px"
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

        // 오늘 날짜를 가져옴
        var today = new Date();
        var day = ("0" + today.getDate()).slice(-2);
        var month = ("0" + (today.getMonth() + 1)).slice(-2);
        var year = today.getFullYear();
        var maxDate = year + "-" + month + "-" + day;

        // 현재 날짜 이상 선택하지 못하게 설정
        $('#userBirth').attr('max', maxDate);

        // 생년월일 유효성 검사
        $("#userBirth").on("change", function() {
            var selectedDate = new Date($(this).val());
            var ageLimit = 14; // 최소 나이 제한
            var ageDate = new Date(today.getFullYear() - ageLimit, today.getMonth(), today.getDate());

            if (selectedDate > today) {
                $("#alertBirth").text("! 미래 날짜는 선택할 수 없습니다.");
            } else if (selectedDate > ageDate) {
                $("#alertBirth").text("! 회원가입은 만 " + ageLimit + "세 이상만 가능합니다.");
            } else {
                $("#alertBirth").text(""); // 오류 메시지 제거
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
                    url : "/user/checkDuplicatedEmail",
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
                url: "/user/checkEmail",
                data: {
                    email: $("#userEmail").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data.result == "error") {
                        alert("서버와 통신중 에러가 발생했습니다.");
                        $("#alertCertified").css({
                            "color": "red",
                            "font-size": "10px"
                        });
                        $("#alertCertified").text("! 서버와 통신중 에러가 발생했습니다.");
                    } else {
                        alert("인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
                        $("#alertCertified").text("! 인증번호를 입력해주세요.");
                        $("#alertCertified").css({
                            "color": "red",
                            "font-size": "10px"
                        });
                        code = data.code;
                        $("#checkEmail").attr("disabled", false);
                        $("#checkEmailButton").attr("disabled", true);

                        // 인증번호 발송 메시지 및 입력 필드 표시
                        $("#alertCertified").show();
                        $("#checkEmail").show();
                        $(".bi-exclamation-square-fill.deepblue").show();
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
                $("#mailKeyHidden").val(code);
            }
        });

        // 도로명 주소 API 팝업창 띄우기
        function openDaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {
                    // 주소 검색 후 결과를 input 필드로 추가
                    const addressFields = document.getElementById("addressFields");

                    // 기존에 생성된 필드가 있다면 제거
                    addressFields.innerHTML = "";

                    // 우편번호 필드 생성
                    const zipInput = document.createElement("input");
                    zipInput.type = "text";
                    zipInput.id = "zip";
                    zipInput.name = "zip";
                    zipInput.className = "form-control";
                    zipInput.placeholder = "우편번호";
                    zipInput.value = data.zonecode;
                    zipInput.readOnly = true;
                    addressFields.appendChild(zipInput);

                    // 도로명주소 필드 생성
                    const roadAdrInput = document.createElement("input");
                    roadAdrInput.type = "text";
                    roadAdrInput.id = "roadAdr";
                    roadAdrInput.name = "roadAdr";
                    roadAdrInput.className = "form-control";
                    roadAdrInput.placeholder = "도로명 주소";
                    roadAdrInput.value = data.roadAddress;
                    roadAdrInput.readOnly = true;
                    addressFields.appendChild(roadAdrInput);

                    // 지번주소 필드 생성
                    const jibunAdrInput = document.createElement("input");
                    jibunAdrInput.type = "text";
                    jibunAdrInput.id = "jibunAdr";
                    jibunAdrInput.name = "jibunAdr";
                    jibunAdrInput.className = "form-control";
                    jibunAdrInput.placeholder = "지번 주소";
                    jibunAdrInput.value = data.jibunAddress;
                    jibunAdrInput.readOnly = true;
                    addressFields.appendChild(jibunAdrInput);

                    // 상세주소 입력 필드 생성
                    const detAdrInput = document.createElement("input");
                    detAdrInput.type = "text";
                    detAdrInput.id = "detAdr";
                    detAdrInput.name = "detAdr";
                    detAdrInput.className = "form-control";
                    detAdrInput.placeholder = "상세 주소를 입력하세요";
                    addressFields.appendChild(detAdrInput);

                    // 버튼 텍스트를 "재검색"으로 변경
                    document.getElementById("searchAddressButton").innerText = "재검색";
                }
            }).open();
        }

        // 주소 검색 버튼 클릭 시 팝업창 열기
        document.getElementById("searchAddressButton").addEventListener("click", function () {
            openDaumPostcode();
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
