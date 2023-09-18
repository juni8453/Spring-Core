package hello.core.singleton;

public class SingletonService {

  private static final SingletonService instance = new SingletonService();

  public static SingletonService getInstance() {
    return instance;
  }

  // 외부에서 임의로 생성하지 못하도록 private 사용
  private  SingletonService() {}

  public void logic() {
    System.out.println("Call Singleton Instance");
  }
}

