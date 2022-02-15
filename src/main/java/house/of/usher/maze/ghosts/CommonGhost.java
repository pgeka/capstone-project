package house.of.usher.maze.ghosts;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CommonGhost implements Ghost {
    @Id
    private int id;

    private String name;

    private boolean free;

    public CommonGhost(int id, String name, boolean free) {
        this.id = id;
        this.name = name;
        this.free = free;
    }

    public CommonGhost() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public void appear() {
    }

    @Override
    public void afterRiddleIsAsked(boolean answer) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonGhost that = (CommonGhost) o;
        return id == that.id &&
                free == that.free &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, free);
    }

    @Override
    public String toString() {
        return "CommonGhost{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", free=" + free +
                '}';
    }
}
