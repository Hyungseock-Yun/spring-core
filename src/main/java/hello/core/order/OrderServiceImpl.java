package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    // final 붙은
public class OrderServiceImpl implements OrderService {

//  @Autowired private MemberRepository memberRepository;
//  @Autowired private DiscountPolicy discountPolicy;
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  // 필드 주입용
//  @Autowired
//  public void setMemberRepository(MemberRepository memberRepository) {
//    this.memberRepository = memberRepository;
//  }
//
//  @Autowired
//  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//    this.discountPolicy = discountPolicy;
//  }
//
//  public OrderServiceImpl() {
//
//  }

  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository,
//                          @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy
                          @MainDiscountPolicy DiscountPolicy discountPolicy
  ) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  // 생성자와 같은 역할을 하는 메서드를 만든 후, 의존성 주입하거나 할 수 있음
  // 아무 메서드에 주입 가능 (스프링빈이어야 동작 가능 - ex: @Component 사용 된 Class)
  @Autowired
  public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//    this.memberRepository = memberRepository;
//    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

}
