package house.of.usher.maze.riddles;

import house.of.usher.maze.Message;

import java.text.MessageFormat;
import java.util.List;
import java.util.Timer;

public class DifficultRiddle extends CommonRiddle {

    public DifficultRiddle() {
    }

    private DifficultRiddle(Builder builder) {
        this.id = builder.id;
        this.riddleText = builder.riddleText;
        this.options = builder.options;
        this.answer = builder.answer;
    }

    public static class Builder {
        private int id;
        private String riddleText;
        private List<String> options;
        private String answer;

        public Builder(int id, String riddleText, String answer, List<String> options) {
            this.id = id;
            this.riddleText = riddleText;
            this.answer = answer;
            this.options = options;
        }

        public Builder() {
        }

        public DifficultRiddle.Builder id(int id) {
            this.id = id;
            return this;
        }

        public DifficultRiddle.Builder riddleText(String riddleText) {
            this.riddleText = riddleText;
            return this;
        }

        public DifficultRiddle.Builder options(List<String> options) {
            this.options = options;
            return this;
        }

        public DifficultRiddle.Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public DifficultRiddle build() {
            return new DifficultRiddle(this);
        }
    }

    public void startTimer() {
        super.timer = new Timer();
        super.timer.schedule(new RiddleTimerTask(), 5000);
        System.out.println(MessageFormat.format(Message.TIME_LIMIT, 5));
    }
}
