package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given-when-then

        //given : 자료 세팅
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when : 실행
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 확인
        assertThat(member).isEqualTo(findMember);
    }
}
