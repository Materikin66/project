




import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_FOLDER = "C:\\Users\\Vlad\\console.db\\";
    private static final String DB_FILE = "console.db";
    private static final String URL = "console" + DB_FOLDER + "/" + DB_FILE;

    public static Connection getConnection() throws SQLException {

        File folder = new File(DB_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }


        File dbFile = new File(DB_FOLDER, DB_FILE);
        if (dbFile.exists() && !dbFile.isFile()) {
            System.out.println("⚠️ Файл базы данных повреждён или это папка. Удаляем...");
            dbFile.delete();
        }


        return DriverManager.getConnection(URL);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Соединение с базой данных установлено!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


