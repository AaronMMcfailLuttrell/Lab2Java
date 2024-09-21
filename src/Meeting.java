import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable {
    private boolean complete;
    private LocalDateTime endDateTime;
    private String location;
    public void complete() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    Duration getDuration() {
        return Duration.between(getDateTime(), getEndDateTime());
    }

    String getLocation() {
        return location;
    }

    void setEndDateTime(LocalDateTime end) {
        endDateTime = end;
    }

    void setLocation(String location) {
        this.location = location;
    }

    Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        this.setName(name);
        this.setDateTime(start);
        this.setEndDateTime(end);
        this.setLocation(location);
    }

    public String getDisplayString() {
        String display = super.getDisplayString();


        if (getDuration() != null) {
            display += ", Duration: " + getDuration().toString();
        }
        if (getLocation() != null) {
            display += ", Location: " + getLocation();
        }

        return display;
    }

}
