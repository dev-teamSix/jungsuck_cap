package com.firstSpring.service.board;

import com.firstSpring.dao.board.QnaDao;
import com.firstSpring.domain.board.QnaDto;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class  QnaServiceImpl implements QnaService {

    @Autowired
    private QnaDao qnaDao;

    @Override
    public int getCount(Map map) throws Exception {
        return qnaDao.count(map);
    }

    @Override
    public int getNoticeCount() throws Exception {
        return qnaDao.countIsNotice();
    }

    @Override
    public int write(QnaDto qnaDto) throws Exception {
        return qnaDao.insert(qnaDto);
    }

    @Override
    public int modify(QnaDto qnaDto) throws Exception {
        return qnaDao.update(qnaDto);
    }

    @Override
    public int remove(Integer bno, String writer) throws Exception {
        return qnaDao.delete(bno, writer);
    }

    @Override
    public QnaDto read(Integer bno) throws Exception {
        QnaDto qnaDto = qnaDao.select(bno);
        //여기서 뭐 처리 더 해줄게 있는지 생각 예외처리
        qnaDao.increaseViewCnt(bno);
        return qnaDto;
    }

    @Override
    public List<QnaDto> getNoticeList() throws Exception {
        return qnaDao.getNoticeList();
    }

    @Override
    public List<QnaDto> getQnaList(Map map) throws Exception {
        return qnaDao.getPage(map);
    }
}
