package com.firstSpring.dao.user;

import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;

import java.util.List;

public interface UserDao {
    // 테스트용 delete 문
    int deleteTestUser(String id) throws Exception;

    // 테스트용 deleteAll 문
    int deleteAllTestUser() throws Exception;

    // 회원가입한 전체 고객의 수 (모든 상태의 회원)
    int countUser() throws Exception;

    // 활성화('O') 상태인 전체 고객의 수
    int countActiveUser() throws Exception;

    // 회원가입한 전체 고객 정보 조회 (최근 가입한 고객부터 나열)
    List<UserDto> selectUserAll() throws Exception;

    // 탈퇴 회원을 제외한, 활성화('O') 상태인 모든 고객 정보 조회 (최근 가입한 고객부터 나열)
    List<UserDto> selectActiveUserAll() throws Exception;

    // 전체 가입고객(탈퇴회원 포함) 중 특정 아이디를 가진 고객 정보 확인
    UserDto selectAllUserId(String id) throws Exception;

    // 전체 가입고객(탈퇴회원 포함) 중 특정 이메일을 가진 고객 정보 확인
    @LogException
    UserDto selectAllUserEmail(String id) throws Exception;

    // 전체 가입고객(탈퇴회원 포함) 중 특정 이메일과 특정 아이디를 가진 고객 정보 확인
    @LogException
    UserDto selectAllUserIdEmail(String id, String email) throws Exception;

    // Login (SELECT)
    // > 회원상태가 활성화('O') & 메일 인증 여부가 'Y' & 특정 id를 가진 회원
    UserDto selectUser(String id) throws Exception;

    int updateRecentLoginDatetime(String id) throws Exception;

    // Sign Up (INSERT)
    // > 아이디, 이메일, 비밀번호, 이름, 생년월일, 성별, 폰번호, 우편번호, 도로명주소, 지번주소, 상세주소, 메일인증키, 시스템컬럼을 insert
    // > 회원상태 활성화('O') & 메일인증 여부('Y')
    int insertUser(UserDto userDto) throws Exception;

    // 이메일 인증 (UPDATE)
    // > 이메일 인증번호 받을 때마다 mail_key 컬럼에 저장
    // > 아이디/비밀번호 찾기 본인 인증 시 사용
    int updateMailKey(UserDto userDto) throws Exception;

    // 아이디 찾기 (SELECT)
    // > 이름 및 이메일로 식별된 특정 회원의 아이디 조회
    UserDto selectUserId(String name,String email) throws Exception;

    // 비밀번호 찾기 (UPDATE)
    // > 비밀번호 찾기 시 새로운 비밀번호로 변경
    int updateUserPwd(String id, String pwd) throws Exception;

    // 회원 정보 수정 (UPDATE)
    int updateUserInfo(UserDto userDto) throws Exception;
//
//    // 생년월일 변경 (UPDATE)
//    int updateUserBefBirth(String id, String birth) throws Exception;
//
//    // 핸드폰 번호 변경 (UPDATE)
//    int updateUserMobileNum(String id, String ph_num) throws Exception;

    // 회원탈퇴 (UPDATE)
    // > 회원 상태를 'O' -> 'L' 으로 변경
    int updateUserState(String id) throws Exception;
}
