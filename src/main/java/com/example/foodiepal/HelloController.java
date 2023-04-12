package com.example.foodiepal;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;




public class HelloController implements Initializable {
//    @FXML
//    private Label label;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        label.setText("Welcome to JavaFX Application!");
//    }
//
//    @FXML
//    private void handleButtonAction(ActionEvent event){
//        System.out.println("You clicked me!");
//        label.setText("Hello World");
//    }

    @FXML
    private Button close;

    @FXML
    private Button loginBtr;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;



    //Database Tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
//    @FXML
//    void close(ActionEvent event) {
//
//    }
//
//    @FXML
//    void login(ActionEvent event) {
//
//    }

    private double x=0;
    private double y=0;


    public void login(){
       // String sql="SELECT+ FROM admin WHERE username= ? and password= ?";
     //  connect=database.connectDb();



        try{

//            prepare= connect.prepareStatement(sql);
//            prepare.setString(1,username.getText());
//            prepare.setString(2,password.getText());
//
//            result=prepare.executeQuery();

//            prepare=connect.prepareStatement("select + from admin where username=? and password=?");
//            prepare.setString(1,username.getText());
//            prepare.setString(2,password.getText());
//            result=prepare.executeQuery();


            Alert alert;

            if(username.getText().isEmpty()|| password.getText().isEmpty()){
//                JOptionPane.showMessageDialog(null,"Please fill all blank fields");
//                username.setText("");
//                password.setText("");
//                username.requestFocus();

                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else{

               //  if(result.next()){
               if(username.getText().trim().equals("Abrar")&& password.getText().equals("admin123")){

                   data.username=username.getText();

              //  JOptionPane.showMessageDialog(null,"Successfully Login!");

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();


                    //to hide your login form
                   loginBtr.getScene().getWindow().hide();

                    //link your dashboard
                    Parent root= FXMLLoader.load(getClass().getResource("dashboard.fxml"));



                    Stage stage=new Stage();
                    Scene scene=new Scene(root);

                   root.setOnMousePressed((MouseEvent event)->{
                       x=event.getSceneX();
                       y=event.getSceneY();
                   });
                   root.setOnMouseDragged((MouseEvent event)->{
                       stage.setX(event.getSceneX()-x);
                       stage.setY(event.getSceneY()-y);

                       stage.setOpacity(1.3f);
                   });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();


                }
                 else{
                    //if wrong username or password
//                     JOptionPane.showMessageDialog(null,"Wrong Username or Password");
//                     username.setText("");
//                     password.setText("");
//                     username.requestFocus();


                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //Close the program

    public  void close(){
        System.exit(0);
    }

    public void initialize(URL url, ResourceBundle rb){

    }
}