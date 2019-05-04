package model;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.time.LocalDate;


public class Task implements Serializable {
    private String title;
    private String description;
    private LocalDate expDate;
    private Priority priority;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
    public Color getTaskColor( ) {
        switch (this.priority) {
            case LOW:
                return Color.PURPLE;
            case MEDIUM:
                return Color.ORANGE;
            case HIGH:
                return Color.RED;
            default:
                throw new IllegalArgumentException("Coś poszło nie tak");
        }
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
