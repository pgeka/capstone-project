package house.of.usher.maze.riddles;

import house.of.usher.maze.Message;

import java.text.MessageFormat;
import java.util.List;
import java.util.Timer;

public class SimpleRiddle extends CommonRiddle {

    public void startTimer() {
        super.timer = new Timer();
        super.timer.schedule(new RiddleTimerTask(), 10000);
        System.out.println(MessageFormat.format(Message.TIME_LIMIT, 10));
    }

    public SimpleRiddle() {
    }

    private SimpleRiddle(SimpleRiddle.Builder builder) {
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

        public SimpleRiddle.Builder id(int id) {
            this.id = id;
            return this;
        }

        public SimpleRiddle.Builder riddleText(String riddleText) {
            this.riddleText = riddleText;
            return this;
        }

        public SimpleRiddle.Builder options(List<String> options) {
            this.options = options;
            return this;
        }

        public SimpleRiddle.Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public SimpleRiddle build() {
            return new SimpleRiddle(this);
        }
    }
}
