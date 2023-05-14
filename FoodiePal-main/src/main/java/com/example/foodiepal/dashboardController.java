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
//    @FXML
//    private Button availableFD_addBtn;
//
   @FXML
   private Button availableFD_btn;
//
//    @FXML
//    private Button availableFD_clearBtn;
//
//
//    @FXML
//    private TableView<categories> availableFD_tableView;
//
//
//    @FXML
//    private TableColumn<categories, String> availableFD_col_price;
//
//    @FXML
//    private TableColumn<categories, String> availableFD_col_productID;
//
//    @FXML
//    private TableColumn<categories, String> availableFD_col_productName;
//
//    @FXML
//    private TableColumn<categories, String> availableFD_col_status;
//
//    @FXML
//    private TableColumn<categories, String> availableFD_col_type;
//
//    @FXML
//    private Button availableFD_deleteBtn;
//
      @FXML
    private AnchorPane availableFD_form;
//
  @FXML
   private TextField availableFD_productID;
//
    @FXML
    private TextField availableFD_productName;
//
   @FXML
   private TextField availableFD_productPrice;
    @FXML
    private ComboBox<?> availableFD_productStatus;
//
    @FXML
   private ComboBox<?> availableFD_productType;
//
//    @FXML
//    private TextField availableFD_search;
//
//    @FXML
//    private Button availableFD_updateBtn;



    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button logout;

//    @FXML
//    private Button order_addBtn;
//
//    @FXML
//    private TextField order_amount;
//
  @FXML
   private Button order_btn;
//    @FXML
//    private Label order_balance;
//
//    @FXML
//    private TableColumn< product, String > order_col_price;
//
//    @FXML
//    private TableColumn< product, String > order_col_productID;
//
//    @FXML
//    private TableColumn< product, String > order_col_productName;
//
//    @FXML
//    private TableColumn< product , String > order_col_quantity;
//
//    @FXML
//    private TableColumn< product, String > order_col_type;
//
   @FXML
   private AnchorPane order_form;
//
//    @FXML
//    private Button order_payBtn;
//
//    @FXML
//    private ComboBox<?> order_productID;
//
//    @FXML
//    private ComboBox<?> order_productName;
//
   @FXML
   private Spinner<Integer> order_quantity;
//
//    @FXML
//    private Button order_receiptBtn;
//
//    @FXML
//    private Button order_removeBtn;
//
//
//
    @FXML
    private TableView< product > order_tableView;
//
//    @FXML
//    private Label order_total;

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

