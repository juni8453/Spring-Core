package hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfigTest {

  @Test
  void basicScan() {
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(
        AutoAppConfig.class);

    MemberService memberService = container.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

}
