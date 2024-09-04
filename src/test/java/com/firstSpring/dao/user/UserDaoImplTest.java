//package com.firstSpring.dao.user;
//
//import com.firstSpring.domain.user.UserDto;
//import org.junit.jupiter.api.Assertions;
//import org.junit.Before;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.Assert.*;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class UserDaoImplTest {
//
//    @Autowired
//    private UserDao userDao;
//
//
//    // 1. 초기 테스트
//    // 1.1. 테스트하는 클래스의 주입 상태 확인
//    @Test
//    public void userDao_주입상태_확인_테스트() throws Exception {
//        assertNotNull(userDao);
//    }
//
//    // 보조기능 메서드 : setUp(), cleanDB()
//    // @Before로 각 테스트 메서드 실행 전 Data setting
//    // > DB 테이블에 더미데이터 50개 insert
//    @Before
//    public void setUp() throws Exception{
//        int insertDataCnt = 50;
//
//        for (int i = 0; i < insertDataCnt; i++) {
//            UserDto userDto = new UserDto(
//                    "user" + i,
//                    "test" + i + "@example.com",
//                    "pwd" + i,
//                    "name" + i,
//                    "2024-07-14",
//                    "M",
//                    "01012345678",
//                    "12345",
//                    "123 Road St",
//                    "456 Jibun St",
//                    "789 Detail St",
//                    "key" + i
//            );
//
//            userDao.insertUser(userDto);
//        }
//        // 테이블에 데이터가 50개가 맞는지 확인
//        assertEquals(insertDataCnt, userDao.countUser());
//    }
//
//    // @After로 각 테스트 메서드 실행 후 Table clear
//    @After
//    public void cleanDB() throws Exception{
//        userDao.deleteAllTestUser();
//        // 테이블 비었는지 확인
//        assertEquals(userDao.countUser(),0);
//    }
//
//    // 2. 보조 기능 테스트
//    // 2.1. 테스트용으로 사용할 메서드(insert, delete, deleteAll) 기능 확인
//
//    // 2.2. 테스트용 INSERT 문 기능 확인
//    // 2.2.1. 고객 관련 데이터가 각 컬럼으로 추가되는지 확인
//    // 2.2.2. PK인 ID 중복 추가 불가능한지 확인
//    // 2.2.3  NOT NULL 에 해당하는 컬럼에 null 추가 불가능한지 확인
//
//    // 2.3. 테스트용 DELETE 문 기능 확인
//    // 2.3.1. 특정 아이디를 보유한 고객 정보를 삭제 가능한지 확인
//    // 2.3.2. 전체 고객 정보를 한꺼번에 삭제 가능한지 확인
//
//    // 2.2.1. 고객 관련 데이터가 각 컬럼으로 추가되는지 확인
//    @Test
//    public void 고객정보_추가_테스트_메서드_기능_확인() throws Exception {
//        // given
//        int expectedCnt = 1; // insert 성공 시 1개의 row 생성되므로 insertUser() 실행 시 예상 결과 = 1
//        int actualCnt;       // insert 성공 여부에 대한 실제 결과
//        int preTotalCnt = userDao.countUser(); // insert 전(pre) - 전체 row 갯수
//        int postTotalCnt;    // insert 후(post) - 전체 row 갯수
//
//        // do
//        // > 30개의 더미데이터 insert
//        for (int i = 1; i <= 30; i++) {
//            UserDto testDto = new UserDto(
//                    "testUser" + i,
//                    "test" + i + "@example.com",
//                    "pwd" + i,
//                    "name" + i,
//                    "2024-07-14",
//                    "M",
//                    "01012345678",
//                    "12345",
//                    "123 Road St",
//                    "456 Jibun St",
//                    "789 Detail St",
//                    "key" + i
//            );
//            actualCnt = userDao.insertUser(testDto);
//
//            assertEquals(expectedCnt, actualCnt);
//        }
//
//        postTotalCnt = userDao.countUser();
//        // then
//        // > insert 전후 row 갯수 비교
//        // > 30개 추가했으므로 insert 전보다 30개 더 많은지 확인
//        assertEquals(preTotalCnt + 30, postTotalCnt);
//    }
//
//    // 2.2.2. PK인 ID 중복 추가 불가능한지 확인 -> DuplicateKeyException 예외 발생
//    @Test
//    public void PK_제약조건_위반_테스트() throws Exception {
//        // given
//        String id1 = "duplicatedId";
//        UserDto testDto1 = new UserDto(
//                id1,
//                "test@example.com",
//                "pwd",
//                "name",
//                "2024-07-14",
//                "M",
//                "01012345678",
//                "12345",
//                "123 Road St",
//                "456 Jibun St",
//                "789 Detail St",
//                "key"
//        );
//        // insert 성공 시 1 반환될 것으로 예상
//        int expectedSuccessCnt = 1;
//        int actualSuccessCnt;
//
//        // 중복된 id 추가 전/후 전체 row 수
//        int preTotalCnt = userDao.countUser();
//        int postTotalCnt1; // insert 성공 후
//        int postTotalCnt2; // insert 실패 후
//
//        // 중복되지 않는 id를 사용하는 새로운 고객에 대한 정보 추가 : insertUser
//        actualSuccessCnt = userDao.insertUser(testDto1);
//        assertEquals(expectedSuccessCnt, actualSuccessCnt);
//
//        // insert 성공 전/후 전체 row 수 비교
//        postTotalCnt1 = userDao.countUser();
//        assertEquals(preTotalCnt+1, postTotalCnt1); // 성공하면 row 1개 추가됨.
//
//        // 중복된 ID 값을 가진 새로운 User 객체 생성
//        String id2 = "duplicatedId";
//        UserDto testDto2 = new UserDto(
//                id2,
//                "test@example.com",
//                "pwd",
//                "name",
//                "2024-07-14",
//                "M",
//                "01012345678",
//                "12345",
//                "123 Road St",
//                "456 Jibun St",
//                "789 Detail St",
//                "key"
//        );
//
//        // do
//
//        // 중복된 id 값을 가진 고객에 대한 정보 추가
//        // 1. 추가하려는 아이디가 존재하는지 미리 조회함. -> 존재하면 Not Null
//        assertNotNull(userDao.selectUser(id2));
//
//        // 2. 동일한 id 값을 가진 고객 정보 추가하려고 하나, 중복 PK 임에 따라 추가될 수 없어 예외 발생함.
//        // 동일한 id 값을 가진 고객에 대한 정보 추가 시, 중복 PK 값 추가이기 때문에 insert 문 실행 불가하여 DuplicateKeyException 예외 던짐.
//        Assertions.assertThrows(DuplicateUserEmailException.class, () -> userDao.insertUser(testDto2));
//
//        // 중복된 id 값 추가 시도 이후의 전체 row 수
//        postTotalCnt2 = userDao.countUser();
//
//        // then
//        // 중복된 id 값을 가진 경우, row 추가 불가능
//        assertEquals(postTotalCnt1, postTotalCnt2);
//        // insert 성공했을 때의 전체 row 수와 insert 실패했을 때 전체 row 수가 동일
//    }
//
//
//    // 2.2.3  NOT NULL 에 해당하는 컬럼에 null 추가 불가능한지 확인 -> DataIntegrityViolationException 예외 발생
//    @Test
//    public void NOT_NULL_제약조건_위반_테스트() throws Exception {
//        // given
//        // 고객 데이터 추가 시, 필수 입력(NOT NULL) 항목 누락하여 insert 한 경우
//        String nullEmail = null;
//        String nullPwd = null;
//        String nullName = null;
//        String nullMailKey = null;
//
//        UserDto testDto = new UserDto(
//                "testID",
//                nullEmail,
//                nullPwd,
//                nullName,
//                "2024-07-14",
//                "M",
//                "01012345678",
//                "12345",
//                "123 Road St",
//                "456 Jibun St",
//                "789 Detail St",
//                nullMailKey
//        );
//
//        // 초기 전체 row 수 확인
//        int preTotalRowCnt = userDao.countUser();
//
//        // do
//        // 제약조건(NOT NULL) 위반에 대한 예외 던짐
//        DataIntegrityViolationException e = Assertions.assertThrows(DataIntegrityViolationException.class, () -> userDao.insertUser(testDto));
//
//        // NOT NULL 에 해당하는 컬럼에 NULL 추가 불가능
//        // insert 실행 후 예상되는 전체 row 수는 실행전과 동일할 것으로 예상
//        int postTotalRowCnt = userDao.countUser();
//
//        // then
//        // NOT NULL 에 해당하는 컬럼에 NULL 값 insert 전/후 동일한 결과 나옴을 확인
//        assertEquals(preTotalRowCnt, postTotalRowCnt);
//    }
//
//    // 2.3.1. 특정 아이디를 보유한 고객 정보를 삭제 가능한지 확인
//    @Test
//    public void 고객정보_삭제_테스트_메서드_기능_확인() throws Exception {
//        // given
//        String id = userDao.selectUser("user1").getId();
//        assertTrue(id!=null);
//
//        int expectedCnt = 1; // 기대하는 값 = 1
//
//        // 삭제 전 전체 row 수
//        int preTotalCnt = userDao.countUser();
//        // 더미데이터로 50개 insert 해두었기 때문에 삭제 전 전체 row 수는 50으로 예상
//        assertTrue(preTotalCnt == 50);
//
//        // do
//        // > user10 ~ user19 삭제
//        for(int i = 0; i < 10; i++) {
//            int actualCnt = userDao.deleteTestUser(id + i);
//
//            // 특정 아이디로 식별한 고객의 정보 삭제됨을 확인
//            assertEquals(expectedCnt, actualCnt);
//        }
//
//        // then
//        // 삭제 후 전체 row 수
//        // 10개 삭제 후 row 수 10 감소
//        int postTotalCnt = userDao.countUser();
//        assertEquals(preTotalCnt-10, postTotalCnt);
//    }
//
//    // 2.3.2. 전체 고객 정보를 한꺼번에 삭제 가능한지 확인
//    @Test
//    public void 전체고객_삭제_테스트_메서드_기능_확인() throws Exception {
//        // given
//        // 1. 전체 row 삭제 시 결과값 50으로 반환 예상
//        int preTotalCnt = 50;
//        // 2. 전체 row 삭제 시 row 갯수 카운트 결과 0으로 예상
//        int postTotalCnt = 0;
//
//        // 전체삭제 실행 성공 시 전체 50개의 row 모두 삭제했으므로 50 반환 예상
//        int expectedCnt = 50;
//
//        // do
//        // 실제 전체 row 삭제 성공 시 1 반환하는지 확인
//        int actualCnt = userDao.deleteAllTestUser();
//        assertEquals(expectedCnt, actualCnt);
//
//        // then
//        // 고객 테이블 내 전체 row 삭제되어 postTotalCnt 는 0 반환 (preTotalCnt 는 삭제 전이므로 50 반환)
//        postTotalCnt = userDao.countUser();
//        assertEquals(preTotalCnt-50, postTotalCnt);
//    }
//
//    @Test
//    public void 회원_탈퇴여부_확인_테스트() throws Exception {
//        // given
//        // 기존에 가입된 회원임을 확인
//        String id = "user1";
//        assertNotNull(userDao.selectUser(id));
//
//        // 탈퇴 처리 성공 시 1 반환
//        int expectedCnt = 1;
//
//        // do
//        // 탈퇴 처리
//        int actualCnt = userDao.updateUserState(id);
//
//        // then
//        // 탈퇴 후 회원 조회 안됨
//        assertNull(userDao.selectUser(id));
//        assertEquals(expectedCnt, actualCnt);
//    }
//
//
//    // 3. 핵심 기능 테스트
//
//    // 3.1. 고객 수 조회
//    // 3.1.1. 회원가입한 고객 수
//    // 3.1.2. 탈퇴회원 제외한 현재 가입 상태의 고객 수
//
//    // 3.2. 고객 정보 조회
//    // 3.2.1. 가입한 회원 정보 조회 성공
//    // 3.2.2. 탈퇴회원 정보 조회 실패
//
//    // 3.3. 비밀번호 변경
//    // 3.3.1. 가입한 회원 PWD 변경 성공
//    // 3.3.2. 탈퇴회원 PWD 변경 실패
//
//    // 3.4. 이메일 변경
//    // 3.4.1. 가입한 회원 이메일 변경 성공
//    // 3.4.2. 탈퇴회원 이메일 변경 실패
//
//    // 3.5. 생년월일 변경
//    // 3.5.1. 가입한 회원 생년월일 변경 성공
//    // 3.5.2. 탈퇴회원 생년월일 변경 실패
//
//    // 3.6. 핸드폰 번호 변경
//    // 3.6.1. 가입한 회원 핸드폰 번호 변경 성공
//    // 3.6.2. 탈퇴회원 핸드폰 번호 변경 실패
//
//    // 3.7. 이메일 인증
//    // 3.7.1. 활성화 상태인 회원의 이름과 이메일로 아이디 조회 성공
//    // 3.7.2. 탈퇴회원의 이름과 이메일로 아이디 조회 실패
//    // 3.7.3. 비회원의 아이디 조회 실패
//
//
//    // 3.1.1. 회원가입한 고객 수
//    @Test
//    public void 회원가입한_고객수_확인테스트() throws Exception {
//        // given
//
//        // insert하기 전 table row 수
//        int preTotalCnt = userDao.countUser();
//
//        // insert 실행 성공 시 1 반환 예정
//        int expectedCnt = 1;
//        int actualCnt;
//
//        // do
//
//        // 20개의 더미데이터를 table에 insert
//        for (int i = 1; i <= 20; i++) {
//            UserDto testDto = new UserDto(
//                    "testUser" + i,
//                    "test" + i + "@example.com",
//                    "pwd" + i,
//                    "name" + i,
//                    "2024-07-14",
//                    "M",
//                    "01012345678",
//                    "12345",
//                    "123 Road St",
//                    "456 Jibun St",
//                    "789 Detail St",
//                    "key" + i
//            );
//            actualCnt = userDao.insertUser(testDto);
//
//            assertEquals(expectedCnt, actualCnt);
//        }
//
//        // then
//        int postTotalCnt = userDao.countUser();
//        // insert 전/후 테이블 row 수 차이 확인
//        // 20개 차이날 것으로 예상
//        assertEquals(preTotalCnt+20, postTotalCnt);
//
//    }
//
//    // 3.1.2. 탈퇴회원 제외한 활성화 상태인 고객 수
//    @Test
//    public void 활성화_상태_고객수_확인테스트() throws Exception {
//        // given
//
//        // 더미데이터로 인해 30개의 활성화 상태인 고객이 테이블에 존재
//
//        // updateUserState 실행 성공 시 1 반환 예정
//        int expectedCnt = 1;
//        // 탈퇴 처리 전 전체 row 수 (초기값)
//        int preTotalCnt = userDao.countUser();
//        // 탈퇴 처리 전 가입상태의 고객 수
//        int preActiveCnt = userDao.countActiveUser();
//
//        // do
//        // updateUserState 실행 결과
//        int actualCnt;
//        for(int i = 1; i <= 5; i++) {
//            actualCnt = userDao.updateUserState("user"+i);
//            assertEquals(expectedCnt, actualCnt);
//        }
//
//        // then
//        // 5명의 고객 탈퇴 처리 후 가입상태의 고객 수
//        int postActiveCnt = userDao.countActiveUser();
//        assertEquals(preActiveCnt-5, postActiveCnt);
//
//        // 탈퇴처리 후 전체 row 수
//        int postTotalCnt = userDao.countUser();
//
//        // then
//        // 전체 row 수는 변화 없으나, 가입상태 회원의 수는 탈퇴처리된 수만큼 감소
//        assertEquals(preTotalCnt, postTotalCnt);
//        assertEquals(postTotalCnt-5, postActiveCnt);
//    }
//
//    // 3.2.1. 가입한 회원 정보 조회
//    @Test
//    public void 가입한_회원_정보_조회_성공_테스트() throws Exception {
//        // given (when)
//        // 임의의 고객 정보 생성 및 추가
//        UserDto testDto = new UserDto(
//                "testUser",
//                "test@example.com",
//                "pwd",
//                "name",
//                "2024-07-14",
//                "M",
//                "01012345678",
//                "12345",
//                "123 Road St",
//                "456 Jibun St",
//                "789 Detail St",
//                "key"
//        );
//        userDao.insertUser(testDto);
//
//        // id 통해서 DB에 제대로 추가되었는지 확인
//        String id = testDto.getId();
//        assertNotNull(userDao.selectUser(id));
//
//        String expectedPwd = testDto.getPwd();
//        String expectedName = testDto.getName();
//
//        // do
//        // 특정 고객의 아이디로 비밀번호 및 이름 조회
//        String actualPwd = userDao.selectUser(id).getPwd();
//        String actualName = userDao.selectUser(id).getName();
//
//        // then
//        assertEquals(expectedPwd, actualPwd);
//        assertEquals(expectedName, actualName);
//    }
//
//    // 3.2.2. 탈퇴회원 정보 조회
//    @Test
//    public void 탈퇴회원_정보_조회_실패_테스트() throws Exception {
//
//        // given
//        // 임의의 고객 아이디: user1
//        String id = "user1";
//
//        // 고객 활성화 상태를 확인
//        UserDto testDto1 = userDao.selectUser(id);
//        assertNotNull(testDto1);
//
//        // 해당 고객 탈퇴시키기 전 전체 가입상태 고객 수 확인
//        int preWithdrawalCnt = userDao.countActiveUser();
//
//        // do
//        // 동일 고객 탈퇴 처리 후 해당 고객 조회 실패하는지 확인
//        userDao.updateUserState(id);
//        UserDto testDto2 = userDao.selectUser(id);
//
//        // then
//        assertNull(testDto2);
//
//        // 고객 탈퇴시킨 후 전체 가입상태 고객 수 감소여부 확인
//        int postWithdrawalCnt = userDao.countActiveUser();
//        assertEquals(preWithdrawalCnt-1, postWithdrawalCnt);
//    }
//
//    // 3.3.1. 가입한 회원 PWD 변경 성공
//    @Test
//    public void 가입한_회원_PWD_변경_성공_테스트() throws Exception {
//        // given
//        String id = "user1";
//        String email = "test1@example.com";
//        assertNotNull(userDao.selectUser(id));
//        assertEquals(userDao.selectUser(id).getEmail(), email);
//
//        String beforePwd = userDao.selectUser(id).getPwd();
//        String afterPwd = "modifiedPassword1";
//
//        // 비밀번호 변경 처리 성공 시 1 반환
//        int expectedCnt = 1;
//
//        // do
//        int actualCnt = userDao.updateUserPwd(id, afterPwd);
//        // 변경된 비밀번호 반환
//        String modifiedPwd = userDao.selectUser(id).getPwd();
//
//        // then
//        assertEquals(expectedCnt, actualCnt);
//        assertTrue(!beforePwd.equals(modifiedPwd));
//        assertEquals(afterPwd, modifiedPwd);
//    }
//
//    // 3.3.2. 탈퇴회원 PWD 변경 실패
//    @Test
//    public void 탈퇴한_회원_PWD_변경_실패_테스트() throws Exception {
//        // given
//        // 기존에 가입 상태였던 고객임을 확인
//        String id = "user1";
//        String email = "test1@example.com";
//        assertNotNull(userDao.selectUser(id));
//        assertEquals(userDao.selectUser(id).getEmail(), email);
//
//        // 변경하려는 비밀번호 선언 및 초기화
//        String pwd = "modifiedPassword1";
//
//        // 비밀번호 변경 실패 시 0 반환
//        int expectedCnt = 0;
//
//        // 회원 탈퇴
//        // 고객 정보 조회 안됨을 확인
//        userDao.updateUserState(id);
//        assertNull(userDao.selectUser(id));
//
//        // do
//        // 비밀번호 변경 시도 및 실패여부 확인
//        int actualCnt = userDao.updateUserPwd(id, pwd);
//
//        // then
//        assertEquals(expectedCnt, actualCnt);
//    }
//
//    // 3.4.1. 가입한 회원 이메일 변경 성공
//    @Test
//    public void 가입한_회원_이메일_변경_성공_테스트() throws Exception {
//        // given
//        // 가입 회원 아이디
//        String id = "user1";
//        // 변경하려는 이메일 주소
//        String email = "modified1@example.com";
//
//        // 이메일 변경 성공 시 1 반환
//        int expectedCnt = 1;
//
//        // do
////        int actualCnt = userDao.updateUserEmail(id, email);
////        assertEquals(expectedCnt, actualCnt);
//
//        // then
//        // DB에 저장된 변경된 이메일이 변경하려던 이메일 주소와 동일한지 확인
//        String modifiedEmail = userDao.selectUser(id).getEmail();
//        assertEquals(email, modifiedEmail);
//    }
//
//    // 3.4.2. 탈퇴회원 이메일 변경 실패
//    @Test
//    public void 탈퇴한_회원_이메일_변경_실패_테스트() throws Exception {
//        // given
//        // 가입 회원 아이디
//        // 기존에 가입 상태였던 고객임을 확인
//        String id = "user1";
//        assertNotNull(userDao.selectUser(id));
//        // 가입회원 탈퇴 처리
//        // 탈퇴처리 성공 시 1 반환
//        String email = userDao.selectUser(id).getEmail();
//        assertEquals(userDao.updateUserState(id), 1);
//
//        // 고객 정보 조회 안됨을 확인
//        assertNull(userDao.selectUser(id));
//
//        // 이메일 변경 실패 시 0 반환
//        int expectedCnt = 0;
//
//        // do
////        int actualCnt = userDao.updateUserEmail(id, email);
//
//        // then
////        assertEquals(expectedCnt, actualCnt);
//    }
//
//    // 3.5.1. 가입한 회원 생년월일 변경 성공
//    @Test
//    public void 가입한_회원_생년월일_변경_성공_테스트() throws Exception {
//        // given
//        String id = "user1";
//        String birth = "2024-07-14";
//        // 기존 가입 회원인지 확인
//        assertNotNull(userDao.selectUser(id));
//        assertEquals(userDao.selectUser(id).getBirth(), birth);
//
//        String beforeBirth = userDao.selectUser(id).getBirth();
//        String afterBirth = "2024-08-14";
//
//        // 폰번호 변경 처리 성공 시 1 반환
//        int expectedCnt = 1;
//
//        // do
////        int actualCnt = userDao.updateUserBefBirth(id, afterBirth);
//        // 변경된 폰번호 반환
//        String modifiedBirth = userDao.selectUser(id).getPwd();
//
//        // then
////        assertEquals(expectedCnt, actualCnt);
//        assertTrue(!beforeBirth.equals(modifiedBirth));
//        assertEquals(afterBirth, modifiedBirth);
//    }
//
//    // 3.5.2. 탈퇴회원 생년월일 변경 실패
//    @Test
//    public void 탈퇴한_회원_생년월일_변경_실패_테스트() throws Exception {
//        // given
//        // 기존에 가입 상태였던 고객임을 확인
//        String id = "user1";
//        String birth = "2024-07-14";
//        assertNotNull(userDao.selectUser(id));
//        assertEquals(userDao.selectUser(id).getBirth(), birth);
//
//        // 변경하려는 생년월일
//        String modifiedPassword = "modifiedPassword";
//
//        // 비밀번호 변경 실패 시 0 반환
//        int expectedCnt = 0;
//
//        // 회원 탈퇴
//        // 고객 정보 조회 안됨을 확인
//        userDao.updateUserState(id);
//        assertNull(userDao.selectUser(id));
//
//        // do
//        // 비밀번호 변경 시도 및 실패여부 확인
////        int actualCnt = userDao.updateUserBefBirth(id, modifiedPassword);
//
//        // then
////        assertEquals(expectedCnt, actualCnt);
//    }
//
//    // 3.6.1. 가입한 회원 핸드폰 번호 변경 성공
//    @Test
//    public void 가입한_회원_핸드폰번호_변경_성공_테스트() throws Exception {
//        // given
//        String id = "user1";
//        String email = "test1@example.com";
//        assertNotNull(userDao.selectUser(id));
//        assertEquals(userDao.selectUser(id).getEmail(), email);
//
//        String beforePwd = userDao.selectUser(id).getPwd();
//        String afterPwd = "modifiedPassword1";
//
//        // 비밀번호 변경 처리 성공 시 1 반환
//        int expectedCnt = 1;
//
//        // do
//        int actualCnt = userDao.updateUserPwd(id, afterPwd);
//        // 변경된 비밀번호 반환
//        String modifiedPwd = userDao.selectUser(id).getPwd();
//
//        // then
//        assertEquals(expectedCnt, actualCnt);
//        assertTrue(!beforePwd.equals(modifiedPwd));
//        assertEquals(afterPwd, modifiedPwd);
//    }
//
//    // 3.6.2. 탈퇴회원 핸드폰 번호 변경 실패
//    @Test
//    public void 탈퇴한_회원_핸드폰번호_변경_실패_테스트() throws Exception {
//        // given
//        // 기존에 가입 상태였던 고객임을 확인
//        String id = "user1";
//        String email = "test1@example.com";
//        assertNotNull(userDao.selectUser(id));
//        assertEquals(userDao.selectUser(id).getEmail(), email);
//
//        // 변경하려는 비밀번호 선언 및 초기화
//        String pwd = "modifiedPassword1";
//
//        // 비밀번호 변경 실패 시 0 반환
//        int expectedCnt = 0;
//
//        // 회원 탈퇴
//        // 고객 정보 조회 안됨을 확인
//        userDao.updateUserState(id);
//        assertNull(userDao.selectUser(id));
//
//        // do
//        // 비밀번호 변경 시도 및 실패여부 확인
//        int actualCnt = userDao.updateUserPwd(id, pwd);
//
//        // then
//        assertEquals(expectedCnt, actualCnt);
//    }
//
//    // 3.7.1. 활성화 상태인 회원의 이름과 이메일로 아이디 조회 성공
//    @Test
//    public void 가입한_회원_이메일_인증_성공_테스트() throws Exception {
//        // given
//        // 더미데이터 첫번째 dto의 이름과 이메일로 dto 셋팅
//        UserDto testDto = new UserDto();
//        testDto.setName("name1");
//        testDto.setEmail("test1@example.com");
//
//        String expectedId = "user1"; // 예상하는 ID
//
//        // do
//        // 아이디 찾기
////        UserDto testDto2 = userDao.selectUserId(testDto.getEmail());
//
//        // then
//        // 기존에 존재하던 회원이 맞는지 확인
////        assertNotNull(testDto2);
////        // 예상하는 ID와 DB에서 꺼내온 dto의 ID와 일치하는지 확인
////        String actualId = testDto2.getId();
////        assertEquals(expectedId, actualId);
//    }
//
//    // 3.7.2. 탈퇴회원의 이름과 이메일로 아이디 조회 실패
//    @Test
//    public void 탈퇴한_회원_이메일_인증_실패_테스트() throws Exception {
//        // given
//        // 가입 회원 아이디
//        // 기존에 가입 상태였던 고객임을 확인
//        String id = "user1";
//        assertNotNull(userDao.selectUser(id));
//        // 가입회원 탈퇴 처리
//        // 탈퇴처리 성공 시 1 반환
//        String email = userDao.selectUser(id).getEmail();
//        assertEquals(userDao.updateUserState(id), 1);
//
//        // 고객 정보 조회 안됨을 확인
//        assertNull(userDao.selectUser(id));
//
//        // do
//        // 아이디 조회
////        UserDto userDto = userDao.selectUserId(userDao.selectUser(id).getEmail());
//
//        // then
////        assertNull(userDto);
//    }
//
//    // 3.7.3. 비회원의 아이디 조회 실패
//    @Test
//    public void 비회원_이메일_인증_실패_테스트() throws Exception {
//        // given
//        UserDto testDto = new UserDto();
//        testDto.setName("non-member");
//        testDto.setEmail("nonmember@example.com");
//        testDto.setMailKey("12345");
//
//        // 비회원임을 확인
//        UserDto testDto2 = userDao.selectUser(testDto.getId());
//        assertNull(testDto2);
//
//        // do
//        // 아이디 찾기
////        UserDto testDto3 = userDao.selectUserId(testDto.getEmail());
//
//        // then
////        assertNull(testDto3);
//    }
//}