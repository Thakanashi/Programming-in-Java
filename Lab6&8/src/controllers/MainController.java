package controllers;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Priority;
import model.Task;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MainController implements Serializable {

    private ObservableList<Task> selectedTask = FXCollections.observableArrayList();

    private  ObservableList<Task> listOfToDoTasks = FXCollections.observableArrayList();
    private  ObservableList<Task> listOfInProcessTasks = FXCollections.observableArrayList();
    private  ObservableList<Task> listOfDoneTasks = FXCollections.observableArrayList();

    @FXML
    private ListView<Task> toDoList;

    @FXML
    private ListView<Task> inProgressList;

    @FXML
    private ListView<Task> doneList;


    @FXML
    private void initialize(){
        toDoList.setCellFactory(lv -> new ListCell<Task>() {

            @Override
            protected void updateItem(Task item, boolean empty) {

                if(item!=null){
                    MenuItem editItem = new MenuItem();

                    ContextMenu contextMenu = new ContextMenu();

                    editItem.textProperty().setValue("Edit");
                    editItem.setOnAction(event ->
                            createEditTaskWindow(item)
                    );

                    MenuItem deleteItem = new MenuItem();

                    deleteItem.textProperty().setValue("Delete");
                    deleteItem.setOnAction(event -> { toDoList.getItems().remove(getItem()); });

                    contextMenu.getItems().addAll(editItem,deleteItem);
                    emptyProperty().addListener((obs,wasEmpty,isNowEmpty) ->{
                        if (isNowEmpty)
                            setContextMenu(null);
                        else
                            setContextMenu(contextMenu);
                    });
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getTitle());
                    setTextFill(item.getTaskColor());
                    setTooltip(new Tooltip(item.getDescription()));
                }
                else {
                    setText("");
                }


            }
        });

        inProgressList.setCellFactory(lv -> new ListCell<Task>() {

            @Override
            protected void updateItem(Task item, boolean empty) {

                if(item!=null){
                    MenuItem editItem = new MenuItem();

                    ContextMenu contextMenu = new ContextMenu();

                    editItem.textProperty().setValue("Edit");
                    editItem.setOnAction(event ->
                            createEditTaskWindow(item)
                    );

                    MenuItem deleteItem = new MenuItem();

                    deleteItem.textProperty().setValue("Delete");
                    deleteItem.setOnAction(event -> { listOfInProcessTasks.remove(getItem());
                        inProgressList.getItems().remove(getItem()); });

                    contextMenu.getItems().addAll(editItem,deleteItem);
                    emptyProperty().addListener((obs,wasEmpty,isNowEmpty) ->{
                        if (isNowEmpty)
                            setContextMenu(null);
                        else
                            setContextMenu(contextMenu);
                    });
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getTitle());
                    setTextFill(item.getTaskColor());
                    setTooltip(new Tooltip(item.getDescription()));
                }
                else {
                    setText("");
                }


            }
        });

        doneList.setCellFactory(lv -> new ListCell<Task>() {

            @Override
            protected void updateItem(Task item, boolean empty) {

                if(item!=null){
                    MenuItem editItem = new MenuItem();

                    ContextMenu contextMenu = new ContextMenu();

                    editItem.textProperty().setValue("Edit");
                    editItem.setOnAction(event ->
                            createEditTaskWindow(item)
                    );

                    MenuItem deleteItem = new MenuItem();

                    deleteItem.textProperty().setValue("Delete");
                    deleteItem.setOnAction(event -> { listOfDoneTasks.remove(getItem());
                        doneList.getItems().remove(getItem()); });

                    contextMenu.getItems().addAll(editItem,deleteItem);
                    emptyProperty().addListener((obs,wasEmpty,isNowEmpty) ->{
                        if (isNowEmpty)
                            setContextMenu(null);
                        else
                            setContextMenu(contextMenu);
                    });
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getTitle());
                    setTextFill(item.getTaskColor());
                    setTooltip(new Tooltip(item.getDescription()));
                }
                else {
                    setText("");
                }


            }
        });


    }

    public void createEditTaskWindow(Task temporaryItem){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/edit_task_view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load(), 474, 356);
            stage.setTitle("Edit task");
            stage.setScene(scene);
            EditTaskController editTaskController = loader.getController();
            editTaskController.setDialogStage(stage);
            editTaskController.setEditTask(temporaryItem);
            editTaskController.setData(listOfToDoTasks);
            toDoList.setItems(listOfToDoTasks);
            editTaskController.setTaskTodoListView(toDoList);
            stage.show();

        }
        catch (Exception e){
            System.out.println("There is a problem with opening Edit new task window");
        }
    }

    @FXML
    private void handleAddNewTaskButtonAction(){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/add_task_view.fxml"));
            Scene scene = new Scene(loader.load(), 474, 356);
            Stage stage = new Stage();
            stage.setTitle("Add new task");
            stage.setScene(scene);
            AddTaskController addTaskController = loader.getController();
            addTaskController.setDialogStage(stage);
            addTaskController.setData(listOfToDoTasks);
            toDoList.setItems(listOfToDoTasks);
            addTaskController.setTaskTodoListView(toDoList);


            stage.show();
        }
        catch (Exception e){
            System.out.println("There is a problem with opening Add new task window");
        }
    }

    @FXML
    private void handleCloseAction(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void handleAboutAction(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About the author");
        alert.setHeaderText(null);
        alert.setContentText("Created by Michał Słomski \n Version 1.0");

        alert.showAndWait();
    }

    @FXML
    private void moveIntoInProgressList(){
        ObservableList<Task> selectedItemFromToDoList = toDoList.getSelectionModel().getSelectedItems();
        selectedItemFromToDoList.forEach(task -> {
            Task tmpTask = new Task();
            tmpTask.setTitle(task.getTitle());
            tmpTask.setDescription(task.getDescription());
            tmpTask.setExpDate(task.getExpDate());
            tmpTask.setPriority(task.getPriority());
            tmpTask.setID(2);
            selectedTask.add(tmpTask);

        });

        selectedItemFromToDoList.forEach(task -> {toDoList.getItems().remove(task);listOfToDoTasks.remove(task);});
        selectedTask.forEach(task -> {inProgressList.getItems().add(task);listOfInProcessTasks.add(task);});
        System.out.println(listOfInProcessTasks);
        selectedTask=FXCollections.observableArrayList();


    }
    @FXML
    private void moveIntoDoneList(){
        ObservableList<Task> selectedItemFromToDoList = inProgressList.getSelectionModel().getSelectedItems();
        selectedItemFromToDoList.forEach(task -> {
            Task tmpTask = new Task();
            tmpTask.setTitle(task.getTitle());
            tmpTask.setDescription(task.getDescription());
            tmpTask.setExpDate(task.getExpDate());
            tmpTask.setPriority(task.getPriority());
            tmpTask.setID(3);
            selectedTask.add(tmpTask);

        });

        selectedItemFromToDoList.forEach(task -> {inProgressList.getItems().remove(task);listOfInProcessTasks.remove(task);});
        selectedTask.forEach(task -> {doneList.getItems().add(task);listOfDoneTasks.add(task);});
        System.out.println(listOfDoneTasks);
        selectedTask=FXCollections.observableArrayList();
    }

    @FXML
    private void handleSaveAction(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Txt Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(selectedFile));

                outputStream.writeObject(new ArrayList<>(listOfToDoTasks));
                outputStream.writeObject(new ArrayList<>(listOfInProcessTasks));
                outputStream.writeObject(new ArrayList<>(listOfDoneTasks));
                outputStream.close();
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException exc){
                exc.printStackTrace();
            }

        }
        else {

        }
    }

    @FXML
    private void handleOpenAction(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Txt Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(selectedFile));


            listOfToDoTasks.setAll((List<Task>)inputStream.readObject());
            toDoList.setItems(listOfToDoTasks);
            listOfInProcessTasks.setAll((List<Task>)inputStream.readObject());
            inProgressList.setItems(listOfInProcessTasks);
            listOfDoneTasks.setAll((List<Task>)inputStream.readObject());
            doneList.setItems(listOfDoneTasks);

            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException exc){
                exc.printStackTrace();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }


        }
        else {

        }
    }

    @FXML
    private void handleExportAction(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            try {
                final Writer writer = new BufferedWriter(new FileWriter(selectedFile));

                listOfToDoTasks.forEach(task -> {
                    try {
                        writer.write(task.getID()+","+task.getTitle()+","+task.getPriority()+","+task.getExpDate()+","+task.getDescription()+"\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                listOfInProcessTasks.forEach(task -> {
                    try {
                        writer.write(task.getID()+","+task.getTitle()+","+task.getPriority()+","+task.getExpDate()+","+task.getDescription()+"\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                listOfDoneTasks.forEach(task -> {
                    try {
                        writer.write( task.getID()+","+task.getTitle()+","+task.getPriority()+","+task.getExpDate()+","+task.getDescription()+"\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.flush();
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }




        }
        else {

        }
    }

    @FXML
    private void handleImportAction(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            try {

                BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
                String line;
                try{
                    while ((line=bufferedReader.readLine())!=null){
                        String [] fields =line.split(",");
                        if(fields.length==5){
                            Task tmpTask = new Task();
                            tmpTask.setTitle(fields[1]);
                            Priority priority = Priority.valueOf(fields[2]);
                            tmpTask.setPriority(priority);
                            if(!fields[3].equals("null")){
                                LocalDate tmpLocalDate = LocalDate.parse(fields[3]);
                                tmpTask.setExpDate(tmpLocalDate);
                            }
                            else
                                tmpTask.setExpDate(null);
                            tmpTask.setDescription(fields[4]);
                            int tmpID = Integer.parseInt(fields[0]);
                            if(tmpID==1){
                                listOfToDoTasks.add(tmpTask);
                            }
                            else if (tmpID==2){
                                listOfInProcessTasks.add(tmpTask);
                            }
                            else{
                                listOfDoneTasks.add(tmpTask);
                            }

                            toDoList.setItems(listOfToDoTasks);
                            inProgressList.setItems(listOfInProcessTasks);
                            doneList.setItems(listOfDoneTasks);
                        }
                        else {
                            throw new Exception("Something is wrong with file!");
                        }
                    }
                }
                catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Something is wrong with file");

                    alert.showAndWait();
                }


            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }







        }
        else {

        }
    }
}

