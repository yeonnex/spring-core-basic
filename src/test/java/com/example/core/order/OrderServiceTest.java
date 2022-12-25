package com.example.core.order;

import com.example.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();
    MemberService memberService = new MemberServiceImpl();
    MemberRepository memberRepository = new MemoryMemberRepository();

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