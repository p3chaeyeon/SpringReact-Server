package com.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import member.service.MemberService;

/* SpringReact/src/main/member/controller/MemberController.java */
@Controller
@RequestMapping(value="/member")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MemberController {
    @Autowired
    MemberService memberService;
    
    @PostMapping("/login")
    @ResponseBody    
    public String login(@RequestBody Map<String, String> credentials) {
        String id = credentials.get("id");
        String pwd = credentials.get("pwd");
        return memberService.login(id, pwd);
    }
//    public String login(@RequestBody String id,@RequestBody String pwd) {
//        return memberService.login(id,  pwd);
//    }
}
