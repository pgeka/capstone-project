package house.of.usher.maze.ghosts;

import house.of.usher.maze.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static house.of.usher.maze.Message.GHOST_APPEARS;
import static house.of.usher.maze.Message.GOOD_GHOST_REWARD;
import static house.of.usher.maze.Message.GOOD_GHOST_WRONG_CHOICE;
import static house.of.usher.maze.Message.RIGHT_CHOICE;
import static house.of.usher.maze.Message.WRONG_CHOICE;
import static house.of.usher.maze.util.CommonMazeUtils.findAllPaths;

public class GoodGhost extends CommonGhost {

    public GoodGhost(int id, String name, boolean free) {
        super(id, name, free);
    }

    public GoodGhost() {
    }

    private GoodGhost(Builder builder) {
        super(builder.id, builder.name, builder.free);
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

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder free(boolean free) {
            this.free = free;
            return this;
        }

        public GoodGhost build() {
            return new GoodGhost(this);
        }
    }

    @Override
    public void appear() {
        System.out.println(MessageFormat.format(GHOST_APPEARS, getName()));
    }

    @Override
    public void afterRiddleIsAsked(boolean answer) {
        if (answer) {
            System.out.println(RIGHT_CHOICE);
            System.out.println(MessageFormat.format("{0} said: {1}", getName(), GOOD_GHOST_REWARD +
                    promptNextRoomFromRandomWayOut(Player.getPlayer().getRoomHistory().getLast())));
        } else {
            System.out.println(WRONG_CHOICE);
            System.out.println(MessageFormat.format("{0} said: {1}", getName(), GOOD_GHOST_WRONG_CHOICE));
        }
    }

    private int promptNextRoomFromRandomWayOut(int currentPoint) {
        List<List<Integer>> paths = new ArrayList<>();
        findAllPaths(currentPoint, new LinkedList<>(), paths);
        List<Integer> randomPath = paths.get(new Random().nextInt(paths.size() - 1));
        int currentPositionIndex = randomPath.indexOf(currentPoint);
        return randomPath.get(currentPositionIndex + 1);
    }
}
