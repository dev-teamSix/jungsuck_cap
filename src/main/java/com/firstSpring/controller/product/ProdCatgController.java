package com.firstSpring.controller.product;


import com.firstSpring.domain.product.ProductCategoryDto;
import com.firstSpring.domain.product.ProductHighCategoryDto;
import com.firstSpring.service.product.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @PostMapping("/modify")
    public String modify(ProductCategoryDto prodCatgDto, RedirectAttributes rAttr, Model m) {
        // 원래 관리자 유효성 검사 해야함
        prodCatgDto.setFirstRegId("manager1");
        prodCatgDto.setLastModId("manager1");

        try {
            // 필수 데이터가 다 들어왔는지 확인
            if(!validation(prodCatgDto)) {
                throw new Exception("FAIL_VALID");
            }

            // 하위 카테고리 수정이라면 상위 카테고리가 유효한지 확인
            if(prodCatgDto.getHighCatgNo() != null) {
                Boolean isHighCatg = prodCatgService.isHighCatg(prodCatgDto.getHighCatgNo());
                if(!isHighCatg) {
                    throw new Exception("NOT_HIGH_CATG");
                }
            }

            // 이름 중복 여부 확인
            Boolean isDuplicatedName = prodCatgService.isDuplicatedName(prodCatgDto.getName(), prodCatgDto.getHighCatgNo());
            if(isDuplicatedName) {
                throw new Exception("DUP_NAME");
            }

            // 모든 유효성 검사가 끝나면 등록 서비스 실행
            if(prodCatgService.modify(prodCatgDto) != 1) {
                throw new Exception("ERR_MOD");
            }
            // 성공했다면 성공 메세지 전달
            rAttr.addFlashAttribute("msg", "OK_MOD");
        } catch (Exception e) {
            e.printStackTrace();
            // 실패하면 에러 메세지와 함께 등록 화면 보여주기
            m.addAttribute("msg", e.getMessage());
            m.addAttribute("category", prodCatgDto);
            return "category";
        }

        // 성공했다면 목록 페이지로 redirect
        return "redirect:/categorys";
    }


    @PostMapping
    public String register(ProductCategoryDto prodCatgDto, RedirectAttributes rAttr, Model m){
        // 원래 관리자 유효성 검사 해야함
        prodCatgDto.setFirstRegId("manager1");
        prodCatgDto.setLastModId("manager1");

        System.out.println(prodCatgDto);
        try {
            // 필수 데이터가 다 들어왔는지 확인
            if(!validation(prodCatgDto)) {
                throw new Exception("ERR_VALID");
            }

            // 하위 카테고리 등록이라면 상위 카테고리가 유효한지 확인
            if(prodCatgDto.getHighCatgNo() != null) {
                Boolean isHighCatg = prodCatgService.isHighCatg(prodCatgDto.getHighCatgNo());
                if(!isHighCatg) {
                    throw new Exception("ERR_HIGH_CATG");
                }
            }

            // 이름 중복 여부 확인
            Boolean isDuplicatedName = prodCatgService.isDuplicatedName(prodCatgDto.getName(), prodCatgDto.getHighCatgNo());
            if(isDuplicatedName) {
               throw new Exception("DUP_NAME");
            }

            // 모든 유효성 검사가 끝나면 등록 서비스 실행
            if(prodCatgService.register(prodCatgDto) != 1) {
                throw new Exception("ERR_REG");
            }
            // 성공했다면 성공 메세지 전달
            rAttr.addFlashAttribute("msg", "OK_REG");
        } catch (Exception e) {
            e.printStackTrace();
            // 실패하면 에러 메세지와 함께 등록 화면 보여주기
            rAttr.addAttribute("msg", e.getMessage());
            rAttr.addAttribute("category", prodCatgDto);
            return "redirect:/category/register";
        }

        // 성공했다면 목록 페이지로 redirect
        return "redirect:/categorys";
    }

    private boolean validation(ProductCategoryDto prodCatgDto) {
        return prodCatgDto.getName() != null && prodCatgDto.getIsDisp() != null && prodCatgDto.getIsUsed() != null;
    }


    @GetMapping("/register")
    public String register(Model m) throws Exception {
        // 상위 카테고리 정보 읽어와서 전달
        List<ProductCategoryDto> highCatgList = prodCatgService.getHighCatgList();
        m.addAttribute("highCatgList", highCatgList);

        // 등록 모드로 설정
        m.addAttribute("mode", "new");
        return "category";
    }

    @GetMapping
    public String getList(Model m) throws Exception{

        // 카테고리 목록 읽어서 전달
        List<ProductCategoryDto> categories = prodCatgService.getList();
        m.addAttribute("categories", categories);

        return "categorys";
    }

    @GetMapping("/{catgNo}")
    public String getCategory(@PathVariable Integer catgNo, Model m, RedirectAttributes rAttr) {
        // 유효하지 않는 번호일 경우 목록 페이지로 redirect

        try {
            if(catgNo == null || catgNo < 0) {
//                rAttr.addFlashAttribute("msg", "ERR_VALID");
//                return "redirect:/categorys";
                throw new Exception("ERR_VALID");
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
}
