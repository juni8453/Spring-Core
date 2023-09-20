package hello.core.lifecycle;

public class NetworkClient {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);
  }

  // 의존관계 주입이 끝나면 호출하는 초기화 콜백
  // Bean 설정 파일에 설정돼있는 상태
  public void init() {
    connect();
    call("초기화 연결 메세지");
  }

  // Spring DI Container 가 종료되기 직전 호 출하는 소멸전 콜백
  // Bean 설정 파일에 설정돼있는 상태
  public void close() {
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
