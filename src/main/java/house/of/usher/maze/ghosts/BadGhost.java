package house.of.usher.maze.ghosts;

import house.of.usher.maze.Player;

import java.text.MessageFormat;

import static house.of.usher.maze.Message.BAD_GHOST_PUNISHMENT;
import static house.of.usher.maze.Message.BAD_GHOST_RIGHT_CHOICE;
import static house.of.usher.maze.Message.GHOST_APPEARS;
import static house.of.usher.maze.Message.RIGHT_CHOICE;
import static house.of.usher.maze.Message.WRONG_CHOICE;

public class BadGhost extends CommonGhost {

    public BadGhost(int id, String name, boolean free) {
        super(id, name, free);
    }

    public BadGhost() {
    }

    private BadGhost(Builder builder) {
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

        public BadGhost build() {
            return new BadGhost(this);
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
            System.out.println(MessageFormat.format("{0} said: {1}", getName(), BAD_GHOST_RIGHT_CHOICE));
        } else {
            System.out.println(WRONG_CHOICE);
            System.out.println(MessageFormat.format("{0} said: {1}", getName(), BAD_GHOST_PUNISHMENT));
            if (Player.getPlayer().getRoomHistory().size() > 1) {
                Player.getPlayer().getRoomHistory().removeLast();
            }
        }
    }
}
