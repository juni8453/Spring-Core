package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  // 테스트 용
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
