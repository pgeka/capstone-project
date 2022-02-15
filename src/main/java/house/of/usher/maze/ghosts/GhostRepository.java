package house.of.usher.maze.ghosts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GhostRepository extends CrudRepository<CommonGhost, Integer>, JpaRepository<CommonGhost, Integer> {
}
