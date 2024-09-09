package com.firstSpring.controller.product;

import com.firstSpring.domain.product.ResponseDto;
import com.firstSpring.domain.product.SearchCondition;
import com.firstSpring.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProdRestController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseDto> getList(SearchCondition sc) {
        try {
            return new ResponseEntity<>(
                    new ResponseDto(
                            "조회 성공!",
                            "/product/read?prodNo=",
                            productService.getSearchPage(sc)
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new ResponseDto(
                            e.getMessage(),
                            null,
                            null
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
