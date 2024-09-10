package com.firstSpring.dao.board;

import com.firstSpring.domain.board.FaqDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class  FaqDaoImpl implements FaqDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.firstSpring.dao.board.FaqMapper.";


    @Override
    public int count(Map map) throws Exception {
        return session.selectOne(namespace+"count",map);
    }

    @Override
    public int countNotice(Map map) throws Exception {
        return session.selectOne(namespace+"countIsNotice",map);
    }

    @Override
    public int countAll() throws Exception {
        return session.selectOne(namespace+"allCount");
    }

    @Override
    public List<FaqDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage",map);
    }

    @Override
    public FaqDto selectOne(Integer bno) throws Exception {
        return session.selectOne(namespace+"selectOne",bno);
    }

    @Override
    public int insert(FaqDto faqDto) throws Exception {
        return session.insert(namespace+"insert",faqDto);
    }

    @Override
    public int update(FaqDto faqDto) throws Exception {
        return session.update(namespace+"update",faqDto);
    }

    @Override
    public int delete(Integer bno, String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno",bno);
        map.put("writer",writer);
        return session.delete(namespace+"delete",map);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt",bno);
    }

    @Override
    public List<FaqDto> selectNotNoticeList() throws Exception {
        return session.selectList(namespace+"selectNotNoticeList");
    }

    @Override
    public List<FaqDto> selectNoticeList() throws Exception {
        return session.selectList(namespace+"selectNoticeList");
    }

    @Override
    public List<FaqDto> getChatSearch(Map map) throws Exception {
        return session.selectList(namespace+"getChatSearch",map);
    }
}
