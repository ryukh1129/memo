package com.meta.memo.service;

import com.meta.memo.domain.Memo;
import com.meta.memo.dto.MemoRequestDto;
import com.meta.memo.dto.MemoResponseDto;
import com.meta.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemoService {
    // 멤버 변수 선언
    private final MemoRepository memoRepository;

    // 생성자 주입(DI)
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Transactional
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        // RequestDto -> Entity 변환
        Memo newMemo = new Memo(memoRequestDto);
        Memo savedMemo = memoRepository.save(newMemo);
        // Entity -> ResponseDto 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(savedMemo);
        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {
        List<MemoResponseDto> memoResponseDtoList = memoRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(MemoResponseDto::new).toList();
        return memoResponseDtoList;
    }

    public List<MemoResponseDto> getMemosByKeyword(String keyword) {
        List<MemoResponseDto> memoResponseDtoList = memoRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream()
                .map(MemoResponseDto::new).toList();
        return memoResponseDtoList;
    }

    public Memo getMemoById(Long id) {
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 id의 메모는 존재하지 않습니다."));
    }

    @Transactional
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
        // 해당 id의 메모가 존재하는지 확인
        Memo foundMemo = getMemoById(id);
        // 메모 내용 수정
        foundMemo.update(memoRequestDto);
        return id;
    }

    @Transactional
    public Long deleteMemo(@PathVariable Long id) {
        // 해당 id의 메모가 존재하는지 확인
        Memo foundMemo = getMemoById(id);
        // 메모 내용 삭제
        memoRepository.delete(foundMemo);
        return id;
    }

}