package house.of.usher.maze.riddles;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RiddleService {
    private final RiddleRepository riddleRepository;

    public RiddleService(RiddleRepository riddleRepository) {
        this.riddleRepository = riddleRepository;
    }

    @Transactional
    public CommonRiddle getRiddleById(int id) {
        return riddleRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<CommonRiddle> getAllRiddles() {
        return riddleRepository.findAll();
    }
}
