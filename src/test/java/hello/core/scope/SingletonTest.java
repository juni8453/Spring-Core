package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

  @Test
  void singletonBeanFind() {
    // SingletonBean 객체를 직접 Bean 으로 등록
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(SingletonBean.class);

    System.out.println("find singletonBean");
    SingletonBean singletonBean1 = container.getBean(SingletonBean.class);
    SingletonBean singletonBean2 = container.getBean(SingletonBean.class);

    assertThat(singletonBean1).isEqualTo(singletonBean2);

    container.close();
  }

  @Scope("singleton")
  static class SingletonBean {

    @PostConstruct
    public void init() {
      System.out.println("SingletonBean init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean destroy");
    }

  }
}
