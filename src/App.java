import java.util.Scanner;
import java.time.LocalDate;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        boolean running = true;
        String data = "2025-11-11";
        LocalDate date = LocalDate.parse(data);
        Task one = new Task("1","abc",date,1,"abc","todo");
        Task dois = new Task("2","abc",date,3,"abc","todo");
        Task tres = new Task("3","abc",date,5,"abc","todo");
        Task four = new Task("4","abc",date,4,"abc","todo");
        Task five = new Task("5","abc",date,2,"abc","todo");
        Task six = new Task("6","abc",date,3,"abc","todo");
        manager.addTask(one);
        manager.addTask(dois);
        manager.addTask(tres);
        manager.addTask(four);
        manager.addTask(five);
        manager.addTask(six);

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
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    adicionarTarefa(manager);
                    break;
                case 2:
                    removerTarefa(manager);
                    break;
                case 3:
                    listarTarefas(manager);
                    break;
                case 4:
                    listarCategoria(manager);
                    break;
                case 5:
                    listarPrioridade(manager);
                    break;
                case 6:
                    listarStatus(manager);
                    break;
                case 7:
                    contarTarefasStatus(manager);
                    break;
                case 8:
                    atualizarTarefa(manager);
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
    public static void adicionarTarefa(TaskManager manager) {
        try{
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
            manager.addTask(t);
            System.out.println("Tarefa adicionada!");
            wait(2);
        }
        catch(Exception e){
            System.out.println("Erro ao adicionar tarefa: " + e.getMessage());
        }
    }

    public static void removerTarefa(TaskManager manager) {
        try{        System.out.print("Índice da tarefa para remover: ");
        int idx = sc.nextInt();
        sc.nextLine();
        manager.removeTask(idx);
        System.out.println("Tarefa removida!");
        wait(2);
    }
    catch(Exception e){
        System.out.println("Erro ao remover tarefa: " + e.getMessage());
    }

    }

    public static void listarTarefas(TaskManager manager) {
        System.out.println("Todas as tarefas:");
        int i = 0;
        for (Task task : manager.getAllTasks()) {
            System.out.println((i+1) + ": " + task);
            i++;
        }
        continuar();
    }

    public static void listarCategoria(TaskManager manager) {
        System.out.print("Categoria: ");
        String cat = sc.nextLine();
        for (Task task : manager.getTasksByCategory(cat)) {
            System.out.println(task);
        }
        continuar();
    }
    
    public static void listarPrioridade(TaskManager manager) {
        System.out.print("Prioridade: ");
        int prio = sc.nextInt();
        sc.nextLine();
        for (Task task : manager.getTasksByPriority(prio)) {
            System.out.println(task);
        }
        continuar();
    }
    
    public static void listarStatus(TaskManager manager) {
        System.out.print("Status (TODO, DOING, DONE): ");
        String status = "";
        while(!status.equals("TODO") && !status.equals("DOING") && !status.equals("DONE")) {
            System.out.print("Status (TODO, DOING, DONE): ");
            status = sc.nextLine().toUpperCase();
        }
        for (Task task : manager.getTasksByStatus(status)){
            System.out.println(task);
        }
        continuar();
    }
    
    public static void contarTarefasStatus(TaskManager manager) {
        String status = "";
        while(!status.equals("TODO") && !status.equals("DOING") && !status.equals("DONE")) {
            System.out.print("Status (TODO, DOING, DONE): ");
            status = sc.nextLine().toUpperCase();
        }
        int count = manager.countByStatus(status);
        System.out.println("Total: " + count);
        continuar();
    }
    
    public static void atualizarTarefa(TaskManager manager) {
        System.out.print("Índice da tarefa para atualizar: ");
        int idx = sc.nextInt() - 1;
        sc.nextLine();
        Task tarefa = null;
        try {
            tarefa = manager.getAllTasks().get(idx);
        } catch (Exception e) {
            System.out.println("Índice inválido!");
            return;
        }
        System.out.println("Deixe em branco para manter o valor atual.");
        System.out.println("Nome atual: " + tarefa.getNome());
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        if (!nome.isEmpty()){
            tarefa.setNome(nome);
        }

        System.out.println("Descrição atual: " + tarefa.getDescricao());
        System.out.print("Nova descrição: ");
        String descricao = sc.nextLine();
        if (!descricao.isEmpty()){
            tarefa.setDescricao(descricao);
        }

        System.out.println("Prazo atual: " + tarefa.getPrazo());
        System.out.print("Novo prazo (yyyy-mm-dd): ");
        String prazoStr = sc.nextLine();
        if (!prazoStr.isEmpty()) {
            try {
                tarefa.setPrazo(java.time.LocalDate.parse(prazoStr));
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
                if (prioridade <= 0 || prioridade > 5) {
                    System.out.println("Prioridade inválida. Valor mantido.");
                }
                else{
                    tarefa.setPrioridade(prioridade);
                }
            } catch (Exception e) {
                System.out.println("Valor inválido. Valor Mantido.");
            }
        }

        System.out.println("Categoria atual: " + tarefa.getCategoria());
        System.out.print("Nova categoria: ");
        String categoria = sc.nextLine();
        if (!categoria.isEmpty()){
            tarefa.setCategoria(categoria);
        }

        System.out.println("Status atual: " + tarefa.getStatus());
        System.out.print("Novo status (TODO, DOING, DONE): ");
        String status = sc.nextLine().toUpperCase();
        if (!status.isEmpty() && (status.equals("TODO") || status.equals("DOING") || status.equals("DONE"))) {
            tarefa.setStatus(status);
        }

        manager.updateTask(idx, tarefa);
        System.out.println("Tarefa atualizada!");
        wait(2);
    }

    public static void continuar() {
        System.out.println(" ");
        char c = '0';
        while(c != 'q'){
            System.out.print("Digite 'q' para continuar: ");
            c = sc.next().charAt(0);
        }
    }
    public static void wait(int sec){
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}