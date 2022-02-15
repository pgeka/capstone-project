package house.of.usher.maze.rooms;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public CommonRoom getRiddleById(int id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<CommonRoom> getAllRiddles() {
        return roomRepository.findAll();
    }
}
