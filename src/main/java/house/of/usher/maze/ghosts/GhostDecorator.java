package house.of.usher.maze.ghosts;

public abstract class GhostDecorator extends CommonGhost{
    public GhostDecorator(int id, String name, boolean free) {
        super(id, name, free);
    }

    public GhostDecorator() {
    }
}
