package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProductColorDto;
import com.firstSpring.domain.product.ProductDto;
import com.firstSpring.domain.product.ProductListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductColorDaoTest {

    @Autowired
    private ProductColorDao productColorDao;

    @Autowired
    private ProductDao productDao;

    /* [ 특정 상품의 컬러(옵션) 정보 목록 조회 ]
    *   - 1개의 상품 추가하고, 그 상품의 컬러 정보 5개 추가한 후 리스트 비교
    * */
    @Test
    public void selectListByProd() throws Exception {
        // 0. 상품 정보, 상품 옵션(컬러) 정보 모두 제거
        productDao.deleteAll();
        productColorDao.deleteAll();

        assertTrue(productDao.selectAll().size() == 0);
        assertTrue(productColorDao.selectAll().size() == 0);


        // 1. 1개의 상품 추가하고, 그 상품의 컬러 정보 5개 추가한 후 리스트 비교
        // 1-1. 상품 1개 등록
        productDao.insert(new ProductDto("test상품"));
        assertTrue(productDao.selectAll().size() == 1);

        // 1-2. 특정 상품의 컬러 정보 5개 추가
        // 1-2-1. 등록한 상품 번호 조회
        Integer prodNo = productDao.selectAll().get(0).getProdNo();
        System.out.println(productDao.selectAll().get(0));
        assertTrue(prodNo != null);

        // 1-2-2. 삽입할 컬러 정보 목록 생성 및 등록
        List<ProductColorDto> newProdColList = new ArrayList<>();
        int insertCnt = 5;
        for(int i=0; i<insertCnt; i++){
            ProductColorDto pcd = new ProductColorDto(null, "컬러"+i, prodNo);
            newProdColList.add(pcd);
            productColorDao.insert(pcd); // 상품 컬러 등록
        }

        // 1-3. 조회한 특정 상품의 컬러 정보 5개가 등록한 컬러 정보 목록과 같은지 비교
        List<ProductColorDto> selectedProdColList = productColorDao.selectListByProd(prodNo);
        assertTrue(selectedProdColList.size() == insertCnt);

        for(int i=0; i<insertCnt; i++){
            // 조회한 정보가 삽입한 정보와 같은지 비교
            assertTrue(newProdColList.get(i).getColName().equals(selectedProdColList.get(i).getColName()));
        }
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void deleteAll() {
    }
}