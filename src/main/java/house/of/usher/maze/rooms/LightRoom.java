package house.of.usher.maze.rooms;

import house.of.usher.maze.MazeComponent;
import house.of.usher.maze.exceptions.NoSuchGhostException;
import house.of.usher.maze.ghosts.CommonGhost;
import house.of.usher.maze.ghosts.Ghost;

import static house.of.usher.maze.Message.GHOST_GETTING_FREE;

public class LightRoom extends CommonRoom {

    public LightRoom() {
    }

    public LightRoom(int roomNumber) {
        super(roomNumber);
    }

    public LightRoom(int id, String title) {
        super(id, title);
    }

    @Override
    public void openWindow(Ghost ghost) {
        CommonGhost commonGhost = (CommonGhost) ghost;
        if (!MazeComponent.ghostsCache.containsKey(commonGhost.getId())) {
            throw new NoSuchGhostException();
        }
        ((CommonGhost) MazeComponent.ghostsCache.get(commonGhost.getId())).setFree(true);
        System.out.println(GHOST_GETTING_FREE);
    }
}
