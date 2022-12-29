package com.example.core;

import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringCoreBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreBasicApplication.class, args);

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        MemberService memberService = ac.getBean("memberService", MemberService.class);
//
//        Member member = new Member(1L, "memberA", Grade.VIP);
//        memberService.join(member);
//        Member findMember = memberService.findMember(member.getId());
//
//        System.out.println(findMember);
    }

}
