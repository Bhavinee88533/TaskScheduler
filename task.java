import java.time.LocalDateTime;

public class task {
    private String title;
    private LocalDateTime deadline;

    public task(String title, LocalDateTime deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Task: " + title + " | Deadline: " + deadline;
    }
}
