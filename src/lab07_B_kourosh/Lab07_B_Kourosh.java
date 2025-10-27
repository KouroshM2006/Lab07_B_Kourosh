/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab07_B_kourosh;

//github link: 

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author 2440557
 */
public class Lab07_B_Kourosh extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) {
         BorderPane root = new BorderPane();

        Pane middlePane = new Pane();
        middlePane.setPrefSize(800, 600);
        middlePane.setStyle("-fx-background-color: lightblue");
        
        Button startButton = new Button("Start");
        Button resetButton = new Button("Reset");
        Button exitButton = new Button("Exit");

        exitButton.setOnAction(e -> stage.close());

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(startButton, resetButton, exitButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10);
        
        double width = middlePane.getWidth();
        double height = middlePane.getHeight();
        
        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(100, 100),
                new LineTo(400, 100),
                new LineTo(400, 300),
                new LineTo(100, 300),
                new ClosePath()
        );
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);

        //object A
        Circle objectA = new Circle(20, Color.RED);
        objectA.setCenterX(100);
        objectA.setCenterY(100);
        
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(objectA);
        pathTransition.setPath(path);
        pathTransition.setDuration(Duration.seconds(8)); // total animation time
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        
        //object B
        Ellipse objectB = new Ellipse(600, 300, 30, 20);
        objectB.setFill(Color.GREEN);
        objectB.setStroke(Color.DARKGREEN);
        objectB.setStrokeWidth(2);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), objectB);
        fade.setFromValue(1.0);
        fade.setToValue(0.3);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(2), objectB);
        scale.setByX(1.5);
        scale.setByY(1.5);

        RotateTransition rotate = new RotateTransition(Duration.seconds(2), objectB);
        rotate.setByAngle(360);

        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), objectB);
        translate.setByY(-150);

        SequentialTransition seqTransition = new SequentialTransition(fade, scale, rotate, translate);
        seqTransition.setCycleCount(1);

        middlePane.getChildren().addAll(path, objectA, objectB);
        
         startButton.setOnAction(e -> {
            pathTransition.playFromStart();
            seqTransition.playFromStart();
        });

        resetButton.setOnAction(e -> {
            pathTransition.stop();
            seqTransition.stop();

            // Reset A
            objectA.setTranslateX(0);
            objectA.setTranslateY(0);

            // Reset B
            objectB.setTranslateX(0);
            objectB.setTranslateY(0);
            objectB.setRotate(0);
            objectB.setOpacity(1.0);
            objectB.setScaleX(1.0);
            objectB.setScaleY(1.0);
        });
        
        root.setBottom(buttonsBox);
        root.setCenter(middlePane);
        root.setPadding(new Insets(25, 25, 25, 25));
        BorderPane.setMargin(buttonsBox, new Insets(15, 15, 15, 15));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
    
}
