package com.firstSpring.dao.board;

import com.firstSpring.domain.board.NoticeDto;
import com.firstSpring.domain.board.SearchCondition;

import java.util.*;


public interface  NoticeDao {
    //게시판 상세
    //게시판 삭제
    //게시판 등록
    //게시판 목록 조회
    //게시판 수정
    //게시판 조회수증가
    //추가해야될 내용 검색 관련
    NoticeDto select(Integer bno) throws Exception;
    int delete(Integer bno,String writer) throws Exception;
    int insert(NoticeDto noticeDto) throws Exception;
    int insert2(NoticeDto noticeDto) throws Exception;
    int insert3(NoticeDto noticeDto) throws Exception;
    int update(NoticeDto noticeDto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;
    List<NoticeDto> selectPage(Map map) throws Exception;
    List<NoticeDto> selectNoticeList() throws Exception;
    List<NoticeDto> selectNotNoticeList() throws Exception;
    List<NoticeDto> selectAll() throws Exception;
    int deleteAll() throws Exception;
    int count(Map map) throws Exception;
    int countIsNotice() throws Exception;
    int allCount() throws Exception;
    int searchResultCnt(SearchCondition sc) throws Exception;
    List<NoticeDto> selectNoticeSearch(SearchCondition sc) throws Exception;
    int getNextKey() throws Exception;;


}
