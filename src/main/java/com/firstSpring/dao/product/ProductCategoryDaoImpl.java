package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProductCategoryDto;
import com.firstSpring.domain.product.ProductHighCategoryDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class  ProductCategoryDaoImpl implements ProductCategoryDao {
    @Autowired
    SqlSession session;

    String namespace = "com.firstSpring.dao.ProductCategoryMapper.";

    // 특정 카테고리 정보 조회
    @Override
    public ProductCategoryDto select(Integer catgNo) throws Exception {
        return session.selectOne(namespace+"select", catgNo);
    }

    // 특정 상위 카테고리 조회 (상위 카테고리 유무 체크용)
    @Override
    public ProductCategoryDto selectHighCatg(Integer catgNo) throws Exception {
        return session.selectOne(namespace+"selectHighCatg", catgNo);
    }

    //  특정 이름의 카테고리 조회 (이름 중복 확인용)
    @Override
    public List<ProductCategoryDto> selectListByName(String name, Integer highCatgNo) throws Exception {
        Map map = new HashMap();
        map.put("name", name);
        map.put("highCatgNo", highCatgNo);
        return session.selectList(namespace+"selectListByName", map);
    }

    // 모든 카테고리 목록 조회
    @Override
    public List<ProductCategoryDto> selectAll() throws Exception{
        return session.selectList(namespace+"selectAll");
    }

    // 상위 카테고리 목록 조회
    @Override
    public List<ProductCategoryDto> selectHighCatgList() throws Exception{
        return session.selectList(namespace+"selectHighCatgList");
    }

    // 모든 카테고리의 하위 카테고리 정보 목록 조회
    @Override
    public List<ProductHighCategoryDto> selectHighLowList() throws Exception {
        return session.selectList(namespace+"selectHighLowList");
    }

    // 특정 상위 카테고리의 하위 카테고리 목록 조회(상위 카테고리 정보 포함)
    @Override
    public List<ProductHighCategoryDto> selectListByHigh(Integer highCatgNo) throws Exception {
        return session.selectList(namespace+"selectListByHigh", highCatgNo);
    }

    // 카테고리 추가
    @Override
    public Integer insert(ProductCategoryDto productCategoryDto) throws Exception {
        return session.insert(namespace+"insert", productCategoryDto);
    }

    // 카테고리 정보 변경
    @Override
    public Integer update(ProductCategoryDto productCategoryDto) throws Exception {
        return session.update(namespace+"update", productCategoryDto);
    }

    /* 테스트용 */
    // 카테고리 삭제
    @Override
    public Integer delete(Integer catgCd) throws Exception {
        return session.delete(namespace+"delete", catgCd);
    }

    // 카테고리 모두 삭제
    @Override
    public Integer deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }
}
