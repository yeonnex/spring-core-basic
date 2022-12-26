package com.example.core.member;

import com.example.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();

    @Test
    @DisplayName("회원 가입")
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.BASIC);
        // when
        memberService.join(member);

        Member savedMember = memberService.findMember(member.getId());
        // then
        Assertions.assertThat(member.getName()).isEqualTo(savedMember.getName());
    }
}