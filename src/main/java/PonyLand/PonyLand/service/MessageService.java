package PonyLand.PonyLand.service;

import PonyLand.PonyLand.dao.MessageDAO;
import PonyLand.PonyLand.dto.MemberDTO;
import PonyLand.PonyLand.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageDAO dao;

    @Transactional
    public int insert(MessageDTO dto) {
        dao.insert(dto);
        return 0;
    }
    public List<MessageDTO> selectAll(String id){
        return dao.selectAll(id);
    }

    public MessageDTO selectBySeq(int seq) {
        return dao.selectBySeq(seq);
    }

    public List<MessageDTO> sentMailAll(String id) {
        return dao.sentMailAll(id);
    }

}
