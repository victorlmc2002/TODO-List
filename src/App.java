import java.util.Scanner;
import java.time.LocalDate;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
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
        manager.iniciarApp();
    }
}