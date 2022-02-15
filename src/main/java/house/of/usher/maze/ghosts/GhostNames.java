package house.of.usher.maze.ghosts;

public enum GhostNames {
    GOOD("Good Ghost"),
    BAD("Bad Ghost");

    private String name;

    GhostNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
