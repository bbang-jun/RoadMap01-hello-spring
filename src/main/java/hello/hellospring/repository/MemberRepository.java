package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional: id나 name을 찾을 때 없는 경우 또한 존재할 수 있음(null)
    // null을 그대로 반환하는 것 대신 Optional로 감싸서 반환하는 방법을 사용함
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
