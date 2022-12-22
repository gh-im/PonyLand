package PonyLand.PonyLand.controller;

import PonyLand.PonyLand.Mapper.MemberMapper;
import PonyLand.PonyLand.dto.AlbumCommentDTO;
import PonyLand.PonyLand.dto.AlbumDTO;
import PonyLand.PonyLand.dto.GuestbookCommentDTO;
import PonyLand.PonyLand.dto.MemberDTO;
import PonyLand.PonyLand.service.AlbumCommentService;
import PonyLand.PonyLand.service.AlbumService;
import PonyLand.PonyLand.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/Album/")
public class AlbumController {

    @Autowired
    private AlbumService service;
    @Autowired
    private AlbumCommentService albumCommentService;


    @Autowired
    private AlbumCommentService AlbumCommentService;

    @Autowired
    private HttpSession session;
    @Autowired
    private MemberService memberService;












//    }

    @RequestMapping("write")  //글쓰기로 가는부분
    public String write(Model model , String Album_host) {

//        model.addAttribute(session.getAttribute("sessionID").toString());
//        System.out.println(session);

        model.addAttribute("id",Album_host);
        return "albumwrite";
    }

    @RequestMapping("insert")   //게시글 입력
    public String insert(AlbumDTO dto, MultipartFile file) {
        try {
            System.out.println(dto.getAlbum_contents() + ":" + dto.getAlbum_title());

            String Album_writer = (String)session.getAttribute("sessionID");
            dto.setAlbum_writer(Album_writer);
            String realPath = session.getServletContext().getRealPath("load");
            System.out.println(realPath);
            File filePath = new File(realPath); //객체생성
                    if(!filePath.exists()) {
                filePath.mkdir();      //파일업로드 폴더가 없으면 생성.
            }
            String Album_oriName  = file.getOriginalFilename();
            dto.setAlbum_oriname(Album_oriName);  //dto에 사진을담음

            String Album_sysName = UUID.randomUUID() + "_" + Album_oriName;
            dto.setAlbum_sysname(Album_sysName); //dto에 사진을담음

            //			현재시간과 겹치지않는 문자열을 자동생성
            Album_oriName= new String(Album_oriName.getBytes("utf8"),"ISO-8859-1");
            file.transferTo(new File(filePath+"/"+Album_sysName));
            service.insert(dto);

        } catch (Exception e) {
            return "error";
        }

        return "redirect:toAlbumpage";
    }

    @RequestMapping("view")
    public String SelectAll(Model model) {
        List<AlbumDTO> list = service.selectAll();

        model.addAttribute("dto", list);


        return "album";
    }


    @RequestMapping("delete")  //삭제
    public String delete(int Album_seq) {
        service.delete(Album_seq);

        return "redirect:toAlbumPage";
    }

    @RequestMapping("update") //수정
    public String update(String Album_title, String Album_contents, int Album_seq, Model model) {
        service.update(Album_title, Album_contents, Album_seq);
//       수정.
//        List<AlbumDTO> list= service.selectAll();
//        model.addAttribute("date",list);
        return "redirect:toAlbumPage";

    }

//사진첩으로 가는길 ,
    @RequestMapping("toAlbumPage")  //전체 목록 뷰
    public String goGuestbook(Model model,String Album_host){
        List<AlbumDTO> list = service.selectAll();
        MemberDTO dto = memberService.findById(Album_host);
        List<AlbumCommentDTO> list1 = albumCommentService.selectComment();  //list로 묶은 댓글목록들을 여기서 가져온다.
        model.addAttribute("dto", list);
//세션담아서 가줌.
        model.addAttribute("id",Album_host);
        model.addAttribute("list1",list1);
        model.addAttribute("list",dto);





        return "album";
    }

    @RequestMapping("UpdateAlbum")
    public String UpdateAlbum(Model model) {
        List<AlbumDTO> list= service.selectAll();
        model.addAttribute("dto",list);
        return "albumupdate";
    }
}
