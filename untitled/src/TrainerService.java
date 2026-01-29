

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TrainerService {
    public static void addTrainer(String fullName) {
        String sql = "INSERT INTO trainers (full_name) VALUES (?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.executeUpdate();
            System.out.println("✅ Тренер добавлен: " + fullName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





