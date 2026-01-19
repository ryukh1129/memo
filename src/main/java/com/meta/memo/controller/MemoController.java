package com.meta.memo.controller;

import com.meta.memo.dto.MemoRequestDto;
import com.meta.memo.dto.MemoResponseDto;
import com.meta.memo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/memos")
public class MemoController {
    // 멤버 변수 선언
    private final MemoService memoService;

    // 생성자 주입(DI)
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping()
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        return memoService.createMemo(memoRequestDto);
    }

    @GetMapping()
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    @PutMapping("{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
        return memoService.updateMemo(id, memoRequestDto);
    }

    @DeleteMapping("{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id);
    }
}