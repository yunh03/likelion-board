package kr.yuns.likelionboard.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardUpdateDto {
    private Long id;
    private String title;
    private String contents;
    private String author;
    private Integer password;
}
