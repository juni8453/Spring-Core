package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {

  @Test
  void statefulServiceSingleton() {
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(
        TestConfig.class);

    StatefulService statefulService1 = container.getBean(StatefulService.class);
    StatefulService statefulService2 = container.getBean(StatefulService.class);

    statefulService1.order("userA", 10000);
    statefulService2.order("userB", 20000);

    // ThreadA : 사용자 A 가 주문 금액 조회
    int price = statefulService1.getPrice();

    // 10000원이 아니라 20000원이 조회된다. -> userB 가 중간에 껴들어서 price 값을 바꿨기 때문.
    assertThat(price).isNotEqualTo(10000);
    assertThat(price).isEqualTo(20000);
  }

  @Test
  void statelessServiceSingleton() {
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(
        TestConfig.class);

    StatelessService statelessService1 = container.getBean(StatelessService.class);
    StatelessService statelessService2 = container.getBean(StatelessService.class);

    // ThreadA : 사용자 A 가 주문 금액 조회
    int userAPrice = statelessService1.order("userA", 10000);
    statelessService2.order("userB", 20000);


    // 중간에 다른 요청이 있어도 10000원이 조회된다.
    assertThat(userAPrice).isEqualTo(10000);
  }

  @Configuration
  static class TestConfig {

    @Bean
    public StatelessService statelessService() {
      return new StatelessService();
    }
  }
}