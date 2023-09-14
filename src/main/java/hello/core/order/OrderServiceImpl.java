package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

  // 주문 생성 요청 -> 할인을 적용해 새로운 주문 생성 후 반환
  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member findMember = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(findMember, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
}
