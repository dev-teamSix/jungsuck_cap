package com.firstSpring.controller.order;

import com.firstSpring.domain.order.CartItemDto;
import com.firstSpring.domain.order.OrderItemDto;
import com.firstSpring.service.order.CartService;
import com.firstSpring.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }

//    HttpServletRequest request;
//    HttpSession session = request.getSession();
//    String cust_id = (String) session.getAttribute("id");

    // 회원가입할 때 cart가 생긴다
    @GetMapping("/tempSignUpPage")
    public String tempCartPage() {
        return "signUp";
    }

    @PostMapping("/createCart")
    public String createCart() throws Exception {
        // 현재 사용자
//            String cust_id = (String) session.getAttribute("id");
        String cust_id = "example";
        cartService.insertCart(cust_id);
        return "redirect:/order/tempItemPage";
    }

    @GetMapping("/cartList")
    public String cartList(Model m, HttpServletRequest request) {
    //        if(!loginCheck(request))
//            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
        // 회원 기능 주석처리
//        String cust_id = (String) request.getSession().getAttribute("id");
        String cust_id = "asdf";

        try {
            Integer cart_no = cartService.getCartNo(cust_id);
            List<CartItemDto> cartItemList = cartService.getCartItem(cart_no);
            Integer maxOrdNo = orderService.maxOrdNo() + 1;
            m.addAttribute("cartItemList", cartItemList);
            m.addAttribute("maxOrdNo", maxOrdNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "cartList";
    }

    @PostMapping("/insertCartItem")
    public String insertCartItem(CartItemDto cartItemDto, HttpServletRequest request, HttpSession session, RedirectAttributes rattr) {
        try {
//            if(!loginCheck(request))
//            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

            // 현재 사용자
//            String cust_id = (String) session.getAttribute("id");
//            String cust_id = (String) request.getSession().getAttribute("id");
            String cust_id = "asdf";

            int success = 0;
            boolean notUpdate = true;
            Integer cart_no = cartService.getCartNo(cust_id);
            List<CartItemDto> cartItemList = cartService.getCartItem(cart_no);
            for(CartItemDto item : cartItemList) {
                Integer prod_num_cart = item.getProd_num();
                Integer prod_num_in = cartItemDto.getProd_num();
                if (prod_num_cart == prod_num_in) {
                    Integer qty_cart = item.getQty();
                    Integer qty_in = cartItemDto.getQty();
                    Integer qty_new = qty_cart + qty_in;
                    cartItemDto.setQty(qty_new);
                    success = cartService.updateQty(cust_id, cartItemDto);
                    notUpdate = false;
                    break;
                }
            }

            if(notUpdate)
                success = cartService.insertCartItem(cust_id, cartItemDto);

            if(success == 0) {
                throw new Exception("insert cart error");
            }

            rattr.addFlashAttribute("msg", "CART_IN_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "CART_IN_ERR");
        }

        return "redirect:/cart/cartList";
    }

    @PostMapping("/deleteCartItem")
    public String deleteCartItem(CartItemDto cartItemDto, HttpSession session, RedirectAttributes rattr) {
        try {
            // 현재 사용자
//            String cust_id = (String) session.getAttribute("id");
            String cust_id = "asdf";
            Integer cart_no = cartService.getCartNo(cust_id);
            Integer prod_num = cartItemDto.getProd_num();

            Map map = new HashMap();
            map.put("prod_num", prod_num);
            map.put("cart_no", cart_no);
            int success = cartService.deleteCartItem(map);

            if(success == 0) {
                throw new Exception("delete cart error");
            }

            rattr.addFlashAttribute("msg", "CART_DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "CART_DEL_ERR");
        }

        return "redirect:/cart/cartList";
    }

    @PostMapping("/updateCartItemQty")
    public String updateCartItemQty(CartItemDto cartItemDto, HttpSession session, RedirectAttributes rattr) {
        try {
            // 현재 사용자
//            String cust_id = (String) session.getAttribute("id");
            String cust_id = "asdf";

            int success = 0;
            Integer cart_no = cartService.getCartNo(cust_id);
            List<CartItemDto> cartItemList = cartService.getCartItem(cart_no);
            for(CartItemDto item : cartItemList) {
                Integer prod_num_cart = item.getProd_num();
                Integer prod_num_in = cartItemDto.getProd_num();
                if (prod_num_cart == prod_num_in) {
                    success = cartService.updateQty(cust_id, cartItemDto);
                    break;
                }
            }

            success = cartService.updateQty(cust_id, cartItemDto);

            if(success == 0) {
                throw new Exception("update qty error");
            }

            rattr.addFlashAttribute("msg", "CART_QTY_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "CART_QTY_ERR");
        }

        return "redirect:/cart/cartList";
    }

    @PostMapping("/ordering")
    public String cartOrdering(@RequestBody CartItemDto cartItemDto, HttpServletRequest request, HttpSession session, RedirectAttributes rattr) {
        try {
//            if(!loginCheck(request))
//            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

            // 현재 사용자
//            String cust_id = (String) session.getAttribute("id");
//            String cust_id = (String) request.getSession().getAttribute("id");
            String cust_id = "asdf";
            System.out.println("cartItemDto.getFrom_cart() = " + cartItemDto.getFrom_cart());
            System.out.println("cartItemDto.getProd_name() = " + cartItemDto.getProd_name());
            System.out.println("cartItemDto.getPrice() = " + cartItemDto.getPrice());

            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProd_num(cartItemDto.getProd_num());
            orderItemDto.setQty(cartItemDto.getQty());
            orderItemDto.setProd_name(cartItemDto.getProd_name());
            orderItemDto.setPrice(cartItemDto.getPrice());
            orderItemDto.setFrom_cart(cartItemDto.getFrom_cart());
            int success = orderService.order(orderItemDto, cust_id);

            if(success == 0) {
                throw new Exception("cart order error");
            }

            rattr.addFlashAttribute("msg", "CART_ORDER_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "CART_ORDER_ERR");
        }

        return "redirect:/order/list";
    }
}
