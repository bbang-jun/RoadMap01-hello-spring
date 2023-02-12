package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// MemberServiceTest를 통합 테스트로 리팩토링
@SpringBootTest // 통합 테스트
@Transactional // 롤백 기능
class MemberServiceIntegrationTest {

    // @BeforeEach로 di 하는 방법 대신 필드 객체 주입 방식 사용
    @Autowired MemberService memberService;
    // MemorMemberRepository -> MemberRepository (구현체는 스프링이 configuration 한 곳에서 올려줌)
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given(뭔가가 주어져서)
        Member member = new Member();
        member.setName("spring");

        // when(실행했을 때)
        long saveId = memberService.join(member);

        // then(결과)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}