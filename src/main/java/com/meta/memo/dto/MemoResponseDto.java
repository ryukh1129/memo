package com.meta.memo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meta.memo.domain.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime modifiedAt;

    public MemoResponseDto(Memo newMemo) {
        this.id = newMemo.getId();
        this.username = newMemo.getUsername();
        this.contents = newMemo.getContents();
        this.modifiedAt = newMemo.getModifiedAt();
    }
}
