package com.firstSpring.service.product;

import com.firstSpring.dao.product.ProductCategoryDao;
import com.firstSpring.dao.product.ProductDao;
import com.firstSpring.domain.product.ProductCategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductCategoryServiceTest {
    @Autowired
    ProductCategoryService prodCatgService;

    @Autowired
    ProductCategoryDao prodCatgDao;

    /** [ 카테고리 추가 테스트 ]
     *  - 카테고리 1개 추가 후 조회한 정보가 삽입 정보와 일치하면 성공
     */
    @Test
    public void register() throws Exception {
        // 0. 모든 카테고리 제거
        prodCatgDao.deleteAll();
        assertTrue(prodCatgDao.selectAll().size() == 0);

        // 1. 카테고리 1개 추가 후 조회한 정보가 삽입 정보와 일치하면 성공
        // 1-1. 삽입할 카테고리 정보 초기화
        ProductCategoryDto prodCatgDto = new ProductCategoryDto(null, "카테카테");
        // 1-2. 카테고리 등록
        prodCatgService.register(prodCatgDto);
        assertTrue(prodCatgDao.selectAll().size() == 1);
        // 1-3. 첫번째 카테고리를 조회해 name 값이 일치하는지 확인
        ProductCategoryDto selectedCatgDto = prodCatgDao.selectAll().get(0);
        assertTrue(prodCatgDto.getName().equals(selectedCatgDto.getName()));

    }

    /** [ 카테고리 추가 예외 테스트 ]
     *  - 필수 데이터를 넣지 않은 경우 예외 발생
     */
    @Test(expected = Exception.class)
    public void RegisterExTest() throws Exception {
        // 0. 모든 카테고리 제거
        prodCatgDao.deleteAll();
        assertTrue(prodCatgDao.selectAll().size() == 0);

        // 1. 필수 데이터를 넣지 않고 등록
        ProductCategoryDto prodCatgDto = new ProductCategoryDto();
        prodCatgService.register(prodCatgDto);
    }


    /** [ 상위 카테고리 여부 확인 테스트 ]
     *  - 상위 카테고리 1개 추가한 후 해당 PK가 상위 카테고리로 판별되면 성공
     *  - 하위 카테고리 1개 추가한 후 해당 PK가 상위 카테고리로 판별되지 않으면 성공
     */
    @Test
    public void isHighCatg() throws Exception{
        // 0. 모든 카테고리 제거
        prodCatgDao.deleteAll();
        assertTrue(prodCatgDao.selectAll().size() == 0);

        // 1.  상위 카테고리 1개 추가한 후 해당 PK가 상위 카테고리로 판별되면 성공
        // 1-1. 삽입할 카테고리 정보 초기화
        ProductCategoryDto prodCatgDto = new ProductCategoryDto(null, "카테카테");
        prodCatgDto.setHighCatgNo(null); // 상위 카테고리로 세팅

        // 1-2. 카테고리 등록
        assertTrue(prodCatgService.register(prodCatgDto)== 1);
        assertTrue(prodCatgDao.selectAll().size() == 1);

        // 1-3. 상위 카테고리 여부 결과가 true로 나와야 함
        Integer selectedCatgNo = prodCatgDao.selectAll().get(0).getCatgNo();
        assertTrue(prodCatgService.isHighCatg(selectedCatgNo));

        // 2. 하위 카테고리 1개 추가한 후 해당 PK가 상위 카테고리로 판별되지 않으면 성공
        // 2-1. 상위 카테고리 번호 읽어오기
        Integer highCatgNo = prodCatgDao.selectHighCatg(selectedCatgNo).getCatgNo();
        assertTrue(highCatgNo != null);
        assertTrue(highCatgNo.equals(selectedCatgNo));

        // 2-2. 하위 카테고리 정보 선언 및 초기화
        ProductCategoryDto lowCatgDto = new ProductCategoryDto(null, "하위카테");
        lowCatgDto.setHighCatgNo(highCatgNo);

        // 2-3. 하위 카테고리 등록
        assertTrue(prodCatgService.register(lowCatgDto)== 1);
        assertTrue(prodCatgDao.selectAll().size() == 2);

        // 2-4. 하위 카테고리 번호 읽어오기
        Integer lowCatgNo = prodCatgDao.selectAll().get(1).getCatgNo();

        // 2-5. 하위 카테고리 번호로 상위 카테고리 여부 판별 결과 false 나와야함
        assertTrue(!prodCatgService.isHighCatg(lowCatgNo));

    }

    /** [ 이름 중복 확인 ]
     *   - 상위 카테고리 1개 추가한 후 동일한 이름으로 중복 확인 했을 때 결과가 true 나와야댐
     *   - 동일한 이름의 하위 카테고리 1개 추가한 후 중복 확인하면 결과가 true
     */
    @Test
    public void isDuplicatedName() throws Exception {
        // 0. 모든 카테고리 제거
        prodCatgDao.deleteAll();
        assertTrue(prodCatgDao.selectAll().size() == 0);

        // 1. 상위 카테고리 1개 추가한 후 동일한 이름으로 중복 확인 했을 때 결과가 true 나와야댐
        // 1-1. 상위 카테고리 등록
        String name = "카테고리다";
        ProductCategoryDto prodCatgDto = new ProductCategoryDto(null, name);
        assertTrue(prodCatgService.register(prodCatgDto) == 1);
        assertTrue(prodCatgDao.selectAll().size() == 1);

        // 1-2. 중복 확인
        assertTrue(prodCatgService.isDuplicatedName(name, null));

    }


}