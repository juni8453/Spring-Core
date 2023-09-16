package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
  
  @Test
  @DisplayName("Bean 이름으로 조회")
  void findBeanByName() {
    MemberService memberService = container.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("Bean 타입으로 조회")
  void findBeanByType() {
    MemberService memberService = container.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("Bean 구체 타입으로 조회")
  void findBeanByDefineType() {
    MemberService memberService = container.getBean(MemberServiceImpl.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("Bean 조회 실패")
  void findBeanByNameX() {
    assertThrows(NoSuchBeanDefinitionException.class,
        () -> container.getBean("xxxxx", MemberService.class));
  }
}
