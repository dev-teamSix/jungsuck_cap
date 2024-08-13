package com.firstSpring.controller;

import com.firstSpring.domain.order.OrderDto;
import com.firstSpring.domain.order.OrderItemDto;
import com.firstSpring.entity.PageHandler;
import com.firstSpring.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    //    private boolean loginCheck(HttpServletRequest request) {
//            // 1. 세션을 얻어서
//            HttpSession session = request.getSession();
//            // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
//            return session.getAttribute("id")!=null;
//    }
//
//    HttpServletRequest request;
//    HttpSession session = request.getSession();
//    String cust_id = (String) session.getAttribute("id");

    @GetMapping("/tempItemPage")
    public String tempItemPage() {
        return "itemDtl";
    }

    @PostMapping("/ordering")
    public String ordering(OrderItemDto orderItemDto, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            // 현재 사용자
//            String cust_id = (String) request.getSession().getAttribute("id");
            String cust_id = "asdf";

            int success = orderService.order(orderItemDto, cust_id);
            System.out.println("success = " + success);

            if(success == 0) {
                throw new Exception("order error");
            }

            rattr.addFlashAttribute("msg", "ORDER_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "ORDER_ERR");
        }

        return "redirect:/order/list";
    }

    @GetMapping("/list")
    public String orderList(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
//        if(!loginCheck(request))
//            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
        // 회원 기능 주석처리
//        String cust_id = (String) request.getSession().getAttribute("id");
        String cust_id = "asdf";
        if(page == null) page = 1;
        if(pageSize == null) pageSize = 5;

        try {
            int totalCnt = orderService.getOrderCount(cust_id);
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

            Map map = new HashMap();
            map.put("cust_id", cust_id);
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);

            List<OrderDto> orders = orderService.getOrderPage(map);
            List<List<OrderItemDto>> orderItemsList = new ArrayList<>();
            for(OrderDto order : orders) {
                orderItemsList.add(orderService.getOrderItem(order.getOrd_no()));
            }
            m.addAttribute("orders", orders);
            m.addAttribute("orderItemsList", orderItemsList);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "orderList"; // 로그인을 한 상태이면, 주문이력 화면으로 이동
    }

    @PostMapping("/cancel")
    public String cancel(OrderDto orderDto, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            // 현재 사용자
//            String cust_id = (String) request.getSession().getAttribute("id");
            String cust_id = "asdf";

            orderDto.setCust_id(cust_id);

            int success = orderService.cancel(orderDto);

            if(success == 0) {
                throw new Exception("order cancel error");
            }

            rattr.addFlashAttribute("msg", "CANCEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "CANCEL_ERR");
        }

        return "redirect:/order/list";
    }
}
