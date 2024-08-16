package com.firstSpring.dao.board;

import com.firstSpring.domain.board.FaqDto;

import java.util.List;
import java.util.Map;

public class FaqDaoImpl implements FaqDao {
    @Override
    public int count(Map map) throws Exception {
        return 0;
    }

    @Override
    public int countNotice(Map map) throws Exception {
        return 0;
    }

    @Override
    public int countAll() throws Exception {
        return 0;
    }

    @Override
    public List<FaqDto> selectPage(Map map) throws Exception {
        return List.of();
    }

    @Override
    public FaqDto selectOne(Integer bno) throws Exception {
        return null;
    }

    @Override
    public int insert(FaqDto faqDto) throws Exception {
        return 0;
    }

    @Override
    public int update(FaqDto faqDto) throws Exception {
        return 0;
    }

    @Override
    public int delete(Integer bno, String writer) throws Exception {
        return 0;
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return 0;
    }
}
