package com.firstSpring.service.product;


import com.firstSpring.dao.product.ProductCategoryDao;
import com.firstSpring.domain.product.ProductCategoryDto;
import com.firstSpring.domain.product.ProductHighCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    // 상위 카테고리 여부 체크
    @Transactional(rollbackFor = Exception.class)
    public Boolean isHighCatg(Integer catgNo) throws Exception{
        try {
            // 상위 카테고리 조회 결과가 null 아니면 true 반환, null이면 false 반환
            return productCategoryDao.selectHighCatg(catgNo) != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("상위 카테고리 조회 중 예외 발생");
        }

    }

    // 카테고리 이름 중복 체크
    @Transactional(rollbackFor = Exception.class)
    public Boolean isDuplicatedName(String name, Integer highCatgNo ) throws Exception{
        try {
            // 같은 이름을 가진 카테고리 개수가 1개 이상이면 true 반환, 아니면 false 반환
            return productCategoryDao.selectListByName(name, highCatgNo).size() >= 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("동일한 이름을 가진 카테고리 조회 중 예외 발생");
        }

    }

    // 모든 카테고리 목록 조회
    @Transactional(rollbackFor = Exception.class)
    public List<ProductCategoryDto> getList() throws Exception{
        try{
            return productCategoryDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("전체 카테고리 목록 조회 중 예외 발생");
        }
    }

    // 상위 카테고리 목록 조회
    @Transactional(rollbackFor = Exception.class)
    public List<ProductCategoryDto> getHighCatgList() throws Exception{
        try{
            return productCategoryDao.selectHighCatgList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("상위 카테고리 목록 조회 중 예외 발생");
        }
    }

    // 모든 상/하위 카테고리 조회
    @Transactional(rollbackFor = Exception.class)
    public List<ProductHighCategoryDto> getHighLowCatgList() throws Exception{
        try {
            return productCategoryDao.selectHighLowList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("상/하위 카테고리 목록 조회 중 예외 발생");
        }
    }

    // 카테고리 상세 조회
    @Transactional(rollbackFor = Exception.class)
    public ProductCategoryDto read(Integer catgNo) throws Exception{
        try {
            return productCategoryDao.select(catgNo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("카테고리 상세 조회 중 예외 발생");
        }
    }

    // 카테고리 등록
    @Transactional(rollbackFor = Exception.class)
    public boolean register(ProductCategoryDto productCategoryDto) throws Exception{
        try {
            if (!validate(productCategoryDto)) {
                return false;
            }
            return productCategoryDao.insert(productCategoryDto) == 1 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 카테고리 변경
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(ProductCategoryDto productCategoryDto) throws Exception{
        try {
            if (!validate(productCategoryDto)) {
                return false;
            }
            return productCategoryDao.update(productCategoryDto) == 1 ? true: false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("카테고리 정보 수정 중 예외 발생");
        }
    }

    // 카테고리 제거
    // Q. 해당 카테고리의 상품들은 어떻게? -> 일정 값으로 세팅 or null로 두기?

    // 수정&등록 전 유효성 검사
    public boolean validate(ProductCategoryDto prodCatgDto) throws Exception{
        // 상위 카테고리가 유효한지 확인
        if(prodCatgDto.getHighCatgNo() != null) {
            Boolean isHighCatg = isHighCatg(prodCatgDto.getHighCatgNo());
            if(!isHighCatg) {
                return false;
            }
        }

        // 이름 중복 여부 확인
        Boolean isDuplicatedName = isDuplicatedName(prodCatgDto.getName(), prodCatgDto.getHighCatgNo());
        if(isDuplicatedName) {
            return false;
        }

        return true;
    }
}
