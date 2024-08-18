package com.firstSpring.controller.board;

import com.firstSpring.domain.board.NoticeDto;
import com.firstSpring.domain.board.PageHandler;
import com.firstSpring.domain.board.QnaDto;
import com.firstSpring.domain.board.SearchCondition;
import com.firstSpring.service.board.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qna")
public class QnaController {
    @Autowired
    QnaService qnaService;

    @GetMapping("/read")
    public String read(SearchCondition sc, Model model, HttpSession session) {
        System.out.println("read호출");
        return "board/qna";
    }

    @PostMapping("/modify")
    public String modify(SearchCondition sc, Model model) {
        System.out.println("modify호출");
        return "board/qnaList";
    }

    @GetMapping("/write")
    public String write(SearchCondition sc, Model model) {
        System.out.println("write get호출");

        return "board/qnaWrite";
    }

    @PostMapping("/writer")
    public String writer(SearchCondition sc, Model model) {
        System.out.println("write post호출");
        return "board/qnaList";
    }
    @PostMapping("/remove")
    public String remove(SearchCondition sc, Model model) {
        System.out.println("remove 호출");
        return "redirect:/board/qnaList";
    }
    @GetMapping("/list")
    public String list(SearchCondition sc, Model model, HttpSession session) {
        try{
            Integer page = sc.getPage();
            Integer pageSize = sc.getPageSize();
            String keyword = sc.getKeyword();
            String option_date = sc.getOption_date();
            String option_key = sc.getOption_key();

            Map map = new HashMap();
            map.put("offset",(page-1)*pageSize);
            map.put("pageSize",pageSize);
            map.put("keyword",sc.getKeyword());
            map.put("option_date",sc.getOption_date());
            map.put("option_key",sc.getOption_key());
            int totalCnt = qnaService.getCount(map);
            System.out.println("map:"+map);

            model.addAttribute("totalCnt",totalCnt);
            System.out.println("totalCnt2="+totalCnt);
            System.out.println("Sc:"+sc);
            System.out.println("page:"+page);
            System.out.println("pageSize:"+pageSize);

            PageHandler pageHandler = new PageHandler(totalCnt,sc);

            System.out.println("ph="+pageHandler);

            if(page < 0 || page > pageHandler.getTotalPage()){
                page = 1;
            }
            if(pageSize < 0 || pageSize > 50){
                pageSize = 10;
            }
            System.out.println("ph2="+pageHandler);
            System.out.println("map="+map);
            List<QnaDto> list = qnaService.getQnaList(map);
            List<QnaDto> noticeList = qnaService.getNoticeList();
            Integer totalNoticeCnt = qnaService.getNoticeCount();
            model.addAttribute("totalNoticeCnt",totalNoticeCnt);
            model.addAttribute("noticeList",noticeList);
            model.addAttribute("list",list);
            model.addAttribute("ph",pageHandler);
            model.addAttribute("sc",sc);

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg","LIST_ERR");
            model.addAttribute("totalCnt",0);
            System.out.println("Exception:"+e.getMessage());
        }
        return "board/qnaList";
    }
}
