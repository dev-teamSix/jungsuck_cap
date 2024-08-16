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
                                <div class="col-ls-5" id="alertId"><c:if test="${not empty valid_id}"><span style="color: red; font-size: 10px;">${valid_id}</span></c:if></div>
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
                                <div class="col-ls-5" id="alertPassword"><c:if test="${not empty valid_pwd}"><span style="color: red; font-size: 10px;">${valid_pwd}</span></c:if></div>
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
                                <div class="col-ls-5" id="alertName"><c:if test="${not empty valid_name}"><span style="color: red; font-size: 10px;">${valid_name}</span></c:if></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <label for="userGender">성별</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input type="radio" class="maleGender" id="userGender" name="gender" <c:if test="${userDto.gender == 'M'}">checked</c:if> value="M">남
                                    <input type="radio" class="femaleGender" id="userGender" name="gender" <c:if test="${userDto.gender == 'Y'}">checked</c:if> value="Y">여
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertGender"><c:if test="${not empty valid_gender}"><span style="color: red; font-size: 10px;">${valid_gender}</span></c:if></div>
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
                                <div class="col-ls-5" id="alertBirth" style="color: red; font-size: 10px;"><c:if test="${not empty valid_birth}"><span style="color: red; font-size: 10px;">${valid_birth}</span></c:if></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" style="display: flex; align-items: center;">
                                    <label for="searchAddressButton">주소</label>
                                    <button type="button" id="searchAddressButton" class="btn btn-primary" style="margin-left: 22px; padding: 5px 10px;">주소 검색</button>
                                </div>
                            </div>

                            <!-- 주소 검색 후 동적으로 생성될 input 필드들 -->
                            <div id="addressFields">
                                <c:if test="${not empty userDto.zip}">
                                    <input type="text" id="zip" name="zip" class="form-control" value="${userDto.zip}" placeholder="우편번호" readonly>
                                </c:if>
                                <c:if test="${not empty userDto.roadAdr}">
                                    <input type="text" id="roadAdr" name="roadAdr" class="form-control" value="${userDto.roadAdr}" placeholder="도로명 주소" readonly>
                                </c:if>
                                <c:if test="${not empty userDto.jibunAdr}">
                                    <input type="text" id="jibunAdr" name="jibunAdr" class="form-control" value="${userDto.jibunAdr}" placeholder="지번 주소" readonly>
                                </c:if>
                                <c:if test="${not empty userDto.detAdr}">
                                    <input type="text" id="detAdr" name="detAdr" class="form-control" value="${userDto.detAdr}" placeholder="상세 주소를 입력하세요">
                                </c:if>
                            </div>

                            <!-- 유효성 검사 메시지를 표시할 요소 추가 -->
                            <div id="validationMessage" style="color: red; font-size: 10px; display: none;">
                                ! 주소를 입력해주세요
                            </div>

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
                                <div class="col-ls-5" id="alertPhone"><c:if test="${not empty valid_phNum}"><span style="color: red; font-size: 10px;">${valid_phNum}</span></c:if></div>
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
                                <div class="col" id="alertEmail"><c:if test="${not empty valid_email}"><span style="color: red; font-size: 10px;">${valid_email}</span></c:if></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col bi bi-exclamation-square-fill deepblue">
                                    인증번호 발송은 서버 상황에 따라 5초에서 10초 정도 시간이 걸릴 수 있습니다.
                                </div>
                            </div>

                            <!-- 이메일 인증번호 입력 -->
                            <div class="row mt-1">
                                <div class="col-ls-5">
                                    <input class="form-control" id="checkEmail" type="text" value="${userDto.mailKey}" placeholder="인증번호를 입력해주세요." aria-label="default input example" disabled="disabled">
                                    <input type="hidden" name="mailKey" id="mailKeyHidden" value="${userDto.mailKey}">
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-ls-5" id="alertCertified"><c:if test="${not empty valid_mailKey}"><span style="color: red; font-size: 10px;">${valid_mailKey}</span></c:if></div>
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
    function checkGender() {
        let maleChecked = document.querySelector('.maleGender').checked;
        let femaleChecked = document.querySelector('.femaleGender').checked;

        let alertGender = document.getElementById('alertGender');
        if (maleChecked || femaleChecked) {
            alertGender.style.display = 'none';
        } else {
            alertGender.style.display = 'block';
        }
    }

    let radios = document.querySelectorAll('input[name="gender"]');
    radios.forEach(function(radio) {
        radio.addEventListener('change', checkGender);
    });
    window.addEventListener("DOMContentLoaded",function (){
        checkGender();
        // errorMsg가 존재하면 alert로 표시
        <c:if test="${not empty errorMsg}">
        alert("${errorMsg}");
        </c:if>
        // 모든 필드가 초기값으로 설정되도록 유지
        if ($("#joinIdInput").val()) {
            $("#alertId").css({
                "color":"black",
                "font-size" : "10px"
            });
            $("#alertId").text("✔️ 사용 가능한 아이디입니다.");
        }

        if ($("#changePassword").val()) {
            $("#alertPassword").css({
                "color":"black",
                "font-size" : "10px"
            });
            $("#alertPassword").text("✔️ 사용 가능한 비밀번호입니다.");
        }

        if ($("#userName").val()) {
            $("#alertName").css({
                "color":"black",
                "font-size" : "10px"
            });
            $("#alertName").text("✔️ 사용 가능한 이름입니다.");
        }

        // if ($("input[name='gender']:checked").val()) {
        //     $("#alertGender").css({
        //         "color":"black",
        //         "font-size" : "10px"
        //     });
        //     $("#alertGender").text("✔️ 성별이 선택되었습니다.");
        // }

        if ($("#userBirth").val()) {
            $("#alertBirth").text("");
        }

        if ($("#userPhone").val()) {
            $("#alertPhone").css({
                "color":"black",
                "font-size" : "10px"
            });
            $("#alertPhone").text("✔️ 사용 가능한 휴대폰번호입니다.");
        }

        if ($("#userEmail").val()) {
            $("#alertEmail").css({
                "color":"black",
                "font-size" : "10px"
            });
            $("#alertEmail").text("✔️ 사용 가능한 이메일주소입니다.");
            $("#checkEmailButton").attr("disabled", false);
        }

        // 폼 로드 시 주소 값과 mailKey 값이 있는지 확인하고 초기화
        if ($("#zip").val() || $("#roadAdr").val() || $("#jibunAdr").val() || $("#detAdr").val()) {
            $("#searchAddressButton").text("재검색");
            $("#addressFields").show();
        }

        let code = "";
        // 폼 로드 시 메일 인증이 완료된 경우 표시
        if ($("#mailKeyHidden").val()) {
            // $("#checkEmail").attr("disabled", true);
            // $("#joinButton").attr("disabled", false);
            // $("#userEmail").attr("disabled", true);  // 이메일 수정 불가하도록 설정
            $("#alertCertified").show();
            $("#checkEmail").show();
            $(".bi-exclamation-square-fill.deepblue").show();
            $("#alertCertified").text("✔️ 메일인증이 완료되었습니다.");
            $("#alertCertified").css({
                "color": "green",
                "font-size": "10px"
            });

            code = $("#checkEmail").val();
            // 메일 인증 완료 시 #checkEmail의 값을 #mailKeyHidden에 저장
            $("#mailKeyHidden").val($("#checkEmail").val());
        }


        $("#joinIdInput").keyup(function () {
            let value = $(event.target).val();
            let num = value.search(/[0-9]/g);
            let eng = value.search(/[a-z]/ig);

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
                    url: "/user/checkDuplicatedId",
                    data: {
                        id: $("#joinIdInput").val()
                    },
                    dataType: "json",
                    success: function(data) {
                        if(data.result === "fail") {
                            $("#alertId").css({
                                "color": "red",
                                "font-size": "10px"
                            });
                            $("#alertId").text(data.message);
                        } else {
                            $("#alertId").css({
                                "color": "black",
                                "font-size": "10px"
                            });
                            $("#alertId").text("✔️ 사용 가능한 아이디입니다.");
                        }
                    },
                    error: function(xhr) {
                        const response = xhr.responseJSON;
                        $("#alertId").css({
                            "color": "red",
                            "font-size": "10px"
                        });
                        $("#alertId").text(response.message);
                    }
                });

            }
        });

        $("#changePassword").keyup(function (event) {
            let value = $(event.target).val();
            let userId = $("#joinIdInput").val(); // ID 값 가져오기

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
            if (value.includes(userId)) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "10px"
                });
                $("#alertPassword").text("! 비밀번호에 ID를 포함할 수 없습니다.");
            } else if (value.length < 8 || value.length > 30) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "10px"
                });
                $("#alertPassword").text("! 비밀번호는 8자리 이상 30자리 이하여야 합니다.");
            } else if (value.replace(/\s| /gi, "").length == 0) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "10px"
                });
                $("#alertPassword").text("! 비밀번호에 공백은 사용할 수 없습니다.");
            } else if (num < 0 || eng < 0 || spe < 0) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "10px"
                });
                $("#alertPassword").text("! 비밀번호는 영어+숫자+특수문자로 이루어져야 합니다.");
            } else if (hasSequentialNumbers(value)) {
                $("#alertPassword").css({
                    "color": "red",
                    "font-size": "10px"
                });
                $("#alertPassword").text("! 비밀번호에 4자리 이상의 연속적인 숫자를 사용할 수 없습니다.");
            } else {
                $("#alertPassword").css({
                    "color": "black",
                    "font-size": "10px"
                });
                $("#alertPassword").text("✔️ 사용 가능한 비밀번호입니다.");
            }
        });


        $("#confirmPassword").keyup(function () {
            let value = $("#confirmPassword").val();

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
            let value = $(event.target).val();
            let txt = value.search(/[가-힣]/g);

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
        let today = new Date();
        let day = ("0" + today.getDate()).slice(-2);
        let month = ("0" + (today.getMonth() + 1)).slice(-2);
        let year = today.getFullYear();
        let maxDate = year + "-" + month + "-" + day;

        // 현재 날짜 이상 선택하지 못하게 설정
        $('#userBirth').attr('max', maxDate);

        // 생년월일 유효성 검사
        $("#userBirth").on("change", function() {
            let selectedDate = new Date($(this).val());
            let ageLimit = 14; // 최소 나이 제한
            let ageDate = new Date(today.getFullYear() - ageLimit, today.getMonth(), today.getDate());

            if (selectedDate > today) {
                $("#alertBirth").text("! 미래 날짜는 선택할 수 없습니다.");
            } else if (selectedDate > ageDate) {
                $("#alertBirth").text("! 회원가입은 만 " + ageLimit + "세 이상만 가능합니다.");
            } else {
                $("#alertBirth").text(""); // 오류 메시지 제거
            }
        });

        $("#userPhone").keyup(function () {
            let value = $(event.target).val();
            let phone = $("#userPhone").val();
            let regex = new RegExp("^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$");

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
            let value = $(event.target).val();
            let email = $("#userEmail").val();
            let regex = new RegExp("^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$");

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
                        email: email
                    },
                    dataType: "json",
                    success: function(data) {
                        $("#alertEmail").css({
                            "color": "black",
                            "font-size": "10px"
                        });
                        $("#alertEmail").text("✔️ 사용 가능한 이메일주소입니다.");
                        $("#checkEmailButton").attr("disabled", false);
                    },
                    error: function(xhr) {
                        const response = xhr.responseJSON;
                        $("#alertEmail").css({
                            "color": "red",
                            "font-size": "10px"
                        });
                        $("#alertEmail").text(response.message);
                    }
                });
            }
        });

        $("#checkEmailButton").click(function () {
            $.ajax({
                type: "post",
                url: "/user/checkEmail",
                data: {
                    email: $("#userEmail").val()
                },
                dataType: "json",
                success: function (data) {
                    // 성공적으로 인증번호가 발송된 경우
                    if (data.result === "success") {
                        alert(data.message);
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
                },
                error: function(xhr) {
                    // 서버 오류 발생 시 처리
                    const response = xhr.responseJSON;
                    alert(response.message);
                    $("#alertCertified").css({
                        "color": "red",
                        "font-size": "10px"
                    });
                    $("#alertCertified").text(response.result || "! 서버와 통신 중 에러가 발생했습니다.");
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

        const zipInput = document.getElementById("zip");
        const roadAdrInput = document.getElementById("roadAdr");
        const jibunAdrInput = document.getElementById("jibunAdr");

        // 필드 입력 시마다 유효성 검사 실행
        [zipInput, roadAdrInput, jibunAdrInput].forEach(function(input) {
            if (input) { // input 필드가 존재할 경우에만 이벤트 리스너 추가
                input.addEventListener("input", validateAddress);
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

        // 주소 유효성 검사 함수
        function validateAddress() {
            const zipInput = document.getElementById("zip");
            const roadAdrInput = document.getElementById("roadAdr");
            const jibunAdrInput = document.getElementById("jibunAdr");

            const validationMessage = document.getElementById("validationMessage");

            // 유효성 검사: 우편번호, 도로명 주소, 지번 주소가 모두 입력되었는지 확인
            if (!zipInput || !roadAdrInput || !jibunAdrInput ||
                zipInput.value.trim() === "" ||
                roadAdrInput.value.trim() === "" ||
                jibunAdrInput.value.trim() === "") {
                validationMessage.style.display = 'block'; // 메시지 표시
                return false; // 폼 제출 방지
            }

            validationMessage.style.display = 'none'; // 메시지 숨기기
            return true; // 폼 제출 허용
        }

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

            // 주소 유효성 검사
            if (!validateAddress()) {
                event.preventDefault(); // 폼 제출 방지
                return; // validateAddress가 false를 반환하면 이 시점에서 함수 종료
            }

            $("#insertForm").submit();
        });
    });
</script>
</body>
</html>
