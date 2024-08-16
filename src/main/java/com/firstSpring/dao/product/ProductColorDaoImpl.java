package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProdColJoinDto;
import com.firstSpring.domain.product.ProductColorDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductColorDaoImpl implements ProductColorDao {
    @Autowired
    SqlSession session;

    String namespace = "com.firstSpring.dao.ProductColorMapper.";

    // 특정 상품의 컬러 정보 리스트 조회
    @Override
    public List<ProductColorDto> selectListByProd(Integer prodNo) {
        return session.selectList(namespace+"selectListByProd",prodNo);
    }

    // 특정 상품의 컬러 정보(조인) 리스트 조회
    @Override
    public List<ProdColJoinDto> selectListByProdWithJoin(Integer prodNo) {
        return session.selectList(namespace+"selectListByProdWithJoin",prodNo);
    }

    // 특정 상품의 특정 컬러 정보 등록
    @Override
    public Integer insert(ProductColorDto productColorDto) {
        return session.insert(namespace+"insert", productColorDto);
    }

    // 특정 상품의 특정 컬러 정보 수정
    @Override
    public Integer update(ProductColorDto productColorDto) {
        return session.update(namespace+"update", productColorDto);
    }

    // 특정 상품의 특정 컬러 정보 제거
    @Override
    public Integer delete(Integer prodNo, Integer colNo) {
        Map map = new HashMap();
        map.put("prodNo",prodNo);
        map.put("colNo",colNo);
        return session.delete(namespace+"delete", map);
    }

    /* 테스트용 */
    // 모든 상품 컬러 정보 조회
    @Override
    public List<ProductColorDto> selectAll() {
        return session.selectList(namespace+"selectAll");
    }

    // 모든 상품 컬러 정보 제거
    @Override
    public Integer deleteAll() {
        return session.delete(namespace+"deleteAll");
    }

}
