package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; // 인터페이스에만 의존

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        // 생성자를 통해 어떤 구현객체가 들어올지 MemberServiceImpl은 알 수 없다
        // 구현 객체의 주입은 외부(AppConfig)에서 결정하고, MemberServiceImpl은 실행에만 집중하면 된다
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

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
