import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private TableView<Task> table = new TableView<>();

    @Override
    public void start(Stage stage) {

        // ===== Колонки =====
        TableColumn<Task, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle())
        );

        TableColumn<Task, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription())
        );

        TableColumn<Task, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus())
        );

        table.getColumns().addAll(titleCol, descCol, statusCol);

        // ===== Поля ввода =====
        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField descField = new TextField();
        descField.setPromptText("Description");

        TextField statusField = new TextField();
        statusField.setPromptText("Status");

        // ===== Кнопка =====
        Button deleteBtn = new Button("Delete Selected");
        deleteBtn.setOnAction(e -> {
            Task selected = table.getSelectionModel().getSelectedItem();

            if (selected != null) {
                TaskDAO.deleteTask(selected.getId());
                refreshTable();
            }
        });
        Button addBtn = new Button("Add Task");
        addBtn.setOnAction(e -> {
            TaskDAO.addTask(new Task(
                    titleField.getText(),
                    descField.getText(),
                    statusField.getText()
            ));
            refreshTable();
        });

        VBox root = new VBox(10,
                titleField,
                descField,
                statusField,
                addBtn,
                deleteBtn,
                table
        );

        refreshTable();

        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle("Project Manager");
        stage.show();
    }

    private void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(TaskDAO.getAllTasks());
    }

    public static void main(String[] args) {
        launch();
    }
}