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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        try {
            // prodNo(상품번호) null 체크
            if(prodNo == null) {
                throw new Exception("ERR_VALID");
            }

            // 상품 상세 정보(상품 기본 정보+ 옵션 목록 + 메인 이미지 ) 조회 결과 전달
            ProductRequest pr = productService.readDetail(prodNo);
            m.addAttribute("pr", pr);

            return "/product";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/products";
        }
    }

    /* 상품 관리 - 상세 조회 */
    @GetMapping("/admin/read")
    public String adminRead(Integer prodNo, Model m, RedirectAttributes rattr) {
        try {
            // prodNo(상품번호) null 체크
            if(prodNo == null) {
                throw new Exception("상품 번호가 필요합니다.");
            }

            // 상품 상세 정보 조회
            ProductDto productDto = productService.read(prodNo);
            m.addAttribute("product", productDto);

            return "/productForm";

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/products";
        }
    }

    /* 상품 등록 */
    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("mode", "new");
        return "/productForm";
    }

    @PostMapping("/register")
    public String register(ProductDto productDto, Model m, RedirectAttributes rattr) {
        try {
            // 등록 식별자번호 세팅
            productDto.setFirstRegId("manager1");
            productDto.setLastModId("manager1");

            // 상품 정보 유효성 검사
            if(!isExisted(productDto)) {
                throw new Exception("필수 정보가 입력되지 않았습니다.");
            }

            // 상품 등록
            productService.register(productDto);

            // 등록 성공 시 상품 목록 페이지로 redirect
            rattr.addFlashAttribute("msg", "상품 등록에 성공 했습니다!");
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();

            // 등록 실패시 폼 화면으로 다시 이동
            m.addAttribute("product", productDto);
            m.addAttribute("msg", e.getMessage());
            return "/productForm";
        }
    }

    /* 상품 변경 */
    @PostMapping("/modify")
    public String modify(ProductDto productDto, Model m, RedirectAttributes rattr) {
        try {
            // 필수 데이터 입력
            productDto.setLastModId("manager2");

            // 유효성 검사
            if(!isExisted(productDto)) {
                throw new Exception("필수 정보가 입력되지 않았습니다.");
            }

            // 상품 정보 수정
            productService.modifyInfo(productDto);

            // 수정 성공 시 상품 목록 페이지로 redirect
            rattr.addFlashAttribute("msg", "상품 수정에 성공 했습니다!");
            return "redirect:/products";
        }catch(Exception e) {
            e.printStackTrace();
            // 수정 실패시 에러 메세지 전달하며 폼 화면으로 다시 이동
            m.addAttribute("product", productDto);
            m.addAttribute("msg", e.getMessage());
            return "/productForm";
        }
    }

    /* 상품 제거 */
    @PostMapping("/remove")
    public String remove(Integer prodNo, RedirectAttributes rattr) {
        try {

            if(prodNo == null) {
                throw new Exception("삭제하고자 하는 상품 번호를 입력해주세요");
            }

            // 상품 제거
            productService.remove(prodNo);

            // 상품 제거 성공시 목록으로 redirect
            rattr.addFlashAttribute("msg", "상품 삭제에 성공했습니다!");
            return "redirect:/products";

        } catch(Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/products/admin/read?prodNo="+prodNo;
        }
    }

    /* 필수 데이터 유무 체크 */
    private boolean isExisted(ProductDto productDto) {
        System.out.println(productDto);
        return productDto.getName() != null
                && productDto.getPrice() != null
                && productDto.getBarc() != null
                && productDto.getProdCatgNo() !=null;
    }
}