package com.example.foodiepal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    private double x=0;
    private double y=0;


    @Override
    public void start(Stage stage) throws IOException {
      //  FXMLLoader root = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root=FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene=new Scene(root);
       // Scene scene = new Scene(root.load());
      //  stage.setTitle("FoodiePal");
//        Button button=(Button)root.lookup("");
//        button.setOnMousePressed((MouseEvent event)->{
//            x=event.getX();
//             y=event.getY();
//        });



        root.setOnMousePressed((MouseEvent event)->{
            x=event.getSceneX();
            y=event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event)->{
            stage.setX(event.getSceneX()-x);
            stage.setY(event.getSceneY()-y);

            stage.setOpacity(.8f);
        });

        root.setOnMouseReleased((MouseEvent event)->{
            stage.setOpacity(1);
        });

       stage.initStyle(StageStyle.TRANSPARENT);



//        root.setOnMousePressed((MouseEvent event)->{
//            x=event.getX();
//            y=event.getY();
//        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}