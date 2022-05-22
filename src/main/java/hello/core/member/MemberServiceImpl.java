package hello.core.member;

//인터페이스가 한개인 경우 Impl로 구현 클래스명 작성한다.
public class MemberServiceImpl implements MemberService{
    //아래 코드는 추상화와 구체화 모두를 의존하고 있어서 좋지 못하다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
