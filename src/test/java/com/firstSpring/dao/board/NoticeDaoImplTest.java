package com.firstSpring.dao.board;

import com.firstSpring.domain.board.NoticeDto;
import com.firstSpring.domain.board.NoticeDtoBuild;
import com.firstSpring.domain.board.SearchCondition;
import com.firstSpring.entity.PageHandler;
import com.firstSpring.service.board.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class NoticeDaoImplTest {

    @Autowired
    private NoticeDao noticeDao;



    @Test
    public void select() throws Exception {
        //게시글 전체삭제
        noticeDao.deleteAll();
        //게시글 count 조회
        //assertTrue(noticeDao.count()==0);
        //테스트 게시글 insert
        NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'0',0);
        assertTrue(noticeDao.insert(noticeDto)==1);
        //insert한 게시글 bno 값 가져오기
        Integer bno = noticeDao.selectAll().get(0).getBno();
        System.out.println("bno:"+bno);
        noticeDto.setBno(bno);
        //bno 값으로 select 후 비교
        NoticeDto noticeDto1 = noticeDao.select(bno);
        System.out.println("원본:"+noticeDto);
        System.out.println("비교:"+noticeDto1);
        assertTrue(noticeDto.equals(noticeDto1));
    }

    @Test
    public void delete() throws Exception {
        //전체삭제
        noticeDao.deleteAll();
        //count 0인지 확인
        //assertTrue(noticeDao.count()==0);
        //테스트 게시글 insert 하고 확인
        NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'0',0);
        assertTrue(noticeDao.insert(noticeDto)==1);
        //저장한 값에서 bno/wirter 정도 읽어와서 삭제 및 카운터 확인
        Integer bno = noticeDao.selectAll().get(0).getBno();
        String writer = noticeDao.selectAll().get(0).getWriter();
        assertTrue(noticeDao.delete(bno,writer)==1);
        //assertTrue(noticeDao.count()==0);

    }

    @Test
    public void insert() throws Exception {
        //전체삭제
        noticeDao.deleteAll();
        //카운터 확인
        //assertTrue(noticeDao.count()==0);
        //글 1개 추가
        NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'0',0);
        assertTrue(noticeDao.insert(noticeDto)==1);
        //카운터 1개 맞는지 확인
        //assertTrue(noticeDao.count()==1);
    }

    @Test
    public void insert2() throws Exception {
        noticeDao.deleteAll();
        //assertTrue(noticeDao.count()==0);
        //공지글 아닌 것 100개 추가
        for(int i=0;i<100;i++){
            NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'0',0);
            assertTrue(noticeDao.insert(noticeDto)==1);
        }
        //System.out.println("insert:"+noticeDao.count());
        //assertTrue(noticeDao.count()==100);

        for(int i=0;i<3;i++){
            NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'1',0);
            //assertTrue(noticeDao.insert(noticeDto)==1);
        }
        System.out.println("isNotice:"+noticeDao.countIsNotice());
        //assertTrue(noticeDao.countIsNotice()==3);
    }

    @Test
    public void insert3() throws Exception {
//        //공지글 아닌 것 100개 추가
//        for(int i=0;i<100;i++){
//            //NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'0',0);
//            NoticeDto noticeDto = new NoticeDto("sukmo","test sukmo","test sukmo","관리자",'0',0);
//            NoticeDto noticeDto2 = new NoticeDto("son","test son","test son","관리자",'0',0);
//            assertTrue(noticeDao.insert3(noticeDto)==1);
//            assertTrue(noticeDao.insert3(noticeDto2)==1);
//        }


        for(int i=0;i<3;i++){
            NoticeDto noticeDto = new NoticeDto("test",i+" 번째 공지사항 입니다.",i+" 번째 공지사항 내용입니다.","관리자",'1',0);
            assertTrue(noticeDao.insert3(noticeDto)==1);
        }
        System.out.println("isNotice:"+noticeDao.countIsNotice());

    }

    //게시물 수정 테스트
    @Test
    public void update() throws Exception {
        //다 지우고 게시물 하나 작성 후 값 변경해서 저장된 값 비교
        noticeDao.deleteAll();
        //assertTrue(noticeDao.count()==0);
        NoticeDto noticeDto = new NoticeDto("테스트","테스트제목","테스트내용","관리자",'0',0);
        NoticeDto noticeDto2 = new NoticeDto("테스트","테스트제목2","테스트내용2","관리자",'1',0);
        assertTrue(noticeDao.insert(noticeDto)==1);
        Integer bno = noticeDao.selectAll().get(0).getBno();
        String title = noticeDao.selectAll().get(0).getTitle();
        String content = noticeDao.selectAll().get(0).getContent();
        noticeDto.setTitle("테스트제목수정");
        noticeDto.setContent("테스트내용수정");
        assertTrue(noticeDao.insert(noticeDto)==1);
        NoticeDto nd =noticeDao.select(bno);
        assertTrue(title.equals(nd.getTitle()));
        assertTrue(content.equals(nd.getContent()));

        System.out.println("noticeDto:"+noticeDto.toString());
        System.out.println("noticeDto2:"+noticeDto2.toString());




    }

    //조회수 증가 테스트
    @Test
    public void increaseViewCnt() throws Exception {
        //일단 다 지우고 하나 추가
        //해당 값 조회수0 일때니 추가하는 로직 실행후 조회수 1인지 확인
        noticeDao.deleteAll();
        NoticeDto noticeDto = new NoticeDto("테스트","테스트제목","테스트내용","관리자",'0',0);
        assertTrue(noticeDao.insert(noticeDto)==1);

        Integer viewCnt = noticeDao.selectAll().get(0).getView_cnt();
        System.out.println("viewCnt:"+viewCnt);
        assertTrue(viewCnt==0);
        Integer bno = noticeDao.selectAll().get(0).getBno();
        System.out.println("bno:"+bno);
        assertTrue(noticeDao.increaseViewCnt(bno)==1);
        Integer viewCnt2 = noticeDao.selectAll().get(0).getView_cnt();
        System.out.println("viewCnt2:"+viewCnt2);
        assertTrue(viewCnt2==1);
    }



    //페이징 테스트
    @Test
    public void selectPage() throws Exception {
        //전체삭제
        //페이징 값 확인을 위한 데이터 insert
        //insert 후 count 확인
        //page에 따른 offset 확인해야되나?
        //페이징 첫페이지 일치하는지 끝페이지 일치하는지 값확인

        //추가로 검증해야 하는 테스트 공지로 지정한 글insert 하고 해당 공지로 지정된 공지글 을 제외한 페이징 처리 확인 테스트

        noticeDao.deleteAll();
        //assertTrue(noticeDao.count()==0);
        for(int i=0;i<100;i++){
            NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'0',0);
            assertTrue(noticeDao.insert(noticeDto)==1);
        }
        //assertTrue(noticeDao.count()==100);
        //Integer count = noticeDao.count();
        PageHandler pageHandler = new PageHandler(100,10,10);
        //공지 지정된 게시글 추가
        for(int i=0;i<3;i++){
            NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'1',0);
            assertTrue(noticeDao.insert(noticeDto)==1);
        }
        //assertTrue(noticeDao.count()==100);
        assertTrue(noticeDao.countIsNotice()==3);

        assertTrue(pageHandler.getBeginPage()==1);
        assertTrue(pageHandler.getEndPage()==10);
        PageHandler ph2 = new PageHandler(230,13,10);

        assertTrue(ph2.getBeginPage()==11);
        assertTrue(ph2.getEndPage()==20);

        PageHandler ph3 = new PageHandler(150,13,10);
        assertTrue(ph3.getBeginPage()==11);
        assertTrue(ph3.getEndPage()==15);

        PageHandler ph4 = new PageHandler(152,13,10);
        assertTrue(ph4.getBeginPage()==11);
        assertTrue(ph4.getEndPage()==16);

        PageHandler ph5 = new PageHandler(259,13,10);
        assertTrue(ph5.getBeginPage()==11);
        assertTrue(ph5.getEndPage()==20);

        PageHandler ph6 = new PageHandler(259,23,10);
        assertTrue(ph6.getBeginPage()==21);
        assertTrue(ph6.getEndPage()==26);

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 10);

        List<NoticeDto> list = noticeDao.selectPage(map);

        assertTrue(list.get(0).getTitle().equals("test title"));
        assertTrue(list.get(1).getTitle().equals("test title"));
        assertTrue(list.get(2).getTitle().equals("test title"));

    }

    //검색 테스트 추가필요
    
    @Test
    public void selectAll() throws Exception {
        noticeDao.deleteAll();
        //assertTrue(noticeDao.count()==0);
        List<NoticeDto> list = noticeDao.selectAll();
        assertTrue(list.size()==0);
        NoticeDto noticeDto = new NoticeDto("테스트","테스트제목","테스트내용","관리자",'0',0);
        assertTrue(noticeDao.insert(noticeDto)==1);

        list = noticeDao.selectAll();
        assertTrue(list.size()==1);

        assertTrue(noticeDao.insert(noticeDto)==1);
        list = noticeDao.selectAll();
        assertTrue(list.size()==2);

        noticeDao.selectAll();

    }

    //전체삭제 테스트
    @Test
    public void deleteAll() throws Exception {
        noticeDao.deleteAll();
        //assertTrue(noticeDao.count()==0);
        NoticeDto noticeDto = new NoticeDto("테스트","테스트제목","테스트내용","관리자",'0',0);
        assertTrue(noticeDao.insert(noticeDto)==1);
        assertTrue(noticeDao.deleteAll()==1);
        //assertTrue(noticeDao.count()==0);

    }

    //카운터 테스트
    @Test
    public void count() throws Exception {
        noticeDao.deleteAll();
        //assertTrue(noticeDao.count()==0);
        NoticeDto noticeDto = new NoticeDto("테스트","테스트제목","테스트내용","관리자",'0',0);
        assertTrue(noticeDao.insert(noticeDto)==1);
      // assertTrue(noticeDao.count()==1);
    }


    //빌더 패턴 테스트 toString 왜 안되나 나중에 다시 확인
    @Test
    public void test(){
        NoticeDtoBuild noticeDtoBuild = new NoticeDtoBuild.Builder("test,","제목","내용","관리자")
                .is_notice('1')
                .view_cnt(4)
                .build();
        System.out.println(noticeDtoBuild);
        System.out.println(noticeDtoBuild.toString());
        System.out.println(noticeDtoBuild.getIs_notice());
        System.out.println(noticeDtoBuild.getView_cnt());
    }

    //select Search 테스트
    //option =>
    @Test
    public void searchSelectPageTest() throws Exception {
        SearchCondition sc = new SearchCondition(5,10,"son","month3","content");
        //SearchCondition sc = new SearchCondition(5,10,"test","content");
        //SearchCondition sc = new SearchCondition(5,10,"test");
        System.out.println("keyword:"+sc.getKeyword());
        System.out.println("content:"+sc.getOption_key());

        System.out.println(noticeDao.selectNoticeSearch(sc));

    }

    @Test
    public void searchCountTest() throws Exception {
        noticeDao.deleteAll();
        //assertTrue(noticeDao.count()==0);
        for(int i=0;i<100;i++){
            NoticeDto noticeDto = new NoticeDto("test","test title","test content","관리자",'0',0);
            assertTrue(noticeDao.insert(noticeDto)==1);
        }
        SearchCondition sc = new SearchCondition(5,10,"test","week","writer");

        int count = noticeDao.searchResultCnt(sc);
        System.out.println(count);

    }
    @Test
    public void getNextKey() throws Exception {
        Integer bno = noticeDao.getNextKey();
        System.out.println("bno:"+bno);
        assertTrue(noticeDao.select(bno)==null);
        NoticeDto noticeDto = new NoticeDto("test","test "+bno,"test "+bno,"관리자",'0',0);
        assertTrue(noticeDao.insert(noticeDto)==1);
        assertTrue(noticeDto.getTitle().equals(noticeDao.select(bno).getTitle()));
        assertTrue(noticeDto.getContent().equals(noticeDao.select(bno).getContent()));
    }
}