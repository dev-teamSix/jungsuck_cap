package com.firstSpring.controller.product;

import com.firstSpring.domain.product.*;
import com.firstSpring.entity.PageHandler;
import com.firstSpring.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 상품 목록 페이지 조회
    @GetMapping("/list")
    public String getList(SearchCondition sc,  Model m) {

        try {
            // 상품 수 전달
            int totalCnt = productService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            // 페이징 정보 읽어서 전달
            PageHandler ph = new PageHandler(totalCnt, sc.getPageNo(), sc.getPageSize());
            m.addAttribute("ph", ph);

            // 상품 목록 조회 서비스 로직 수행 -> 조회한 목록 모델로 전달
            List<ProductListDto> productList = productService.getSearchPage(sc);
            m.addAttribute("productList", productList);
            m.addAttribute("sc", sc);
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return "/productList";
    }

    // 상품 상세 조회
    @GetMapping("/read")
    public String read(Integer prodNo, SearchCondition sc,  Model m) throws Exception {

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
            return "redirect:/product/list"+sc.getQueryString();
        }
    }

    /* 상품 관리 - 상세 조회 */
    @GetMapping("/admin/read")
    public String adminRead(Integer prodNo, SearchCondition sc, Model m, RedirectAttributes rattr) {
        try {
            // prodNo(상품번호) null 체크
            if(prodNo == null) {
                throw new Exception("상품 번호가 필요합니다.");
            }

            // 상품 상세 정보 조회
            ProductDto productDto = productService.read(prodNo);
            m.addAttribute("product", productDto);

            System.out.println(productDto);
            return "/productForm";

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/product/list"+sc.getQueryString();
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
            return "redirect:/product/list";
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
    public String modify(SearchCondition sc, @ModelAttribute ProductDto productDto, Model m, RedirectAttributes rattr) {
        try {
            // 필수 데이터 입력
            productDto.setLastModId("manager2");
            System.out.println(productDto);
            // 유효성 검사
            if(!isExisted(productDto)) {
                throw new Exception("필수 정보가 입력되지 않았습니다.");
            }

            // 상품 정보 수정
            productService.modifyInfo(productDto);

            // 수정 성공 시 상품 목록 페이지로 redirect
            rattr.addFlashAttribute("msg", "상품 수정에 성공 했습니다!");
            return "redirect:/product/list"+sc.getQueryString();
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
    public String remove(Integer prodNo, SearchCondition sc, RedirectAttributes rattr) {
        try {

            if(prodNo == null) {
                throw new Exception("삭제하고자 하는 상품 번호를 입력해주세요");
            }

            // 상품 제거
            productService.remove(prodNo);

            // 상품 제거 성공시 목록으로 redirect
            rattr.addFlashAttribute("msg", "상품 삭제에 성공했습니다!");
            return "redirect:/product/list"+sc.getQueryString();

        } catch(Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/product/admin/read?prodNo="+prodNo;
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