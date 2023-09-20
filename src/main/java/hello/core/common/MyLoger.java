package hello.core.common;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLoger {

  private String uuid;
  private String requestUrl;

  // Bean 생성 시점에 URL 을 알 수 없으므로 외부에서 주입받아야 한다.
  public void setRequestUrl(String requestUrl) {
    this.requestUrl = requestUrl;
  }

  public void log(String message) {
    System.out.println("[" + uuid + "]" + "[" + requestUrl + "] " + message);
  }

  // HTTP 요청(고객 요청)시 UUID 부여
  // HTTP 요청 당 Bean 이 하나씩 생기니까 UUID 를 저장해두면 다른 HTTP 요청과 구분할 수 있다.
  @PostConstruct
  public void init() {
    uuid = UUID.randomUUID().toString();
    System.out.println("[" + uuid + "] request scope bean create:" + this);
  }

  @PreDestroy
  public void close() {
    System.out.println("[" + uuid + "] request scope bean close:" + this);
  }
}

