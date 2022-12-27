package com.example.core.bean;

import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationContextSameBeanFindTest.SameConfig.class);

    @Test
    @DisplayName("타입으로 빈 조회시, 같은 타입이 둘 이상 있으면 중복 오류가 발생한다")
    void sameTypeBeansException() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 빈 조회시, 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다")
    void sameTypeBean() {
        assertThat(ac.getBean("memberRepository1", MemberRepository.class)).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllSameTypeBeans() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        beansOfType.forEach((name, object) -> {
            assertThat(object).isInstanceOf(MemberRepository.class);
        });
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Configuration
    static class SameConfig {
        @Bean
        MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
