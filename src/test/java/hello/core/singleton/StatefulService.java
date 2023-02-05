package hello.core.singleton;

public class StatefulService {

 // 싱글톤 방식에서 상태를 유지하게 되면 멀티 쓰레드 사용 시 값을 공유하게 되어 문제가 발생.
 // 항상 무상태(stateless)를 유지하도록 설계해야한다.
//  private int price;    // 상태를 유지하는 필드

  public int order(String name, int price) {
    System.out.println("name = " + name);
    System.out.println("price = " + price);
//    this.price = price;   // 여기가 문제!
    return price;
  }

//  public int getPrice() {
//    return price;
//  }

}
