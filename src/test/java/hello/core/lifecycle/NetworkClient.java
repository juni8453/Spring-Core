package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);
  }

  // 의존관계 주입이 끝나면 호출하는 초기화 콜백
  // 즉, 의존관계 주입 이후 객체 데이터 세팅 시 사용
  @Override
  public void afterPropertiesSet() throws Exception {
    connect();
    call("초기화 연결 메세지");
  }

  // Spring DI Container 가 종료되기 직전 호출하는 소멸전 콜백
  @Override
  public void destroy() throws Exception {
    System.out.println("close: " + url);
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // Service 시작시 호출
  public void connect() {
    System.out.println("connect: " + url);
  }

  public void call(String message) {
    System.out.println("call: " + url + " message = " + message);
  }
}
