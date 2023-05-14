package com.example.foodiepal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.*;

import java.net.JarURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.function.Predicate;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;



public class dashboardController extends Dashboard  implements Initializable  {

   @FXML
   private Button availableFD_btn;

      @FXML
    private AnchorPane availableFD_form;

  @FXML
   private TextField availableFD_productID;

    @FXML
    private TextField availableFD_productName;

   @FXML
   private TextField availableFD_productPrice;
    @FXML
    private ComboBox<?> availableFD_productStatus;

    @FXML
   private ComboBox<?> availableFD_productType;
    private Button availableFD_updateBtn;



    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button logout;

  @FXML
   private Button order_btn;

   @FXML
   private AnchorPane order_form;

   @FXML
   private Spinner<Integer> order_quantity;

    @FXML
    private TableView< product > order_tableView;


    @FXML
    private Label username;

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button minimize;
    @FXML
    private Button close;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;





    public void switchForm(ActionEvent event){

        if(event.getSource()==dashboard_btn){
            dashboard_form.setVisible(true);
            availableFD_form.setVisible(false);
            order_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff;-fx-border-width: 0px;");
            availableFD_btn.setStyle("-fx-background-color: transparent;-fx-border-width:1px;-fx-text-fill:#000;");
            order_btn.setStyle("-fx-background-color: transparent;-fx-border-width:1px;-fx-text-fill:#000;");
            dashboardNC();
            dashboardTI();
            dashboardTIncome();
            dashboardNOCCChart();
            dashboardICC();
        }
        else if(event.getSource()==availableFD_btn){
            dashboard_form.setVisible(false);
            availableFD_form.setVisible(true);
            order_form.setVisible(false);

            availableFD_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff;-fx-border-width: 0px;");
            dashboard_btn.setStyle("-fx-background-color: transparent;-fx-border-width:1px;-fx-text-fill:#000;");
            order_btn.setStyle("-fx-background-color: transparent;-fx-border-width:1px;-fx-text-fill:#000;");

            availableFDShowData();
            availableFDSearch();

        }
        else if(event.getSource()==order_btn){
            dashboard_form.setVisible(false);
            availableFD_form.setVisible(false);
            order_form.setVisible(true);

            order_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff;-fx-border-width: 0px;");
            availableFD_btn.setStyle("-fx-background-color: transparent;-fx-border-width:1px;-fx-text-fill:#000;");
            dashboard_btn.setStyle("-fx-background-color: transparent;-fx-border-width:1px;-fx-text-fill:#000;");

            orderProductId();
            orderProductName();
            orderSpinner();
            //orderListData();

            orderDisplayData();
            orderDisplayTotal();
        }

    }

    private  double x=0;
    private double y=0;

    public void logout(){
        try{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType>option=alert.showAndWait();
            if(option.get().equals(ButtonType.OK)){

                logout.getScene().getWindow().hide();
                //linking login form
                Parent root=FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage=new Stage();
                Scene scene=new Scene(root);

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

                stage.setScene(scene);
                stage.show();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void displayUsername(){
        String user=data.username;
        user=user.substring(0,1).toUpperCase()+ user.substring(1);
        username.setText(user);

    }

    public  void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage=(Stage) main_form.getScene().getWindow();
        stage.setIconified(true);

    }

    public void initialize(URL url, ResourceBundle rb){
       dashboardNC();
        dashboardTI();
        dashboardTIncome();
        dashboardNOCCChart();
        dashboardICC();
        displayUsername();
        availableFDStatus();
        availableFDType();
        availableFDShowData();

        orderProductId();
        orderProductName();
        orderSpinner();
        //orderListData();

        orderDisplayData();
        orderDisplayTotal();


    }
}
