




import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Меню администратора клуба ===");
            System.out.println("1. Добавить клиента");
            System.out.println("2. Добавить тренера");
            System.out.println("3. Добавить услугу");
            System.out.println("4. Записать тренировку");
            System.out.println("5. Выйти");
            System.out.print("Выберите пункт: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введите имя клиента: ");
                    ClientService.addClient(scanner.nextLine());
                    break;
                case "2":
                    System.out.print("id имя тренера: ");
                    TrainerService.addTrainer(scanner.nextLine());
                    break;
                case "3":
                    System.out.print("Введите название услуги: ");
                    ServiceService.addService(scanner.nextLine());
                    break;
                case "4":
                    recordTraining(scanner);
                    break;
                case "5":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный пункт меню");
            }
        }
    }

    private static void recordTraining(Scanner scanner) {
        try (Connection conn = Database.getConnection()) {
            System.out.println("\nТренеры:");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM trainers");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("full_name"));
            }
            System.out.print("Введите ID тренера: ");
            int trainerId = Integer.parseInt(scanner.nextLine());

            System.out.println("\nКлиенты:");
            rs = conn.createStatement().executeQuery("SELECT * FROM clients");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("full_name"));
            }
            System.out.print("Введите ID клиента: ");
            int clientId = Integer.parseInt(scanner.nextLine());

            System.out.println("\nУслуги:");
            rs = conn.createStatement().executeQuery("SELECT * FROM services");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
            System.out.print("Введите ID услуги: ");
            int serviceId = Integer.parseInt(scanner.nextLine());

            System.out.print("Введите дату (yyyy-mm-dd): ");
            String date = scanner.nextLine();

            System.out.print("Введите время (HH:mm): ");
            String time = scanner.nextLine();

            TrainingService.addTraining(trainerId, clientId, serviceId, date, time);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


