package com.firstSpring.controller.order;

import com.firstSpring.domain.order.ActionCartOrderDto;
import com.firstSpring.domain.order.CartItemDto;
import com.firstSpring.domain.order.OrderItemDto;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.service.order.CartService;
import com.firstSpring.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return session.getAttribute("sessionUser")!=null;
    }

//    HttpServletRequest request;
//    HttpSession session = request.getSession();
//    String cust_id = (String) session.getAttribute("id");

//    // 회원가입할 때 cart가 생긴다
//    @GetMapping("/tempSignUpPage")
//    public String tempCartPage() {
//        return "signUp";
//    }
//
//    @PostMapping("/createCart")
//    public String createCart() throws Exception {
//        // 현재 사용자
////            String cust_id = (String) session.getAttribute("id");
//        String cust_id = "example";
//        cartService.insertCart(cust_id);
//        return "redirect:/";
//    }

    @GetMapping("/cartList")
    public String cartList(Model m, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/form";  // 로그인을 안했으면 로그인 화면으로 이동

        // 회원 기능 주석처리
        String cust_id = ((UserDto) request.getSession().getAttribute("sessionUser")).getId();
//        String cust_id = "asdf";

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
            if (!loginCheck(request))
                return "redirect:/login/form";  // 로그인을 안했으면 로그인 화면으로 이동

            // 현재 사용자
//            String cust_id = (String) session.getAttribute("id");
            String cust_id = ((UserDto) request.getSession().getAttribute("sessionUser")).getId();
//            String cust_id = "asdf";

            Integer col_no_in = Integer.valueOf(request.getParameter("color"));
            cartItemDto.setCol_no(col_no_in);

            int success = 0;
            boolean notUpdate = true;
            Integer cart_no = cartService.getCartNo(cust_id);
            List<CartItemDto> cartItemList = cartService.getCartItem(cart_no);
            Integer prod_no_in = cartItemDto.getProd_no();
            for(CartItemDto item : cartItemList) {
                Integer prod_no_cart = item.getProd_no();
                Integer col_no_cart = item.getCol_no();
                if (prod_no_cart == prod_no_in && col_no_cart == col_no_in) {
                    Integer qty_cart = item.getQty();
                    Integer qty_in = cartItemDto.getQty();
                    Integer qty_new = qty_cart + qty_in;
                    cartItemDto.setQty(qty_new);
                    success = cartService.updateQty(cust_id, cartItemDto);
                    notUpdate = false;
                    break;
                }
            }

            System.out.println("notUpdate = " + notUpdate);

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
            String cust_id = ((UserDto) session.getAttribute("sessionUser")).getId();
//            String cust_id = "asdf";
            Integer cart_no = cartService.getCartNo(cust_id);
            Integer prod_no = cartItemDto.getProd_no();
            Integer col_no = cartItemDto.getCol_no();

            Map map = new HashMap();
            map.put("prod_no", prod_no);
            map.put("cart_no", cart_no);
            map.put("col_no", col_no);
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
            String cust_id = ((UserDto) session.getAttribute("sessionUser")).getId();
//            String cust_id = "asdf";

            int success = 0;
            Integer cart_no_in = cartService.getCartNo(cust_id);
            Integer prod_no_in = cartItemDto.getProd_no();
            Integer col_no_in = cartItemDto.getCol_no();

            List<CartItemDto> cartItemList = cartService.getCartItem(cart_no_in);
            for(CartItemDto item : cartItemList) {
                Integer prod_no_db = item.getProd_no();
                Integer col_no_db = item.getCol_no();
                if (prod_no_db == prod_no_in && col_no_db == col_no_in) {
                    success = cartService.updateQty(cust_id, cartItemDto);
                    break;
                }
            }

//            success = cartService.updateQty(cust_id, cartItemDto);

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
    @ResponseBody
    public ResponseEntity<String> cartOrdering(@RequestBody ActionCartOrderDto actionCartOrderDto, HttpServletRequest request, HttpSession session) {
        try {
            if(!loginCheck(request))
                return new ResponseEntity<String>("주문 권한이 없습니다.", HttpStatus.UNAUTHORIZED);
//                return "redirect:/login/in";  // 로그인을 안했으면 로그인 화면으로 이동

            List<CartItemDto> cartItemDtoList = actionCartOrderDto.getCartItemDtoList();

            // 현재 사용자
//            String cust_id = (String) session.getAttribute("id");
            String cust_id = ((UserDto) request.getSession().getAttribute("sessionUser")).getId();
//            String cust_id = "asdf";
            Integer cart_no = cartService.getCartNo(cust_id);

            for (CartItemDto cartItemDto : cartItemDtoList) {
                cartItemDto.setCart_no(cart_no);

                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setProd_no(cartItemDto.getProd_no());
                orderItemDto.setCol_no(cartItemDto.getCol_no());
                orderItemDto.setQty(cartItemDto.getQty());
                orderItemDto.setProd_name(cartItemDto.getProd_name());
                orderItemDto.setPrice(cartItemDto.getPrice());
                orderItemDto.setCol_name(cartItemDto.getCol_name());
                orderItemDto.setFrom_cart(cartItemDto.getFrom_cart());
                int success = orderService.order(orderItemDto, cust_id);
                System.out.println("cartItemDto.getFrom_cart() = " + cartItemDto.getFrom_cart());
                System.out.println("cartItemDto.getCol_name() = " + cartItemDto.getCol_name());
                if(success == 0) {
                    throw new Exception("cart order error");
                }

                Integer prod_no = cartItemDto.getProd_no();
                Integer col_no = cartItemDto.getCol_no();

                Map map = new HashMap();
                map.put("prod_no", prod_no);
                map.put("cart_no", cart_no);
                map.put("col_no", col_no);
                cartService.deleteCartItem(map);
            }

            return new ResponseEntity<String>("CART_ORDER_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("CART_ORDER_ERR", HttpStatus.BAD_REQUEST);
        }
    }
}
