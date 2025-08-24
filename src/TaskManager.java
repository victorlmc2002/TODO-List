import java.util.*;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }
    
    public void addTask(Task task) {
        tasks.add(task);
        tasks.sort(Comparator.comparingInt(Task::getPrioridade));
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void updateTask(int index, Task newTask) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, newTask);
            tasks.sort(Comparator.comparingInt(Task::getPrioridade));
        }
        else {
            System.out.println("Índice inválido para atualização de tarefa.");
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getTasksByCategory(String category) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getCategoria().equalsIgnoreCase(category)) {
                result.add(t);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada na categoria: " + category);
        }
        return result;
    }

    public List<Task> getTasksByPriority(int priority) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPrioridade() == priority) {
                result.add(t);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada com prioridade " + priority);
        }
        return result;
    }

    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getStatus().equals(status)) {
                result.add(t);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada com status " + status);
        }
        return result;
    }

    public int countByStatus(String status) {
        int count = 0;
        for (Task t : tasks) {
            if (t.getStatus().equals(status)) {
                count++;
            }
        }
        return count;
    }
}
