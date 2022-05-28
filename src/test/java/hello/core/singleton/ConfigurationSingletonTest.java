package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepoFromMemberService = memberService.getMemberRepository();
        MemberRepository memberRepoFromOrderService = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepoFromMemberService);
        System.out.println("orderService -> memberRepository = " + memberRepoFromOrderService);
        System.out.println("memberRepository = " + memberRepository);
        //셋 다 동일한 memberRepository

        assertThat(memberRepoFromMemberService).isSameAs(memberRepository);
        assertThat(memberRepoFromOrderService).isSameAs(memberRepository);
    }
    
    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
