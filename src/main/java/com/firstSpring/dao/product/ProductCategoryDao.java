package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProductCategoryDto;
import com.firstSpring.domain.product.ProductHighCategoryDto;

import java.util.List;

public interface ProductCategoryDao {
    // 특정 카테고리 정보 조회
    ProductCategoryDto select(Integer catgNo) throws Exception;

    // 특정 상위 카테고리 조회 (상위 카테고리 유무 체크용)
    ProductCategoryDto selectHighCatg(Integer catgNo) throws Exception;

    //  특정 이름의 카테고리 조회 (이름 중복 확인용)
    List<ProductCategoryDto> selectListByName(String name, Integer highCatgNo) throws Exception;

    // 모든 카테고리 목록 조회
    List<ProductCategoryDto> selectAll() throws Exception;

    // 상위 카테고리 목록 조회
    List<ProductCategoryDto> selectHighCatgList() throws Exception;

    // 모든 카테고리의 하위 카테고리 정보 목록 조회
    List<ProductHighCategoryDto> selectHighLowList() throws Exception;

    // 카테고리 추가
    Integer insert(ProductCategoryDto productCategoryDto) throws Exception;

    // 카테고리 정보 변경
    Integer update(ProductCategoryDto productCategoryDto) throws Exception;

    /* 테스트용 */
    // 카테고리 삭제
    Integer delete(Integer catgCd) throws Exception;

    // 카테고리 모두 삭제
    Integer deleteAll() throws Exception;
}
