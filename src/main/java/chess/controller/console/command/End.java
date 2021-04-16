package chess.controller.console.command;

import chess.chessgame.domain.manager.ChessGameManager;
import chess.service.ChessServiceImpl;

import java.util.List;

public class End implements Command {
    private static final int END_COMMAND_PROPER_SIZE = 1;

    public End(List<String> inputCommand) {
        if (inputCommand.size() != END_COMMAND_PROPER_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 종료 명령입니다.");
        }
    }

    @Override
    public ChessGameManager execute(ChessServiceImpl chessServiceImpl, long gameId) {
        return chessServiceImpl.end(gameId);
    }
}
