package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

  MemberService memberService = new MemberServiceImpl();
  OrderService orderService = new OrderServiceImpl();

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

}
