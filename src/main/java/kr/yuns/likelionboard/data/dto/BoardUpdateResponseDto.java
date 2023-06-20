package kr.yuns.likelionboard.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardUpdateResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
    private Integer errorCode;
}
