package hello.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.discount.AllDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AllBeanUseSpringBootTest {

  @Autowired
  private AllDiscountPolicy allDiscountPolicy;

  @Test
  void findSameTypeAllBeanUseSpringBootTest() {
    Member member = new Member(1L, "userA", Grade.VIP);
    int fixDiscountPrice = allDiscountPolicy.discount(member, 10000, "fixDiscountPolicy");

    assertThat(allDiscountPolicy).isInstanceOf(AllDiscountPolicy.class);
    assertThat(fixDiscountPrice).isEqualTo(1000);

    int rateDiscountPrice = allDiscountPolicy.discount(member, 20000, "rateDiscountPolicy");

    assertThat(rateDiscountPrice).isEqualTo(2000);
  }

}
