package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // key값을 생성하기 위한 변수
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 셋팅
        store.put(member.getId(), member); // map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // optional를 통해 store.get(id)가 null이어도 감쌈.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식 사용
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // map 상태로 데이터를 저장하고 있었으나 반환은 list인 상태
        // store의 values는 Map<Long, Member>의 values이므로 Member이다.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
