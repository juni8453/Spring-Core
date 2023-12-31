package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

  public static void main(String[] args) {
    ApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = container.getBean("memberService", MemberService.class);
    OrderService orderService = container.getBean("orderService", OrderService.class);

    Long memberId = 1L;
    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10000);
    System.out.println("order = " + order);
    System.out.println("할인이 적용된 가격 = " + order.calculatePrice());
  }
}
