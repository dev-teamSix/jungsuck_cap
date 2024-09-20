package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProdColJoinDto;
import com.firstSpring.domain.product.ProductColorDto;

import java.util.List;

public interface  ProductColorDao {
    // 특정 상품의 컬러 정보 리스트 조회
    List<ProductColorDto> selectListByProd(Integer prodNo);

    // 특정 상품의 컬러 정보(조인) 리스트 조회
    List<ProdColJoinDto> selectListByProdWithJoin(Integer prodNo);

    // 특정 상품의 특정 컬러 정보 등록
    Integer insert(ProductColorDto productColorDto);

    // 특정 상품의 특정 컬러 정보 수정
    Integer update(ProductColorDto productColorDto);

    // 특정 상품의 특정 컬러 정보 제거
    Integer delete(Integer prodNo, Integer colNo);

    /* 테스트용 */
    // 모든 상품 컬러 정보 조회
    List<ProductColorDto> selectAll();

    // 모든 상품 컬러 정보 제거
    Integer deleteAll();
}
