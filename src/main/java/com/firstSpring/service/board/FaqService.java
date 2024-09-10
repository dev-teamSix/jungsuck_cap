package com.firstSpring.service.board;

import com.firstSpring.domain.board.QnaDto;

import java.util.List;
import java.util.Map;

public interface  QnaService {
    //카운터 조회
    int getCount(Map map) throws Exception;
    //상단고정(공지) 카운터 조회
    int getNoticeCount() throws Exception;
    //게시판 작성
    int write(QnaDto qnaDto) throws Exception;
    //게시판 수정
    int modify(QnaDto qnaDto) throws Exception;
    //게시판 삭제
    int remove(Integer bno,String writer) throws Exception;
    //게시판 조회(상세+viewCnt증가)
    QnaDto read(Integer bno) throws Exception;

    //게시판 상단고정(공지글) 가져오기
    List<QnaDto> getNoticeList() throws Exception;

    //게시판 상단미지정(일반글) 가져오기  //게시판 목록조회
    List<QnaDto> getQnaList(Map map) throws Exception;


}
