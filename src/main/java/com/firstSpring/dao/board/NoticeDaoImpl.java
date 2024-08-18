package com.firstSpring.dao.board;

import com.firstSpring.domain.board.NoticeDto;
import com.firstSpring.domain.board.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoticeDaoImpl implements NoticeDao{
    @Autowired
    private SqlSession session;
    private static String namespace = "com.firstSpring.dao.board.NoticeMapper.";

    @Override
    public NoticeDto select(Integer bno) throws Exception {
        return session.selectOne(namespace+"select",bno);
    }

    @Override
    public int delete(Integer bno,String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno",bno);
        map.put("writer",writer);
        return session.delete(namespace+"delete",map);
    }

    @Override
    public int insert(NoticeDto dto) throws Exception {
        return session.insert(namespace+"insert",dto);
    }

    @Override
    public int insert2(NoticeDto dto) throws Exception {
        return session.insert(namespace+"insert2",dto);
    }

    @Override
    public int insert3(NoticeDto dto) throws Exception {
        return session.insert(namespace+"insert3",dto);
    }

    @Override
    public int update(NoticeDto dto) throws Exception {
        return session.update(namespace+"update",dto);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt",bno);
    }

    @Override
    public List<NoticeDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage",map);
    }

    @Override
    public List<NoticeDto> selectNoticeList() throws Exception {
        return session.selectList(namespace+"selectNoticeList");
    }
    @Override
    public List<NoticeDto> selectNotNoticeList() throws Exception {
        return session.selectList(namespace+"selectNotNoticeList");
    }

    @Override
    public List<NoticeDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int count(Map map) throws Exception {
        return session.selectOne(namespace+"count",map);
    }

    @Override
    public int countIsNotice() throws Exception {
        return session.selectOne(namespace+"countIsNotice");
    }

    @Override
    public int allCount() throws Exception {
        return session.selectOne(namespace+"allCount");
    }

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
//        System.out.println("sc in searchResultCnt() = " + sc);
//        System.out.println("session = " + session);
        return session.selectOne(namespace+"searchResultCnt", sc);
    }

    @Override
    public List<NoticeDto> selectNoticeSearch(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"selectNoticeSearch",sc);
    }

    @Override
    public int getNextKey() throws Exception {
        return session.selectOne(namespace+"getNextKey");
    }
}
