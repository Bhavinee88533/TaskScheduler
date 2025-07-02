import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

public class TaskScheduler {
    private static List<task> tasks = new ArrayList<>();
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Task\n2. View Tasks\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                addTask();
            } else if (choice == 2) {
                viewTasks();
            } else {
                System.out.println("Exiting...");
                scheduler.shutdown();
                break;
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();

        System.out.print("Enter deadline (yyyy-MM-dd HH:mm): ");
        String deadlineStr = sc.nextLine();

        try {
            LocalDateTime deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            task task = new task(title, deadline);
            tasks.add(task);
            scheduleReminder(task);
            System.out.println("Task added!");
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (task t : tasks) {
                System.out.println(t);
            }
        }
    }

    private static void scheduleReminder(task task) {
        long delay = Duration.between(LocalDateTime.now(), task.getDeadline()).toMillis();
        if (delay > 0) {
            scheduler.schedule(() -> {
                System.out.println(" Reminder: " + task.getTitle() + " is due now!");
            }, delay, TimeUnit.MILLISECONDS);
        } else {
            System.out.println("Deadline is in the past. Reminder not scheduled.");
        }
    }
}
