package com.firstSpring.service.user;

import com.firstSpring.domain.user.UserDto;

public interface UserService {
    // 로그인 - SELECT (=READ; 조회 기능)
    UserDto getCustLoginInfo(String id);

    // 로그인 시 최근 로그인 일시 업데이트
    int updateRecentLoginHist(String id) throws Exception;

    // 아이디 찾기
    UserDto findUserId(String email);

    // 전체 가입고객(탈퇴회원 포함)의 아이디 조회하여 아이디 중복여부 확인
    boolean checkDuplicatedId(String id);

    // 아이디 조회여부 확인
    boolean checkExistOfId(String id);

    // 암호화 전후 비밀번호 비교
    boolean checkPwdMatch(String id, String pwd);

    // 비밀번호 찾기
    // 새로운 비밀번호로 변경
    boolean modifyUserPwd(String id, String pwd) throws Exception;

    // 회원가입
    boolean saveCustJoinInfo(UserDto userDto);

    // 이메일 인증
    // 매 인증 시 랜덤 문자 및 숫자로 구성된 인증코드 업데이트
    // 본인인증 시 부여된 인증코드 업데이트
    int saveCustMailKey(UserDto userDto) throws Exception;


    // 회원 정보 수정
    boolean modifyUserInfo(UserDto userDto);


    // 이메일 인증번호 전송
    int sendMail(String email);



    // 회원탈퇴
    // 회원 상태를 'O' -> 'L' 으로 변경
    // 가입회원 여부 확인
    // 가입된 회원이면 탈퇴 처리
    // 미가입 회원이면 예외 처리
    boolean changeWithdrawalState(String id);
}
