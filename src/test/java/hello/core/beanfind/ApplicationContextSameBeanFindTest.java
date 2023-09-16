package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {

  AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
  void findBeanByTypeDuplication() {
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
        () -> container.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, Bean 이름을 지정하면 된다.")
  void findBeanByName() {
    MemberRepository memberRepository1 = container.getBean("memberRepository1",
        MemberRepository.class);

    assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
  }

  @Test
  @DisplayName("특정 타입을 모두 조회할 수 있다.")
  void findAllBeanByType() {
    Map<String, MemberRepository> beansOfType = container.getBeansOfType(MemberRepository.class);
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value = " + beansOfType.get(key));
    }

    System.out.println("beansOfType = " + beansOfType);
    assertThat(beansOfType.size()).isEqualTo(2);
  }

  @Configuration
  static class SameBeanConfig {

    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }
}
