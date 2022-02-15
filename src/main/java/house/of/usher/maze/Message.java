package house.of.usher.maze;

public interface Message {
    String FINISH_MAZE = "You have finished the maze! Now you are a little bit enlightened.";
    String CHOOSE_NEXT_DOOR = "What door do you want to go through? Feel free to choose one of the following: ";
    String RIGHT_CHOICE = "Congratulations! Good for you! For now, you are able to choose next door. Be careful and so long!";
    String WRONG_CHOICE = "Unfortunately, you have answered incorrectly. Go away! Leave now and never come back!";
    String BAD_GHOST_PUNISHMENT = "That's why you are punished by throwing you to one room back. Try best next time!";
    String GOOD_GHOST_REWARD = "Let me suggest the next room. According to the map, way out passes through: ";
    String GOOD_GHOST_REWARD_SHORTEST_WAY_OUT = "Let me suggest the next room. According to the map, the shortest way out passes through: ";
    String BAD_GHOST_RIGHT_CHOICE = "You aren’t as silly as I expected. Farewell.";
    String DEMON_RIGHT_CHOICE = "Anyway, I won't be you competitor. Go forward before I change my mind.";
    String GOOD_GHOST_WRONG_CHOICE = "Don't worry. It happens sometimes. Go in peace.";
    String SET_GHOST_FREE_NOTIFICATION = "Before you leave room, you are allowed to set ghost free. " +
            "But keep in mind, since ghost become free, it won't drops a hint never again. Set ghost free?";
    String EMPTY_ROOM = "Room is empty. It seems ghost was here some time ago.";
    String HOLY_GHOST_INTRO = "It's not usual ghost you have seen before. Holy ghost like this one has a great power!";
    String HOLY_GHOST_WRONG_CHOICE = "The ghost is totally angry! You will be teleported to the start room.";
    String FOLLOWING_DEMON = "Demon appears. It follows you since that moment and further until maze are finished";
    String DEMON_APPEARS = "Demon appears. It's trying to finish the maze as faster as you are. When demon finish the maze first, you loose one HP";
    String DEMON_WRONG_CHOICE = "You get wrong. You better go faster to finish.";
    String SPEED_THINGS_UP = "Demon do next step. For now it is entering to room {0}. You better speed things up!";
    String SHOULD_BE_NUMBER = "Value should be number.";
    String SHOULD_BE_MATCH = "Value should match one of specified number.";
    String WELCOME = "Welcome to {0}! Shh.. Do you feel it? There is mysterious vibe here.";
    String GHOST_GETTING_FREE = "Ghost is getting free dramatically... You will never see it again if you come back to the room.";
    String NO_SUCH_GHOST = "There is no such ghost yet.";
    String SO_SLOW = "So slow. Your time is over. That's why you have lost 1 health point.";
    String TIME_LIMIT = "You have only {0} seconds to answer correctly.";
    String GHOST_APPEARS = "Ghost of {0} appears here!";
    String MAZE_FINISHED_BY_DEMON = "Demon has finished the maze first!";
    String FAILED_CONNECTION_ESTABLISHMENT = "There is error during {0} connection establishment. ";
    String SUCCESS_CONNECTION_ESTABLISHMENT = "{0} connection is established.";
    String END_GAME = "Sorry, you are died.";
}
