package house.of.usher.maze;

import house.of.usher.maze.exceptions.InvalidAnswerOptionException;
import house.of.usher.maze.exceptions.InvalidFreeOptionException;
import house.of.usher.maze.exceptions.InvalidRoomNumberException;
import house.of.usher.maze.ghosts.CommonGhost;
import house.of.usher.maze.ghosts.Ghost;
import house.of.usher.maze.riddles.CommonRiddle;
import house.of.usher.maze.riddles.Riddle;
import house.of.usher.maze.rooms.Room;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static house.of.usher.maze.Message.EMPTY_ROOM;
import static house.of.usher.maze.Message.FINISH_MAZE;
import static house.of.usher.maze.Message.SHOULD_BE_MATCH;
import static house.of.usher.maze.Message.SHOULD_BE_NUMBER;
import static house.of.usher.maze.validators.CommonMazeValidator.validateAnswerOption;
import static house.of.usher.maze.validators.CommonMazeValidator.validateMatchingSpecifiedRooms;
import static house.of.usher.maze.validators.CommonMazeValidator.validateSettingGhostFreeOption;

@Component
public class MazeComponent {

    //todo: proxy -- aop -- logging -- functional interface as argument

    private static final Logger logger = Logger.getLogger(MazeComponent.class);

    public static final int FINISH_POINT = 8;
    public static final Map<Integer, List<Integer>> GRAPH = new HashMap<>();
    public static Observer demonObserver;

    static {
        GRAPH.put(1, Arrays.asList(2, 3, 4));
        GRAPH.put(2, Arrays.asList(1, 3, 4));
        GRAPH.put(3, Arrays.asList(1, 2, 5));
        GRAPH.put(4, Arrays.asList(1, 2, 5));
        GRAPH.put(5, Arrays.asList(3, 4, 6));
        GRAPH.put(6, Arrays.asList(5, 7, 9));
        GRAPH.put(7, Arrays.asList(6, 8, 9));
        GRAPH.put(8, Arrays.asList(9, 7));
        GRAPH.put(9, Arrays.asList(6, 7, 8));
    }

    public static Map<Integer, Ghost> ghostsCache = new HashMap<>();

    private final KindContentFactory kindContentFactory;
    private final EvilContentFactory evilContentFactory;
    private final HOAFacade hoaFacade;

    public MazeComponent(KindContentFactory kindContentFactory,
                         EvilContentFactory evilContentFactory,
                         HOAFacade hoaFacade) {
        this.kindContentFactory = kindContentFactory;
        this.evilContentFactory = evilContentFactory;
        this.hoaFacade = hoaFacade;
    }

    @PostConstruct
    public void start() {
        Player.getPlayer().addRoomToHistory(1);
        Player.getPlayer().addObserver(new PlayerObserver());
        runScript(kindContentFactory, 1);
    }

    private void runScript(ContentAbstractFactory factory, int roomNumber) {
        Ghost ghost = (BigInteger.valueOf(roomNumber).isProbablePrime((int) Math.log(roomNumber)))
                ? (roomNumber == 1)
                ? factory.getDemonGhostPreset(roomNumber)
                : factory.getHolyGhostPreset(roomNumber)
                : factory.getGhostPreset(roomNumber);
        Riddle riddle = factory.getRiddlePreset(roomNumber);
        Room room = factory.getRoomPreset(roomNumber);

        HOAFacade.Triple triple = hoaFacade.getAllRelatedData(roomNumber);
        logger.debug(triple.toString());

        CommonGhost commonGhost = (CommonGhost) ghost;

        if (!commonGhost.isFree()) {
            room.welcome();
            ghost.appear();
            boolean answerState = askRiddle(riddle);
            ghost.afterRiddleIsAsked(answerState);
            setGhostFree(room, ghost, answerState);
        } else {
            System.out.println(EMPTY_ROOM);
            logger.info(EMPTY_ROOM);
        }

        roomNumber = chooseNextRoom(Player.getPlayer().getRoomHistory().getLast());

        Player.getPlayer().addRoomToHistory(roomNumber);

        if (roomNumber != FINISH_POINT) {
            runScript((roomNumber % 2 == 0)
                            ? kindContentFactory
                            : evilContentFactory,
                    Player.getPlayer().getRoomHistory().getLast());
        } else {
            System.out.println(FINISH_MAZE);
            logger.info(FINISH_MAZE);
        }
    }

    private void setGhostFree(Room room, Ghost ghost, boolean answerState) {
        if (answerState) {
            System.out.println(Message.SET_GHOST_FREE_NOTIFICATION);
            System.out.println("Type [1] for setting ghost free. Otherwise, [2] is you option:");
            int choice = 0;
            Scanner scanner = new Scanner(System.in);
            while (choice != 1 && choice != 2) {
                try {
                    choice = scanner.nextInt();
                    validateSettingGhostFreeOption(choice);
                } catch (InputMismatchException e) {
                    System.out.println(SHOULD_BE_NUMBER);
                    logger.error(SHOULD_BE_NUMBER);
                } catch (InvalidFreeOptionException e) {
                    System.out.println(SHOULD_BE_MATCH);
                    logger.error(SHOULD_BE_MATCH);
                } finally {
                    scanner.nextLine();
                }
            }
            if (choice == 1) {
                room.openWindow(ghost);
            }
        }
    }

    private int chooseNextRoom(int roomNumber) {
        List<Integer> doors = GRAPH.get(roomNumber);
        //todo: check choosing room possibility

        System.out.println(Message.CHOOSE_NEXT_DOOR);
        doors.forEach(e -> System.out.println(String.format("[%d]", e)));

        int nextDoor = 0;
        Scanner scanner = new Scanner(System.in);
        while (nextDoor == 0 || !doors.contains(nextDoor)) {
            try {
                nextDoor = scanner.nextInt();
                validateMatchingSpecifiedRooms(doors, nextDoor);
            } catch (InputMismatchException e) {
                System.out.println(SHOULD_BE_NUMBER);
                logger.error(SHOULD_BE_NUMBER);
            } catch (InvalidRoomNumberException e) {
                System.out.println(SHOULD_BE_MATCH);
                logger.error(SHOULD_BE_MATCH);
            } finally {
                scanner.nextLine();
            }
        }
        return nextDoor;
    }

    private boolean askRiddle(Riddle riddle) {
        riddle.startTimer();
        CommonRiddle commonRiddle = (CommonRiddle) riddle;
        System.out.println(commonRiddle.getRiddleText());
        AtomicInteger optionNumber = new AtomicInteger(1);
        commonRiddle.getOptions().forEach(e ->
                System.out.println(String.format("[%d] %s ", optionNumber.getAndIncrement(), e)));
        System.out.println("Your option is: ");
        int userAnswer = 0;
        Scanner scanner = new Scanner(System.in);
        while (!Arrays.asList(1, 2, 3).contains(userAnswer)) {
            try {
                userAnswer = scanner.nextInt();
                validateAnswerOption(userAnswer);
            } catch (InputMismatchException e) {
                System.out.println(SHOULD_BE_NUMBER);
                logger.error(SHOULD_BE_NUMBER);
            } catch (InvalidAnswerOptionException e) {
                System.out.println(SHOULD_BE_MATCH);
                logger.error(SHOULD_BE_MATCH);
            } finally {
                scanner.nextLine();
            }
        }
        riddle.endTimer();
        return commonRiddle.getOptions().indexOf(commonRiddle.getAnswer()) + 1 == userAnswer;
    }
}
