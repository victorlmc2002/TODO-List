import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new LinkedList<>();
    }

    // Scanner compartilhado
    private static Scanner sc = new Scanner(System.in);

    // Inicializa tarefas de exemplo e inicia o loop principal
    public void iniciarApp() {
        boolean running = true;
        System.out.println("Bem-vindo ao ZG-Hero TODO List!");
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Remover tarefa");
            System.out.println("3. Listar todas as tarefas");
            System.out.println("4. Listar por categoria");
            System.out.println("5. Listar por prioridade");
            System.out.println("6. Listar por status");
            System.out.println("7. Contar tarefas por status");
            System.out.println("8. Atualizar tarefa");
            System.out.println("9. Ordenar tarefas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int op = sc.nextInt();

            switch (op) {
                case 1:
                    criaTask();
                    break;
                case 2:
                    removerTarefa();
                    break;
                case 3:
                    listarTarefas();
                    break;
                case 4:
                    listarCategoria();
                    break;
                case 5:
                    listarPrioridade();
                    break;
                case 6:
                    listarStatus();
                    break;
                case 7:
                    contarTarefasStatus();
                    break;
                case 8:
                    atualizarTarefa();
                    break;
                case 9:
                    ordenarTarefas();
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }

    // Remove tarefa pelo índice informado
    public void removerTarefa() {
        try {
            System.out.print("Índice da tarefa para remover: ");
            int idx = sc.nextInt();
            sc.nextLine();
            removeTask(idx);
            System.out.println("Tarefa removida!");
            wait(2);
        } catch(Exception e) {
            System.out.println("Erro ao remover tarefa: " + e.getMessage());
        }
    }

    // Lista todas as tarefas
    public void listarTarefas() {
        System.out.println("Todas as tarefas:");
        int i = 0;
        for (Task task : getAllTasks()) {
            System.out.println((i+1) + ": " + task);
            i++;
        }
        continuar();
    }

    // Lista tarefas por categoria
    public void listarCategoria() {
        System.out.print("Categoria: ");
        String cat = sc.nextLine();
        for (Task task : getTasksByCategory(cat)) {
            System.out.println(task);
        }
        continuar();
    }

    // Lista tarefas por prioridade
    public void listarPrioridade() {
        System.out.print("Prioridade: ");
        int prio = sc.nextInt();
        sc.nextLine();
        for (Task task : getTasksByPriority(prio)) {
            System.out.println(task);
        }
        continuar();
    }

    // Lista tarefas por status
    public void listarStatus() {
        String status = "";
        sc.nextLine();
        while(!status.equals("TODO") && !status.equals("DOING") && !status.equals("DONE")) {
            System.out.print("Status (TODO, DOING, DONE): ");
            status = sc.nextLine().toUpperCase();
        }
        for (Task task : getTasksByStatus(status)){
            System.out.println(task);
        }
        continuar();
    }

    // Conta tarefas por status
    public void contarTarefasStatus() {
        String status = "";
        sc.nextLine();
        while(!status.equals("TODO") && !status.equals("DOING") && !status.equals("DONE")) {
            System.out.print("Status (TODO, DOING, DONE): ");
            status = sc.nextLine().toUpperCase();
        }
        int count = countByStatus(status);
        System.out.println("Total: " + count);
        continuar();
    }

    // Atualiza tarefa existente
    public void atualizarTarefa() {
        System.out.print("Índice da tarefa para atualizar: ");
        int idx = sc.nextInt() - 1;
        sc.nextLine();
        Task tarefa = null;
        try {
            tarefa = getAllTasks().get(idx);
        } catch (Exception e) {
            System.out.println("Índice inválido!");
            return;
        }
        System.out.println("Deixe em branco para manter o valor atual.");
        System.out.println("Nome atual: " + tarefa.getNome());
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()) tarefa.setNome(nome);

        System.out.println("Descrição atual: " + tarefa.getDescricao());
        System.out.print("Nova descrição: ");
        String descricao = sc.nextLine();
        if (!descricao.isEmpty()) tarefa.setDescricao(descricao);

        System.out.println("Prazo atual: " + tarefa.getPrazo());
        System.out.print("Novo prazo (yyyy-mm-dd): ");
        String prazoStr = sc.nextLine();
        if (!prazoStr.isEmpty()) {
            try {
                tarefa.setPrazo(LocalDate.parse(prazoStr));
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Prazo mantido.");
            }
        }

        System.out.println("Prioridade atual: " + tarefa.getPrioridade());
        System.out.print("Nova prioridade (1-5): ");
        String prioridadeStr = sc.nextLine();
        if (!prioridadeStr.isEmpty()) {
            try {
                int prioridade = Integer.parseInt(prioridadeStr);
                if (prioridade >= 1 && prioridade <= 5) tarefa.setPrioridade(prioridade);
                else System.out.println("Prioridade inválida. Mantida.");
            } catch (Exception e) {
                System.out.println("Valor inválido. Mantida.");
            }
        }

        System.out.println("Categoria atual: " + tarefa.getCategoria());
        System.out.print("Nova categoria: ");
        String categoria = sc.nextLine();
        if (!categoria.isEmpty()) tarefa.setCategoria(categoria);

        System.out.println("Status atual: " + tarefa.getStatus());
        System.out.print("Novo status (TODO, DOING, DONE): ");
        String status = sc.nextLine().toUpperCase();
        if (!status.isEmpty() && (status.equals("TODO") || status.equals("DOING") || status.equals("DONE"))) {
            tarefa.setStatus(status);
        }

        updateTask(idx, tarefa);
        System.out.println("Tarefa atualizada!");
        wait(2);
    }

    // Ordena tarefas por parâmetro
    public void ordenarTarefas() {
        String criterio = "";
        sc.nextLine();
        while (!criterio.equals("nome") && !criterio.equals("prioridade") && !criterio.equals("prazo") && !criterio.equals("categoria") && !criterio.equals("status")) {
            System.out.print("Ordenar por (nome, prioridade, prazo, categoria, status): ");
            criterio = sc.nextLine().toLowerCase();
        }
        sortTasksBy(criterio);
        System.out.println("Tarefas ordenadas por " + criterio);
        wait(2);
    }

    // Aguarda o usuário digitar 'q' para continuar
    public void continuar() {
        System.out.println(" ");
        char c = '0';
        while(c != 'q'){
            System.out.print("Digite 'q' para continuar: ");
            c = sc.next().charAt(0);
        }
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

    // Cria uma nova tarefa a partir da entrada do usuário
    public void criaTask(){
        try{
            sc.nextLine();
            System.out.print("Nome da tarefa: ");
            String nome = sc.nextLine();

            System.out.print("Descrição: ");
            String descricao = sc.nextLine();
            
            LocalDate prazo = null;
            while (prazo == null) {
                System.out.print("Prazo (yyyy-mm-dd): ");
                String prazoStr = sc.nextLine();
                try {
                    prazo = LocalDate.parse(prazoStr);
                } catch (Exception e) {
                    System.out.println("Formato de data inválido. Tente novamente.");
                }                
            }

            int prioridade = 0;
            while ((prioridade <= 0 )||(prioridade > 5)) {
                System.out.print("Prioridade (1-5): ");
                prioridade = sc.nextInt();
            }
            sc.nextLine();

            System.out.print("Categoria: ");
            String categoria = sc.nextLine();

            String status = "";
            while(!status.equals("TODO") && !status.equals("DOING") && !status.equals("DONE")) {
                System.out.print("Status (TODO, DOING, DONE): ");
                status = sc.nextLine().toUpperCase();
            }

            Task t = new Task(nome, descricao, prazo, prioridade, categoria, status);
            System.out.println(t);
            addTask(t);
            System.out.println("Tarefa adicionada!");
            wait(2);
        }
        catch(Exception e){
            System.out.println("Erro ao adicionar tarefa: " + e.getMessage());
        }
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
        List<Task> result = new LinkedList<>();
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
        List<Task> result = new LinkedList<>();
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
        List<Task> result = new LinkedList<>();
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
    // Aguarda por um número de segundos    
    public static void wait(int sec){
    try {
        Thread.sleep(sec * 1000L);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
        }
    }
}
