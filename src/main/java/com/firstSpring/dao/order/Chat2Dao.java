package com.firstSpring.dao.order;

import com.firstSpring.domain.order.Chat2Dto;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Chat2Dao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.firstSpring.dao.order.ChatMapper.";

    public List<Chat2Dto> selectMsgData(String cust_id) throws Exception {
        return session.selectList(namespace + "selectMsgData", cust_id);
    }

    public int selectMsgNo() throws Exception {
        return session.selectOne(namespace + "selectMsgNo");
    }
}