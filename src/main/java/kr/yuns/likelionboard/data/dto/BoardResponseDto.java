package kr.yuns.likelionboard.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
}
