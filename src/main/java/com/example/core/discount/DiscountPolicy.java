package com.example.core.discount;

import com.example.core.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @param member
     * @param price
     * @return 할인 금액을 반환한다.
     */
    int discount(Member member, int price);
}
