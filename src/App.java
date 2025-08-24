import java.util.Scanner;
import java.time.LocalDate;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
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
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Nome da tarefa: ");
                    String nome = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("Prazo (yyyy-mm-dd): ");
                    String prazoStr = sc.nextLine();
                    LocalDate prazo = LocalDate.parse(prazoStr);
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
                    break;
                case 2:
                    System.out.print("Índice da tarefa para remover: ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    manager.removeTask(idx);
                    System.out.println("Tarefa removida!");
                    wait(2);
                    break;
                case 3:
                    System.out.println("Todas as tarefas:");
                    int i = 0;
                    for (Task task : manager.getAllTasks()) {
                        System.out.println(i + ": " + task);
                        i++;
                    }
                    continuar();
                    break;
                case 4:
                    System.out.print("Categoria: ");
                    String cat = sc.nextLine();
                    for (Task task : manager.getTasksByCategory(cat)) {
                        System.out.println(task);
                    }
                    continuar();
                    break;
                case 5:
                    System.out.print("Prioridade: ");
                    int prio = sc.nextInt();
                    sc.nextLine();
                    for (Task task : manager.getTasksByPriority(prio)) {
                        System.out.println(task);
                    }
                    continuar();
                    break;

                case 6:
                    System.out.print("Status (TODO, DOING, DONE): ");
                    String st = sc.nextLine().toUpperCase();
                    for (Task task : manager.getTasksByStatus(st)) {
                        System.out.println(task);
                    }
                    continuar();
                    break;

                case 7:
                    System.out.println("Status (TODO, DOING, DONE): ");
                    String stCount = sc.nextLine().toUpperCase();
                    int count = manager.countByStatus(stCount);
                    System.out.println("Total: " + count);
                    continuar();
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
