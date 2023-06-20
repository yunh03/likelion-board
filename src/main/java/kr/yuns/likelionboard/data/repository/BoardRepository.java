package kr.yuns.likelionboard.data.repository;

import kr.yuns.likelionboard.data.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
