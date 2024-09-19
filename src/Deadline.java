import java.time.LocalDateTime;

public class Deadline extends Event implements Completable{

    boolean complete;
    public void complete() {
        complete = true;
    }
    public boolean isComplete() {
        return complete;
    }

    Deadline(String name, LocalDateTime deadline) {
        this.setName(name);
        this.setDateTime(deadline);
    }
}
