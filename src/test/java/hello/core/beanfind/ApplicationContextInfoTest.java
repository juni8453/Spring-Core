package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
  
  AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("모든 Bean 출력")
  void findAllBean() {
    String[] beanDefinitionNames = container.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
      Object bean = container.getBean(beanDefinitionName);
      System.out.println("name = " + beanDefinitionName + "object = " + bean);
    }
  }

  @Test
  @DisplayName("Application Bean 출력")
  void findApplicationBean() {
    String[] beanDefinitionNames = container.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = container.getBeanDefinition(beanDefinitionName);

      // 스프링이 내부에서 사용하는 Bean 출력 (ROLE_INFRASTRUCTURE)
      // 사용자가 Bean 으로 등록한 객체 또는 외부 Library 출력 (ROLE_APPLICATION)
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        Object bean = container.getBean(beanDefinitionName);
        System.out.println("name = " + beanDefinition + " object = " + bean);
      }
    }
  }

}
