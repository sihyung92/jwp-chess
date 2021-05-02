package chess.controller.room;

import chess.chessgame.domain.room.game.ChessGameManager;
import chess.chessgame.domain.room.user.User;
import chess.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping("/room")
@Controller
public class RoomController {
    private final RoomService roomService;
    private final ObjectMapper objectMapper;

    public RoomController(RoomService roomService, ObjectMapper objectMapper) {
        this.roomService = roomService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("{roomId:[\\d]+}")
    public String enterRoom(@PathVariable long roomId, Model model, @SessionAttribute User user) {
        if (roomService.isAuthorityUser(user, roomId)) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "방에 입장할 수 없습니다.");
        }
        ChessGameManager game = roomService.findGameBy(roomId);
        model.addAttribute("gameId", game.getId());
        model.addAttribute("roomId", roomId);
        model.addAttribute("positionsAndPieces", convertDto(game));
        model.addAttribute("color", game.nextColor());

        return "chess";
    }

    private List<PieceDto> convertDto(ChessGameManager chessGameManager) {
        return chessGameManager.getAliveSquares().stream()
                .map(square -> new PieceDto(square.getPosition().toString(), square.getNotationText(), square.getColor().name()))
                .collect(toList());
    }
}
