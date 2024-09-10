package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ProductDto;
import com.firstSpring.domain.product.ProductListDto;
import com.firstSpring.domain.product.SearchCondition;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductDaoTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    ProductDao productDao;

    /* [ 상품 추가 성공 테스트 ]
    *   1. 상품 20개 추가 -> 모두 추가 성공하는지 확인
    *   2. 상품 1개 추가 -> 추가한 상품명과 조회된 상품명이 같은지 확인
    * */
    @Test
    public void insertTest()  throws Exception{
        // 0. 모든 상품 삭제
        productDao.deleteAll();

        // 상품 개수 0개인지 확인
        assertTrue(productDao.selectAll().size() == 0);

        // 1.  상품 개수가 0인 상태에서 상품 20개 추가
        int insertCnt = 20;
        for(int i=1; i<=insertCnt; i++) {
            ProductDto product = new ProductDto("상품1");
            assertTrue(productDao.insert(product) == 1);
        }
        assertTrue(productDao.selectAll().size()==insertCnt);

        // 2.  상품 1개 추가 -> 새로운 상품이 추가됐는지 확인, 해당 키로 조회했을 때 상품명이 같은지 확인

        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 2-1. 상품 추가
        ProductDto nextProduct = new ProductDto("햇햇햇");
        assertTrue(productDao.insert(nextProduct)==1);

        // 2-2. 새로운 상품이 추가됐는지 확인, 해당 키로 조회했을 때 상품명이 같은지 확인
        ProductDto productDto = productDao.selectAll().get(0);
        assertTrue(nextProduct.getName().equals(productDao.select(productDto.getProdNo()).getName()) );

    }


    /* [ 상품 추가 예외 테스트 ]
    *   1. 동일한 PK의 상품 1개 추가 -> DuplicateKeyException 예외 발생
    *   2. 상품번호가 정해진 타입길이를 넘김 -> DataIntegrityViolationException 예외 발생
    * */
