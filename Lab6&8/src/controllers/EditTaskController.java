package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Priority;
import model.Task;

public class EditTaskController {

    private ListView<Task> editedToDoList;
    //private ListView<Task> editedInProgressList;
   // private ListView<Task> editedDoneList;


    private static ObservableList<Task> editTaskFeaturesList;
    @FXML
    private TextField editTitleTextField;
    @FXML
    private ComboBox editComboBoxPriority;
    @FXML
    private TextArea editDescriptionTextArea;
    @FXML
    private DatePicker editExpDate;

    private Task editTask;
    private Stage editDialogStage;
    private ObservableList<Priority> priorityComboBoxList = FXCollections.observableArrayList(Priority.values());

    public void setEditTask(Task tmp){
        editTask = tmp;
        editTitleTextField.setText(editTask.getTitle());
        editComboBoxPriority.setValue(editTask.getPriority());
        editDescriptionTextArea.setText(editTask.getDescription());
        editExpDate.setValue(editTask.getExpDate());
    }

    void setTaskTodoListView(ListView<Task> taskTodoListView) {
        editedToDoList = taskTodoListView;

    }
    public void setData(ObservableList<Task> data) {
        editTaskFeaturesList = data ;

    }


    public void setDialogStage(Stage dialogStage) {
        this.editDialogStage= dialogStage;
    }

    @FXML
    public void initialize(){
        editTaskFeaturesList = FXCollections.observableArrayList();
        editComboBoxPriority.setItems(priorityComboBoxList);
    }


    @FXML
    private void handleEditAction(){
         editTask.setTitle(editTitleTextField.getText());
         editTask.setDescription(editDescriptionTextArea.getText());
         editTask.setExpDate(editExpDate.getValue());
         Priority priority = (Priority) editComboBoxPriority.getValue();
         editTask.setPriority(priority);
         editedToDoList.getItems().set(editedToDoList.getSelectionModel().getSelectedIndex(),editTask);
         editDialogStage.close();

    }

}
