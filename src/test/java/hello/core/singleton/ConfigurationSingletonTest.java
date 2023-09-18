package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

  @Test
  void configurationTest() {
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(
        AppConfig.class);

    MemberServiceImpl memberService = container.getBean(MemberServiceImpl.class);
    OrderServiceImpl orderService = container.getBean(OrderServiceImpl.class);
    MemberRepository memberRepository = container.getBean("memberRepository", MemberRepository.class);

    // AppConfig 에서 MemoryMemberRepository 가 두 개 생성되지 않고 싱글턴이 보장되는 것을 확인
    // 즉, 애플리케이션에서 사용되는 모든 MemberRepository 는 모두 같다.
    assertThat(memberService.getMemberRepository()).isEqualTo(orderService.getMemberRepository());
    assertThat(memberService.getMemberRepository()).isEqualTo(memberRepository);
    assertThat(orderService.getMemberRepository()).isEqualTo(memberRepository);
  }

}
