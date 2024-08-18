package com.firstSpring.dao.board;

import com.firstSpring.domain.board.QnaDto;

import java.util.List;
import java.util.Map;

public interface QnaDao {

    //상단 미지정(미공지) 게시글카운터 조회
    int count(Map map) throws Exception;
    //전체카운터
    int countAll() throws Exception;
    //상단지정(공지) 게시글 카운터 조회
    int countIsNotice() throws Exception;
    //qna 게시글 목록 조회
    List<QnaDto> getPage(Map map) throws Exception;
    //qna 공지 목록 조회
    List<QnaDto> getNoticeList() throws Exception;
    //게시글 상세조회
    QnaDto select(Integer bno) throws Exception;
    //게시글 전체조회
    List<QnaDto> selectAll() throws Exception;
    //qna게시글 작성
    int insert(QnaDto qd) throws Exception;
    //qna게시글 삭제
    int delete(Integer bno, String writer) throws Exception;
    //qna게시글 전체삭제
    int deleteAll() throws Exception;
    //gna게시글 수정
    int update(QnaDto qd) throws Exception;
    //qna 답글 작성
    int reply(QnaDto qnaDto) throws Exception;
    //qna 게시판 조회수 증가
    int increaseViewCnt(Integer bno) throws Exception;
    //key값 다음값 알아오기
    int getNextKey() throws Exception;


}
