package house.of.usher.maze;

import java.util.LinkedList;
import java.util.Observable;

public class Player extends Observable {
    public static final int TOTAL_HP = 10;

    private static volatile Player player;
    private static int healthPoints = TOTAL_HP;
    private LinkedList<Integer> roomHistory = new LinkedList<>();

    private Player() {
    }

    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    public LinkedList<Integer> getRoomHistory() {
        return roomHistory;
    }

    public void addRoomToHistory(int roomNumber) {
        roomHistory.add(roomNumber);
        setChanged();
        notifyObservers(ObserverType.ADD_ROOM);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void increaseHealthPoints() {
        Player.healthPoints++;
        setChanged();
        notifyObservers(ObserverType.CHANGE_HP);
    }

    public void decreaseHealthPoints() {
        Player.healthPoints--;
        setChanged();
        notifyObservers(ObserverType.CHANGE_HP);
    }

    public enum ObserverType{
        ADD_ROOM,
        CHANGE_HP
    }
}
