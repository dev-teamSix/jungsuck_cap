package com.firstSpring.service.product;

import com.firstSpring.dao.product.ProductDao;
import com.firstSpring.domain.product.ProductDto;
import com.firstSpring.domain.product.ProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Autowired
    ProductDao productDao;

    /* [ 상품 목록 조회 ]
     *  - 상품 10개 등록한 후 조회 결과 개수 10개면 성공
     * */
    @Test
    public void getListTest() throws Exception {
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 목록 조회 조건 세팅
        Map cond = new HashMap();
        cond.put("prodCatgCd", null);
        cond.put("sortBy", null);

        // 1. 상품 10개 등록한 후 조회 결과 개수 10개면 성공
        // 1-1.  상품 10개 등록
        int insertCnt = 10;
        for (int i = 1; i <= insertCnt; i++) {
            ProductDto product = new ProductDto("상품" + i);
            assertTrue(productService.register(product) == 1);
        }

        // 1-2. 목록 조회 결과 개수가 삽입 개수와 일치해야함
        assertTrue(productService.getList(cond).size() == insertCnt);


    }

    /* [ 상품 상세 조회 ]
     *   - 상품 1개 등록한 후 정보 일치하면 성공
     *   - 없는 번호로 조회할 경우 실패
     * */
    @Test
    public void readTest() throws Exception {
        // 0. 모든 카테고리 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 1. 상품 1개 등록한 후 정보 일치하면 성공
        // 1-1. 새로운 상품 등록
        ProductDto product = new ProductDto("상품1");
        assertTrue(productService.register(product) == 1);
        assertTrue(productService.getList(new HashMap()).size() == 1);

        // 1-2. 목록에서 첫번째 상품 정보가 삽입한 상품 정보와 일치한지 확인
        Integer firstProdNo = productService.getList(new HashMap()).get(0).getProdNo();
        ProductDto selectedProduct = productService.read(firstProdNo);
        assertTrue(product.getName().equals(selectedProduct.getName()));

        // 1-3. 한번 더 조회했을 때 조회수 늘어나는지 확인
        int beforeViewCnt = selectedProduct.getViewCnt();
        selectedProduct = productService.read(firstProdNo);
        assertTrue(beforeViewCnt+1 == selectedProduct.getViewCnt());


        // 2. 없는 번호로 조회할 경우 실패
        // 2-1. 모든 카테고리 제거
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 2-2. 없는 번호로 조회 결과 null
        assertTrue(productDao.select(1212) == null);
    }

    /*  [ 상품 상세 + 이미지 정보 + 옵션 목록 조회 ]
    * - 상품, 이미지, 옵션을 하나씩 추가한 후 조회한 결과가 일치하는지 확인
    * */
    @Test
    public void readDetailTest() throws Exception {

    }



    /* [ 상품 등록 ]
     *   - 상품 1개 등록한 후 정보 일치하면 성공
     * */
    @Test
    public void register() throws Exception {

    }

    /*  [ 상품 정보 변경 ]
     *   - 상품 1개 등록 -> 정보 변경 -> 변경 내용 모두 일치하는지 확인
     * */
    @Test
    public void modifyTest() throws Exception {
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 1. 상품 1개 등록 -> 정보 변경 -> 변경 내용 모두 일치하는지 확인
        // 1-1. 새로운 상품 등록
        ProductDto product = new ProductDto( "상품1");
        assertTrue(productService.register(product) == 1);
        assertTrue(productService.getList(new HashMap()).size() == 1);

        // 1-2. 목록에서 첫번째 상품 정보를 읽어와 변경
        ProductDto readProduct = productService.getList(new HashMap()).get(0);
        product.setProdNo(readProduct.getProdNo());
        assertTrue(product.equals(productService.read(readProduct.getProdNo())));

        System.out.println(readProduct);
        readProduct.setName("상품222");
        readProduct.setShortDet("상품이 아닙니다");
        readProduct.setLongDet("상품일까아닐까맞춰보세요");
        readProduct.setLastModId("관리자22");

        // 1-3. 수정된 정보 반영
        assertTrue(productService.modifyInfo(readProduct) == 1);

        // 1-4. 수정된 상품 정보 읽어와서 수정 전 데이터와 불일치해야 성공
        ProductDto modifiedProduct = productService.getList(new HashMap()).get(0);
        assertTrue(modifiedProduct.equals(readProduct));
        assertTrue(!modifiedProduct.equals(product));
    }

    /* [상품 삭제 ]
     *   - 상품 1개 추가 -> 목록 개수 1개 -> 해당 상품 제거 -> 목록 개수 0개
     * */
    @Test
    public void removeTest() throws Exception {
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 1. 상품 1개 추가 -> 목록 개수 1개 확인 -> 해당 상품 제거 -> 목록 개수 0개
        // 1-1. 상품 등록 후 목록 조회 결과 개수 1개인지 확인
        assertTrue(productService.register(new ProductDto("상품1")) == 1);
        assertTrue(productService.getList(new HashMap()).size() == 1);

        // 1-2. 상품 제거
        ProductDto product = productService.getList(new HashMap()).get(0);
        assertTrue(productService.remove(product.getProdNo()) == 1);

        // 1-3. 목록 조회 결과 개수 0개인지 확인
        assertTrue(productService.getList(new HashMap()).size() == 0);

    }
}