package house.of.usher.maze.rooms;

public enum RoomNames {
    RODERICK_BEDROOM("Roderick room"),
    MADELINE_BEDROOM("Madeline room"),
    LIBRARY("Library");

    private String name;

    RoomNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
