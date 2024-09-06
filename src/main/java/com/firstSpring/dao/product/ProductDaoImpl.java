package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProductDto;
import com.firstSpring.domain.product.ProductListDto;
import com.firstSpring.domain.product.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class  ProductDaoImpl implements ProductDao {
    @Autowired
    SqlSession session;

    String namespace = "com.firstSpring.dao.ProductMapper.";

    // 전체 or 특정 카테고리의 상품 목록 조회
    // map에는 카테고리 코드, 정렬 기준이 전달되어야 한다.
    @Override
    public List<ProductDto> selectList(Map map) throws Exception  {
        return session.selectList(namespace+"selectList", map);
    }

    @Override
    public List<ProductDto> selectSearchList(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"selectSearchList", sc);
    }

    // 상품 페이지 조회
    @Override
    public List<ProductDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    }

    // 상품 페이지 조인된 상태로 조회
    @Override
    public List<ProductListDto> selectPageWithJoin(Map map) throws Exception {
        return session.selectList(namespace+"selectPageWithJoin", map);
    }

    @Override
    public List<ProductListDto> selectSearchPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"selectSearchPage", sc);
    }

    // 특정 상품 정보 읽기
    @Override
    public ProductDto select(Integer prodNo) throws Exception{
        return session.selectOne(namespace+"select",prodNo);
    }


    // 상품 등록하기
    @Override
    public int insert(ProductDto product) throws Exception {
        return session.insert(namespace+"insert",product);
    }

    // 특정 상품 정보 변경
    @Override
    public int updateInfo(ProductDto product) throws Exception {
        return session.update(namespace+"updateInfo", product);
    }

    // 상품 판매량 증가
    @Override
    public int increaseTotalSales(Map map) throws Exception{
        return session.update(namespace+"increaseTotalSales",map);
    }

    // 상품 조회수 증가
    @Override
    public int increaseViewCnt(Integer prodNo) throws Exception {
        return session.update(namespace+"increaseViewCnt",prodNo);
    }

    // 상품 리뷰 개수, 평균 평점 변경
    @Override
    public int updateRvCntAndAvgRatg(Map map) throws Exception {
        return session.update(namespace+"updateRvCntAndAvgRatg",map);
    }

    // 특정 상품 제거
    @Override
    public int delete(Integer prodNo) throws Exception {
        return  session.delete(namespace+"delete",prodNo);
    }

    // 모든 상품 제거
    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }

    // 모든 상품 조회
    @Override
    public List<ProductDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    // 테스트용 상품 등록
    @Override
    public int insertTest(ProductDto product) throws Exception{
        return session.insert(namespace+"insertTest",product);
    }
}
