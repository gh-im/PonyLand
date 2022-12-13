package PonyLand.PonyLand.dao;

import PonyLand.PonyLand.dto.MemberDTO;
import PonyLand.PonyLand.Repository.member.SpringDataJpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private SpringDataJpaMemberRepository sdjr;

    public Long countMember(){
        return sdjr.countBy();
    }

    public void insert(MemberDTO dto){
        sdjr.save(dto);
    }

    public MemberDTO login(String member_id, String member_pw){
        return sdjr.findByMemberIdAndMemberPw(member_id, member_pw);
    }

    public String getIdBySeq(int seq){
        return sdjr.findByMember_seq(seq).getMemberId();
    }
}
