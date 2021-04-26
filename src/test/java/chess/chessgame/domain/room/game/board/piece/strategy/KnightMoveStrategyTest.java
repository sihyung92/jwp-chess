package chess.chessgame.domain.room.game.board.piece.strategy;

import chess.chessgame.domain.room.game.board.Board;
import chess.chessgame.domain.room.game.board.InitBoardInitializer;
import chess.chessgame.domain.room.game.board.Square;
import chess.chessgame.domain.room.game.board.TestBoardInitializer;
import chess.chessgame.domain.room.game.board.piece.Knight;
import chess.chessgame.domain.room.game.board.piece.Pawn;
import chess.chessgame.domain.room.game.board.piece.attribute.Color;
import chess.chessgame.domain.room.game.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static chess.chessgame.domain.room.game.board.piece.Fixture.whiteKnight;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightMoveStrategyTest {
    private Board board;

    private static Stream<Arguments> knightCanMoveTest() {
        return Stream.of(
                Arguments.of(Position.of("b1"), Position.of("c3"), true),
                Arguments.of(Position.of("b1"), Position.of("a3"), true),
                Arguments.of(Position.of("a3"), Position.of("c4"), true)
        );
    }

    @BeforeEach
    void setUp() {
        board = InitBoardInitializer.getBoard();
    }

    @DisplayName("나이트의 이동 가능한 경우 테스트")
    @ParameterizedTest
    @MethodSource
    void knightCanMoveTest(Position from, Position to, boolean expected) {
        assertThat(whiteKnight.canMove(board.createMoveOrder(from, to))).isEqualTo(expected);
    }

    @DisplayName("잘못된 방향으로 이동하려고 한다면 예외")
    @ParameterizedTest
    @CsvSource({"a1, a3", "a1, b8"})
    void throwExceptionWhenWrongDirection(String from, String to) {
        assertThatThrownBy(() -> whiteKnight.canMove(board.createMoveOrder(Position.of(from), Position.of(to))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 없는 방향입니다.");
    }

    @DisplayName("목적지에 같은 팀의 말이 있다면 예외")
    @ParameterizedTest
    @CsvSource({"WHITE,c3,b1", "BLACK,c3,d1"})
    void throwExceptionWhenMoveToSameTeam(Color color, String from, String to) {
        Position fromPosition = Position.of(from);
        Position toPosition = Position.of(to);

        Square fromSquare = new Square(fromPosition, new Knight(color));
        Square toSquare = new Square(toPosition, new Pawn(color));

        Board testBoard = TestBoardInitializer.createTestBoard(Arrays.asList(fromSquare, toSquare));

        assertThatThrownBy(() -> testBoard.move(fromPosition, toPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 진영의 말이 있어서 행마할 수 없습니다.");
    }
}