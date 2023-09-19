package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

  // 새로운 할인 정책으로 변경하면서 OCP, DIP 를 지키려면 ? -> 인터페이스만 ? -> NPE 발생
  // 즉, 외부에서 내부에 구현체를 넣어줘야한다. (AppConfig)
  private final DiscountPolicy discountPolicy;
  private final MemberRepository memberRepository;

  /* 같은 타입 Bean 이 여럿 인 경우 Bean 이름으로 매칭하는 방법
  @Autowired
  public OrderServiceImpl(DiscountPolicy rateDiscountPolicy, MemberRepository memberRepository) {
    this.discountPolicy = rateDiscountPolicy;
    this.memberRepository = memberRepository;
  }
  */

  /* 같은 타입 Bean 이 여럿 인 경우 @Qualifier 이름으로 매칭하는 방법
  @Autowired
  public OrderServiceImpl(@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy, MemberRepository memberRepository) {
    this.discountPolicy = discountPolicy;
    this.memberRepository = memberRepository;
  }

  @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy 대신 직접 만든 어노테이션으로 컴파일시 체크 가능하도록 사용하는 방법
  @Autowired
  public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
    this.discountPolicy = discountPolicy;
    this.memberRepository = memberRepository;
  }
  */

  @Autowired
  public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
    this.discountPolicy = discountPolicy;
    this.memberRepository = memberRepository;
  }

  // 주문 생성 요청 -> 할인을 적용해 새로운 주문 생성 후 반환
  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member findMember = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(findMember, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  // 테스트 용
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
