

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainingService {

    public static boolean isTrainerFree(int trainerId, String date, String time) {
        String sql = "SELECT COUNT(*) FROM trainings WHERE trainer_id = ? AND training_date = ? AND training_time = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trainerId);
            ps.setString(2, date);
            ps.setString(3, time);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addTraining(int trainerId, int clientId, int serviceId, String date, String time) {
        if (!isTrainerFree(trainerId, date, time)) {
            System.out.println("❌ Тренер занят в это время!");
            return;
        }
        String sql = "INSERT INTO trainings (trainer_id, client_id, service_id, training_date, training_time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trainerId);
            ps.setInt(2, clientId);
            ps.setInt(3, serviceId);
            ps.setString(4, date);
            ps.setString(5, time);
            ps.executeUpdate();
            System.out.println("✅ Тренировка записана!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



