package com.firstSpring.dao.board;

import com.firstSpring.domain.board.QnaDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QnaDaoImpl implements QnaDao{
    @Autowired
    private SqlSession session;
    private static String namespace = "com.firstSpring.dao.board.QnaMapper.";

    @Override
    public int count(Map map) throws Exception {
        return session.selectOne(namespace+"count",map);
    }

    @Override
    public int countAll() throws Exception {
        return session.selectOne(namespace+"countAll");
    }

    @Override
    public int countIsNotice() throws Exception {
        return session.selectOne(namespace+"countIsNotice");
    }

    @Override
    public List<QnaDto> getPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    }
    @Override
    public List<QnaDto> getNoticeList() throws Exception {
        return session.selectList(namespace+"selectQnaNotice");
    }

    @Override
    public QnaDto select(Integer bno) throws Exception {
        return session.selectOne(namespace+"select", bno);
    }

    @Override
    public List<QnaDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public int insert(QnaDto qnaDto) throws Exception {
        return session.insert(namespace+"insert", qnaDto);
    }

    @Override
    public int delete(Integer bno,String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno",bno);
        map.put("writer",writer);
        return session.delete(namespace+"delete",map);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int update(QnaDto qnaDto) throws Exception {
        return session.update(namespace+"update", qnaDto);
    }

    @Override
    public int reply(QnaDto qnaDto) throws Exception {
        return session.insert(namespace+"reply", qnaDto);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt",bno);
    }

    @Override
    public int getNextKey() throws Exception {
        return session.selectOne(namespace+"getNextKey");
    }
}
