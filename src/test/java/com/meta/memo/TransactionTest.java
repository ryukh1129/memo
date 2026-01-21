package com.meta.memo;

import com.meta.memo.domain.Memo;
import com.meta.memo.repository.MemoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;

@SpringBootTest
public class TransactionTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    MemoRepository memoRepository;

    @Test
    @DisplayName("메모 생성 성공 확인")
    @Transactional(propagation = Propagation.REQUIRED)
    @Rollback(false)
    void test1() {
        Memo memo = new Memo();
        memo.setUsername("test");
        memo.setContents("@Transactional 동작 테스트");

        em.persist(memo);
    }

    @Test
    @DisplayName("메모 생성 실패 확인")
    void test2() {
        Memo memo = new Memo();
        memo.setUsername("test2");
        memo.setContents("@Transactional 동작 테스트");

        em.persist(memo);
    }

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("트랜잭션 전파 테스트")
    void test3() {
        memoRepository.save(em);
    }
}
