package hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.discount.AllDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

  @Test
  void findAllBean() {
    // @SpringBootTest 를 사용하지 않아서 자동으로 Bean 등록이 되지 않는다.
    // 즉, 직접 Spring Container 를 띄워서 사용하므로 다른 Bean 등록을 위해 AutoAppConfig 클래스를 Bean 으로 등록한 것.
    // AutoAppConfig 클래스는 Bean 으로 등록되고, @ComponentScan 어노테이션을 활용해 다른 Bean 을 등록한다.
    ApplicationContext container = new AnnotationConfigApplicationContext(AutoAppConfig.class,
        AllDiscountPolicy.class);

    AllDiscountPolicy allDiscountPolicy = container.getBean(AllDiscountPolicy.class);
    Member member = new Member(1L, "userA", Grade.VIP);
    int fixDiscountPrice = allDiscountPolicy.discount(member, 10000, "fixDiscountPolicy");

    assertThat(allDiscountPolicy).isInstanceOf(AllDiscountPolicy.class);
    assertThat(fixDiscountPrice).isEqualTo(1000);

    int rateDiscountPrice = allDiscountPolicy.discount(member, 20000, "rateDiscountPolicy");

    assertThat(rateDiscountPrice).isEqualTo(2000);
  }
}
