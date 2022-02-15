package house.of.usher.maze;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

public class PlayerObserver implements Observer {

    private static final Logger logger = Logger.getLogger(PlayerObserver.class);

    @Override
    public void update(Observable o, Object arg) {
        if (Player.ObserverType.CHANGE_HP != arg) {
            return;
        }
        if (Player.getPlayer().getHealthPoints() == 0) {
            System.out.println(Message.END_GAME);
            System.exit(0);
        }
        System.out.println(MessageFormat.format("You have {0}/{1} health points",
                Player.getPlayer().getHealthPoints(), Player.TOTAL_HP));
        logger.info(MessageFormat.format("HP status has changed. HP = {0}",
                Player.getPlayer().getHealthPoints()));
    }
}
