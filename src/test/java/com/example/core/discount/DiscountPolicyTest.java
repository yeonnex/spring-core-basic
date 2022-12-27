package com.example.core.discount;

import com.example.core.AppConfig;
import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;
import com.example.core.order.Order;
import com.example.core.order.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountPolicyTest {
    AppConfig appConfig = new AppConfig();
    OrderService orderService = appConfig.orderService();
    MemberService memberService = appConfig.memberService();

    @Test
    @DisplayName("할인 정책을 적용한다")
    void discount() {
        Member member = createVIPMember();
        Order order = orderService.createOrder(member.getId(), "itemA", 20000);
        // 정률 할인
        assertThat(order.getDiscountPrice()).isEqualTo(2000);

        // 정액 할인
        //assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    private Member createVIPMember() {
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        return member;
    }
}
