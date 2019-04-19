package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class Controller {

    private DrawerTask task;

    @FXML
    private TextField resultField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField myTextField;

    @FXML
    private Canvas canvas;
    @FXML
    private void handleRunBtnAction(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(gc.getCanvas().getLayoutX(), gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

            if(isNumeric(myTextField.getText()))
            {
                long numberOfSimulationPoints = Integer.parseInt(myTextField.getText());
                if(numberOfSimulationPoints>0)
                {

                    task = new DrawerTask(numberOfSimulationPoints,gc);
                    progressBar.progressProperty().bind(task.progressProperty());
                    task.setOnSucceeded(event -> resultField.setText(Double.toString((Double) task.getValue())));
                    new Thread(task).start();
                }
                else {
                    showDialogAlert();
                }
            }
            else {
                showDialogAlert();

            }
    }

    @FXML
    private void handleStopBtnAction(){
        if(task!=null)
            task.cancel();
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void showDialogAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter valid number!");
        alert.showAndWait();

    }


}
