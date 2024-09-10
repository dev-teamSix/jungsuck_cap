package com.firstSpring.service.board;

import com.firstSpring.domain.board.FaqDto;
import com.firstSpring.domain.board.NoticeDto;
import com.firstSpring.domain.board.QnaDto;

import java.util.List;
import java.util.Map;

public interface FaqService {
    //FAQ 전체 (all)
    int getAllCount() throws Exception;
    //FAQ 공지글 지정 카운터(is_notice:1)
    int getNoticeCount(Map map) throws Exception;
    //FAQ 카운터 공지 미지정 카운터(is_notice:0)
    int getCount(Map map) throws Exception;
    //FAQ 삭제
    int remove(Integer bno, String writer) throws Exception;
    //FAQ 수정
    int modify(FaqDto faqDto) throws Exception;
    //FAQ 등록
    int write(FaqDto faqDto) throws Exception;

    List<FaqDto> getNoticeList() throws Exception;

    List<FaqDto> getNotNoticeList() throws Exception;
    //FAQ 목록조회
    List<FaqDto> getPage(Map map) throws Exception;
    //FAQ 상세조회
    FaqDto read(Integer bno) throws Exception;

    List<FaqDto> getChatSearch(Map map) throws Exception;

    List<FaqDto> test() throws Exception;
}
