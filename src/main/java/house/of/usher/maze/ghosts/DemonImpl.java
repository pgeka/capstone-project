package house.of.usher.maze.ghosts;

import house.of.usher.maze.MazeComponent;
import house.of.usher.maze.Player;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import static house.of.usher.maze.Message.MAZE_FINISHED_BY_DEMON;
import static house.of.usher.maze.Message.SPEED_THINGS_UP;
import static house.of.usher.maze.util.CommonMazeUtils.findShortestPath;

public class DemonImpl implements Demon {
    private static LinkedList<Integer> shortestPath;
    private int currentRoom;

    private String name;
    private int id;
    private boolean free;

    public DemonImpl() {
        prepareData();
    }

    public DemonImpl(Builder builder) {
        this();
        name = builder.name;
        id = builder.id;
        free = builder.free;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public LinkedList<Integer> getShortestPath() {
        return shortestPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public static class Builder {
        String name;
        int id;
        boolean free;

        public Builder(int id, String name, boolean free) {
            this.name = name;
            this.id = id;
            this.free = free;
        }

        public Builder() {
        }

        public DemonImpl.Builder id(int id) {
            this.id = id;
            return this;
        }

        public DemonImpl.Builder name(String name) {
            this.name = name;
            return this;
        }

        public DemonImpl.Builder free(boolean free) {
            this.free = free;
            return this;
        }

        public DemonImpl build() {
            return new DemonImpl(this);
        }
    }

    private void prepareData() {
        List<Integer> shortestPath = findShortestPath(Player.getPlayer().getRoomHistory().getLast());
        DemonImpl.shortestPath = new LinkedList<>(shortestPath);
        currentRoom = Player.getPlayer().getRoomHistory().getLast();
    }

    @Override
    public void nextStep() {
        int currentPositionIndex = shortestPath.indexOf(currentRoom);
        this.currentRoom = shortestPath.get(currentPositionIndex + 1);
        System.out.println(MessageFormat.format(SPEED_THINGS_UP, this.currentRoom));
        if (this.currentRoom == MazeComponent.FINISH_POINT) {
            System.out.println(MAZE_FINISHED_BY_DEMON);
            Player.getPlayer().decreaseHealthPoints();
            Player.getPlayer().deleteObserver(MazeComponent.demonObserver);
        }
    }
}
