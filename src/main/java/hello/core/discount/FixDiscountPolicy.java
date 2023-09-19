package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
/*@Qualifier("fixDiscountPolicy") -> 컴파일시 타입 체크가 안됨
-> @FixDiscountPolicy 를 만들어 사용
* */
public class FixDiscountPolicy implements DiscountPolicy {

  private int discountFixAmount = 1000;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return discountFixAmount;
    }

    return 0;

  }
}
