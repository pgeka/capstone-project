package house.of.usher.maze.ghosts;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GhostService {
    private final GhostRepository ghostRepository;

    public GhostService(GhostRepository ghostRepository) {
        this.ghostRepository = ghostRepository;
    }

    @Transactional
    public CommonGhost getGhostById(int id) {
        return ghostRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<CommonGhost> getAllGhosts() {
        return ghostRepository.findAll();
    }
}
