package PonyLand.PonyLand.controller;

import PonyLand.PonyLand.dto.AlbumCommentDTO;
import PonyLand.PonyLand.service.AlbumCommentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Comment/")
public class AlbumCommentController {

    @Autowired
    private AlbumCommentService service;
    @Autowired
    private Gson g;

    @Autowired
    private HttpSession session;

    @ResponseBody
    @RequestMapping("writer")  //댓글 입력부분
    public String write(String Album_seq,String Album_Comment_contents) {

        String Album_Comment_writer = (String) session.getAttribute("sessionID"); //세션값.
        System.out.println("1번 : " + Album_seq + " : " + Album_Comment_contents);
        AlbumCommentDTO dto = new AlbumCommentDTO();
        dto.setAlbum_Comment_writer(Album_Comment_writer); //세션값
        dto.setAlbum_Comment_parent_seq(Integer.parseInt(Album_seq)); //게시글번호를 parent_seq에 담아줌.
        dto.setAlbum_Comment_contents(Album_Comment_contents);//댓글 내용을 dto에 담아준다.
        System.out.println("2번 : " + dto.getAlbum_Comment_parent_seq());
        System.out.println("3번 : " + dto.getAlbum_Comment_contents());
        System.out.println("a");
        service.insert(dto);
        System.out.println("b");

        String s = g.toJson(dto);

        return s;
    }

    @RequestMapping("delete") //댓글 삭제
    public String delete(int Album_Comment_seq) {
        service.delete(Album_Comment_seq);

        return "redirect:/Album/toAlbumPage";

    }






//    @RequestMapping("select")
//    public String select(Model model, int Album_Comment_parent_seq) {
//
//        List<AlbumCommentDTO> list1 = service.selectComment(Album_Comment_parent_seq);
//        model.addAttribute("dto1",list1);
//        return "album";
//    }





}
