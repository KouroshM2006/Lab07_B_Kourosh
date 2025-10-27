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
import java.util.Random;
import javafx.geometry.Pos;

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
    public void start(Stage primaryStage) {
       //creating BorderPane and scene
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 250, 300);
        
        //creating VBox and labels
        VBox middle = new VBox();
        Label topLabel = new Label("Random Game");
        Label bottomLabel = new Label("Waiting...");
        Label lblImage = new Label();
        
        //setting Vbox and labels alignment
        middle.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(bottomLabel, Pos.CENTER);
        BorderPane.setAlignment(topLabel, Pos.CENTER);
        
        //setting labels and vbox to the BorderPane
        root.setTop(topLabel);
        root.setBottom(bottomLabel);
        middle.getChildren().add(lblImage);
        root.setCenter(middle);
        
        //getting random number for image
        Random random = new Random();
        int randomNumber = random.nextInt(20) + 101;
        
        //getting image
        Image image = new Image("file:images/" + randomNumber + ".jpg");
        lblImage.setGraphic(new ImageView(image));
        
        //setting the stage
        primaryStage.setTitle("Java Games");
        primaryStage.setScene(scene);
        primaryStage.show();  
    }
    
}
