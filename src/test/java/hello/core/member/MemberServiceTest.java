package hello.core.member;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {

  private MemberService memberService;

  @BeforeEach
  void setUp() {
    ApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
    memberService = container.getBean("memberService", MemberService.class);
  }

  @Test
  void join() {
    // given
    Member member = new Member(1L, "memberA", Grade.VIP);

    // when
    memberService.join(member);
    Member findMember = memberService.findMember(1L);

    // then
    assertThat(member).isEqualTo(findMember);
  }

}
