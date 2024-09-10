package com.firstSpring.controller.board;

import com.firstSpring.domain.board.NoticeDto;
import com.firstSpring.domain.board.PageHandler;
import com.firstSpring.domain.board.QnaDto;
import com.firstSpring.domain.board.SearchCondition;
import com.firstSpring.domain.user.UserDto;
import com.firstSpring.service.board.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qna")
public class  QnaController {
    @Autowired
    QnaService qnaService;

    @GetMapping("/read")
    public String read(Integer bno, SearchCondition sc, Model model, HttpSession session) {
        System.out.println("read호출");
        try{
            QnaDto qnadto = qnaService.read(bno);
            model.addAttribute(qnadto);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "board/qna";
    }

    @PostMapping("/modify")
    public String modify(SearchCondition sc, Model model) {
        System.out.println("modify호출");
        return "board/qnaList";
    }

    @GetMapping("/write")
    public String write(SearchCondition sc, Model model, HttpServletRequest request) {
        System.out.println("write get호출");
        String id = ((UserDto) request.getSession().getAttribute("sessionUser")).getId();
        String writer = ((UserDto) request.getSession().getAttribute("sessionUser")).getName();
        String is_admin = ((UserDto) request.getSession().getAttribute("sessionUser")).getIsAdm();

        return "board/qnaWrite";
    }

    @PostMapping("/write")
    public String writer(SearchCondition sc, Model model) {
        System.out.println("write post호출");
        return "board/qnaList";
    }
    @GetMapping("/reply")
    public String reply(SearchCondition sc, Model model) {
        System.out.println("reply get호출");
        return "board/reply";
    }


    @PostMapping("/remove")
    public String remove(SearchCondition sc, Model model) {
        System.out.println("remove 호출");
        return "redirect:/board/qnaList";
    }
    @GetMapping("/list")
    public String list(SearchCondition sc, Model model, HttpSession session) {
        try{
            System.out.println("list 호출");
            System.out.println("SearchConditon:"+sc.toString());
            Integer page = sc.getPage();
            Integer pageSize = sc.getPageSize();
            String keyword = sc.getKeyword();
            String option_date = sc.getOption_date();
            String option_key = sc.getOption_key();

            System.out.println("page"+page);
            System.out.println("pageSize"+pageSize);
            System.out.println("keyword"+keyword);
            System.out.println("option_date"+option_date);
            System.out.println("option_key"+option_key);

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
