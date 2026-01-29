

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClientService {
    public static void addClient(String fullName) {
        String sql = "INSERT INTO clients (full_name) VALUES (?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.executeUpdate();
            System.out.println("✅ Клиент добавлен: " + fullName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




