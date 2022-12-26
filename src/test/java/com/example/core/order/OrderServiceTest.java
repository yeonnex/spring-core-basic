package com.example.core.order;

import com.example.core.AppConfig;
import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    @DisplayName("주문 생성 - 고정 할인")
    void createOrder_FIX() {
        createVIPMember();

        Order order = orderService.createOrder(1L, "아이템A", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
        assertThat(order.calculatePrice()).isEqualTo(9000);
    }

    private void createVIPMember() {
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
    }

}