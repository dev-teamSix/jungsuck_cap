package com.firstSpring.service.user;

import com.firstSpring.dao.user.UserDao;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.entity.LogException;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    @Autowired
    private UserServiceImpl(UserDao userDao, BCryptPasswordEncoder passwordEncoder,JavaMailSender javaMailSender) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }


    // 로그인 - SELECT (=READ; 조회 기능)
    @Transactional(rollbackFor = Exception.class)
    @Override
    @LogException
    public UserDto getCustLoginInfo(String id) {
        // 1. 아이디 조회 성공 -> 매개변수로 받은 아이디로 조회한 고객 정보를 userDto 에 저장
        // 1.1. 최근 로그인 일시 업데이트
        // 2. 예외 발생 시 null 반환
        try {
            updateRecentLoginHist(id);
            return userDao.selectUser(id);
        }
        catch (Exception e) {
            return null;
        }
    }

    // 로그인 시 최근 로그인 일시 업데이트
    @Override
    @LogException
    public int updateRecentLoginHist(String id) throws Exception {
        return userDao.updateRecentLoginDatetime(id);
    }

    // 아이디 찾기
    @Override
    @LogException
    public UserDto findUserId(String email) {
        // 1. 아이디 조회 성공 -> 특정 고객의 아이디가 저장되어 있는 userDto 반환
        // 2. 아이디 조회 실패 -> userDto = null
        UserDto userDto = null;
        try {
            userDto = userDao.selectUserId(email);
            return userDto;
        }
        catch (Exception e) {
            return userDto;
        }
    }

    // 전체 가입고객(탈퇴회원 포함)의 아이디 조회하여 아이디 중복여부 확인
    @Override
    @LogException
    public boolean checkDuplicatedId(String id) {
        // 1. 중복되는 아이디가 없으면 true
        // 2. 중복되는 아이디가 있으면 false
        try {
             if(userDao.selectAllUserId(id)==null) {
                 return true;
             }else {
                 return false;
             }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 가입 회원 아이디 조회여부 확인
    @Override
    @LogException
    public boolean checkExistOfId(String id) {
        try {
            if (userDao.selectUser(id) != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 암호화 전후 비밀번호 비교
    @Override
    @LogException
    public boolean checkPwdMatch(String id, String pwd) {
        // id로 조회한 비밀번호 (암호화하여 저장되어 있음)
        String encodedPwd;
        try {
            encodedPwd = userDao.selectUser(id).getPwd();
            // 비밀번호 비교 결과: True/False
            return authenticatePwd(pwd, encodedPwd);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 비밀번호 찾기
    // 새로운 비밀번호로 변경
    @Override
    @LogException
    public boolean modifyUserPwd(String id, String pwd) {
        // 비밀번호 변경 성공여부
        // 비밀번호 변경 성공 시 true 반환
        // 비밀번호 변경 실패 시 false 반환

        // 가입회원인지 확인 후 비밀번호 변경 시도
        // 가입회원이 아닌 경우 비밀번호 변경 실패
        if (checkExistOfId(id)) {
            try {
                userDao.updateUserPwd(id, pwd);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    // 회원가입
    @Override
    @LogException
    public boolean saveCustJoinInfo(UserDto userDto) {
        // 1. 회원 비밀번호 암호화해서 userDto에 저장
        // 2. 회원가입 성공 시 1 반환
        // 3. 회원가입 실패 시 0 반환

        // 입력란에서 비밀번호 및 비밀번호 확인용으로 2개 받아옴.
        // split() 메서드로 , 기준으로 나눠서 저장
        String pwd1 = userDto.getPwd().split(",")[0];

        String password = passwordEncoder.encode(pwd1);
        userDto.setPwd(password);

        try {
            userDao.insertUser(userDto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 이메일 인증
    // 매 인증 시 랜덤 문자 및 숫자로 구성된 인증코드 업데이트
    // 본인인증 시 부여된 인증코드 업데이트
    @Override
    @LogException
    public int saveCustMailKey(UserDto userDto) throws Exception {
        return userDao.updateMailKey(userDto);
    }

    // 회원 정보 수정
    @Override
    @LogException
    public boolean modifyUserInfo(UserDto userDto) {
        // 1. 매개변수로 들어온 userDto의 값으로 새로운 dto객체에 값 세팅
        // 2. 수정 성공 시 true 반환
        // 3. 수정 실패 시 false 반환

        UserDto modifyUserDto = new UserDto();

        modifyUserDto.setEmail(userDto.getEmail());
        modifyUserDto.setBirth(userDto.getBirth());
        modifyUserDto.setPhNum(userDto.getPhNum());

        try {
            userDao.updateUserInfo(modifyUserDto);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
    public int sendMail(String email) {
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;

        // 메일 제목, 내용
        String subject = "회원가입 인증 메일입니다.";
        String content = "홈페이지를 방문해주셔서 감사합니다. "+
                "인증 번호는 "+ checkNum + " 입니다." +
                "\r\n" +
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        // 보내는 사람
        String from = "0711kyungh@naver.com";

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

            // 인증번호를 반환
            return checkNum;
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 암호화 전후 비밀번호 비교
    // 암호화 전 비밀번호(rawPwd)와 암호화 후 비밀번호(encodedPwd)
    private boolean authenticatePwd(String rawPwd, String encodedPwd) {
        return passwordEncoder.matches(rawPwd, encodedPwd);
    }

}