//    @Test
//    public void insertExceptionTest() throws Exception{
//        // 0. 모든 상품 삭제
//        productDao.deleteAll();
//        // 상품 개수 0개인지 확인
//        assertTrue(productDao.selectAll().size() == 0);
//
//        // 1-1. 상품 1개 추가
//        ProductDto product = new ProductDto( "캡모자");
//        assertTrue(productDao.insert(product) == 1);
//
//        // 1-2. 동일한 pk의 상품 1개 추가
//        Integer duplicatedKey = productDao.selectAll().get(0).getProdNo();
//        ProductDto duplicatedProduct = new ProductDto( "캡모자아닌데요");
//        // 1-2-검증. insert시 DuplicateKeyException 예외 발생
//        thrown.expect(DuplicateKeyException.class);
//        assertTrue(productDao.insert(duplicatedProduct)==0);
//
//
//        // 2. 상품 번호가 정해진 타입 길이(30글자)를 넘김 -> DataIntegrityViolationException 발생
//        product = new ProductDto("P000000000000000000000000000000000000000000000000000000000000001L", "캡모자");
//        thrown.expect(DataIntegrityViolationException.class);
//        assertTrue(productDao.insert(product)==0);
//
//    }


    /* [ 상품 목록 조회 테스트 ]
    *   - 서로 다른 카테고리의 상품 10개 추가 후 전체 목록 조회(카테고리 고려 x)
    *       -> 개수 10개인지 확인
    *       -> 정렬 기준이 등록순(지정X)일 때 첫번째 행의 상품 번호가 가장 마지막 번호인지 확인
    *       -> 정렬 기준이 낮은 가격순일 때 첫번째 행의 상품 가격이 가장 작은지 확인
    *       -> 정렬 기준이 높은 가격순일 때 첫번째 행의 상품 가격이 가장 큰 값인지 확인
    *       -> 정렬 기준이 판매량 순일 때 첫번째 행의 판매량이 가장 큰 값이 확인
    *   - 서로 다른 카테고리의 상품 각각 1개씩 추가(총 2개) (정렬 기준 고려 X)
    *       -> 한 카테고리의 상품 목록의 개수가 1개인지 확인
    *       -> 없는 카테고리 상품 조회한 경우 결과 개수 0
    *   - 등록 날짜가 동일한 상품 5개에 대해 정렬 기준이 등록순일 때 사전순으로 정렬되는지 확인
    * */
    @Test
    public void selectListTest() throws Exception {
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        // 상품 개수 0개인지 확인
        assertTrue(productDao.selectAll().size() == 0);


        // 1. 같은 카테고리의 상품 10개를 등록한 후 정렬 잘 되는지 확인
        // 1-1. 서로 다른 카테고리 상품 10개 등록
        int startNum = 1;
        int lastNum = 10;
        int insertCnt = lastNum - startNum + 1;
        int maxPrice = 10000;
        int minPrice = maxPrice - 10*(lastNum-startNum);
        int year = 2024;
        int maxTotalSales = 1000;
        for(int i=startNum; i<=lastNum; i++) {
            ProductDto product = new ProductDto("상품"+(char)('a'+i)); // 상품번호, 상품명
            product.setProdNo(i);                          // 상품 카테고리 코드
            product.setPrice(maxPrice - 10*(i-1));                  // 상품 가격
            product.setTotalSales(maxTotalSales - 10*(i-1));        // 판매량
            product.setFirstRegDt((year+i) +"-08-05 02:27:00") ;    // 최초등록일시
            product.setLastModDt((year+i) +"-08-05 02:27:00");      // 최종수정일시
            assertTrue(productDao.insertTest(product) == 1);    // 상품 등록
        }

        // 1-2. 검색조건(정렬 기준, 카테고리) 세팅 - 정렬 기준 지정X -> 등록순, 카테고리 지정 X -> 모든 상품
        Map cond = new HashMap();
        cond.put("prodCatgCd", null);
        // 1-3. 모든 상품 조회 결과 개수 10개인지 확인
        List<ProductDto> productDtoList = productDao.selectList(cond);
        assertTrue(productDtoList.size()==insertCnt);


        // 1-4. 정렬 기준이 등록순(지정X)일 때 첫번째 행의 상품 번호가 가장 마지막 번호인지 확인
//        assertTrue(("P"+lastNum).equals(productDtoList.get(0).getProdNo()));
//        assertTrue(((year+insertCnt)+"").equals(productDtoList.get(0).getFirstRegDt().substring(0, 4)));

        // 1-5. 정렬 기준이 낮은 가격순일 때 첫번째 행의 상품 가격이 가장 작은지 확인
        cond.put("sortBy", "lowPrice"); // 정렬 기준 낮은 가격으로 세팅
        productDtoList = productDao.selectList(cond);
        assertTrue(productDtoList.size() ==insertCnt); // 상품 개수가 추가한 개수만큼이어야 됨
        assertTrue(productDtoList.get(0).getPrice() == minPrice);

        // 1-6. 정렬 기준이 높은 가격순일 때 첫번째 행의 상품 가격이 가장 큰 값인지 확인
        cond.put("sortBy", "highPrice"); // 정렬 기준 높은 가격순으로 세팅
        productDtoList = productDao.selectList(cond);
        assertTrue(productDtoList.size() == insertCnt); // 상품 개수가 추가한 개수만큼이어야 됨
        assertTrue(productDtoList.get(0).getPrice() == maxPrice);

        // 1-7. 정렬 기준이 판매량 순일 때 첫번째 행의 판매량이 가장 큰 값인지 확인
        cond.put("sortBy", "totalSales");
        productDtoList = productDao.selectList(cond);
        assertTrue(productDtoList.size() == insertCnt); // 개수가 추가한 개수만큼이어야 됨
        System.out.println(productDtoList.get(0) );
        assertTrue(productDtoList.get(0).getTotalSales() == maxTotalSales);



        // 2. 서로 다른 카테고리의 상품 각각 2개씩 추가(총 4개)
        // 2-0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 2-1. 1, 2번 카테고리 값을 가지는 상품 각각 1개씩 추가
        ProductDto productDto1 = new ProductDto( "상품1");
        productDto1.setProdCatgNo(1);
        assertTrue(productDao.insert(productDto1) == 1);
        assertTrue(productDao.selectAll().size() == 1);

        ProductDto productDto2 = new ProductDto( "상품3");
        productDto2.setProdCatgNo(2);
        assertTrue(productDao.insert(productDto2) == 1);
        assertTrue(productDao.selectAll().size() == 2);


        // 2-1. 한 카테고리의 상품 목록의 개수가 1개인지 확인
        cond.put("prodCatgNo", 1);
        cond.put("sortBy", null);
        assertTrue(productDao.selectList(cond).size() == 1);
        assertTrue(productDao.selectList(cond).get(0).getName().equals(productDto1.getName()));

        cond.put("prodCatgNo", 2);
        assertTrue(productDao.selectList(cond).size() == 1);
        assertTrue(productDao.selectList(cond).get(0).getName().equals(productDto2.getName()));

        // 2-2. 없는 카테고리 상품 조회한 경우 결과 개수 0
        cond.put("prodCatgNo",1213123);
        assertTrue(productDao.selectList(cond).size() == 0);


        // 3. 등록 날짜가 동일한 상품 5개에 대해 정렬 기준이 등록순일 때 사전순으로 정렬되는지 확인
        // 3-0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 3-1. 동일한 등록날짜의 상품 5개 등록
        startNum = 1;
        lastNum = 5;
        insertCnt = lastNum - startNum + 1;
        for(int i=startNum; i<=lastNum; i++) {
            ProductDto product = new ProductDto("상품"+(char)('a'+(i-1))); // 상품번호, 상품명
            product.setFirstRegDt("2024-08-05 02:27:00");   // 최초등록일시
            product.setLastModDt("2024-08-05 02:27:00");    // 최종등록일시
            assertTrue(productDao.insert(product) == 1);    // 상품 등록
        }
        assertTrue(productDao.selectAll().size()==insertCnt);

        // 3-2. 정렬기준이 등록순 -> 첫번째 행의 상품명이 '상품a'여야 함
        cond.put("prodCatgNo", null);
        cond.put("sortBy", "");
        productDtoList = productDao.selectList(cond);
        assertTrue(productDtoList.size()==insertCnt);
        assertTrue(productDtoList.get(0).getName().equals("상품a"));
    }



    /* [ 상품 목록 조회 예외 테스트 ]
    *   - 상품 정렬 기준이 int 값인 경우 예외 발생
    *   - 상품 카테고리 값이 String 값인 경우 예외 발생
    * */
    @Test
    public void selectListExceptionTest() throws Exception {
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 1. 상품 정렬 기준이 int값인 경우 예외 발생
        // 1-1. 정렬 기준을 숫자로 세팅
        Map cond = new HashMap();
        cond.put("sortBy", 123);

        // 1-2. myBatis null 비교 과정에서 예외 발생
        thrown.expect(MyBatisSystemException.class);
        productDao.selectList(cond);

        // 2. 상품 카테고리 값이 String 값인 경우 예외 발생
        // 2-1. 카테고리 값을 숫자로 세팅
        cond.put("sortBy", null);
        cond.put("prodCatgNo", "12314");
        // 2-2. myBatis null 비교 과정에서 예외 발생
        productDao.selectList(cond);

    }

    /* [ 상품 검색 테스트 ]
    *
    * */
    @Test
    public void selectSearchList() throws Exception {
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        assertTrue(productDao.insert(new ProductDto("product")) == 1);
        SearchCondition sc = new SearchCondition(1, 10, null, "", "product1");
        List<ProductListDto> list =  productDao.selectSearchPage(sc);
        assertTrue(list.size() == 0);


        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);
        for(int i=0; i<20; i++) {
            assertTrue(productDao.insert(new ProductDto("product"+(i%5))) == 1);
        }
        List<ProductDto> prdList =  productDao.selectSearchList(sc);
        assertTrue(prdList.size() == 4);

    }

    /* [ 상품 상세 조회 테스트 ]
    *   1. 한개의 상품을 추가한 후 해당 번호로 조회한 결과와 추가한 데이터가 같아야함.
    *   2. 1번과 동일한 내용을 가지는 상품을 한 개 더 추가한 후 조회한 각각의 데이터가 달라야함.
    *   3. 없는 번호로 조회한 경우 결과가 null
    * */
    @Test
    public void selectTest() throws Exception{
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        //  1. 한개의 상품을 추가한 후 해당 번호로 조회한 결과와 추가한 데이터가 같아야함.
        // 1-1. 상품 추가
        ProductDto productDto1 = new ProductDto(null, 1, "not-cap", "012031202301", 0, "manager1", "manager1");
        assertTrue(productDao.insert(productDto1) == 1);
        // 1-2. 추가한 데이터의 상품 번호로 상세 조회
        ProductDto selectedProduct1 = productDao.selectAll().get(0);
        productDto1.setProdNo(selectedProduct1.getProdNo());
        // 1-3. 추가한 데이터와 조회한 데이터가 같으면 성공
        assertTrue(selectedProduct1.equals(productDto1));

        // 2. 1번과 동일한 내용을 가지는 상품을 한 개 더 추가한 후 조회한 각각의 데이터가 달라야함.
        // 2-1. 번호만 다른 동일한 내용의 상품 추가]

        ProductDto productDto2 =  new ProductDto(null, 1, "not-cap", "012031202301", 0, "manager1", "manager1");
        assertTrue(productDao.insert(productDto2) == 1);
        assertTrue(productDao.selectAll().size() == 2);
        // 2-2. 추가한 데이터의 상품 번호로 상세 조회
        ProductDto selectedProduct2 = productDao.selectAll().get(1);

        // 2-3. 추가한 데이터와 조회한 데이터가 다르면 성공
        assertTrue(!selectedProduct1.equals(selectedProduct2));


        // 3. 없는 번호로 조회한 경우 조회 실패(결과 null)
        assertTrue(productDao.select(productDto1.getProdNo()+200000) == null);

    }

    /* [ 상품 상세 조회 예외 테스트 ]
    *   -
    * */
