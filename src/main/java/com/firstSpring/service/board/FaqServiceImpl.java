package com.firstSpring.service.board;


import com.firstSpring.dao.board.FaqDao;
import com.firstSpring.domain.board.FaqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FaqServiceImpl implements FaqService{
    @Autowired
    private FaqDao faqDao;

    @Override
    public int getAllCount() throws Exception {
        return faqDao.countAll();
    }

    @Override
    public int getCount(Map map) throws Exception {
        return faqDao.count(map);
    }

    @Override
    public int getNoticeCount(Map map) throws Exception {
        return faqDao.countNotice(map);
    }

    @Override
    public int remove(Integer bno, String writer) throws Exception {
        return faqDao.delete(bno,writer);
    }

    @Override
    public int modify(FaqDto faqDto) throws Exception {
        return faqDao.update(faqDto);
    }

    @Override
    public int write(FaqDto faqDto) throws Exception {
        return faqDao.insert(faqDto);
    }

    @Override
    public List<FaqDto> getNoticeList() throws Exception {
        return faqDao.selectNoticeList();
    }

    @Override
    public List<FaqDto> getNotNoticeList() throws Exception {
        return faqDao.selectNotNoticeList();
    }

    //페이지 가져오기
    @Override
    public List<FaqDto> getPage(Map map) throws Exception {
        return faqDao.selectPage(map);
    }
    
    @Override
    public FaqDto read(Integer bno) throws Exception {
        //faq 상세정보
        FaqDto faqDto = faqDao.selectOne(bno);
        //조회수 증가
        faqDao.increaseViewCnt(bno);
        return faqDto;
    }

    //챗봇에 의해 호출 예정
    @Override
    public List<FaqDto> getChatSearch(Map map) throws Exception {
        return faqDao.getChatSearch(map);
    }

    public List<FaqDto> test() throws Exception{
        Map map = new HashMap();
        map.put("keyword","환불");
        List<FaqDto> list = faqDao.getChatSearch(map);
        return list;
    }

    @Override
    public List<FaqDto> getList() throws Exception {
        return faqDao.selectAll();
    }
}
