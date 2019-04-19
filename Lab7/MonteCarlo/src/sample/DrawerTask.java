package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import model.Equation;
import model.GenerateRandomNumber;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawerTask  extends  Task{

    private GraphicsContext gc;
    private long numberOfSimulationPoints;
    private final double MIN =-8;
    private final double MAX = 8;

    public DrawerTask(long N, GraphicsContext gc) {
        this.gc=gc;
        this.numberOfSimulationPoints=N;
    }


    @Override
    protected Object call() throws Exception {
        double result,x,y;
        long  numberOfHits=0;

        BufferedImage bi = new BufferedImage((int)gc.getCanvas().getWidth(),(int) gc.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<numberOfSimulationPoints; i++){
            x=GenerateRandomNumber.generate();
            y=GenerateRandomNumber.generate();
            if(Equation.calc(x,y)){
                double transaltedX = gc.getCanvas().getWidth() * (x-MIN) / (MAX-MIN);
                double translatedY=  gc.getCanvas().getHeight() * (y-MIN) / (MAX-MIN);
                bi.setRGB((int)transaltedX,(int)(-translatedY+gc.getCanvas().getHeight()), Color.BLACK.getRGB());
                numberOfHits++;
            }
            else{
                double transaltedX = gc.getCanvas().getWidth() * (x-MIN) / (MAX-MIN);
                double translatedY=  gc.getCanvas().getHeight() * (y-MIN) / (MAX-MIN);
                bi.setRGB((int)transaltedX,(int)(-translatedY+gc.getCanvas().getHeight()),Color.YELLOW.getRGB());
            }

            if(i % 10000 == 0){
                Platform.runLater(()->gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 ));

            }

            updateProgress(i,numberOfSimulationPoints);
            if(isCancelled()) break;

        }
        Platform.runLater(()->gc.drawImage(SwingFXUtils.toFXImage(bi,null),0,0));  //drawing points where number is less than 1000
        result=numberOfHits * (MAX-MIN)*(MAX-MIN)/numberOfSimulationPoints;
        return result;
    }
}
