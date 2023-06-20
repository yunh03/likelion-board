package kr.yuns.likelionboard.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Pattern;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String contents;

    @Column(nullable = false, length = 4)
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{0,4}$")
    private String author;

    @Column(nullable = false, length = 4)
    @Pattern(regexp = "^[0-9]{0,4}$")
    private Integer password;

    @Column(updatable = false, nullable = false)
    private String createdAt;
}
