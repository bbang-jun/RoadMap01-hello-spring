package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test 코드가 끝날 때마다 자동 호출
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // findById의 반환형은 optional이므로 get()으로 꺼냄
        Member result = repository.findById(member.getId()).get();

        // 위에서 new로 생성한 member와 db에 저장된 것과 같으면 테스트 통과
        // 방법 1(단순 콘솔)
        // System.out.println("result = " + (result==member));
        // 방법 2(org.junit.jupiter.api)
        //Assertions.assertEquals(member, result);
        // 방법 3(org.assertj.core.api)
        assertThat(member).isEqualTo(result);
        //  Assertions.assertThat(member).isEqualTo(result); 을 alt+enter로 static 선언하면 위와 같이 사용 가능
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 복붙 후 shift + f6을 하면 변수명 동시에 변경 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
