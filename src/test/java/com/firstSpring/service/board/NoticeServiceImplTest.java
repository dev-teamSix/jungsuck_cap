package com.firstSpring.service.board;

import com.firstSpring.domain.board.NoticeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class NoticeServiceImplTest {

    @Autowired
    NoticeService noticeService;

    @Test
    public void getAllCount() throws Exception {
       // assertTrue(noticeService.getAllCount());
    }

    @Test
    public void getCount() throws Exception {

    }

    @Test
    public void getNoticeCount() throws Exception {
    }

    @Test
    public void remove() throws Exception {
        Integer bno=3787;
        String writer="관리자";
        assertTrue(noticeService.remove(bno,writer)==1);
        NoticeDto noticeDto =noticeService.read(3787);
        assertTrue(null==noticeDto);

    }

    @Test
    public void modify() throws Exception {
        //전체삭제 해서 해야되지만 지금은 데이터 남겨두려고 안할거라 아무거나 하나 선택(0번째)
       Integer bno = noticeService.getList().get(0).getBno();
       System.out.println("bno:"+bno);
       NoticeDto noticeDto = noticeService.read(bno);
       NoticeDto noticeDto2 = noticeService.read(bno);
       assertTrue(noticeDto.equals(noticeDto2));
       System.out.println("변경전:"+noticeDto);
       System.out.println("변경전2:"+noticeDto);
       noticeDto.setContent("내용 334333333");
       noticeDto.setTitle("제목 qdasd3333333");
       Integer view_cnt = noticeDto.getView_cnt();
       System.out.println("조회수:"+view_cnt);
       assertTrue(noticeService.modify(noticeDto) ==1);
       NoticeDto noticeDtoChange = noticeService.read(bno);
        System.out.println("조회수:"+noticeDtoChange.getView_cnt());
       System.out.println("change:"+noticeDtoChange);
       assertTrue(!noticeDto2.equals(noticeDtoChange));

    }

    @Test
    public void write() throws Exception {

        NoticeDto noticeDto = new NoticeDto("test","test writer title","test writer content","관리자",'0',0);
        assertTrue(noticeService.write(noticeDto)==1);

    }

    @Test
    public void getPage() {
    }

    @Test
    public void getList() {
    }

    @Test
    public void read() throws Exception {

        Integer bno = noticeService.getList().get(0).getBno();
        System.out.println("bno:"+bno);
        NoticeDto noticeDto = noticeService.read(bno);
        System.out.println("notice:"+noticeDto);
    }
}