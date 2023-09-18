package hello.core.singleton;

public class StatelessService {

  // 필드 변수를 없애고 지역변수로 price 를 활용
  public int order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
    return price;
  }
}
