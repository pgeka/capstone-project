package house.of.usher.maze.rooms;

import house.of.usher.maze.MazeComponent;
import house.of.usher.maze.exceptions.NoSuchGhostException;
import house.of.usher.maze.ghosts.CommonGhost;
import house.of.usher.maze.ghosts.Ghost;
import house.of.usher.maze.ghosts.HolyBadGhost;

public class DarkRoom extends CommonRoom {

    public DarkRoom() {
    }

    public DarkRoom(int roomNumber) {
        super(roomNumber);
    }

    public DarkRoom(int id, String title) {
        super(id, title);
    }

    @Override
    public void openWindow(Ghost ghost) {
        CommonGhost commonGhost = (CommonGhost) ghost;
        if (!MazeComponent.ghostsCache.containsKey(commonGhost.getId())) {
            throw new NoSuchGhostException();
        }
        if (ghost instanceof HolyBadGhost) {
            System.out.println("Ghost is getting free dramatically... You will never see it again if you come back to the room.");
            ((CommonGhost) MazeComponent.ghostsCache.get(commonGhost.getId())).setFree(true);
        } else {
            System.out.println("You have tried to set ghost free. But some of them are too wicked and full of sin to leave this old house. You will meet it again if you dare to come back.");
        }
    }
}
