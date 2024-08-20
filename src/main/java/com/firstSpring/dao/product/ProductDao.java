package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProductDto;
import com.firstSpring.domain.product.ProductListDto;

import java.util.List;
import java.util.Map;

public interface  ProductDao {
    // 전체 or 특정 카테고리의 상품 목록 조회
    // map에는 카테고리 코드, 정렬 기준이 전달되어야 한다.
    List<ProductDto> selectList(Map map) throws Exception;

    List<ProductDto> selectPage(Map map) throws Exception;

    List<ProductListDto> selectPageWithJoin(Map map) throws Exception;

    // 특정 상품 정보 읽기
    ProductDto select(Integer prodNo) throws Exception;

    // 상품 등록하기
    int insert(ProductDto product) throws Exception;

    // 특정 상품 정보 변경
    int updateInfo(ProductDto product) throws Exception;

    // 상품 판매량 증가
    int increaseTotalSales(Map map) throws Exception;

    // 상품 조회수 증가
    int increaseViewCnt(Integer prodNo) throws Exception;

    // 상품 리뷰 개수, 평균 평점 변경
    int updateRvCntAndAvgRatg(Map map) throws Exception;

    // 특정 상품 제거
    int delete(Integer prodNo) throws Exception;

    // 모든 상품 제거
    int deleteAll() throws Exception;

    // 모든 상품 조회
    List<ProductDto> selectAll() throws Exception;

    // 테스트용 상품 등록
    int insertTest(ProductDto product) throws Exception;
}
