package com.meta.memo.controller;

import com.meta.memo.domain.Memo;
import com.meta.memo.dto.MemoRequestDto;
import com.meta.memo.dto.MemoResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("api/memos")
public class MemoController {
    // JDBC를 통한 MySQL 영ㄴ결
    private final JdbcTemplate jdbcTemplate;

    public MemoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping()
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        // RequestDto -> Entity 변환
        Memo newMemo = new Memo(memoRequestDto);

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환 받기 위한 객체

        String sql = "INSERT INTO memo (username, contents) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newMemo.getUsername());
            preparedStatement.setString(2, newMemo.getContents());
            return preparedStatement;
        }, keyHolder);

        // DB INSERT 후 받아온 키 확인
         Long id = keyHolder.getKey().longValue();
        newMemo.setId(id);

        // Entity -> ResponseDto 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(newMemo);

        return memoResponseDto;
    }

    @GetMapping()
    public List<MemoResponseDto> getMemos() {
        // DB 조회
        String sql = "SELECT * FROM memo";

        List<MemoResponseDto> memoResponseDtoList = jdbcTemplate.query(sql, new RowMapper<MemoResponseDto>() {
            @Override
            public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                return new MemoResponseDto(id, username, contents);
            }
        });
        return memoResponseDtoList;
    }

    @PutMapping("{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
        // 해당 id의 메모가 존재하는지 확인
        Memo foundMemo = findById(id);

        // 해당 id의 메모가 데이터베이스에 존재하는지 확인
        if (foundMemo != null) {
            String sql = "UPDATE memo SET username = ?, contents = ? WHERE id = ?";
            jdbcTemplate.update(sql, memoRequestDto.getUsername(), memoRequestDto.getContents(), id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 id의 메모는 존재하지 않습니다.");
        }
    }
//
//    @DeleteMapping("{id}")
//    public Long deleteMemo(@PathVariable Long id) {
//        // 해당 id의 메모가 데이터베이스에 존재하는지 확인
//        if (memoList.containsKey(id)) {
//            // true면, 해당 메모 삭제
//            memoList.remove(id);
//            return id;
//        } else {
//            throw new IllegalArgumentException("선택한 id의 메모는 존재하지 않습니다.");
//        }
//    }

    // 특정 id의 존재 여부 확인 공용 메서드
    private Memo findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM memo WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Memo memo = new Memo();
                memo.setUsername(resultSet.getString("username"));
                memo.setContents(resultSet.getString("contents"));
                return memo;
            } else {
                return null;
            }
        }, id);
    }
}
