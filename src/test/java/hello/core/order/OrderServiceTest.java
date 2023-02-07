package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

  MemberService memberService;
  OrderService orderService;


  @BeforeEach
  public void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
    orderService = appConfig.orderService();
  }

  @Test
  void createOrder() throws Exception {
    //given
    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);

    //when
    memberService.join(member);
    Order order = orderService.createOrder(memberId, "itemA", 10000);

    //then
    assertThat(order.getDiscountPrice()).isEqualTo(1000);

  }

  // 필드 주입은 사용 X
//  @Test
//  void fieldInjectionTest() {
//    OrderServiceImpl orderService = new OrderServiceImpl();
//    orderService.setMemberRepository(new MemoryMemberRepository());
//    orderService.setDiscountPolicy(new RateDiscountPolicy());
//    orderService.createOrder(1L, "itemA", 10000);
//  }
}
