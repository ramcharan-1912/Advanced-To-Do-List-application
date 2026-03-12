import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<String> tasks = new ArrayList<>();
    static ArrayList<String> priorities = new ArrayList<>();
    static ArrayList<String> dueDates = new ArrayList<>();
    static ArrayList<Boolean> completed = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== TO DO LIST MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task Completed");
            System.out.println("4. Edit Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Search Task");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {

                case 1:
                    addTask();
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    markCompleted();
                    break;

                case 4:
                    editTask();
                    break;

                case 5:
                    deleteTask();
                    break;

                case 6:
                    searchTask();
                    break;

                case 7:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addTask() {

        System.out.print("Enter task: ");
        String task = sc.nextLine();

        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = sc.nextLine();

        System.out.print("Enter due date: ");
        String dueDate = sc.nextLine();

        tasks.add(task);
        priorities.add(priority);
        dueDates.add(dueDate);
        completed.add(false);

        System.out.println("Task added successfully!");
    }

    static void viewTasks() {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\n------ TASK LIST ------");

        for (int i = 0; i < tasks.size(); i++) {

            String status = completed.get(i) ? "Completed" : "Pending";

            System.out.println((i + 1) + ". " + tasks.get(i)
                    + " | Priority: " + priorities.get(i)
                    + " | Due Date: " + dueDates.get(i)
                    + " | Status: " + status);
        }
    }

    static void markCompleted() {

        viewTasks();

        if (tasks.isEmpty())
            return;

        System.out.print("Enter task number: ");

        int num = Integer.parseInt(sc.nextLine());

        if (num > 0 && num <= tasks.size()) {

            completed.set(num - 1, true);
            System.out.println("Task marked as completed.");

        } else {

            System.out.println("Invalid task number.");
        }
    }

    static void editTask() {

        viewTasks();

        if (tasks.isEmpty())
            return;

        System.out.print("Enter task number to edit: ");

        int num = Integer.parseInt(sc.nextLine());

        if (num > 0 && num <= tasks.size()) {

            System.out.print("Enter new task: ");
            String newTask = sc.nextLine();

            tasks.set(num - 1, newTask);

            System.out.println("Task updated successfully.");

        } else {

            System.out.println("Invalid task number.");
        }
    }

    static void deleteTask() {

        viewTasks();

        if (tasks.isEmpty())
            return;

        System.out.print("Enter task number to delete: ");

        int num = Integer.parseInt(sc.nextLine());

        if (num > 0 && num <= tasks.size()) {

            tasks.remove(num - 1);
            priorities.remove(num - 1);
            dueDates.remove(num - 1);
            completed.remove(num - 1);

            System.out.println("Task deleted successfully.");

        } else {

            System.out.println("Invalid task number.");
        }
    }

    static void searchTask() {

        System.out.print("Enter keyword to search: ");

        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).toLowerCase().contains(keyword)) {

                System.out.println((i + 1) + ". " + tasks.get(i)
                        + " | Priority: " + priorities.get(i)
                        + " | Due Date: " + dueDates.get(i));

                found = true;
            }
        }

        if (!found) {

            System.out.println("No matching task found.");
        }
    }
}