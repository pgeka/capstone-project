package house.of.usher.maze;

import house.of.usher.maze.ghosts.Ghost;
import house.of.usher.maze.ghosts.GhostService;
import house.of.usher.maze.riddles.Riddle;
import house.of.usher.maze.riddles.RiddleService;
import house.of.usher.maze.rooms.Room;
import house.of.usher.maze.rooms.RoomService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HOAFacade {

    private final RiddleService riddleService;
    private final GhostService ghostService;
    private final RoomService roomService;

    public HOAFacade(RiddleService riddleService,
                     GhostService ghostService,
                     RoomService roomService) {
        this.riddleService = riddleService;
        this.ghostService = ghostService;
        this.roomService = roomService;
    }

    public Triple<Ghost, Riddle, Room> getAllRelatedData(int roomNumber) {
        Ghost ghost = ghostService.getGhostById(roomNumber);
        Riddle riddle = riddleService.getRiddleById(roomNumber);
        Room room = roomService.getRiddleById(roomNumber);
        return new Triple<>(ghost, riddle, room);
    }

    public class Triple<T extends Ghost, U extends Riddle, V extends Room> {

        private final T first;
        private final U second;
        private final V third;

        public Triple(T first, U second, V third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }

        public V getThird() {
            return third;
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "first=" + first +
                    ", second=" + second +
                    ", third=" + third +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triple<? extends Ghost, ? extends Riddle, ? extends Room> triple = (Triple<?, ?, ?>) o;
            return Objects.equals(first, triple.first) &&
                    Objects.equals(second, triple.second) &&
                    Objects.equals(third, triple.third);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }
    }
}
