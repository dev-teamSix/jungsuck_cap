package com.firstSpring.service.board;

import com.firstSpring.domain.board.NoticeDto;

import java.util.List;
import java.util.Map;

public interface  NoticeService {

    //공지사항 전체 (all)
    int getAllCount() throws Exception;
    //공지사항 공지글 지정 카운터(is_notice:1)
    int getCount(Map map) throws Exception;
    //공지사항 카운터(is_notice:0)
    int getNoticeCount() throws Exception;
    //공지사항 삭제
    int remove(Integer bno, String writer) throws Exception;
    //공지사항 수정
    int modify(NoticeDto noticeDto) throws Exception;
    //공지사항 등록
    int write(NoticeDto noticeDto) throws Exception;
    List<NoticeDto> getNoticeList() throws Exception;
    List<NoticeDto> getNotNoticeList() throws Exception;
    //공지사항 목록조회
    List<NoticeDto> getPage(Map map) throws Exception;
    //공지사항 목록조회
    List<NoticeDto> getList() throws Exception;
    //공지사항 상세조회
    NoticeDto read(Integer bno) throws Exception;


}
