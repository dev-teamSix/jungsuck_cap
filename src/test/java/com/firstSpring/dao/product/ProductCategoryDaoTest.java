package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProductCategoryDto;
import com.firstSpring.domain.product.ProductHighCategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductCategoryDaoTest {
    @Autowired
    ProductCategoryDao productCategoryDao;

    /** [ 카테고리 추가 테스트 ]
     *  - 카테고리 1개 추가한 후 조회한 결과가 삽입한 정보와 일치하면 성공
     */
    @Test
    public void insertTest() throws Exception{
        // 0. 카테고리 모두 제거
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 1. 카테고리 한 개 추가 했을 때 추가한 정보와 조회한 정보가 일치하면 성공
        // 1-1. 추가할 카테고리 정보 생성
        ProductCategoryDto prodCatgDto = new ProductCategoryDto(null, "카테고리1", "설명1", "Y", "Y" );

        // 1-2. 카테고리 등록
        assertTrue(productCategoryDao.insert(prodCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 1);

        // 1-3. 등록한 카테고리의 번호 가져와서 세팅
        Integer insertedCatgNo = productCategoryDao.selectAll().get(0).getCatgNo();
        prodCatgDto.setCatgNo(insertedCatgNo);

        // 1-4. 등록한 카테고리 상세 조회해서 비교
        ProductCategoryDto selectedProdCatgDto = productCategoryDao.select(insertedCatgNo);
        assertTrue(prodCatgDto.equals(selectedProdCatgDto));
    }

    /* [ 특정 카테고리 상세 조회 ]
    *   - 카테고리 한 개 추가 했을 때 추가한 정보와 조회한 정보가 일치하면 성공
    *   - 없는 번호로 조회하면 실패
    * */
    @Test
    public void selectTest() throws Exception{
        // 0. 카테고리 모두 제거
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 1. 카테고리 한 개 추가 했을 때 추가한 정보와 조회한 정보가 일치하면 성공
        // 1-1. 추가할 카테고리 정보 생성
        ProductCategoryDto prodCatgDto = new ProductCategoryDto(null, "카테고리1", "설명1", "Y", "Y" );

        // 1-2. 카테고리 등록
        assertTrue(productCategoryDao.insert(prodCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 1);

        // 1-3. 등록한 카테고리의 번호 가져와서 세팅
        Integer insertedCatgNo = productCategoryDao.selectAll().get(0).getCatgNo();
        prodCatgDto.setCatgNo(insertedCatgNo);

        // 1-4. 등록한 카테고리 상세 조회해서 비교
        ProductCategoryDto selectedProdCatgDto = productCategoryDao.select(insertedCatgNo);
        assertTrue(prodCatgDto.equals(selectedProdCatgDto));

        // 2. 없는 번호로 조회할 경우 실패
        // 2-1. 모든 카테고리 제거
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 2-2. 없는 번호로 조회했을 때 결과가 null
        assertTrue(productCategoryDao.select(100000)==null);

    }

    /** [ 특정 상위 카테고리 조회 ]
     *  - 상위 카테고리를 추가한 후 조회한 정보가 삽입한 정보와 같으면 성공
     *  - 상위 카테고리가 아닌 번호로 조회를 시도하면 실패
     */
    @Test
    public void selectHighCatg()  throws Exception{
        // 0. 모든 카테고리 삭제
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 1. 상위 카테고리를 추가한 후 조회한 정보가 삽입한 정보와 같으면 성공
        // 1-1. 추가할 카테고리 정보 생성
        ProductCategoryDto prodCatgDto = new ProductCategoryDto(null, "카테고리1", "설명1", "Y", "Y" );
        prodCatgDto.setHighCatgNo(null); // 상위 카테고리로 설정

        // 1-2. 카테고리 등록
        assertTrue(productCategoryDao.insert(prodCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 1);

        // 1-3. 등록한 카테고리의 번호 가져와서 세팅
        Integer insertedCatgNo = productCategoryDao.selectAll().get(0).getCatgNo();
        prodCatgDto.setCatgNo(insertedCatgNo);

        // 1-3. 등록한 상위 카테고리 조회해서 비교
        ProductCategoryDto selectedProdCatgDto = productCategoryDao.selectHighCatg(insertedCatgNo);
        assertTrue(prodCatgDto.equals(selectedProdCatgDto));

        // 2. 상위 카테고리가 아닌 번호로 조회를 시도하면 실패
        // 2-1. 하위 카테고리 정보 세팅
        ProductCategoryDto lowProdCatgDto = new ProductCategoryDto(null, "하위카테", "설명 없음", "Y", "Y");
        Integer highCatgNo = productCategoryDao.selectAll().get(0).getCatgNo();
        lowProdCatgDto.setHighCatgNo(highCatgNo);

        // 2-3-2. 하위 카테고리 정보 등록
        assertTrue(productCategoryDao.insert(lowProdCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 2);

        // 2-4. 하위 카테고리 번호로 조회하면 실패
        // 2-4-1. 하위 카테고리 번호 조회
        Integer lowCatgNo = productCategoryDao.selectAll().get(1).getCatgNo();
        assertTrue(productCategoryDao.selectHighCatg(lowCatgNo) == null);
    }

    /** [ 같은 카테고리 내 특정 이름의 카테고리 정보 조회 ]
     *  - 동일한 이름의 카테고리 2개 등록한 후 조회 결과 개수가 2개면 성공
     */
    @Test
    public void selectListByNameTest()  throws Exception{
        // 0. 모든 카테고리 삭제
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 1.동일한 이름의 카테고리 2개 등록한 후 조회 결과 개수가 2개면 성공
        // 1-1. 동일한 이름의 카테고리 2개 등록
        String name = "카테고리";
        ProductCategoryDto prodCatgDto = new ProductCategoryDto(null, name);
        assertTrue(productCategoryDao.insert(prodCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 1);
        assertTrue(productCategoryDao.insert(prodCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 2);

        // 1-2. 다른 이름의 카테고리 1개 등록
        ProductCategoryDto otherNameProdCatgDto = new ProductCategoryDto(null, name+"12313");
        assertTrue(productCategoryDao.insert(otherNameProdCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 3);


        // 1-2. 특정 이름의 카테고리 목록 조회 결과 개수가 2개면 성공;
        assertTrue(productCategoryDao.selectListByName(name, null).size() == 2);

        // 2. 첫번째 상위 카테고리에 대한 하위 카테고리를 1개 추가한 후 동일한 이름의 카테고리 개수가 2개면 성공
        // 2-1. 상위 카테고리 번호 읽어오기
        Integer highCatgNo = productCategoryDao.selectHighCatgList().get(0).getCatgNo();

        // 2-2. 하위 카테고리 등록
        ProductCategoryDto lowProdCatgDto = new ProductCategoryDto(null, name);
        lowProdCatgDto.setHighCatgNo(highCatgNo);
        assertTrue(productCategoryDao.insert(lowProdCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 4);

        // 2-3. 조회 결과 2개여야 됨
        assertTrue(productCategoryDao.selectListByName(name, highCatgNo).size() == 2);

    }



    /* [ 모든 상위 카테고리 목록(하위 카테고리 목록 포함) 조회 ]
    *   - 상위 카테고리 한 개 추가, 해당 카테고리의 하위 카테고리 한 개 추가 -> 조회 결과 개수 1개
    *   - 위 상위 카테고리의 하위 카테고리 한 개 더 추가 -> 조회 결과 개수 1개, 하위 카테고리 목록 길이 2
    *     * */
    @Test
    public void selectHighLowListTest() throws Exception {
        // 0. 카테고리 모두 제거
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 1. 상위 카테고리 한 개 추가, 해당 카테고리의 하위 카테고리 한 개 추가 -> 조회 결과 개수 1개
        // 1-1. 상위 카테고리 추가
        ProductCategoryDto highCategory = new ProductCategoryDto(null, "상위1");
        assertTrue(productCategoryDao.insert(highCategory) ==1);
        assertTrue(productCategoryDao.selectAll().size() == 1);

        // 1-2. 하위 카테고리 추가
        Integer highCatgNo = productCategoryDao.selectAll().get(0).getCatgNo();
        ProductCategoryDto lowCategory = new ProductCategoryDto(null, "핸디캡");
        lowCategory.setHighCatgNo(highCatgNo);
        assertTrue(productCategoryDao.insert(lowCategory) ==1);
        assertTrue(productCategoryDao.selectAll().size() == 2);

        // 1-3. 모든 상위 카테고리 목록(하위 카테고리 목록 포함) 조회 결과 1이여야 성공
        List<ProductHighCategoryDto> highCategorylist = productCategoryDao.selectHighLowList();
        System.out.println(highCategorylist);
        assertTrue(highCategorylist.size() == 1);
        assertTrue(highCategorylist.get(0).getLowCategoryList().size() == 1);

        // 2. 위 상위 카테고리의 하위 카테고리 한 개 더 추가 -> 조회 결과 개수 1개, 하위 카테고리 목록 길이 2
        // 2-1. 하위 카테고리 추가
        lowCategory = new ProductCategoryDto(null, "캡숑짱캡");
        lowCategory.setHighCatgNo(highCatgNo);
        assertTrue(productCategoryDao.insert(lowCategory) ==1);
        assertTrue(productCategoryDao.selectAll().size() == 3);

        // 2-2. 모든 상위 카테고리 목록(하위 카테고리 목록 포함) 조회 결과 1, 첫번째 상위 카테고리의 하위 카테고리 목록 조회 결과 2이면 성공
        highCategorylist = productCategoryDao.selectHighLowList();
        System.out.println(highCategorylist);
        assertTrue(highCategorylist.size() == 1);
        assertTrue(highCategorylist.get(0).getLowCategoryList().size() == 2);

    }


    /** [ 카테고리 정보 변경 테스트 ]
     *  - 상품 추가 후 변경하면 변경한 정보와 조회한 정보가 일치해야함
     */
    @Test
    public void updateTest() throws Exception {
        // 0. 카테고리 모두 제거
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 1. 상품 추가 후 변경하면 변경한 정보와 조회한 정보가 일치해야함
        // 1-1. 추가할 카테고리 정보 생성
        ProductCategoryDto prodCatgDto = new ProductCategoryDto();
        prodCatgDto.setName("카테고리");
        prodCatgDto.setHighCatgNo(null);
        prodCatgDto.setDetail("변경 전 카테고리입니다.");
        prodCatgDto.setIsDisp("Y");
        prodCatgDto.setIsUsed("Y");
        prodCatgDto.setFirstRegId("manager1");
        prodCatgDto.setLastModId("manager1");

        // 1-2. 카테고리 등록
        assertTrue(productCategoryDao.insert(prodCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 1);

        // 1-3. 등록한 카테고리의 번호로 조회해서 정보 변경
        Integer insertedCatgNo = productCategoryDao.selectAll().get(0).getCatgNo();
        assertTrue(insertedCatgNo != null);

        ProductCategoryDto beforeProdCatgDto = productCategoryDao.select(insertedCatgNo);
        ProductCategoryDto afterProdCatgDto = productCategoryDao.select(insertedCatgNo);
        afterProdCatgDto.setName("카테고리22");
        afterProdCatgDto.setHighCatgNo(12);
        afterProdCatgDto.setDetail("변경 후의 카테고리 입니다");
        afterProdCatgDto.setIsUsed("N");
        afterProdCatgDto.setIsDisp("N");
        afterProdCatgDto.setLastModId("manager2");

        // 1-4. 변경한 정보로 업데이트
        assertTrue(productCategoryDao.update(afterProdCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 1);

        // 1-5. 변경 전 데이터와 조회해온 데이터가 다르면 성공
        ProductCategoryDto selectedProdCatgDto = productCategoryDao.select(insertedCatgNo);
        assertTrue(!beforeProdCatgDto.equals(selectedProdCatgDto));

        // 1-6. 변경 후 데이터와 조회해온 데이터가 동일하면 성공
        assertTrue(afterProdCatgDto.equals(selectedProdCatgDto));
    }


    /** [ 카테고리 삭제 테스트 ]
     *  - 카테고리 하나 추가한 후 제거한 결과 1, 상품 목록 개수 0
     *  - 없는 번호로 제거할 경우 결과 0
     */
    @Test
    public void deleteTest() throws Exception {
        // 0. 카테고리 모두 제거
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 1. 카테고리 하나 추가한 후 제거한 결과 1, 상품 목록 개수 0
        // 1-1. 추가할 카테고리 정보 생성
        ProductCategoryDto prodCatgDto = new ProductCategoryDto(null, "카테고리1", "설명1", "Y", "Y" );

        // 1-2. 카테고리 등록
        assertTrue(productCategoryDao.insert(prodCatgDto) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 1);

        // 1-3. 등록한 카테고리의 번호 가져옴
        Integer insertedCatgNo = productCategoryDao.selectAll().get(0).getCatgNo();

        // 1-4. 등록한 카테고리 제거
        assertTrue(productCategoryDao.delete(insertedCatgNo) == 1);
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 2.  없는 번호로 제거할 경우 결과 0
        // 2-1. 모든 카테고리 제거
        productCategoryDao.deleteAll();
        assertTrue(productCategoryDao.selectAll().size() == 0);

        // 2-2. 없는 번호로 제거하면 결과가 0
        assertTrue(productCategoryDao.delete(123) == 0);

    }

}