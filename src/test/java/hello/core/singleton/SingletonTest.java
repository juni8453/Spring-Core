package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

  @Test
  @DisplayName("Spring 없는 순수한 DI 컨테이너")
  void pureContainer() {
    AppConfig appConfig = new AppConfig();

    MemberService memberService1 = appConfig.memberService();
    MemberService memberService2 = appConfig.memberService();

    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    assertThat(memberService1).isNotSameAs(memberService2);
  }

  @Test
  @DisplayName("Singleton 패턴을 적용한 객체 사용 테스트")
  void singletonServiceTest() {
    SingletonService instance1 = SingletonService.getInstance();
    SingletonService instance2 = SingletonService.getInstance();

    assertThat(instance1).isSameAs(instance2);
  }
}
