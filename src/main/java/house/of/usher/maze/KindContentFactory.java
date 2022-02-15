package house.of.usher.maze;

import house.of.usher.maze.ghosts.CommonGhost;
import house.of.usher.maze.ghosts.Demon;
import house.of.usher.maze.ghosts.DemonImpl;
import house.of.usher.maze.ghosts.Ghost;
import house.of.usher.maze.ghosts.GhostDemonAdapter;
import house.of.usher.maze.ghosts.GhostService;
import house.of.usher.maze.ghosts.GoodGhost;
import house.of.usher.maze.ghosts.HolyGoodGhost;
import house.of.usher.maze.riddles.CommonRiddle;
import house.of.usher.maze.riddles.Riddle;
import house.of.usher.maze.riddles.RiddleService;
import house.of.usher.maze.riddles.SimpleRiddle;
import house.of.usher.maze.rooms.CommonRoom;
import house.of.usher.maze.rooms.LightRoom;
import house.of.usher.maze.rooms.Room;
import house.of.usher.maze.rooms.RoomService;
import org.springframework.stereotype.Component;

@Component
public class KindContentFactory implements ContentAbstractFactory {
    private final RiddleService riddleService;
    private final GhostService ghostService;
    private final RoomService roomService;

    public KindContentFactory(RiddleService riddleService,
                              GhostService ghostService,
                              RoomService roomService) {
        this.riddleService = riddleService;
        this.ghostService = ghostService;
        this.roomService = roomService;
    }

    @Override
    public Ghost getGhost() {
        return new GoodGhost();
    }

    @Override
    public Room getRoom() {
        return new LightRoom();
    }

    @Override
    public Riddle getRiddle() {
        return new SimpleRiddle();
    }

    @Override
    public Riddle getRiddlePreset(int id) {
        CommonRiddle commonRiddle = riddleService.getRiddleById(id);
        return new SimpleRiddle.Builder()
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
            MazeComponent.ghostsCache.put(id, new GoodGhost.Builder()
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
            MazeComponent.ghostsCache.put(id, new HolyGoodGhost((GoodGhost) getGhostPreset(id)));
        }
        return MazeComponent.ghostsCache.get(id);
    }

    @Override
    public Ghost getDemonGhostPreset(int id) {
        if (!MazeComponent.ghostsCache.containsKey(id)) {
            CommonGhost commonGhost = ghostService.getGhostById(id);
            Demon demon = new DemonImpl.Builder()
                    .id(commonGhost.getId())
                    .name(commonGhost.getName())
                    .free(commonGhost.isFree())
                    .build();
            MazeComponent.ghostsCache.put(id, new GhostDemonAdapter(demon));
        }
        return MazeComponent.ghostsCache.get(id);
    }

    @Override
    public Room getRoomPreset(int id) {
        CommonRoom commonRoom = roomService.getRiddleById(id);
        return new LightRoom(commonRoom.getId(), commonRoom.getTitle());
    }
}
