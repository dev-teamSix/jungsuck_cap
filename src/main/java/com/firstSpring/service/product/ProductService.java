package com.firstSpring.service.product;

import com.firstSpring.dao.product.ImageDao;
import com.firstSpring.dao.product.ImageDaoImpl;
import com.firstSpring.dao.product.ProductColorDao;
import com.firstSpring.dao.product.ProductDao;
import com.firstSpring.domain.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class  ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    ProductColorDao productColorDao;

    @Autowired
    ImageDao imageDao;

    // 상품 목록 조회
    @Transactional(rollbackFor = Exception.class)
    public List<ProductDto> getList(Map map) throws Exception {
        try {
            return productDao.selectList(map);
        } catch (Exception e) {
            throw new Exception("상품 목록 조회 서비스 예외 발생");
        }

    }

    // 상품 목록 페이지 단위로 조회
    public List<ProductListDto> getPageWithJoin(Map map) throws Exception {
        try{
            return productDao.selectPageWithJoin(map);
        } catch (Exception e) {
            throw new Exception("상품 목록 페이징 조회 서비스 예외 발생");
        }
    }

    // 상품 조회
    // map 내용 - 상품번호, 변경자식별번호
    @Transactional(rollbackFor = Exception.class)
    public ProductDto read(Integer prodNo) throws Exception {
        try {
            // 조회수 증가
            if(productDao.increaseViewCnt(prodNo) != 1) {
                // 조회수 증가에 실패하면 예외 발생시키기
                throw new Exception("조회수 증가 실패");
            }
            return productDao.select(prodNo);
        } catch (Exception e) {
            throw new Exception("상품 조회 서비스 예외 발생 -"+e.getMessage());
        }

    }

    // 상품 상세 조회(기본 정보+메인이미지 정보+옵션(색상) 정보)
    // 이렇게 따로따로 조회 하는게 맞나..?
    @Transactional(rollbackFor = Exception.class)
    public ProductRequest readDetail(Integer prodNo) throws Exception {

        ProductRequest pr = new ProductRequest();
        try{
            // 상품 상세 조회
            ProductDto productDto = productDao.select(prodNo);
            if(productDto == null) {
                throw new Exception("잘못된 상품 번호로 조회 실패");
            }
            // 반환 객체에 상품 상세 조회 결과 넣기
            pr.setProduct(productDto);

            // 조회수 증가
            if(productDao.increaseViewCnt(prodNo) != 1) {
                // 조회수 증가에 실패하면 예외 발생시키기
                throw new Exception("조회수 증가 실패");
            }

            // 메인 이미지 정보 조회
            Integer mainImgNo = productDto.getMainImgNo();
            if(mainImgNo != null) {
                // 이미지 정보를 읽어서
                ImageDto imageDto = imageDao.select(mainImgNo);
                // 반환 객체에 넣기
                pr.setMainImg(imageDto);
            }

            // 상품 옵션(색상) 정보 목록 조회
            List<ProdColJoinDto> prodColDto = productColorDao.selectListByProdWithJoin(prodNo);
            // 반환 객체에 조회해온 옵션 목록 추가
            pr.setProdColList(prodColDto);

        } catch (Exception e) {
            throw new Exception("상품 상세 조회 서비스 예외 발생");
        }

        return pr;
    }



    // 상품 등록
    @Transactional(rollbackFor = Exception.class)
    public Integer register(ProductDto productDto) throws Exception {
        try{
            return productDao.insert(productDto);
        } catch (Exception e) {
            throw new Exception("상품 등록 서비스 예외 발생");
        }

    }

    // 상품 정보 변경
    @Transactional(rollbackFor = Exception.class)
    public Integer modifyInfo(ProductDto productDto) throws Exception {
        try {
            return productDao.updateInfo(productDto);
        } catch (Exception e) {
            throw new Exception("상품 수정 서비스 예외 발생");
        }

    }


    // 상품 제거
    @Transactional(rollbackFor = Exception.class)
    public Integer remove(Integer prodNo) throws Exception {
        try{
            return productDao.delete(prodNo);
        } catch (Exception e) {
            throw new Exception("상품 삭제 서비스 예외 발생");
        }
    }
}
