package com.project.abook.member.repository;

import com.project.abook.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);
    Optional<Member> findByUserName(String userName);

    boolean existsByUserId(String userId);
    boolean existsByUserName(String userName);
}
