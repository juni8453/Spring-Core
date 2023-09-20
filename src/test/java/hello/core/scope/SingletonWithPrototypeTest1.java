package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(
        PrototypeBean.class);

    PrototypeBean prototypeBean1 = container.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    assertThat(prototypeBean1.getCount()).isEqualTo(1);

    PrototypeBean prototypeBean2 = container.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    assertThat(prototypeBean2.getCount()).isEqualTo(1);
  }

  @Test
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
    ClientBean clientBean1 = container.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBean clientBean2 = container.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(1);
  }

  @RequiredArgsConstructor
  @Scope("singleton") /* 생략 가능 */
  static class ClientBean {

    /* 1. ObjectProvider 를 활용해 프로토타입 빈 객체를 조회 -> 생성 -> 의존 주입 -> 초기화 -> 반환 실시
    private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
    */


    // 2. javax.inject 의 Provider 를 build.gradle 에서 주입받아 사용
    private final Provider<PrototypeBean> provider;

    public int logic() {
      PrototypeBean prototypeBean = provider.get();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }

}
