package kr.yuns.likelionboard.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardDeleteDto {
    private Long id;
    private Integer password;
}
