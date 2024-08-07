package com.firstSpring.dao.user;

import com.firstSpring.domain.user.UserDto;

import java.sql.Timestamp;
import java.util.List;

public interface UserDao {
    // 테스트용 delete 문
    int deleteTestUser(String id) throws Exception;

    // 테스트용 deleteAll 문
    int deleteAllTestUser() throws Exception;

    // 현재 시간 확인하는 select 문 (Timestamp 활용)
    Timestamp selectNow() throws Exception;

    // 회원가입한 고객의 수
    int countUser() throws Exception;

    // 가입 상태의 고객의 수
    int countActiveUser() throws Exception;

    // 회원가입한 전체 고객 조회
    List<UserDto> selectUserAll() throws Exception;

    // 탈퇴 회원을 제외한, 가입상태 'Y' 인 모든 고객 조회
    List<UserDto> selectActiveUserAll() throws Exception;

    // SELECT
    // 1. 로그인
    // 1.1. 아이디로 식별된 특정 고객 조회
    // 1.1.1. view 에서 입력한 id 를 매개변수로 받고 UserDto 인스턴스에 고객 정보 담아서 반환
    UserDto selectUser(String id) throws Exception;

    // UPDATE
    // 1.2. 로그인 시 최근 접속일시 업데이트
    // view 에서 입력한 id 를 매개변수로 받고 UserDto 인스턴스에 수정된 고객 정보 담아서 반환
    int updateRecentLoginDatetime(String id) throws Exception;

    // INSERT
    // 2. 회원가입
    // 2.1. 아이디, 비밀번호, 이름, 생년월일, 성별, 전화번호, 휴대폰 번호, 이메일, 가입일, 가입상태, 최초등록일, 최초등록자, 최종수정일, 최종수정자 데이터 추가
    // 2.1.2. view 에서 입력한 고객 정보를 UserDto 객체로 받아서 처리하고, 따로 특정 반환 사항 없이 실행 됐으면 1 반환
    int insertUser(UserDto userDto) throws Exception;

    // 2.2. 전체 가입고객(탈퇴회원 포함) 중 특정 아이디를 가진 고객 존재여부 확인
    UserDto selectAllUserId(String id) throws Exception;

    // UPDATE
    // 3. 이메일 인증
    // 3.1. 이메일 인증번호 받을 때마다 mail_key 컬럼에 저장
    // 3.1.1. 아이디/비밀번호 찾기 본인 인증 시 사용
    int updateMailKey(UserDto userDto) throws Exception;

    // SELECT
    // 4.1. 아이디 찾기
    // 4.1.1. 이름 및 이메일로 식별된 특정 회원의 아이디 조회
    UserDto selectUserId(UserDto userDto) throws Exception;

    // UPDATE
    // 4.2. 비밀번호 찾기
    // 4.2.1. 비밀번호 찾기 시 새로운 비밀번호로 변경
    int updateUserPwd(String id, String pwd) throws Exception;

    // UPDATE
    // 5. 회원정보 변경
    // 5.1. 이메일 변경
    int updateUserEmail(String id, String email) throws Exception;

    // 5.2. 생년월일 변경
    int updateUserBefBirth(String id, Integer bef_birth) throws Exception;

    // 5.3. 핸드폰 번호 변경
    int updateUserMobileNum(String id, Integer mobile_num) throws Exception;

    // UPDATE
    // 6. 회원탈퇴
    // 6.1. 회원가입 상태를 Y -> N 으로 변경
    int updateUserState(String id) throws Exception;
}
