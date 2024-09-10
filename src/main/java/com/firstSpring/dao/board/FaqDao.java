package com.firstSpring.dao.board;

import com.firstSpring.domain.board.FaqDto;

import java.util.List;
import java.util.Map;

public interface  FaqDao {
    //FAQ게시판 공지 미지정 게시글 카운터 조회
    int count(Map map) throws Exception;
    //공지로 지정된 게시글 카운터 조회
    int countNotice(Map map) throws Exception;
    //FAQ게시판 공지글 전체 카운터 조회
    int countAll() throws Exception;
    //FAQ게시판 목록 조회
    List<FaqDto> selectPage(Map map) throws Exception;
    //FAQ게시판 select
    FaqDto selectOne(Integer bno) throws Exception;
    //FAQ게시판 등록
    int insert(FaqDto faqDto) throws Exception;
    //FAQ게시판 수정
    int update(FaqDto faqDto) throws Exception;
    //FAQ게시판 삭제
    int delete(Integer bno,String writer) throws Exception;
    //조회수 증가
    int increaseViewCnt(Integer bno) throws Exception;

    List<FaqDto> selectNoticeList() throws Exception;
    List<FaqDto> selectNotNoticeList() throws Exception;
    List<FaqDto> getChatSearch(Map map) throws Exception;
}
