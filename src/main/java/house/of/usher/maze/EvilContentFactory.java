package house.of.usher.maze;

import house.of.usher.maze.ghosts.BadGhost;
import house.of.usher.maze.ghosts.CommonGhost;
import house.of.usher.maze.ghosts.DemonImpl;
import house.of.usher.maze.ghosts.Ghost;
import house.of.usher.maze.ghosts.GhostDemonAdapter;
import house.of.usher.maze.ghosts.GhostService;
import house.of.usher.maze.ghosts.HolyBadGhost;
import house.of.usher.maze.riddles.CommonRiddle;
import house.of.usher.maze.riddles.DifficultRiddle;
import house.of.usher.maze.riddles.Riddle;
import house.of.usher.maze.riddles.RiddleService;
import house.of.usher.maze.rooms.CommonRoom;
import house.of.usher.maze.rooms.DarkRoom;
import house.of.usher.maze.rooms.Room;
import house.of.usher.maze.rooms.RoomService;
import org.springframework.stereotype.Component;

@Component
public class EvilContentFactory implements ContentAbstractFactory {
    private final RiddleService riddleService;
    private final GhostService ghostService;
    private final RoomService roomService;

    public EvilContentFactory(RiddleService riddleService,
                              GhostService ghostService,
                              RoomService roomService) {
        this.riddleService = riddleService;
        this.ghostService = ghostService;
        this.roomService = roomService;
    }

    @Override
    public Ghost getGhost() {
        return new BadGhost();
    }

    @Override
    public Room getRoom() {
        return new DarkRoom();
    }

    @Override
    public Riddle getRiddle() {
        return new DifficultRiddle();
    }

    @Override
    public Riddle getRiddlePreset(int id) {
        CommonRiddle commonRiddle = riddleService.getRiddleById(id);
        return new DifficultRiddle.Builder()
                .id(commonRiddle.getId())
                .riddleText(commonRiddle.getRiddleText())
                .options(commonRiddle.getOptions())
                .answer(commonRiddle.getAnswer())
                .build();
    }

    @Override
    public Ghost getGhostPreset(int id) {
        if (!MazeComponent.ghostsCache.containsKey(id)) {
            CommonGhost commonGhost = ghostService.getGhostById(id);
            MazeComponent.ghostsCache.put(id, new BadGhost.Builder()
                    .id(commonGhost.getId())
                    .name(commonGhost.getName())
                    .free(commonGhost.isFree())
                    .build());
        }
        return MazeComponent.ghostsCache.get(id);
    }

    @Override
    public Ghost getHolyGhostPreset(int id) {
        if (!MazeComponent.ghostsCache.containsKey(id)) {
            MazeComponent.ghostsCache.put(id, new HolyBadGhost((BadGhost) getGhostPreset(id)));
        }
        return MazeComponent.ghostsCache.get(id);
    }

    @Override
    public Ghost getDemonGhostPreset(int id) {
        if (!MazeComponent.ghostsCache.containsKey(id)) {
            CommonGhost commonGhost = ghostService.getGhostById(id);
            MazeComponent.ghostsCache.put(id, new GhostDemonAdapter(new DemonImpl.Builder()
                    .id(commonGhost.getId())
                    .name(commonGhost.getName())
                    .free(commonGhost.isFree())
                    .build()));
        }
        return MazeComponent.ghostsCache.get(id);
    }

    @Override
    public Room getRoomPreset(int id) {
        CommonRoom commonRoom = roomService.getRiddleById(id);
        return new DarkRoom(commonRoom.getId(), commonRoom.getTitle());
    }
}
