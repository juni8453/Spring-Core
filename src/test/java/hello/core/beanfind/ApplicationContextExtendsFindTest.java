package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {

  AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(
      TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 중복 오류가 발생한다.")
  void findBeanByParentTypeDuplicate() {
    assertThrows(NoUniqueBeanDefinitionException.class,
        () -> container.getBean(DiscountPolicy.class));
  }

  @Test
  @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 Bean 이름을 지정하면 된다.")
  void findBeanByParentTypeBeanName() {
    DiscountPolicy rateDiscountPolicy = container.getBean("rateDiscountPolicy",
        DiscountPolicy.class);

    DiscountPolicy fixDiscountPolicy = container.getBean("fixDiscountPolicy",
        DiscountPolicy.class);

    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);
  }

  @Test
  @DisplayName("특정 하위 타입으로 조회 (권장하지 않음)")
  void findBeanBySubType() {
    RateDiscountPolicy rateDiscountPolicy = container.getBean(RateDiscountPolicy.class);

    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회")
  void findAllBeanByParentType() {
    Map<String, DiscountPolicy> beansOfType = container.getBeansOfType(DiscountPolicy.class);
    assertThat(beansOfType.size()).isEqualTo(2);
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회 - Object")
  void findAllBeanByObjectType() {
    Map<String, Object> beansOfType = container.getBeansOfType(Object.class);
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value = " + beansOfType.get(key));
    }
  }

  @Configuration
  static class TestConfig {

    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }

}
