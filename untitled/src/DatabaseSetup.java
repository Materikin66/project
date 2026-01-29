

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void setup() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {


            stmt.execute("""
                CREATE TABLE IF NOT EXISTS trainers (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    full_name TEXT NOT NULL
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS clients (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    full_name TEXT NOT NULL
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS services (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS trainings (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    trainer_id INTEGER NOT NULL,
                    client_id INTEGER NOT NULL,
                    service_id INTEGER NOT NULL,
                    training_date TEXT NOT NULL,
                    training_time TEXT NOT NULL,
                    FOREIGN KEY(trainer_id) REFERENCES trainers(id),
                    FOREIGN KEY(client_id) REFERENCES clients(id),
                    FOREIGN KEY(service_id) REFERENCES services(id)
                );
            """);


            stmt.execute("INSERT INTO trainers (full_name) VALUES ('Иван Иванов'), ('Мария Смирнова');");
            stmt.execute("INSERT INTO clients (full_name) VALUES ('Пётр Петров'), ('Анна Иванова');");
            stmt.execute("INSERT INTO services (name) VALUES ('Персональная тренировка'), ('Групповая тренировка');");

            System.out.println("✅ Таблицы и тестовые данные созданы!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setup();
    }
}
