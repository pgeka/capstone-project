package house.of.usher.maze.riddles;

import house.of.usher.maze.Message;
import house.of.usher.maze.Player;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Entity
public class CommonRiddle implements Riddle {

    @Id
    protected int id;

    protected String riddleText;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "riddle_options")
    protected List<String> options;

    protected String answer;

    @Transient
    protected Timer timer;

    public CommonRiddle() {
    }

    public CommonRiddle(int id, String riddleText, List<String> options, String answer) {
        this.id = id;
        this.riddleText = riddleText;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRiddleText() {
        return riddleText;
    }

    public void setRiddleText(String riddleText) {
        this.riddleText = riddleText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public void startTimer() {
    }

    @Override
    public void endTimer() {
        if (Objects.nonNull(timer)) {
            timer.cancel();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonRiddle that = (CommonRiddle) o;
        return id == that.id &&
                Objects.equals(riddleText, that.riddleText) &&
                Objects.equals(options, that.options) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(timer, that.timer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, riddleText, options, answer, timer);
    }

    @Override
    public String toString() {
        return "CommonRiddle{" +
                "id=" + id +
                ", riddleText='" + riddleText + '\'' +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                ", timer=" + timer +
                '}';
    }

    protected class RiddleTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println(Message.SO_SLOW);
            Player.getPlayer().decreaseHealthPoints();
        }
    }
}
