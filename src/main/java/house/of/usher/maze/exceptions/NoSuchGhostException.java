package house.of.usher.maze.exceptions;

import house.of.usher.maze.Message;
import house.of.usher.maze.rooms.LightRoom;
import org.apache.log4j.Logger;

public class NoSuchGhostException extends RuntimeException {

    private static final Logger logger = Logger.getLogger(LightRoom.class);

    public NoSuchGhostException() {
        super();
        logger.error(Message.NO_SUCH_GHOST);
    }

    public NoSuchGhostException(String message) {
        super(message);
        logger.error(message);
    }
}
