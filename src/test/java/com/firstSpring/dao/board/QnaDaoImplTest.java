package com.firstSpring.dao.board;

import com.firstSpring.domain.board.QnaDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class QnaDaoImplTest {

    @Autowired
    private QnaDao qnaDao;

    @Test
    public void count() throws Exception {
        //전체삭제
        //qnaDao.deleteAll();
        //생성자를 통해 데이
        Integer bno = qnaDao.getNextKey();
        System.out.println(bno);
        QnaDto dto = new QnaDto("test",bno,"공지사항 입니다","공지사항 내용입니다.","test",'1');
        Map map = new HashMap();
        assertTrue(qnaDao.insert(dto)==1);
        //assertTrue(qnaDao.count(map)==1);
    }

    @Test
    public void insertTest() throws Exception {
        //전체삭제
        //qnaDao.deleteAll();
        //삭제 확인
        //assertTrue(qnaDao.countAll()==0);
        for(int i=0; i<50;i++){
            Integer bno = qnaDao.getNextKey();
            QnaDto dto = new QnaDto("test",bno,"test title"+i,"test content"+i,"test");
            assertTrue(qnaDao.insert(dto)==1);
        }
        //assertTrue(qnaDao.countAll()==50);

    }
    //상세조회 테스트
    @Test
    public void selectTest() throws Exception {
        //전체삭제
        qnaDao.deleteAll();
        assertTrue(qnaDao.countAll()==0);
        Integer bno = qnaDao.getNextKey();
        System.out.println(bno);
        QnaDto dto = new QnaDto("test",bno,"test title","test content","test");

        assertTrue(qnaDao.insert(dto)==1);
        //assertTrue(qnaDao.count()==1);
        Integer selectBno = qnaDao.selectAll().get(0).getBno();
        QnaDto qnadto = qnaDao.selectAll().get(0);
        System.out.println("qnadto="+qnadto);

        QnaDto qd = qnaDao.select(selectBno);
        assertTrue(dto.getTitle().equals(qd.getTitle()));

    }
    //조회수 증가 테스트
    @Test
    public void viewCntTest() throws Exception {
        //전체삭제
        qnaDao.deleteAll();
        assertTrue(qnaDao.countAll()==0);
        Integer bno = qnaDao.getNextKey();
        System.out.println(bno);
        QnaDto dto = new QnaDto("test",bno,"test title","test content","test");

        assertTrue(qnaDao.insert(dto)==1);
        //assertTrue(qnaDao.count()==1);
        Integer selectBno = qnaDao.selectAll().get(0).getBno();
        QnaDto qnaDto = qnaDao.select(selectBno);
        System.out.println("selectBno="+selectBno);
        int before = qnaDto.getView_cnt();

        System.out.println("이것도 0인가:"+qnaDao.increaseViewCnt(selectBno));
        QnaDto qnaDto2 = qnaDao.select(selectBno);
        int after = qnaDto2.getView_cnt();
        System.out.println("before:"+before);
        System.out.println("after:"+after);
        assertTrue(before!=after);
    }
    //수정 테스트
    @Test
    public void updateTest() throws Exception {
        //전체삭제 후 카운터 확인
        qnaDao.deleteAll();
        assertTrue(qnaDao.countAll()==0);
        Integer bno = qnaDao.getNextKey();
        System.out.println(bno);
        QnaDto dto = new QnaDto("test",bno,"test title","test content","test");

        assertTrue(qnaDao.insert(dto)==1);
        //assertTrue(qnaDao.count()==1);
        //아무거나 1개 bno 가져와서 값 가져와서 수정후 변경 되었는지 확인
        Integer selectBno = qnaDao.selectAll().get(0).getBno();
        QnaDto qnaDto = qnaDao.select(selectBno);
        System.out.println("selectBno="+selectBno);
        String title=qnaDto.getTitle();
        String content=qnaDto.getContent();
        String writer =qnaDto.getWriter();
        char is_notice =qnaDto.getIs_notice();
        QnaDto qnaDto2 = new QnaDto("test2",bno,"test title change","test content2","test2");
        qnaDto2.setBno(selectBno);
        System.out.println("값 확인:"+qnaDao.update(qnaDto2));
        //assertTrue(qnaDao.update(qnaDto2)==1);
        assertTrue(qnaDao.countAll()==1);
        assertTrue(!title.equals(qnaDto2.getTitle()));
        assertTrue(!content.equals(qnaDto2.getContent()));
        assertTrue(!writer.equals(qnaDto2.getWriter()));
        assertTrue(is_notice != qnaDto2.getIs_notice());
        System.out.println("qnaDto2:"+qnaDto2);

    }
    @Test
    public void deleteTest()throws Exception{
        //전체삭제 후 카운터 확인
        qnaDao.deleteAll();
        assertTrue(qnaDao.countAll()==0);
        Integer bno = qnaDao.getNextKey();
        System.out.println(bno);
        QnaDto dto = new QnaDto("test",bno,"test title","test content","test");

        assertTrue(qnaDao.insert(dto)==1);
        //assertTrue(qnaDao.count()==1);
        //아무거나 1개 bno 가져와서 해당 bno 게시글 지우고 카운터 확인
        Integer selectBno = qnaDao.selectAll().get(0).getBno();
        String writer=qnaDao.selectAll().get(0).getWriter();
        assertTrue(qnaDao.delete(selectBno,writer)==1);
        assertTrue(qnaDao.countAll()==0);


    }



}