//    public void availableFDAdd(){
//        String sql="INSERT INTO category (product_id,product_name,type,price,status) "
//                +"VALUES(?,?,?,?,?)";
//        connect= database.connectDb();
//
//        try{
//            prepare=connect.prepareStatement(sql);
//            prepare.setString(1,availableFD_productID.getText());
//            prepare.setString(2,availableFD_productName.getText());
//            prepare.setString(3,(String) availableFD_productType.getSelectionModel().getSelectedItem());
//            prepare.setString(4,availableFD_productPrice.getText());
//            prepare.setString(5,(String)availableFD_productStatus.getSelectionModel().getSelectedItem());
//
//            Alert alert;
//
//            if(availableFD_productID.getText().isEmpty()
//            || availableFD_productName.getText().isEmpty()
//            ||availableFD_productType.getSelectionModel()==null
//            || availableFD_productPrice.getText().isEmpty()
//            || availableFD_productStatus.getSelectionModel()==null){
//
//                alert= new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please fill all blank fields");
//                alert.showAndWait();
//
//            }
//            else {
//
//                String checkData="SELECT product_id FROM category WHERE product_id='"
//                        +availableFD_productID.getText()+"'";
//
//                connect=database.connectDb();
//
//                statement=connect.createStatement();
//                result=statement.executeQuery(checkData);
//
//                if(result.next()){
//                    alert= new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Product ID: "+availableFD_productID.getText()+" is already exit!");
//                    alert.showAndWait();
//                }
//                else{
//                    prepare.executeUpdate();
//
//                    alert= new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Added|");
//                    alert.showAndWait();
//
//                    //to show the data
//                    availableFDShowData();
//                    //to clear the fields
//                    availableFDClear();
//
//                }
//
//
//
//            }
//
//
//        }catch (Exception e){
//                e.printStackTrace();
//        }
//    }
//
//    public void availableFDUpdate(){
//        String sql="UPDATE category SET product_name ='"
//                +availableFD_productName.getText()+ "',type='"
//                + availableFD_productType.getSelectionModel().getSelectedItem()+"',price='"
//                +availableFD_productPrice.getText()+"',status='"
//                +availableFD_productStatus.getSelectionModel().getSelectedItem()
//                +"' WHERE product_id='"+availableFD_productID.getText()+"'";
//
//        connect=database.connectDb();
//
//        try{
//
//
//            Alert alert;
//
//            if(availableFD_productID.getText().isEmpty()
//            || availableFD_productName.getText().isEmpty()
//            || availableFD_productType.getSelectionModel().getSelectedItem()==null
//            || availableFD_productPrice.getText().isEmpty()
//            || availableFD_productStatus.getSelectionModel().getSelectedItem()==null){
//
//                alert=new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please fill all the blank fields");
//                alert.showAndWait();
//
//            }
//            else{
//
//
//                alert=new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Are you sure you want to UPDATE Product ID: "
//                +availableFD_productID.getText()+"?");
//
//                Optional<ButtonType> option=alert.showAndWait();
//
//                if(option.get().equals(ButtonType.OK)){
//
//                    alert=new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Updated!");
//                    alert.showAndWait();
//
//
//                    statement=connect.createStatement();
//                    statement.executeUpdate(sql);
//
//                    //to show the data
//                    availableFDShowData();
//                    //to clear the fields
//                    availableFDClear();
//
//                }
//                else{
//                    alert=new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Cancelled.");
//                    alert.showAndWait();
//
//
//                }
//
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void availableFDDelete() {
//        String sql = "DELETE FROM category WHERE product_id ='"
//                + availableFD_productID.getText() + "'";
//
//        connect = database.connectDb();
//        try {
//
//
//            Alert alert;
//
//            if (availableFD_productID.getText().isEmpty()
//                    || availableFD_productName.getText().isEmpty()
//                    || availableFD_productType.getSelectionModel().getSelectedItem() == null
//                    || availableFD_productPrice.getText().isEmpty()
//                    || availableFD_productStatus.getSelectionModel().getSelectedItem() == null) {
//
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please fill all the blank fields");
//                alert.showAndWait();
//
//            } else {
//
//
//                alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Are you sure you want to DELETE Product ID: "
//                        + availableFD_productID.getText() + "?");
//
//                Optional<ButtonType> option = alert.showAndWait();
//
//                if (option.get().equals(ButtonType.OK)) {
//
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Deleted!");
//                    alert.showAndWait();
//
//
//                    statement = connect.createStatement();
//                    statement.executeUpdate(sql);
//
//                    //to show the data
//                    availableFDShowData();
//                    //to clear the fields
//                    availableFDClear();
//
//                } else {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Cancelled.");
//                    alert.showAndWait();
//
//
//                }
//
//
//            }
//        }
//        catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//
//    public void availableFDClear(){
//        availableFD_productID.setText("");
//        availableFD_productName.setText("");
//        availableFD_productType.getSelectionModel().clearSelection();
//        availableFD_productPrice.setText("");
//        availableFD_productStatus.getSelectionModel().clearSelection();
//
//    }
//
//
//
//    public ObservableList<categories> availableFDListData(){
//
//       //ObservableList<categories>listData=FXCollections.observableArrayList();
//        final ObservableList<categories>listData=FXCollections.observableArrayList();
//
//        String sql="SELECT * FROM category";
//
//        connect=database.connectDb();
//
//        try{
//            prepare=connect.prepareStatement(sql);
//            result=prepare.executeQuery();
//
//            categories cat;
//
//            while(result.next()){
//                cat=new categories(result.getString("product_id"),
//                        result.getString("product_name"),result.getString("type"),
//                        result.getDouble("price"),result.getString("status"));
//
//                listData.add(cat);
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return  listData;
//
//    }
//
////    public void availableFDSearch(){
////
////
////
////       FilteredList<categories>filter=new FilteredList<>(availableFDList,e->true);
////
////
////
////
////          availableFD_search.textProperty().addListener((observabl, newValue, oldValue) -> {
////
////                       filter.setPredicate(predicateCategories -> {
////
////
////                           if ( newValue == null || newValue.isEmpty() ) {
////                                return true;
////                            }
////
////
////
////                           String searchKey = newValue.toLowerCase();
////
////                            if (predicateCategories.getProductId().toLowerCase().contains(searchKey)) {
////                                return true;
////                            } else if (predicateCategories.getName().toLowerCase().contains(searchKey)) {
////                                return true;
////                            } else if (predicateCategories.getType().toLowerCase().contains(searchKey)) {
////                                return true;
////                            } else if (predicateCategories.getPrice().toString().contains(searchKey)) {
////                                return true;
////                            } else if (predicateCategories.getStatus().toLowerCase().contains(searchKey)) {
////                                return true;
////                            } else {
////                                return false;
////                            }
//////
////
////
////
////                            });
////                });
////
////        SortedList<categories>sortList=new SortedList<>(filter);
////        sortList.comparatorProperty().bind(availableFD_tableView.comparatorProperty());
////
////        availableFD_tableView.setItems(sortList);
////
////
////
////    }
////
////
////
////   private  ObservableList<categories>availableFDList;
////
////    public  void availableFDShowData(){
////        availableFDList=availableFDListData();
////        availableFD_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
////        availableFD_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
////        availableFD_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
////        availableFD_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
////        availableFD_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
////
////
////        availableFD_tableView.setItems(availableFDList);
////
////
////
////    }
//
//
//    ///-----------chatgpt
//
//    private FilteredList<categories> filteredAvailableFDList;
//    private SortedList<categories> sortedAvailableFDList;
//
//    public void availableFDShowData() {
//
//        ObservableList<categories> availableFDList = availableFDListData();
//        availableFD_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
//        availableFD_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        availableFD_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
//        availableFD_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        availableFD_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
//
//        filteredAvailableFDList = new FilteredList<>(availableFDList, (category) -> true);
//        sortedAvailableFDList = new SortedList<>(filteredAvailableFDList);
//        sortedAvailableFDList.comparatorProperty().bind(availableFD_tableView.comparatorProperty());
//
//        availableFD_tableView.setItems(sortedAvailableFDList);
//    }
//
//    public void availableFDSearch() {
//        String searchKey = availableFD_search.getText().toLowerCase();
//
//        filteredAvailableFDList.setPredicate((category) -> {
//            if (searchKey.isEmpty()) {
//                return true;
//            }
//            return category.getProductId().toLowerCase().contains(searchKey)
//                    || category.getName().toLowerCase().contains(searchKey)
//                    || category.getType().toLowerCase().contains(searchKey)
//                    || category.getPrice().toString().contains(searchKey)
//                    || category.getStatus().toLowerCase().contains(searchKey);
//        });
//    }
//
//
//    //------------end
//
//    public void availableFDSelect(){
//        categories catData=availableFD_tableView.getSelectionModel().getSelectedItem();
//        int num=availableFD_tableView.getSelectionModel().getSelectedIndex();
//
//        if((num-1)<-1){
//            return;
//        }
//        availableFD_productID.setText(catData.getProductId());
//        availableFD_productName.setText(catData.getName());
//        availableFD_productPrice.setText(String.valueOf(catData.getPrice()));
//
//
//
//    }
//
//
//
//
//
//
//    //Available foods/drinks
//
//    private  String[] categories={"Meals","Drinks"};
//
//    public  void availableFDType(){
//        List<String>listCat=new ArrayList<>();
//
//        for(String data:categories){
//            listCat.add(data);
//        }
//
//        ObservableList listData=FXCollections.observableArrayList(listCat);
//        availableFD_productType.setItems(listData);
//
//
//    }
//    private String[] status={"Available","Not Available"};
//    public void availableFDStatus(){
//        List<String> listStatus= new ArrayList<>();
//        for(String data: status){
//            listStatus.add(data);
//        }
//        ObservableList listData= FXCollections.observableArrayList(listStatus);
//        availableFD_productStatus.setItems(listData);
//
//    }

//    public void orderAdd(){
//
//        orderCustomerId();
//        orderTotal();
//
//        String sql = "INSERT INTO product" + "( customer_id , product_id , product_name , type , price , quantity , date)"
//                    + "VALUES( ? , ? , ? , ? , ? , ? , ? )";
//
//        connect = database.connectDb();
//
//        try {
//
//            String orderType = "";
//            double orderPrice = 0;
//
//            String checkData = "SELECT * FROM category WHERE product_id = '" + order_productID.getSelectionModel().getSelectedItem() + "'";
//
//            statement = connect.createStatement();
//            result = statement.executeQuery( checkData );
//
//            if( result.next() ){
//                orderType = result.getString("type");
//                orderPrice = result.getDouble("price");
//            }
//            prepare = connect.prepareStatement( sql );
//            prepare.setString( 1 , String.valueOf( customerId ));
//            prepare.setString( 2, (String) order_productID.getSelectionModel().getSelectedItem() );
//            prepare.setString( 3 , (String) order_productName.getSelectionModel().getSelectedItem());
//
//            prepare.setString( 4 , orderType );
//
//            double totalPrice = orderPrice * qty;
//            prepare.setString( 5 , String.valueOf( totalPrice ));
//
//            prepare.setString( 6 , String.valueOf( qty ));
//
//            Date date = new Date();
//            java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
//            prepare.setString( 7 , String.valueOf( sqlDate ));
//
//            //prepare.executeUpdate();
//
////            String insertInfo = "INSERT INTO product_info ( customer_id , total , date )";
////
////            prepare = connect.prepareStatement( sql );
////            prepare.setString( 1 , String.valueOf( customerId ));
////            prepare.setString( 2 , String.valueOf( totalP ));
////            prepare.setString( 3 , String.valueOf( sqlDate ));
//
//            prepare.executeUpdate();
//
//            //orderDisplayTotal();
//
//           // orderListData();
//            orderDisplayTotal();
//            orderDisplayData();
//
//        }catch ( Exception e ){
//            e.printStackTrace();
//        }
//    }
//
//    public void orderPay(){
//        orderCustomerId();
//        orderTotal();
//        String sql = "INSERT INTO product_info (customer_id, total, date) VALUES(?,?,?)";
//        connect =database.connectDb();
//        try {
//            Alert alert;
//            if(balance == 0
//            || String.valueOf(balance) =="$0.0"
//            || String.valueOf(balance) == null
//            || totalP == 0
//            || String.valueOf(totalP) =="$0.0"
//            || String.valueOf(totalP) == null){
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Invalid :3");
//                alert.showAndWait();
//            }
//            else {
//                alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Are you sure?");
//              Optional<ButtonType> option =  alert.showAndWait();
//              if(option.get().equals(ButtonType.OK)){
//                  prepare = connect.prepareStatement(sql);
//                  prepare.setString(1, String.valueOf(customerId));
//                  prepare.setString(2, String.valueOf(totalP));
//                  Date date = new Date();
//                  java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//
//                  prepare.setString(3,String.valueOf(sqlDate));
//                  prepare.executeUpdate();
//                  alert = new Alert(Alert.AlertType.INFORMATION);
//                  alert.setTitle("Information Message");
//                  alert.setHeaderText(null);
//                  alert.setContentText("Successful!");
//                  alert.showAndWait();
//                  order_total.setText("$0.0");
//                  order_balance.setText("$0.0");
//                  order_amount.setText("");
//              }
//              else {
//                  alert = new Alert(Alert.AlertType.ERROR);
//                  alert.setTitle("Information Message");
//                  alert.setHeaderText(null);
//                  alert.setContentText("Cancelled!");
//                  alert.showAndWait();
//              }
//
//            }
//
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    private double totalP = 0;
//    public void orderTotal(){
//
//        orderCustomerId();
//        String sql = "SELECT SUM(price) FROM product WHERE customer_id = " + customerId;
//
//        connect = database.connectDb();
//        try {
//            prepare = connect.prepareStatement( sql );
//            result = prepare.executeQuery();
//
//            if( result.next() ){
//                totalP = result.getDouble("SUM(price)");
//            }
//
//        }catch ( Exception e ){
//            e.printStackTrace();
//        }
//    }
//
//    private double amount;
//    private double balance;
//    public void orderAmount(){
//
//        orderTotal();
//
//        Alert alert;
//        if( order_amount.getText().isEmpty()
//            || order_amount.getText() == null
//            || order_amount.getText() == ""){
//            alert = new Alert( Alert.AlertType.ERROR );
//            alert.setHeaderText( null );
//            alert.setContentText( "Please type the amount!");
//            alert.showAndWait();
//        }
//        else {
//            amount = Double.parseDouble(order_amount.getText());
//            if(amount <totalP){
//                order_amount.setText("");
//            }
//            else {
//                balance = amount -totalP;
//                order_balance.setText("$"+String.valueOf(balance));
//            }
//
//        }
//    }
//    public void orderDisplayTotal(){
//        orderTotal();
//        order_total.setText( "$" + String.valueOf( totalP ));
//    }
//
//    public ObservableList<product> orderListData(){
//
//        orderCustomerId();
//        ObservableList<product> listData = FXCollections.observableArrayList();
//        String sql = "SELECT * FROM product WHERE customer_id = " + customerId;
//
//        connect = database.connectDb();
//
//        try {
//            prepare = connect.prepareStatement( sql );
//            result = prepare.executeQuery();
//
//            product prod;
//            while ( result.next() ){
//                prod = new product( result.getInt("id"),
//                        result.getString("product_id"),
//                        result.getString("product_name") ,
//                        result.getString("type") ,
//                        result.getDouble("price") ,
//                        result.getInt("quantity") );
//                listData.add( prod );
//            }
//        }catch ( Exception e ){
//            e.printStackTrace();
//        }
//
//        return listData;
//    }
   //private int item ;
//    public void orderRemove(){
//        String sql = "DELETE FROM product WHERE id = " + item;
//        connect = database.connectDb();
//        try {
//            Alert alert;
//            if(item == 0
//                    || String.valueOf(item) == null
//                    || String.valueOf(item) ==""){
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("ERROR Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please select the item first");
//                alert.showAndWait();
//            }
//            else {
//                alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Are you sure you want to Remove item: " + item +"?");
//                Optional<ButtonType> option =  alert.showAndWait();
//                if(option.get().equals((ButtonType.OK) )){
//                    statement = connect.createStatement();
//                    statement.executeUpdate(sql);
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Removed!");
//                    alert.showAndWait();
//                    orderDisplayData();
//                    orderDisplayTotal();
//                    order_amount.setText("");
//                    order_balance.setText("$0.0");
//
//                }
//                else {
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Cancelled!");
//                    alert.showAndWait();
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    public void orderSelectData(){
//        product prod = order_tableView.getSelectionModel().getSelectedItem();
//        int num = order_tableView.getSelectionModel().getSelectedIndex();
//        if((num -1) <-1) return;
//        item = prod.getId();
//    }
//    private ObservableList< product > orderData;
//    public void orderDisplayData(){
//
//        orderData = orderListData();
//
//        order_col_productID.setCellValueFactory( new PropertyValueFactory<>("productId"));
//        order_col_productName.setCellValueFactory( new PropertyValueFactory<>("name"));
//        order_col_type.setCellValueFactory( new PropertyValueFactory<>("type"));
//        order_col_price.setCellValueFactory( new PropertyValueFactory<>("price"));
//        order_col_quantity.setCellValueFactory( new PropertyValueFactory<>("quantity"));
//
//        order_tableView.setItems( orderData );
//    }
//    private int customerId;
//
//    public void orderCustomerId(){
//
//        String sql = "SELECT customer_id FROM product";
//       // String sql = "SELECT id FROM product";
//
//        connect = database.connectDb();
//
//        try {
//            prepare = connect.prepareStatement( sql );
//            result = prepare.executeQuery();
//
//            while ( result.next() ){
//                customerId = result.getInt("customer_id");
//            }
//
//            String checkData = "SELECT customer_id FROM product_info";
//
//            statement = connect.createStatement();
//            result = statement.executeQuery(checkData);
//
//            int customerInfoId = 0;
//            while ( result.next() ){
//                customerInfoId = result.getInt("customer_id");
//            }
//
//            if( customerId == 0 ){
//                customerId += 1;
//            }else if( customerId == customerInfoId ){
//                customerId += 1;
//            }
//        }catch ( Exception e ){
//            e.printStackTrace();
//        }
//    }
//    public void orderProductId(){
//        String sql="SELECT product_id FROM category WHERE status= 'Available' ";
//        connect=database.connectDb();
//        try {
//            prepare=connect.prepareStatement(sql);
//            result=prepare.executeQuery();
//
//            ObservableList listData=FXCollections.observableArrayList();
//
//            while(result.next()){
//                listData.add(result.getString("product_id"));
//            }
//
//            order_productID.setItems(listData);
//
//            orderProductName();
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void orderProductName(){
//
//        String sql="SELECT product_name FROM category WHERE product_id='"
//                +order_productID.getSelectionModel().getSelectedItem()+"'";
//
//        connect=database.connectDb();
//
//        try{
//            prepare=connect.prepareStatement(sql);
//            result=prepare.executeQuery();
//
//            ObservableList listData=FXCollections.observableArrayList();
//
//            while(result.next()){
//                listData.add(result.getString("product_name"));
//            }
//
//            order_productName.setItems(listData);
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    private SpinnerValueFactory<Integer>spinner;
//
//    public void orderSpinner(){
//        spinner=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,50,0);
//
//        order_quantity.setValueFactory(spinner);
//
//
//    }
//
//    private int qty;
//    public void orderQuantity(){
//        qty=order_quantity.getValue();
//        System.out.println(qty);
//    }



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
