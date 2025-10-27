/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab07_B_kourosh;

//github link: https://github.com/KouroshM2006/Lab07_B_Kourosh

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.Insets; 
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 *
 * @author 2440557
 */
public class Lab07_B_Kourosh extends Application{
    
    private FadeTransition fadeTransition;
    private double speed = 1.0;
    private boolean play = true;
    final private Image[] IMAGES = new Image[20];
    int currentIndex = 1;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
    /**
     * method to play the animation
     */
    private void playAnimation() {
        if (fadeTransition.getStatus() != javafx.animation.Animation.Status.RUNNING) {
            fadeTransition.playFromStart();
        }
    }
    
    /**
     * method to update the speed of the transition
     */
    private void updateSpeed() {
        fadeTransition.stop();
        fadeTransition.setDuration(Duration.seconds(2.0 / speed));
        fadeTransition.play();
    }
    
    /**
     * method to update the image in the image view
     * @param imageView 
     */
    private void switchImage(ImageView imageView) {
        if (currentIndex >= IMAGES.length) {
            currentIndex = 0;
        }
        imageView.setImage(IMAGES[currentIndex]);
        
        currentIndex++;
        fadeTransition.play();
    }
    
    @Override
    public void start(Stage primaryStage) {
        for (int i = 0; i < 20; i++) {
            IMAGES[i] = new Image("file:images/" + (i + 101) + ".jpg");
        }
       //creating BorderPane and scene
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 250, 300);
        
        //creating VBox and labels
        HBox buttonsBox = new HBox();
        VBox middle = new VBox();
        Label topLabel = new Label("Random Game");
        Label bottomLabel = new Label("Waiting...");
        Label lblImage = new Label();
        ImageView imageView = new ImageView(IMAGES[0]);
        //fade transition
        
        fadeTransition = new FadeTransition(Duration.seconds(2.0 / speed), imageView);
        fadeTransition.setOnFinished(e -> switchImage(imageView));
        
        //creating buttons
        Button btn = new Button("play");
        Button speedPlus = new Button("Speed+");
        Button speedMinus = new Button("Speed-");
        
        btn.setOnAction(e -> {
            if (play) {
                playAnimation();
                btn.setText("Pause");
                play = false;
            } else {
                fadeTransition.pause();
                btn.setText("Play");
                play = true;
            }
        });
        
        speedPlus.setOnAction(e -> {
            speed *= 2;
            updateSpeed();
        });
        
        speedMinus.setOnAction(e -> {
            speed /= 2;
            if (speed < 0.25) speed = 0.25;
            updateSpeed();
        });
        
        //setting Vbox and labels alignment
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(5);
        middle.setAlignment(Pos.CENTER);
        middle.setSpacing(5);
        BorderPane.setAlignment(bottomLabel, Pos.CENTER);
        BorderPane.setAlignment(topLabel, Pos.CENTER);
        
        //setting labels and vbox to the BorderPane
        root.setTop(topLabel);
        root.setBottom(bottomLabel);
        buttonsBox.getChildren().addAll(btn, speedPlus, speedMinus);
        middle.getChildren().addAll(imageView, buttonsBox);
        root.setCenter(middle);
        
        //setting the stage
        primaryStage.setTitle("Java Games");
        primaryStage.setScene(scene);
        primaryStage.show();  
    }
    
}
