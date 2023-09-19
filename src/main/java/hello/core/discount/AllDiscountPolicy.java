package hello.core.discount;

import hello.core.member.Member;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllDiscountPolicy {

  // Map, List 형태로 같은 Type 의 모든 Bean 을 받아낼 수 있다.
  private final Map<String, DiscountPolicy> policyMap;
  private final List<DiscountPolicy> policyList;

  @Autowired
  public AllDiscountPolicy(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
    this.policyMap = policyMap;
    this.policyList = policyList;
    System.out.println("policyMap = " + policyMap);
    System.out.println("policyList = " + policyList);
  }

  // map.get(Bean 이름) 하고 해당 Bean 의 메서드를 호출해 사용할 수 있다.
  public int discount(Member member, int price, String discountCode) {
    DiscountPolicy discountPolicy = policyMap.get(discountCode);
    return discountPolicy.discount(member, price);
  }
}
