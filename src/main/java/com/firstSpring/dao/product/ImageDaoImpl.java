package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ImageDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class  ImageDaoImpl implements ImageDao {
    @Autowired
    SqlSession session;

    String namespace = "com.firstSpring.dao.ImageMapper.";

    // 특정 이미지 상세 조회
    public ImageDto select(Integer imgNo) {
        return session.selectOne(namespace + "select", imgNo);
    }
}
