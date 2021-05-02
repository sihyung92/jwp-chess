package chess.controller.room;

import java.util.Objects;

class PieceDto {
    private final String position;
    private final String notation;
    private final String color;

    public PieceDto(String position, String notation, String color) {
        this.position = position;
        this.notation = notation;
        this.color = color;
    }

    public String getPosition() {
        return position;
    }

    public String getNotation() {
        return notation;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return notation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceDto)) return false;
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(getNotation(), pieceDto.getNotation()) && Objects.equals(getColor(), pieceDto.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotation(), getColor());
    }
}
