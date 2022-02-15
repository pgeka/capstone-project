package house.of.usher.maze.ghosts;

import house.of.usher.maze.MazeComponent;
import house.of.usher.maze.Player;

import java.util.Observable;
import java.util.Observer;

import static house.of.usher.maze.Message.DEMON_APPEARS;
import static house.of.usher.maze.Message.DEMON_RIGHT_CHOICE;
import static house.of.usher.maze.Message.DEMON_WRONG_CHOICE;

public class GhostDemonAdapter extends CommonGhost implements Observer {
    private DemonImpl demon;

    public GhostDemonAdapter(Demon demon) {
        this.demon = (DemonImpl) demon;
    }

    public int getId() {
        return demon.getId();
    }

    public void setId(int id) {
        demon.setId(id);
    }

    public String getName() {
        return demon.getName();
    }

    public void setName(String name) {
        demon.setName(name);
    }

    public boolean isFree() {
        return demon.isFree();
    }

    public void setFree(boolean free) {
        demon.setFree(false);
    }

    @Override
    public void appear() {
        System.out.println(DEMON_APPEARS);
    }

    @Override
    public void afterRiddleIsAsked(boolean answer) {
        if (answer) {
            System.out.println(DEMON_RIGHT_CHOICE);
        } else {
            MazeComponent.demonObserver = this;
            Player.getPlayer().addObserver(MazeComponent.demonObserver);
            System.out.println(DEMON_WRONG_CHOICE);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (Player.ObserverType.ADD_ROOM != arg) {
            return;
        }
        demon.nextStep();
    }
}
