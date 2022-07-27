package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 1. AppConfig가 애플리케이션의 실제 동작에 필요한 "구현 객체 생성" 을 전담
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        // 2. AppConfig는 생성한 객체 인스턴스의 참조를 "생성자를 통해서 주입"
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 할인 정책 변경은 "구성 영역"인 AppConfig만 변경하면 된다
    }
}
