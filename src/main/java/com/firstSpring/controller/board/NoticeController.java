package com.firstSpring.controller.board;

import com.firstSpring.domain.board.NoticeDto;
import com.firstSpring.domain.board.PageHandler;
import com.firstSpring.domain.board.SearchCondition;
import com.firstSpring.service.board.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
    public class NoticeController {

    @Autowired
    NoticeService noticeService;

    //read 게시판 읽기
    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, String option_date, String option_key, String keyword, RedirectAttributes rattr, Model m) {
        try {
            NoticeDto noticeDto = noticeService.read(bno);
            m.addAttribute(noticeDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
            m.addAttribute("option_date", option_date);
            m.addAttribute("option_key", option_key);
            m.addAttribute("keyword", keyword);
        } catch (Exception e) {
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);
            rattr.addAttribute("option_date", option_date);
            rattr.addAttribute("option_key", option_key);
            rattr.addAttribute("keyword", keyword);
            return "redirect:/notice/list";
        }
        return "board/notice";
    }

    //modify 게시판 수정
    @PostMapping("/modify")
    public String modify(NoticeDto noticeDto, RedirectAttributes rattr, Model m, HttpSession session) {
        System.out.println("modify 호출");
        String id = (String) session.getAttribute("id");
        String writer = (String) session.getAttribute("writer");

        if(id==null || id.equals("")) {
            id="test";
            writer = noticeDto.getWriter();
            System.out.println("writer:"+writer);
            System.out.println("noticeDto:"+noticeDto);
        }
        try{
            if(noticeService.modify(noticeDto)!=1){
                throw new Exception("modify failed");
            }
            rattr.addFlashAttribute("msg","MOD_OK");
            return "redirect:/notice/list";

        }catch(Exception e){
            e.printStackTrace();
            m.addAttribute(noticeDto);
            m.addAttribute("msg", "MOD_ERROR");
            return "board/noticeList";
        }
    }

    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");
        return "board/noticeWrite";
    }

    @PostMapping("/write")
    //write 게시판 작성
    public String write(@RequestParam("is_notice") char is_notice, NoticeDto noticeDto, RedirectAttributes rattr, Model m, HttpSession session) {
        System.out.println("wirte: post 호출");
        String id = (String) session.getAttribute("id");
        String writer = (String) session.getAttribute("writer");

        if(id==null || id.equals("")) {
            id="test";
            writer="test";
        }
        noticeDto.setId(id);
        noticeDto.setWriter(writer);
        System.out.println("noticeDto:"+noticeDto);
        try{
            if(noticeService.write(noticeDto)!= 1){
                throw new Exception("write failed");
            }
            rattr.addFlashAttribute("msg","WRT_OK");
            return "redirect:/notice/list";
        }catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(noticeDto);
            m.addAttribute("msg", "WRT_ERROR");
            return "board/noticeList";
        }

    }
    //remove 게시판 삭제
    @PostMapping("/remove")
    public String remove(NoticeDto noticeDto, Integer bno, Integer page, Integer pageSize, RedirectAttributes rattr, HttpSession session){
        System.out.println("remove 호출");
        String id = (String) session.getAttribute("id");
        String writer = (String) session.getAttribute("writer");

        if(id==null || id.equals("")) {
            id="test";
            writer = noticeDto.getWriter();
            System.out.println("writer:"+writer);
            System.out.println("noticeDto:"+noticeDto);
        }

        String msg ="DEL_OK";
        rattr.addFlashAttribute("msg", msg);

        try{
            if(noticeService.remove(bno,writer)!=1)
                throw new Exception("Delete failed");
        }catch (Exception e){
            e.printStackTrace();
            msg = "DEL_ERR";
        }


        return "redirect:/notice/list";
    }
    //게시판 목록 조회
    @GetMapping("/list")
    public String list(SearchCondition sc, Model model, HttpSession session){
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

            int totalCnt = noticeService.getCount(map);
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
            List<NoticeDto> list = noticeService.getPage(map);
            List<NoticeDto> noticeList = noticeService.getNoticeList();
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
        return "board/noticeList";
    }
    //관리자 유무 체크


}
