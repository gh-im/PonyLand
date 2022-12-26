package PonyLand.PonyLand.dao;

import PonyLand.PonyLand.Mapper.GameMapper;
import PonyLand.PonyLand.Mapper.MemberMapper;
import PonyLand.PonyLand.Repository.member.SpringDataJpaMemberRepository;
import PonyLand.PonyLand.dto.MemberDTO;
import PonyLand.PonyLand.dto.RacingDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MemberDAO {


    @Autowired
    private SpringDataJpaMemberRepository sdjr;

    @Autowired
    MemberMapper MemberMapper;

    @Autowired
    GameMapper gameMapper;

    @Autowired
    private SqlSession sqlSession;

    public Long countMember(){
        return sdjr.countBy();
    }

    public void insert(MemberDTO dto){
        sdjr.save(dto);
    }

    public MemberDTO login(String member_id, String member_pw){
        return sdjr.findByMemberIdAndMemberPw(member_id, member_pw);
    }
    public MemberDTO loginForKakao(String member_id){
        return sdjr.findByMemberId(member_id);
    }



    public String getIdByRowNum(int rn){
        return sdjr.getIdByRowNum(rn);
    }

    public MemberDTO findById(String id){return sdjr.findByMemberId(id);}

    public void addView(String id){
        sdjr.addView(id);
    }

    public int update(MemberDTO dto) {
        return MemberMapper.update(dto);
    }

    public void imgupdate(MemberDTO dto) {
         MemberMapper.imgupdate(dto);
    }
    public void message(MemberDTO dto) {
         MemberMapper.message(dto);
    }

    public void bettingCoin(Map<String, Object> map) {gameMapper.bettingCoin(map);}

//    public void updateCoin(Map<String, Object> map) {gameMapper.updateCoin(map);}

    public boolean duplCheck(String memberId) {
        int count = MemberMapper.duplCheck(memberId);
        return count > 0;
    }
    //racing select 문
    public RacingDTO selectBet(String id){return gameMapper.selectBet(id);}


    public void updateWin(RacingDTO dto) {gameMapper.updateWin(dto);
    }

    public void updateLose(RacingDTO dto) {gameMapper.updateLose(dto);
    }

    public void deleteBet(String id) {gameMapper.deleteBet(id);}
}
