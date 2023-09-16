package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;

  // 새로운 할인 정책으로 변경하면서 OCP, DIP 를 지키려면 ? -> 인터페이스만 ? -> NPE 발생
  // 즉, 외부에서 내부에 구현체를 넣어줘야한다. (AppConfig)
  private final DiscountPolicy discountPolicy;

  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  // 주문 생성 요청 -> 할인을 적용해 새로운 주문 생성 후 반환
  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member findMember = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(findMember, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
