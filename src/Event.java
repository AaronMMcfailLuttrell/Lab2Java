import java.time.LocalDateTime;

public abstract class Event implements Comparable<Event> {
    private String name;
    private LocalDateTime dateTime;
    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Event e) {
        return dateTime.compareTo(e.dateTime);
    }

    public String getDisplayString() {
        String display = "";
        display += "Name: " + getName();
        display += ", Start Time: " + getDateTime();
        return display;
    }

}
