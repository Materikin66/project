

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ServiceService {
    public static void addService(String name) {
        String sql = "INSERT INTO services (name) VALUES (?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println("✅ Услуга добавлена: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