//    @Test
//    public void selectExceptionTest() {
//        // 0. 모든 상품 삭제
//        productDao.deleteAll();
//        assertTrue(productDao.selectAll().size() == 0);
//    }


    /* [ 상품 정보 변경 테스트 ]
    *   - 상품 1개 추가한 후 해당 상품의 정보를 변경 -> 변경한 정보와 조회해온 결과 정보가 같아야함
    *   - 상품 1개를 추가한 후 없는 상품의 정보를 변경 -> 결과가 null
    * */
    @Test
    public void updateInfoTest() throws Exception {
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 1. 상품 1개 추가한 후 해당 상품의 정보를 변경 -> 변경한 정보와 조회해온 결과 정보가 같음
        // 1-1. 상품 1개 추가
        ProductDto product = new ProductDto( "윤서가쓰던모자");
        product.setShortDet("향긋해요.");
        product.setShortDet("파란색, 향긋해요.");
        productDao.insert(product);
        assertTrue(productDao.selectAll().size() == 1);

        // 1-2. 추가한 상품 조회해서 수정
        ProductDto selectedProduct = productDao.select(productDao.selectAll().get(0).getProdNo());
        selectedProduct.setShortDet("향긋하지 않아요.");
        selectedProduct.setName("윤서가안쓴모자");
        selectedProduct.setLongDet("파란색 아님. 향긋하지 않음");
        assertTrue(productDao.updateInfo(selectedProduct) == 1);

        // 1-3. 수정한 상품 조회해서 수정 내용 일치하면 성공
        ProductDto updatedProduct = productDao.select(productDao.selectAll().get(0).getProdNo());
        assertTrue(updatedProduct.getName().equals(selectedProduct.getName()));
        assertTrue(updatedProduct.getShortDet().equals(selectedProduct.getShortDet()));
        assertTrue(updatedProduct.getLongDet().equals(selectedProduct.getLongDet()));


        // 2. 상품 1개를 추가한 후 없는 상품의 정보를 변경 -> 결과가 null
        // 2-1. 상품 1개 더 추가
        ProductDto product2 = new ProductDto("모자2");
        product2.setShortDet("모자일까");
        product2.setShortDet("모자입니다.");
        productDao.insert(product2);
        assertTrue(productDao.selectAll().size() == 2);

        // 2-2. 없는 번호로 조회했을 때 결과가 null
        assertTrue(productDao.select(10000) == null);
    }


    /* [ 상품 정보 변경 예외 테스트 ]
    *   - 변경하고자 하는 상품명 값이 길이 30을 벗어난 경우 예외 발생
    *   - 변경하고자 하는 상품명 값이 null인 경우 경우 예외 발생
    * */
    @Test
    public void updateInfoExceptionTest() throws Exception {
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 1. 변경하고자 하는 상품명 값이 길이 30을 벗어난 경우 예외 발생
        // 1-1. 상품 하나 추가
        ProductDto product = new ProductDto("윤서가쓰던모자");
        product.setShortDet("향긋해요.");
        product.setShortDet("파란색, 향긋해요.");
        productDao.insert(product);
        assertTrue(productDao.selectAll().size() == 1);

        // 1-2. 추가한 상품을 조회한 후 상품명을 길이가 30이 넘는 값으로 세팅
        ProductDto selectedProduct = productDao.select(productDao.selectAll().get(0).getProdNo());
        selectedProduct.setName("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");

        // 1-3. 삽입하면 예외 발생해야함
        thrown.expect(DataIntegrityViolationException.class);
        productDao.insert(selectedProduct);

        // 2. 변경하고자 하는 상품명 값이 null인 경우 경우 예외 발생
        // 2-1. 상품 추가
        product = new ProductDto("윤서가쓰던모자");
        product.setShortDet("향긋해요.");
        product.setShortDet("파란색, 향긋해요.");
        productDao.insert(product);
        assertTrue(productDao.selectAll().size() == 2);

        // 2-2. 추가한 상품을 조회한 후 상품명을 null값으로 세팅
        selectedProduct = productDao.select(productDao.selectAll().get(1).getProdNo());
        selectedProduct.setName(null);

        // 2-3. 삽입하면 예외 발생해야함
        thrown.expect(DataIntegrityViolationException.class);
        productDao.insert(selectedProduct);

    }

    /* [ 상품 조회수 증가 테스트 ]
    *   - 상품 1개를 추가한 후 해당 상품의 조회수 증가 -> 조회했을 때 조회수가 1
    *   - 위와 동일한 상품의 조회수 증가 -> 조회했을 때 조회수가 2
    *   - 없는 상품의 조회수를 증가시키면 결과가 0
    * */
   @Test
    public void increaseViewCnt() throws Exception {
       // 0. 모든 상품 삭제
       productDao.deleteAll();
       assertTrue(productDao.selectAll().size() == 0);

       // 1. 상품 1개를 추가한 후 해당 상품의 조회수 증가 -> 조회했을 때 조회수가 1
       // 1-1. 상품 추가
       ProductDto product = new ProductDto( "모자다~~");
       assertTrue(productDao.insert(product) == 1);
       assertTrue(productDao.selectAll().size() == 1);

       // 1-2. 증가 전 조회수 조회
       System.out.println( productDao.select(productDao.selectAll().get(0).getProdNo()));
       int beforeViewCnt = productDao.select(productDao.selectAll().get(0).getProdNo()).getViewCnt();

       // 1-3. 해당 상품의 조회수 증가시키기
       Integer targetProdNum = productDao.selectAll().get(0).getProdNo();
       assertTrue(productDao.increaseViewCnt(targetProdNum)==1);

       // 1-4. 증가 후 조회수를 조회해 이전 조회수에 +1 되었으면 성공
       int afterViewCnt = productDao.select(targetProdNum).getViewCnt();
       assertTrue(beforeViewCnt+1 == afterViewCnt);

       // 2. 위와 동일한 상품의 조회수 증가 -> 조회했을 때 조회수가 2
       assertTrue(productDao.increaseViewCnt(targetProdNum)==1);

       beforeViewCnt = afterViewCnt;
       afterViewCnt = productDao.select(targetProdNum).getViewCnt();
       assertTrue(beforeViewCnt+1 == afterViewCnt);


       // 3. 없는 상품의 조회수를 증가시키면 결과가 0
       // 3-1. 모든 상품 삭제
       productDao.deleteAll();
       assertTrue(productDao.selectAll().size() == 0);

       // 3-2. 없는 상품의 조회수 증가시키면 실패(결과 0)
       Integer notProdNum = 2131;
       assertTrue(productDao.select(notProdNum) == null );
       assertTrue( productDao.increaseViewCnt(notProdNum)==0);
   }

    /* [ 상품 판매량 증가 테스트 ]
     *   - 상품 1개를 추가한 후 해당 상품의 판매량 증가 -> 조회했을 때 판매량이 1 증가되어 있어야함
     *   - 위와 동일한 상품의 판매량 증가 -> 조회했을 때 1 증가되어 있어야함
     *   - 없는 상품의 판매량을 증가시키면 결과가 0
     * */


    /* [ 상품 리뷰 개수, 평균 별점 변경 테스트 ]
    *   - 상품 하나를 추가한 후 해당 상품의 리뷰 개수 및 평균 별점 변경 -> 다시 조회한 데이터가 변경한 데이터와 일치해야함
    *   - 없는 상품 번호로 변경할 경우 실패(결과가 0)
    * */
    /* [ 상품 리뷰 개수, 평균 별점 변경 예외 테스트 ]
    *   - 상품 리뷰 개수를 null로 변경할 경우 예외 발생
    *   - 평균 별점을 null로 변경할 경우 예외 발생
    *   - 평균 별점을 int 타입으로 변경할 경우 예외 발생?
    * */



    /* [ 상품 리뷰 개수, 평균 별점 변경 예외 테스트 ]
     *   - 상품 리뷰 개수를 null로 변경할 경우 예외 발생
     *   - 평균 별점을 null로 변경할 경우 예외 발생
     *   - 평균 별점을 int 타입으로 변경할 경우 예외 발생?
     * */


    /* [ 상품 삭제 테스트 ]
    *   - 상품을 한 개 추가한 후 해당 상품을 제거하면 결과가 1
    *   - 상품을 2개 추가한 후 첫번째 상품을 제거하면 결과가 1, 총 상품 개수 1
    *   - 없는 상품 번호의 데이터를 제거할 경우 실패(결과가 0)
    * */
    @Test
    public void delete() throws Exception{
        // 0. 모든 상품 삭제
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 1.  상품을 한 개 추가한 후 해당 상품을 제거하면 결과가 1
        // 1-1. 상품 추가
        assertTrue(productDao.insert(new ProductDto( "예쁜 모자"))==1);
        assertTrue(productDao.selectAll().size() == 1);

        // 1-2. 추가한 상품을 조회해 해당 상품 번호로 제거
        ProductDto selectedProduct = productDao.selectAll().get(0);
        assertTrue(productDao.delete(selectedProduct.getProdNo())==1);

        // 1-3. 모든 상품 조회했을 때 개수가 0인지 확인
        assertTrue(productDao.selectAll().size() == 0);


        // 2. 상품을 2개 추가한 후 첫번째 상품을 제거하면 결과가 1, 총 상품 개수 1
        // 2-1. 상품 2개 추가
        assertTrue(productDao.insert(new ProductDto( "안예쁜 모자"))==1);
        assertTrue(productDao.insert(new ProductDto("모자당"))==1);
        assertTrue(productDao.selectAll().size() == 2);

        // 2-2. 첫번째 상품을 조회해 해당 상품 번호로 제거
        selectedProduct = productDao.selectAll().get(0);
        assertTrue(productDao.delete(selectedProduct.getProdNo())==1);

        // 2-3. 모든 상품 조회했을 때 개수가 1인지 확인
        assertTrue(productDao.selectAll().size() == 1);


        // 3. 없는 상품 번호의 데이터를 제거할 경우 실패(결과가 0)
        // 3-1. 모든 상품 제거
        productDao.deleteAll();
        assertTrue(productDao.selectAll().size() == 0);

        // 3-2. 없는 상품 번호의 데이터 제거 -> 결과가 0이 나와야함
        assertTrue(productDao.delete(null) == 0);


    }


}