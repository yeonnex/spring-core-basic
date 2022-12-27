package com.example.core.bean;

import com.example.core.AppConfig;
import com.example.core.order.OrderService;
import com.example.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void allBeans() {
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            System.out.print("beanDefinitionName = " + beanDefinitionName);
            System.out.print(" ||| object = " + ac.getBean(beanDefinitionName));
            System.out.println();
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void applicationBeans() {
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName + " object = " + ac.getBean(beanDefinitionName));
            }
        }
    }

    @Test
    @DisplayName("빈 이름으로 빈 조회하기")
    void beanByBeanName() {
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름 없이 타입으로만 조회")
    void beanByType() {
        OrderService orderService = ac.getBean(OrderService.class);
        assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
    }

    @Test
    @DisplayName("등록되지 않은 빈 이름으로 조회시 예외 발생")
    void beanNotRegister() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxx", OrderService.class));
    }
}
