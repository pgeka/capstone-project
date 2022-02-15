package house.of.usher.maze.ghosts;

import house.of.usher.maze.Player;

import java.text.MessageFormat;
import java.util.List;

import static house.of.usher.maze.Message.GOOD_GHOST_REWARD_SHORTEST_WAY_OUT;
import static house.of.usher.maze.Message.GOOD_GHOST_WRONG_CHOICE;
import static house.of.usher.maze.Message.HOLY_GHOST_INTRO;
import static house.of.usher.maze.Message.RIGHT_CHOICE;
import static house.of.usher.maze.Message.WRONG_CHOICE;
import static house.of.usher.maze.util.CommonMazeUtils.findShortestPath;

public class HolyGoodGhost extends GhostDecorator {

    private GoodGhost ghost;

    public HolyGoodGhost(GoodGhost ghost) {
        super(ghost.getId(), ghost.getName(), ghost.isFree());
        this.ghost = ghost;
    }

    public HolyGoodGhost(int id, String name, boolean free, GoodGhost ghost) {
        super(id, name, free);
        this.ghost = ghost;
    }

    private HolyGoodGhost() {
    }

    @Override
    public void appear() {
        ghost.appear();
        System.out.println(HOLY_GHOST_INTRO);
    }

    @Override
    public void afterRiddleIsAsked(boolean answer) {
        if (answer) {
            System.out.println(RIGHT_CHOICE);
            System.out.println(MessageFormat.format("{0} said: {1}", ghost.getName(),
                    GOOD_GHOST_REWARD_SHORTEST_WAY_OUT +
                            promptNextRoomFromTheShortestWayOut(Player.getPlayer().getRoomHistory().getLast())));
        } else {
            System.out.println(WRONG_CHOICE);
            System.out.println(MessageFormat.format("{0} said: {1}", ghost.getName(), GOOD_GHOST_WRONG_CHOICE));
        }
    }

    private int promptNextRoomFromTheShortestWayOut(int currentPoint) {
        List<Integer> shortestPath = findShortestPath(currentPoint);
        int currentPositionIndex = shortestPath.indexOf(currentPoint);
        return shortestPath.get(currentPositionIndex + 1);
    }
}
