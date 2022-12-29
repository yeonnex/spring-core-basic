package com.example.core.autoconfig;

import com.example.core.AutoAppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfigTest {
    @Test
    @DisplayName("컴포넌트 스캔을 통한 자동 빈 설정 테스트")
    void autoConfig() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println(beanDefinitionName + bean);
            }
        }
    }
}
