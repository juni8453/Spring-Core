package hello.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

  private final MemberService memberService = new MemberServiceImpl();
  private final OrderService orderService = new OrderServiceImpl();

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
