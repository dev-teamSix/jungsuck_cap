package com.firstSpring.dao.order;

import com.firstSpring.domain.order.ChatDto;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.firstSpring.dao.order.ChatMapper.";

    public List<ChatDto> selectMsgData(String cust_id) throws Exception {
        return session.selectList(namespace + "selectMsgData", cust_id);
    }

    public int selectMsgNo() throws Exception {
        return session.selectOne(namespace + "selectMsgNo");
    }
}