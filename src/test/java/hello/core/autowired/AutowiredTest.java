package hello.core.autowired;

import hello.core.member.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {

  @Test
  void autoWiredOption() {
    ApplicationContext container = new AnnotationConfigApplicationContext(TestBean.class);
  }

  static class TestBean {

    /* 1. required = false */
    // Member 는 Sptring DI Container 에 관리받는 Bean 객체가 아니기 때문에 의존관계 주입을 할 수 없다.
    // required = false 기 때문에 주입 대상이 없는 이런 경우 setNoBean1() 은 호출조차 안됨.
    @Autowired(required = false)
    public void setNoBean1(Member noBean1) {
      System.out.println("noBean1 = " + noBean1);
    }

    /* 2. @Nullable */
    // 주입 대상이 없지만 호출은 가능, 대신 Null 로 들어옴
    @Autowired
    public void setNoBean2(@Nullable Member noBean2) {
      System.out.println("noBean2 = " + noBean2);
    }

    /* 3. Optional<> */
    // 역시 주입 대상이 없지만 호출은 가능, 대신 Optional.empty 로 들어옴
    @Autowired
    public void setNoBean3(Optional<Member> noBean3) {
      System.out.println("noBean3 = " + noBean3);
    }
  }
}
