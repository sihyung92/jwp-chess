package chess.chessgame.domain.statistics;

import chess.chessgame.domain.piece.attribute.Color;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {
    DRAW(Double::equals, "무승부"),
    WHITE_WIN((whiteScore, blackScore) -> whiteScore > blackScore, "백 승리"),
    BLACK_WIN((whiteScore, blackScore) -> whiteScore < blackScore, "흑 승리");

    private final BiPredicate<Double, Double> matchResultConditionScore;
    private final String text;

    MatchResult(BiPredicate<Double, Double> matchResultConditionScore, String text) {
        this.matchResultConditionScore = matchResultConditionScore;
        this.text = text;
    }

    public static MatchResult generateMatchResult(double whiteScore, double blackScore) {
        return Arrays.stream(values())
                .filter(matchResult -> matchResult.matchResultConditionScore.test(whiteScore, blackScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("조건에 맞는 승패 결과가 없습니다."));
    }

    public static MatchResult generateMatchResultByKingAliveColor(Color color) {
        if (color == Color.WHITE) {
            return WHITE_WIN;
        }
        return BLACK_WIN;
    }

    public String getText() {
        return text;
    }
}
