package com.firstSpring.service.board;

import com.firstSpring.dao.board.NoticeDao;
import com.firstSpring.domain.board.NoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class  NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public int getAllCount() throws Exception {
        return noticeDao.allCount();
    }

    @Override
    public int getCount(Map map) throws Exception {
        return noticeDao.count(map);
    }

    @Override
    public int getNoticeCount() throws Exception {
        return noticeDao.countIsNotice();
    }

    @Override
    public int remove(Integer bno, String writer) throws Exception {
        return noticeDao.delete(bno,writer);
    }

    @Override
    public int modify(NoticeDto noticeDto) throws Exception {
        return noticeDao.update(noticeDto);
    }

    @Override
    public int write(NoticeDto noticeDto) throws Exception {
        return noticeDao.insert(noticeDto);
    }

    @Override
    public List<NoticeDto> getNoticeList() throws Exception {
        return noticeDao.selectNoticeList();
    }
    @Override
    public List<NoticeDto> getNotNoticeList() throws Exception {
        return noticeDao.selectNotNoticeList();
    }

    @Override
    public List<NoticeDto> getPage(Map map) throws Exception {
        return noticeDao.selectPage(map);
    }

    //검색어 적용해야될듯 체크할것
    @Override
    public List<NoticeDto> getList() throws Exception {
        return noticeDao.selectAll();
    }

    @Override
    public NoticeDto read(Integer bno) throws Exception {
        //공지사항 상세정보
        NoticeDto noticeDto = noticeDao.select(bno);
        //조회수 증가
        noticeDao.increaseViewCnt(bno);
        return noticeDto;
    }
}
