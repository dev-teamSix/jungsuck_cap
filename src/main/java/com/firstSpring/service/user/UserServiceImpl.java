package com.firstSpring.service.user;

import com.firstSpring.controller.user.Exception.*;
import com.firstSpring.dao.user.UserDao;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.firstSpring.controller.user.Exception.UserErrorCode.*;

@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder,JavaMailSender javaMailSender) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    // 특수한 값으로 반환 X, void로 바꿀 것, 예외는 예외로 처리할 것
    // 가입 회원 아이디 조회여부 확인
    @Override
    @LogException
    public boolean checkExistOfId(String id) {
        if (userDao.selectUser(id) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDto getCustLoginInfo(String id) {
        // 아이디 조회 성공 -> 매개변수로 받은 아이디로 조회한 고객 정보를 userDto 에 저장
        // 예외 발생 시 null 처리
        UserDto userDto = userDao.selectUser(id);

        if(userDto==null) {
            throw new NotFindUserIdException(NOT_MATCH_ID);
        }
        return userDto;
    }

    // 임시 비밀번호와 매개변수로 들어온 비밀번호가 일치하는지 확인
    @Override
    public String matchPwd(String pwd, String target) {
        if(!authenticatePwd(pwd,target))
            throw new NotFindPwdException(NOT_MATCH_PWD);
        return target;
    }



    // PWD 일치여부 확인
    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogException
    public boolean checkPwdMatch(String id, String pwd) {
        // id로 조회한 비밀번호 (암호화하여 저장되어 있음)
        String encodedPwd;
        try {
            encodedPwd = userDao.selectUser(id).getPwd();

            // 비밀번호 비교 결과: True/False
            // true -> 최근 로그인 일시 업데이트 후 true 반환
            // false -> false반환
            if(authenticatePwd(pwd, encodedPwd)) {
                updateRecentLoginHist(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 로그인 시 최근 로그인 일시 업데이트
    @Override
    @LogException
    public int updateRecentLoginHist(String id)  {
        return userDao.updateRecentLoginDatetime(id);
    }

    // 아이디 찾기
    @Override
    @LogException
    public UserDto findUserId(String name,String email) {
        UserDto userDto = userDao.selectUserId(name, email);
        if(userDto==null) {
            throw new NotFindUserIdException(NOT_MATCH_ID);
        }
        return userDto;
    }

    // 전체 가입고객(탈퇴회원 포함) 중 특정 이메일과 특정 아이디를 가진 고객 정보 확인
    @Override
    @LogException
    public UserDto findUserIdAndEmail(String id, String email) {
        UserDto userDto = userDao.selectAllUserIdEmail(id,email);
        if(userDto==null) {
            throw new NotFindUserIdEmailException(NOT_MATCH_ID_EMAIL);
        }
        return userDto;
    }

    // 전체 가입고객(탈퇴회원 포함)의 아이디 조회하여 아이디 중복여부 확인
    @Override
    @LogException
    public UserDto checkDuplicatedId(String id) {
        UserDto userDto = userDao.selectAllUserId(id);
        if(userDto != null)
            throw new DuplicateUserIdException(DUPLICATED_ID);
        return userDto;
    }

    // 전체 가입고객(탈퇴회원 포함)의 이메일 조회하여 이메일 중복여부 확인
    @Override
    @LogException
    public UserDto checkDuplicatedEmail(String email) {
        UserDto userDto = userDao.selectAllUserEmail(email);
        if(userDto !=null)
            throw new DuplicateUserEmailException(DUPLICATED_EMAIL);
        return userDto;
    }


    // 회원가입
    // 1. 회원 비밀번호 암호화해서 userDto에 저장
    // 2. 회원가입 성공 시 1 반환
    // 3. 회원가입 실패 시 0 반환

    // 입력란에서 비밀번호 및 비밀번호 확인용으로 2개 받아옴.
    // split() 메서드로 , 기준으로 나눠서 저장
    @Override
    @LogException
    public void saveCustJoinInfo(UserDto userDto) {
        String pwd1 = userDto.getPwd().split(",")[0];
        String password = passwordEncoder.encode(pwd1);
        userDto.setPwd(password);

        userDao.insertUser(userDto);
    }

    // 회원가입 시, 유효성 체크
    // validator 에러메세지 결과 반환
    @LogException
    @Override
    public Map<String,String> validateHandling(Errors errors) {
        Map<String,String> validatorResult = new HashMap<>();

        // 유효성 검사에 실패한 필드 목록을 받음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getCode());
        }
        return validatorResult;
    }

    // 이메일 인증
    // 매 인증 시 랜덤 문자 및 숫자로 구성된 인증코드 업데이트
    // 본인인증 시 부여된 인증코드 업데이트
    @Override
    @LogException
    public int saveCustMailKey(UserDto userDto)  {
        return userDao.updateMailKey(userDto);
    }

    // 회원 정보 수정
    @Override
    @LogException
    public UserDto modifyUserInfo(UserDto userDto) {
        // 1. 매개변수로 들어온 userDto의 값으로 새로운 dto객체에 값 세팅
        // 2. 수정 성공 시 true 반환
        // 3. 수정 실패 시 false 반환

        UserDto modifyUserDto = new UserDto();

        modifyUserDto.setEmail(userDto.getEmail());
        modifyUserDto.setBirth(userDto.getBirth());
        modifyUserDto.setPhNum(userDto.getPhNum());

        userDao.updateUserInfo(modifyUserDto);
        return modifyUserDto;
    }

    // 회원탈퇴
    // 회원 상태를 'O' -> 'L' 으로 변경
    // 가입회원 여부 확인
    // 가입된 회원이면 탈퇴 처리
    // 미가입 회원이면 예외 처리
    @Override
    @LogException
    public boolean changeWithdrawalState(String id) {
        try {
            if (checkExistOfId(id)) {
                userDao.updateUserState(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 메일 인증번호 전송
    @Override
    @LogException
    @Transactional
    public int sendMail(String email) {
        checkDuplicatedEmail(email);

        int checkNum = getCheckNum();
        // 메일 제목, 내용
        String subject = "본인인증 메일입니다.";
        String content = "홈페이지를 방문해주셔서 감사합니다. "+
                "인증 번호는 "+ checkNum + " 입니다." +
                "\r\n" +
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        // 보내는 사람
        String from = "0711kyungh@naver.com";
        extracted(email, from, subject, content);
        // 인증번호를 반환
        return checkNum;
    }

    // 임시 비밀번호 메일전송
    // 임시 비밀번호로 회원 비밀번호를 업데이트
    @Override
    @LogException
    @Transactional
    public String sendTempPassword(String id,String email) {
        findUserIdAndEmail(id, email);

        String tempPassword = getTempPassword();

        // 메일 제목, 내용
        String subject = "임시 비밀번호 안내 이메일 입니다.";
        String content = "홈페이지를 방문해주셔서 감사합니다. " +
                "회원님의 임시 비밀번호는 " + tempPassword + " 입니다." +
                "\r\n" +
                "로그인 후에 비밀번호를 변경해주세요!";
        // 보내는 사람
        String from = "0711kyungh@naver.com";
        // 메일 전송
        extracted(email, from, subject, content);
        // 임시비밀번호로 회원 비밀번호 업데이트
        modifyUserPwd(id, tempPassword);
        // 임시비밀번호 반환
        return tempPassword;
    }

    // 비밀번호 찾기
    // 새로운 비밀번호로 변경
    @Override
    @LogException
    public void modifyUserPwd(String id, String pwd) {
        String encodePassword = passwordEncoder.encode(pwd); // 비밀번호 암호화
        userDao.updateUserPwd(id, encodePassword);
    }

    private void extracted(String email, String from, String subject, String content) {
        try {
            // 메일 내용 넣을 객체와, 이를 도와주는 Helper 객체 생성
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");

            // 메일 내용을 채워줌
            mailHelper.setFrom(from, "관리자"); // 보내는 사람
            mailHelper.setTo(email); // 받는 사람
            mailHelper.setSubject(subject); // 제목
            mailHelper.setText(content); // 내용

            // 메일 전송
            javaMailSender.send(mail);
        } catch (Exception e) {
            throw new MailSendException(e, MAIL_SEND_FAIL);
        }
    }

    // 임시 비밀번호 생성
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    private static int getCheckNum() {
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        return checkNum;
    }

    // 암호화 전후 비밀번호 비교
    // 암호화 전 비밀번호(rawPwd)와 암호화 후 비밀번호(encodedPwd)
    private boolean authenticatePwd(String rawPwd, String encodedPwd) {
        return passwordEncoder.matches(rawPwd, encodedPwd);
    }

}