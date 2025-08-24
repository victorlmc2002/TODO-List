import java.util.*;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // Ordena tarefas por um parâmetro específico
    public void sortTasksBy(String parametro) {
        switch (parametro.toLowerCase()) {
            case "nome":
                tasks.sort(Comparator.comparing(Task::getNome));
                break;
            case "prioridade":
                tasks.sort(Comparator.comparingInt(Task::getPrioridade));
                break;
            case "prazo":
                tasks.sort(Comparator.comparing(Task::getPrazo));
                break;
            case "categoria":
                tasks.sort(Comparator.comparing(Task::getCategoria));
                break;
            case "status":
                tasks.sort(Comparator.comparing(Task::getStatus));
                break;
            default:
                System.out.println("Parâmetro de ordenação inválido. Opções: nome, prioridade, prazo, categoria, status.");
        }
    }

    // Adiciona uma nova tarefa e mantém a lista ordenada por prioridade
    public void addTask(Task task) {
        tasks.add(task);
        sortTasksBy("prioridade");
    }

    // Remove uma tarefa pelo índice
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    // Atualiza uma tarefa existente e mantém a lista ordenada por prioridade
    public void updateTask(int index, Task newTask) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, newTask);
            sortTasksBy("prioridade");
        }
        else {
            System.out.println("Índice inválido para atualização de tarefa.");
        }
    }

    // Retorna todas as tarefas
    public List<Task> getAllTasks() {
        return tasks;
    }

    // Retorna tarefas filtradas por categoria
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

    // Retorna tarefas filtradas por prioridade
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

    // Retorna tarefas filtradas por status
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

    // Conta tarefas por status
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
