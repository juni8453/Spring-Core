package hello.core.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

  RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP 는 10% 할인이 적용되어야 한다.")
  void discount() {
    // given
    Member member = new Member(1L, "vip_member", Grade.VIP);

    // when
    int discountA = rateDiscountPolicy.discount(member, 10000);
    int discountB = rateDiscountPolicy.discount(member, 20000);


    // then
    assertThat(discountA).isEqualTo(1000);
    assertThat(discountB).isEqualTo(2000);
  }

  @Test
  @DisplayName("VIP 가 아니면 할인이 적용되면 안된다.")
  void not_vip() {
    // given
    Member member = new Member(1L, "basic_member", Grade.BASIC);

    // when
    int discountA = rateDiscountPolicy.discount(member, 10000);

    // then
    assertThat(discountA).isEqualTo(0);
  }

}