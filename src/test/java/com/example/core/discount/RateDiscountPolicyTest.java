package com.example.core.discount;

import com.example.core.AppConfig;
import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class RateDiscountPolicyTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    DiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP 회원은 정률 할인이 적용된다")
    void vip() {
        Member member = createVIPMember();
        int discount = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("BASIC 회원은 정률 할인이 적용되지 않는다")
    void no_vip() {
        Member member = createBasicPMember();
        int discount = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }

    private Member createVIPMember() {
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        return member;
    }
    private Member createBasicPMember() {
        Member member = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(member);
        return member;
    }

}