package com.meta.memo.service;

import com.meta.memo.domain.Memo;
import com.meta.memo.dto.MemoRequestDto;
import com.meta.memo.dto.MemoResponseDto;
import com.meta.memo.repository.MemoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class MemoService {
    // JDBC를 통한 MySQL 데이터베이스 연결
    private final JdbcTemplate jdbcTemplate;

    public MemoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        // RequestDto -> Entity 변환
        Memo newMemo = new Memo(memoRequestDto);

        MemoRepository memoRepository= new MemoRepository(jdbcTemplate);
        Memo savedMemo = memoRepository.save(newMemo);

        // Entity -> ResponseDto 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(savedMemo);

        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {
        MemoRepository memoRepository= new MemoRepository(jdbcTemplate);
        List<MemoResponseDto> memoResponseDtoList = memoRepository.findAll();
        return memoResponseDtoList;
    }

    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
        MemoRepository memoRepository= new MemoRepository(jdbcTemplate);

        // 해당 id의 메모가 존재하는지 확인
        Memo foundMemo = memoRepository.findById(id);

        // 메모 내용 수정
        if (foundMemo != null) {
            Long updatedId = memoRepository.update(id, memoRequestDto);
            return updatedId;
        } else {
            throw new IllegalArgumentException("선택한 id의 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteMemo(@PathVariable Long id) {
        MemoRepository memoRepository= new MemoRepository(jdbcTemplate);

        // 해당 id의 메모가 존재하는지 확인
        Memo foundMemo = memoRepository.findById(id);

        // 메모 내용 삭제
        if (foundMemo != null) {
            Long deletedId = memoRepository.delete(id);
            return deletedId;
        } else {
            throw new IllegalArgumentException("선택한 id의 메모는 존재하지 않습니다.");
        }
    }

}