package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    //문제점 : 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 고쳐야 한다.
    //DIP 위반 : 추상뿐 아니라 구체 클래스에도 의존을 하고 있음
    /**
     * OCP 위반 : 지금 코드는 기능을 확장하면 클라이언트 코드에 영향을 준다.
     * FixDiscountPolicy 를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl 의 소스 코드도 함께 변경해야 함
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //아래처럼 한다면?
    private final DiscountPolicy discountPolicy; //NPE 발생
    //위를 해결하려면? 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해야한다 -> DI 임


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //SRP 잘 지킴
        //할인에 대한 수정이 필요한 경우 주문쪽을 안고치고 할인쪽만 수정하면 됨

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
