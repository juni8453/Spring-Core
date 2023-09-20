package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

  @Test
  public void lifeCycleTest() {
    AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(
        LifeCycleConfig.class);

    NetworkClient client = container.getBean(NetworkClient.class);
    container.close();
  }

  @Configuration
  static class LifeCycleConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient() {
      // 수정자 주입
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hell-spring.dev");
      return networkClient;
    }
  }
}
