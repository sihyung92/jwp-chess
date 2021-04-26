package chess.chessgame.domain.room.game.board.piece.attribute;

public class Notation {
    private final String notation;

    public Notation(String notation) {
        this.notation = notation;
    }

    public String getNotationText(Color color) {
        return color.changeNotation(notation);
    }
}
