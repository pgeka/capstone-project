package house.of.usher.maze;

import house.of.usher.maze.ghosts.Ghost;
import house.of.usher.maze.riddles.Riddle;
import house.of.usher.maze.rooms.Room;

public interface ContentAbstractFactory {
    Ghost getGhost();

    Room getRoom();

    Riddle getRiddle();

    Riddle getRiddlePreset(int id);

    Ghost getGhostPreset(int id);

    Ghost getHolyGhostPreset(int id);

    Ghost getDemonGhostPreset(int id);

    Room getRoomPreset(int id);
}
