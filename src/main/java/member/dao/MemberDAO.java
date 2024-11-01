package member.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import member.bean.MemberDTO;

/* SpringReact/src/main/member/controller/MemberDAO.java */
@Mapper
public interface MemberDAO {
    public MemberDTO login(Map<String,String>map);    
}
