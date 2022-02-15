package house.of.usher.maze.ghosts;

import house.of.usher.maze.Player;

import static house.of.usher.maze.Message.HOLY_GHOST_INTRO;
import static house.of.usher.maze.Message.HOLY_GHOST_WRONG_CHOICE;

public class HolyBadGhost extends GhostDecorator {

    private BadGhost ghost;

    public HolyBadGhost(BadGhost ghost) {
        super(ghost.getId(), ghost.getName(), ghost.isFree());
        this.ghost = ghost;
    }

    public HolyBadGhost(int id, String name, boolean free, BadGhost ghost) {
        super(id, name, free);
        this.ghost = ghost;
    }

    private HolyBadGhost() {
    }

    @Override
    public void appear() {
        ghost.appear();
        System.out.println(HOLY_GHOST_INTRO);
    }

    @Override
    public void afterRiddleIsAsked(boolean answer) {
        if (!answer) {
            System.out.println(HOLY_GHOST_WRONG_CHOICE);
            Player.getPlayer().getRoomHistory().clear();
            Player.getPlayer().getRoomHistory().add(1);
        } else {
            ghost.afterRiddleIsAsked(true);
        }
    }
}
