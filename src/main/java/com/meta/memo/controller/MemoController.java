package com.meta.memo.controller;

import com.meta.memo.domain.Memo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/memos")
public class MemoController {
    // 임시 데이터베이스(내장 메모리 배열) 사용
    private final Map<Long, Memo> memoList = new HashMap<>();
}
