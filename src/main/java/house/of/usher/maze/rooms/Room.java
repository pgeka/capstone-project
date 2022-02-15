package house.of.usher.maze.rooms;

import house.of.usher.maze.ghosts.Ghost;

public interface Room {
    void openWindow(Ghost ghost);
    void welcome();
}
