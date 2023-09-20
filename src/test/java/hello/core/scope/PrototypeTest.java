package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.scope.SingletonTest.SingletonBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  void prototypeBeanFind() {
    // PrototypeBean 객체를 직접 Bean 으로 등록
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(PrototypeBean.class);
    System.out.println("find prototypeBean1");
    PrototypeBean prototypeBean1 = container.getBean(PrototypeBean.class);

    System.out.println("find prototypeBean2");
    PrototypeBean prototypeBean2 = container.getBean(PrototypeBean.class);

    assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

    container.close();
  }

  @Scope("prototype")
  static class PrototypeBean {

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean destroy");
    }
  }
}
