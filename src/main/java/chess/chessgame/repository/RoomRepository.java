package chess.chessgame.repository;

import chess.chessgame.domain.room.Room;
import chess.chessgame.domain.room.game.ChessGameManager;
import chess.chessgame.domain.room.user.User;

import java.util.List;

public interface RoomRepository {
    Room createRoom(String roomName, ChessGameManager chessGameManager, List<User> users);

    void updateBlackUser(Room room);

    List<Room> findAllActiveRoom();

    Room findRoomBy(long roomId);

    Room findRoomByUserId(long userId);
}
