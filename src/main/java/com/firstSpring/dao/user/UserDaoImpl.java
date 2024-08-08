package com.firstSpring.dao.user;

import com.firstSpring.domain.user.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession session;

    private static final String namespace = "com.firstSpring.dao.user.UserDao.";

    // 테스트용 delete 문
    @Override
    public int deleteTestUser(String id) throws Exception {
        return session.delete(namespace + "deleteTestUser", id);
    }

    // 테스트용 deleteAll 문
    @Override
    public int deleteAllTestUser() throws Exception {
        return session.delete(namespace + "deleteAllTestUser");
    }


    // 회원가입한 전체 고객의 수 (모든 상태의 회원)
    @Override
    public int countUser() throws Exception {
        return session.selectOne(namespace + "countUser");
    }

    // 활성화('O') 상태인 전체 고객의 수
    @Override
    public int countActiveUser() throws Exception {
        return session.selectOne(namespace + "countActiveUser");
    }

    // 회원가입한 전체 고객 정보 조회 (최근 가입한 고객부터 나열)
    @Override
    public List<UserDto> selectUserAll() throws Exception {
        return session.selectList(namespace + "selectUserAll");
    }

    // 탈퇴 회원을 제외한, 활성화('O') 상태인 모든 고객 정보 조회 (최근 가입한 고객부터 나열)
    @Override
    public List<UserDto> selectActiveUserAll() throws Exception {
        return session.selectList(namespace + "selectActiveUserAll");
    }

    // 전체 가입고객(탈퇴회원 포함) 중 특정 아이디를 가진 고객 정보 확인
    @Override
    public UserDto selectAllUserId(String id) throws Exception {
        return session.selectOne(namespace + "selectAllUserId", id);
    }

    // Login (SELECT)
    // > 회원상태가 활성화('O') & 메일 인증 여부가 'Y' & 특정 id를 가진 회원
    @Override
    public UserDto selectUser(String id) throws Exception {
        return session.selectOne(namespace + "selectUser", id);
    }

    // Sign Up (INSERT)
    // > 아이디, 이메일, 비밀번호, 이름, 생년월일, 성별, 폰번호, 우편번호, 도로명주소, 지번주소, 상세주소, 메일인증키, 시스템컬럼을 insert
    // > 회원상태 활성화('O') & 메일인증 여부('Y')
    @Override
    public int insertUser(UserDto userDto) throws Exception {
        return session.insert(namespace + "insertUser", userDto);
    }


    // 이메일 인증 (UPDATE)
    // > 이메일 인증번호 받을 때마다 mail_key 컬럼에 저장
    // > 아이디/비밀번호 찾기 본인 인증 시 사용
    @Override
    public int updateMailKey(UserDto userDto) throws Exception {
        return session.update(namespace + "updateMailKey", userDto);
    }


    // 아이디 찾기 (SELECT)
    // > 이름 및 이메일로 식별된 특정 회원의 아이디 조회
    @Override
    public UserDto selectUserId(UserDto userDto) throws Exception {
        return session.selectOne(namespace + "selectUserId", userDto);
    }

    // 비밀번호 찾기 (UPDATE)
    // > 비밀번호 찾기 시 새로운 비밀번호로 변경
    @Override
    public int updateUserPwd(String id, String pwd) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id); // 변경될 회원 ID
        map.put("pwd", pwd); // 변경할 비밀번호
        return session.update(namespace + "updateUserPwd", map);
    }

    // 이메일 변경 (UPDATE)
    @Override
    public int updateUserEmail(String id, String email) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id); // 변경될 회원 ID
        map.put("email", email); // 변경할 이메일
        return session.update(namespace + "updateUserEmail", map);
    }

    // 생년월일 변경 (UPDATE)
    @Override
    public int updateUserBefBirth(String id, String birth) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id); // 변경될 회원 ID
        map.put("birth", birth);
        return session.update(namespace + "updateUserBefBirth", map);
    }

    // 핸드폰 번호 변경 (UPDATE)
    @Override
    public int updateUserMobileNum(String id, String ph_num) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id); // 변경될 회원 ID
        map.put("ph_num", ph_num); // 변경할 핸드폰 번호
        return session.update(namespace + "updateUserMobileNum", map);
    }

    // 회원탈퇴 (UPDATE)
    // > 회원 상태를 'O' -> 'L' 으로 변경
    @Override
    public int updateUserState(String id) throws Exception {
        return session.update(namespace + "updateUserState", id);
    }
}