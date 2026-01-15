package com.meta.memo.controller;

import com.meta.memo.domain.Memo;
import com.meta.memo.dto.MemoRequestDto;
import com.meta.memo.dto.MemoResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/memos")
public class MemoController {
    // 임시 데이터베이스(내장 메모리 배열) 사용
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping()
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        // RequestDto -> Entity 변환
        Memo newMemo = new Memo(memoRequestDto);

        // (임시) 현재 Memo들의 최대 id를 체크하고 마지막 id를 부여
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        newMemo.setId(maxId);

        // DB 저장
        memoList.put(newMemo.getId(), newMemo);

        // Entity -> ResponseDto 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(newMemo);

        return null;
    }
}
