package house.of.usher.maze.rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<CommonRoom, Integer>, JpaRepository<CommonRoom, Integer> {
}
