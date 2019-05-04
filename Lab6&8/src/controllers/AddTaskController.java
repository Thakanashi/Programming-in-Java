package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Priority;
import model.Task;


public class AddTaskController {


    private Stage dialogStage;
    private ObservableList<Task> taskFeaturesList;
    private ObservableList<Priority> priorityComboBoxList;
    private ListView<Task> taskTodoListView;

    public void setData(ObservableList<Task> data) {
        this.taskFeaturesList = data;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    void setTaskTodoListView(ListView<Task> taskTodoListView) {
        this.taskTodoListView = taskTodoListView;
    }

    @FXML
    private ComboBox priorityComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField titleTextField;

    @FXML
    private DatePicker expDateField;


    private Task task = new Task();

    public AddTaskController() {
        taskFeaturesList = FXCollections.observableArrayList();
        priorityComboBoxList = FXCollections.observableArrayList(Priority.values());
    }

    @FXML
    public void initialize() {

        priorityComboBox.setValue(Priority.LOW);
        priorityComboBox.setItems(priorityComboBoxList);
    }

    @FXML
    public void handleAddAction() {

        if(titleTextField.getText()==null){
            task.setTitle("Bez tytułu");
        }
        task.setTitle(titleTextField.getText());
        task.setDescription(descriptionTextArea.getText());
        task.setExpDate(expDateField.getValue());
        Priority priority = (Priority) priorityComboBox.getValue();
        task.setPriority(priority);
        task.setID(1);
        taskTodoListView.getItems().add(task);
        dialogStage.close();



    }
    

}
