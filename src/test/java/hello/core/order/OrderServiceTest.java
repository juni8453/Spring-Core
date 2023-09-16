package hello.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

  private MemberService memberService;
  private OrderService orderService;

  @BeforeEach
  void setUp() {
    ApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
    memberService = container.getBean("memberService", MemberService.class);
    orderService = container.getBean("orderService", OrderService.class);
  }

  @Test
  void createOrder() {
    // given
    Long memberId = 1L;
    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    // when
    Order order = orderService.createOrder(memberId, "itemA", 10000);

    // then
    assertThat(order.getDiscountPrice()).isEqualTo(1000);
    assertThat(order.calculatePrice()).isEqualTo(order.getItemPrice() - order.getDiscountPrice());
  }

}
