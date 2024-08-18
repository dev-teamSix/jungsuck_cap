package com.firstSpring.controller.product;

import com.firstSpring.domain.product.ProductColorDto;
import com.firstSpring.domain.product.ProductDto;
import com.firstSpring.domain.product.ProductListDto;
import com.firstSpring.domain.product.ProductRequest;
import com.firstSpring.entity.PageHandler;
import com.firstSpring.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 상품 목록 페이지 조회
    @GetMapping
    public String getList(Integer pageSize, Integer pageNo, Integer prodCatgNo, String sortBy, Model m) {
        // 페이징 정보 입력값 확인
        if(pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        if(pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }

        // 정렬 기준 값 확인
        if(sortBy == null || "".equals(sortBy)) {
            sortBy = "firstRegDt";
        }

        // 카테고리 번호, 정렬 기준 세팅
        Map cond = new HashMap();
        cond.put("prodCatgNo", prodCatgNo);
        cond.put("sortBy", sortBy);
        m.addAttribute("prodCatgNo", prodCatgNo); // 카테고리 번호 모델에 전달
        m.addAttribute("sortBy", sortBy); // 정렬 기준 모델에 전달

        try {
            // 상품 수 전달
            int totalCnt = productService.getList(cond).size();
            m.addAttribute("totalCnt", totalCnt);

            // 페이징 정보 읽어서 전달
            PageHandler ph = new PageHandler(totalCnt, pageNo, pageSize);
            cond.put("offset", ph.getOffset());
            cond.put("rowCnt", ph.getPageSize());

            m.addAttribute("ph", ph);

            // 상품 목록 조회 서비스 로직 수행 -> 조회한 목록 모델로 전달
//            List<ProductDto> productList = productService.getPage(cond);
            List<ProductListDto> productList = productService.getPageWithJoin(cond);
            m.addAttribute("productList", productList);

        } catch (Exception e ) {
            e.printStackTrace();
        }

        return "/products";
    }

    // 상품 상세 조회
    @GetMapping("/read")
    public String read(Integer prodNo, Model m) throws Exception {
        // prodNo(상품번호) null 체크
        if(prodNo == null) {
            throw new Exception("ERR_VALID");
        }


        try {
            // 상품 상세 정보(상품 기본 정보+ 옵션 목록 + 메인 이미지 ) 조회 결과 전달
            ProductRequest pr = productService.readDetail(prodNo);
            m.addAttribute("pr", pr);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/product";
    }


}
