package com.meta.memo.dto;

import com.meta.memo.domain.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;

    public MemoResponseDto(Memo newMemo) {
        this.id = newMemo.getId();
        this.username = newMemo.getUsername();
        this.contents = newMemo.getContents();
    }
}
