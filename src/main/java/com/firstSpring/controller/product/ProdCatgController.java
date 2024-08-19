package com.firstSpring.controller.product;


import com.firstSpring.domain.product.ProductCategoryDto;
import com.firstSpring.service.product.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categorys")
public class ProdCatgController {
    @Autowired
    ProductCategoryService prodCatgService;

    @ExceptionHandler(Exception.class)
    public String exception(Model m, Exception e) {
        e.printStackTrace();
        m.addAttribute("msg", e.getMessage());
        return "productError";
    }

    /* 카테고리 수정 */
    @PostMapping("/modify")
    public String modify(ProductCategoryDto prodCatgDto, RedirectAttributes rAttr, Model m) {
        try {
            // 최초 등록자&수정자 세팅
            prodCatgDto.setFirstRegId("manager1");
            prodCatgDto.setLastModId("manager1");

            // 필수 데이터가 있는지 확인
            if(!isCheckExist(prodCatgDto)) {
                throw new Exception("NOT_EXIST");
            }

            // 수정 서비스 실행
            if(prodCatgService.modify(prodCatgDto)) {
                // 성공했다면 성공 메세지 전달 후 목록 페이지로 redirect
                rAttr.addFlashAttribute("msg", "OK_MOD");
                return "redirect:/categorys";
            } else {
                throw new Exception("ERR_MOD");
            }


        } catch (Exception e) {
            e.printStackTrace();
            // 실패하면 에러 메세지와 함께 등록 화면 보여주기
            rAttr.addFlashAttribute("msg", e.getMessage());
            rAttr.addFlashAttribute("category", prodCatgDto);
            return "redirect:/categorys/read?catgNo="+prodCatgDto.getCatgNo();
        }
    }

    /* 카테고리 등록 */
    @GetMapping("/register")
    public String register(Model m) throws Exception {
        // 상위 카테고리 정보 읽어와서 전달
        List<ProductCategoryDto> highCatgList = prodCatgService.getHighCatgList();
        m.addAttribute("highCatgList", highCatgList);

        // 등록 모드로 설정
        m.addAttribute("mode", "new");
        return "category";
    }

    @PostMapping("/register")
    public String register(ProductCategoryDto prodCatgDto, RedirectAttributes rAttr, Model m){
        try {
            // 원래 관리자 유효성 검사 해야함
            prodCatgDto.setFirstRegId("manager1");
            prodCatgDto.setLastModId("manager1");

            // 필수 데이터가 있는지 확인
            if(!isCheckExist(prodCatgDto)) {
                throw new Exception("NOT_EXIST");
            }

            // 등록 서비스 실행
            if(prodCatgService.register(prodCatgDto)) {
                // 성공했다면 성공 메세지 전달 후 목록 페이지로 redirect
                rAttr.addFlashAttribute("msg", "OK_REG");
                return "redirect:/categorys";
            } else {
                throw new Exception("ERR_REG");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 실패하면 에러 메세지와 함께 등록 화면 보여주기
            rAttr.addFlashAttribute("msg", e.getMessage());
            rAttr.addFlashAttribute("category", prodCatgDto);
            return "redirect:/categorys/register";
        }


    }

    /* 카테고리 목록 조회 */
    @GetMapping
    public String getList(Model m) throws Exception{

        // 카테고리 목록 읽어서 전달
        List<ProductCategoryDto> categories = prodCatgService.getList();
        m.addAttribute("categories", categories);

        return "categorys";
    }


    /* 카테고리 상세 조회 */
    @GetMapping("/read")
    public String getCategory(Integer catgNo, Model m, RedirectAttributes rAttr) {
        try {
            if(catgNo == null || catgNo < 0) {
                throw new Exception("NOT_EXIST");
            }

            // 카테고리 번호로 데이터 읽어와 전달
            ProductCategoryDto category = prodCatgService.read(catgNo);
            m.addAttribute("category", category);

            // 상위 카테고리 리스트 읽어와 전달
            List<ProductCategoryDto> highCatgList = prodCatgService.getHighCatgList();
            m.addAttribute("highCatgList", highCatgList);
            return "category";
        } catch (Exception e) {
            e.printStackTrace();
            rAttr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/categorys";
        }
    }

    /* 필수 데이터 존재 유무 체크 */
    private boolean isCheckExist(ProductCategoryDto prodCatgDto) {
        // 필수 데이터가 다 들어왔는지 확인
        return prodCatgDto.getName() != null && prodCatgDto.getIsDisp() != null && prodCatgDto.getIsUsed() != null;

    }
}