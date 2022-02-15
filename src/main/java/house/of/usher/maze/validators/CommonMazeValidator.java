package house.of.usher.maze.validators;

import house.of.usher.maze.exceptions.InvalidAnswerOptionException;
import house.of.usher.maze.exceptions.InvalidFreeOptionException;
import house.of.usher.maze.exceptions.InvalidRoomNumberException;

import java.util.Arrays;
import java.util.List;

public class CommonMazeValidator {
    public static void validateMatchingSpecifiedRooms(List<Integer> doors, int nextDoor) {
        if (!doors.contains(nextDoor)) {
            throw new InvalidRoomNumberException();
        }
    }

    public static void validateSettingGhostFreeOption(int option) {
        if (option != 1 && option != 2) {
            throw new InvalidFreeOptionException();
        }
    }

    public static void validateAnswerOption(int option) {
        if (!Arrays.asList(1, 2, 3).contains(option)) {
            throw new InvalidAnswerOptionException();
        }
    }
}
