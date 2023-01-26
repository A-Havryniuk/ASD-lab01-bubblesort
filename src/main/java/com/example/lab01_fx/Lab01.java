package com.example.lab01_fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Lab01 extends Application  {
   // private ModuleLayer.Controller CONTROLLER;
    private final Pane PANE = new Pane();
    @Override
    public void start(Stage stage)  {
       Scene scene = createScene();
       stage.setTitle("Bubble sort");
       stage.setScene(scene);
       stage.setResizable(false);
       stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public Scene createScene() {
        TextField sizeOfArray_TextField = new TextField("Enter a size of Array");
        sizeOfArray_TextField.relocate(10, 20);
        sizeOfArray_TextField.setPrefSize(200, 30);

        Button createButton = new Button("Create and sort");
        createButton.resize(40, 40);
        createButton.relocate(sizeOfArray_TextField.getLayoutX() + 220, sizeOfArray_TextField.getLayoutY());
        createButton.setOnAction(actionEvent -> {
            int size = Integer.parseInt(sizeOfArray_TextField.getText());
            CreateAndSort(size);
        });

        TextField numOfRow = new TextField("Enter number of row");
        TextField numOfCol = new TextField("Enter number of column");
        numOfRow.setPrefSize(150, 30);
        numOfCol.setPrefSize(150, 30);
        numOfRow.relocate(createButton.getLayoutX() + 150, createButton.getLayoutY());
        numOfCol.relocate(numOfRow.getLayoutX() + 150, numOfRow.getLayoutY());

        Button personalTask_button = new Button("do magic");
        personalTask_button.setPrefSize(100,20);
        personalTask_button.relocate(numOfCol.getLayoutX() + 150, numOfCol.getLayoutY());
        personalTask_button.setOnAction(actionEvent -> {
            int size1 = Integer.parseInt(numOfRow.getText());
            int size2 = Integer.parseInt(numOfCol.getText());
            PersonalTask(size1, size2);
        });

        PANE.getChildren().addAll(sizeOfArray_TextField, createButton, numOfCol, numOfRow, personalTask_button);
        return new Scene(PANE, 800, 600);
    }

    public void CreateAndSort(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; ++i)
            array[i] = (int)(Math.random()*200-100);
        long i1 = System.currentTimeMillis();
        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 200);
        textArea.setText(Arrays.toString(array));
        textArea.appendText("\n");
//---------------------------------------------------------------------------------------------
        boolean sorted = false;
        Integer temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; ++i) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
            textArea.appendText(Arrays.toString(array));
            textArea.appendText("\n");
        }
//---------------------------------------------------------------------------------------------
        long i2 = System.currentTimeMillis();
        textArea.appendText("Result array:\n");
        textArea.appendText(Arrays.toString(array));
        textArea.appendText("\n");
        textArea.appendText("Time(ms): ");
        textArea.appendText(i2-i1+"");
        textArea.relocate(20, 60);
        PANE.getChildren().add(textArea);
    }

    public void PersonalTask(int size1, int size2) {
        Double[][] array = new Double[size1][size2];
        Random rClass = new Random();
        for(int i = 0; i < size1; ++i)
        {
            for(int j = 0; j < size2; ++j)
            {
                array[i][j] = rClass.nextInt(10000)/100.0;
            }
        }
        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 200);
        for(int i = 0; i < size1; ++i)
        {
            for(int j = 0; j < size2; ++j)
            {
                textArea.appendText(""+array[i][j] + " | ");
            }
            textArea.appendText("\n");
        }
        textArea.relocate(400, 60);

        double min;
        int indxMinRow = 0;
        for(int i = 0; i < size1; ++i)
        {
            min = Double.MAX_VALUE;
            for(int j = 0; j < size2; ++j)
            {
                if(array[i][j]<min)
                {
                    min = array[i][j];
                    indxMinRow = j;
                }
            }
            array[i][indxMinRow] = Math.log(array[i][indxMinRow]);
        }
//---------------------------------------------------------------------------------------------
        boolean sorted = false;
        Double temp;
        while(!sorted)
        {
            sorted = true;
            for(int k = 0; k < size2-1; ++k)
            {
                if(array[0][k]>array[0][k+1])
                {
                    for(int i = 0; i < size1; ++i)
                    {
                        temp = array[i][k];
                        array[i][k] = array[i][k+1];
                        array[i][k+1] = temp;
                    }
                    sorted = false;
                }
            }
        }
//---------------------------------------------------------------------------------------------
        TextArea textArea2 = new TextArea();
        textArea2.setPrefSize(300, 200);
        for(int i = 0; i < size1; ++i)
        {
            for(int j = 0; j < size2; ++j)
            {
                textArea2.appendText(String.format("%,.3f", array[i][j]));
                textArea2.appendText(" | ");
            }
            textArea2.appendText("\n");
        }
        textArea2.relocate(400, 300);
        PANE.getChildren().addAll(textArea, textArea2);

    }
}