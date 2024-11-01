package member.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;
import member.dao.MemberDAO;
import member.service.MemberService;

/* SpringReact/src/main/member/controller/MemberServiceImpl.java */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;
	
	@Autowired
	private HttpSession session;
    
    @Override
    public String login(String id, String pwd) {
        Map<String,String> map = new HashMap<>();
        map.put("id", id);
        map.put("pwd", pwd);
        MemberDTO memberDTO = memberDAO.login(map);
        
        if(memberDTO==null) {
            return "FAIL";
        }
        else {
			session.setAttribute("memId", memberDTO.getId());
			session.setAttribute("memName", memberDTO.getName());
			session.setAttribute("memEmail", memberDTO.getEmail1() + "@" + memberDTO.getEmail2());
			System.out.println("session id" + session.getAttribute("memId"));
			return "SUCCESS";		
		}
    }

}
