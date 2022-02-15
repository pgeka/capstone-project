package house.of.usher.maze.ghosts;

public class GhostFactory {
    public Ghost getGhost(GhostNames name) {
        switch (name) {
            case GOOD:
                return new GoodGhost();
            case BAD:
                return new BadGhost();
            default:
                return null;
        }
    }
}
