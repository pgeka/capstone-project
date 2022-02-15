package house.of.usher.maze.rooms;

import house.of.usher.maze.Message;
import house.of.usher.maze.ghosts.Ghost;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.MessageFormat;
import java.util.Objects;

@Entity
public class CommonRoom implements Room {
    @Id
    private int id;

    private String title;

    public CommonRoom() {
    }

    public CommonRoom(int id) {
        this.id = id;
    }

    public CommonRoom(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonRoom that = (CommonRoom) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "CommonRoom{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public void openWindow(Ghost ghost) {
    }

    @Override
    public void welcome() {
        System.out.println(MessageFormat.format(Message.WELCOME, getTitle()));
    }
}
