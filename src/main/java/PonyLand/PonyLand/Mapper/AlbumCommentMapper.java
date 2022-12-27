package PonyLand.PonyLand.Mapper;

import PonyLand.PonyLand.dto.AlbumCommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface AlbumCommentMapper {

    int insert(AlbumCommentDTO dto);

    List<AlbumCommentDTO> selectComment();

    int delete(int Album_Comment_seq);

//    AlbumCommentDTO selectParent(int Album_Comment_seq);
}
