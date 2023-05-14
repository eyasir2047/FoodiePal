package com.example.foodiepal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;

public class OrderPay extends  AvailableFoods implements OrderPayInterface{
    @FXML
    private Button order_addBtn;

    @FXML
    private TextField order_amount;

    @FXML
    private Button order_btn;
    @FXML
    private Label order_balance;

    @FXML
    private TableColumn< product, String > order_col_price;

    @FXML
    private TableColumn< product, String > order_col_productID;

    @FXML
    private TableColumn< product, String > order_col_productName;

    @FXML
    private TableColumn< product , String > order_col_quantity;

    @FXML
    private TableColumn< product, String > order_col_type;

    @FXML
    private AnchorPane order_form;

    @FXML
    private Button order_payBtn;

    @FXML
    private ComboBox<?> order_productID;

    @FXML
    private ComboBox<?> order_productName;

    @FXML
    private Spinner<Integer> order_quantity;

    @FXML
    private Button order_receiptBtn;

    @FXML
    private Button order_removeBtn;



    @FXML
    private TableView< product > order_tableView;

    @FXML
    private Label order_total;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public void orderAdd(){

        orderCustomerId();
        orderTotal();

        String sql = "INSERT INTO product" + "( customer_id , product_id , product_name , type , price , quantity , date)"
                + "VALUES( ? , ? , ? , ? , ? , ? , ? )";

        connect = database.connectDb();

        try {

            String orderType = "";
            double orderPrice = 0;

            String checkData = "SELECT * FROM category WHERE product_id = '" + order_productID.getSelectionModel().getSelectedItem() + "'";

            statement = connect.createStatement();
            result = statement.executeQuery( checkData );

            if( result.next() ){
                orderType = result.getString("type");
                orderPrice = result.getDouble("price");
            }
            prepare = connect.prepareStatement( sql );
            prepare.setString( 1 , String.valueOf( customerId ));
            prepare.setString( 2, (String) order_productID.getSelectionModel().getSelectedItem() );
            prepare.setString( 3 , (String) order_productName.getSelectionModel().getSelectedItem());

            prepare.setString( 4 , orderType );

            double totalPrice = orderPrice * qty;
            prepare.setString( 5 , String.valueOf( totalPrice ));

            prepare.setString( 6 , String.valueOf( qty ));

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
            prepare.setString( 7 , String.valueOf( sqlDate ));

            //prepare.executeUpdate();

//            String insertInfo = "INSERT INTO product_info ( customer_id , total , date )";
//
//            prepare = connect.prepareStatement( sql );
//            prepare.setString( 1 , String.valueOf( customerId ));
//            prepare.setString( 2 , String.valueOf( totalP ));
//            prepare.setString( 3 , String.valueOf( sqlDate ));

            prepare.executeUpdate();

            //orderDisplayTotal();

            // orderListData();
            orderDisplayTotal();
            orderDisplayData();

        }catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public void orderPay(){
        orderCustomerId();
        orderTotal();
        String sql = "INSERT INTO product_info (customer_id, total, date) VALUES(?,?,?)";
        connect =database.connectDb();
        try {
            Alert alert;
            if(balance == 0
                    || String.valueOf(balance) =="$0.0"
                    || String.valueOf(balance) == null
                    || totalP == 0
                    || String.valueOf(totalP) =="$0.0"
                    || String.valueOf(totalP) == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
            }
            else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option =  alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(totalP));
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(3,String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();
                    order_total.setText("$0.0");
                    order_balance.setText("$0.0");
                    order_amount.setText("");
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled!");
                    alert.showAndWait();
                }

            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private double totalP = 0;
    public void orderTotal(){

        orderCustomerId();
        String sql = "SELECT SUM(price) FROM product WHERE customer_id = " + customerId;

        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement( sql );
            result = prepare.executeQuery();

            if( result.next() ){
                totalP = result.getDouble("SUM(price)");
            }

        }catch ( Exception e ){
            e.printStackTrace();
        }
    }

    private double amount;
    private double balance;
    public void orderAmount(){

        orderTotal();

        Alert alert;
        if( order_amount.getText().isEmpty()
                || order_amount.getText() == null
                || order_amount.getText() == ""){
            alert = new Alert( Alert.AlertType.ERROR );
            alert.setHeaderText( null );
            alert.setContentText( "Please type the amount!");
            alert.showAndWait();
        }
        else {
            amount = Double.parseDouble(order_amount.getText());
            if(amount <totalP){
                order_amount.setText("");
            }
            else {
                balance = amount -totalP;
                order_balance.setText("$"+String.valueOf(balance));
            }

        }
    }
    public void orderDisplayTotal(){
        orderTotal();
        order_total.setText( "$" + String.valueOf( totalP ));
    }

    public ObservableList<product> orderListData(){

        orderCustomerId();
        ObservableList<product> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product WHERE customer_id = " + customerId;

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement( sql );
            result = prepare.executeQuery();

            product prod;
            while ( result.next() ){
                prod = new product( result.getInt("id"),
                        result.getString("product_id"),
                        result.getString("product_name") ,
                        result.getString("type") ,
                        result.getDouble("price") ,
                        result.getInt("quantity") );
                listData.add( prod );
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }

        return listData;
    }
    private int item ;
    public void orderRemove(){
        String sql = "DELETE FROM product WHERE id = " + item;
        connect = database.connectDb();
        try {
            Alert alert;
            if(item == 0
                    || String.valueOf(item) == null
                    || String.valueOf(item) ==""){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();
            }
            else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to Remove item: " + item +"?");
                Optional<ButtonType> option =  alert.showAndWait();
                if(option.get().equals((ButtonType.OK) )){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Removed!");
                    alert.showAndWait();
                    orderDisplayData();
                    orderDisplayTotal();
                    order_amount.setText("");
                    order_balance.setText("$0.0");

                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled!");
                    alert.showAndWait();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void orderSelectData(){
        product prod = order_tableView.getSelectionModel().getSelectedItem();
        int num = order_tableView.getSelectionModel().getSelectedIndex();
        if((num -1) <-1) return;
        item = prod.getId();
    }
    private ObservableList< product > orderData;
    public void orderDisplayData(){

        orderData = orderListData();

        order_col_productID.setCellValueFactory( new PropertyValueFactory<>("productId"));
        order_col_productName.setCellValueFactory( new PropertyValueFactory<>("name"));
        order_col_type.setCellValueFactory( new PropertyValueFactory<>("type"));
        order_col_price.setCellValueFactory( new PropertyValueFactory<>("price"));
        order_col_quantity.setCellValueFactory( new PropertyValueFactory<>("quantity"));

        order_tableView.setItems( orderData );
    }
    private int customerId;

    public void orderCustomerId(){

        String sql = "SELECT customer_id FROM product";
        // String sql = "SELECT id FROM product";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement( sql );
            result = prepare.executeQuery();

            while ( result.next() ){
                customerId = result.getInt("customer_id");
            }

            String checkData = "SELECT customer_id FROM product_info";

            statement = connect.createStatement();
            result = statement.executeQuery(checkData);

            int customerInfoId = 0;
            while ( result.next() ){
                customerInfoId = result.getInt("customer_id");
            }

            if( customerId == 0 ){
                customerId += 1;
            }else if( customerId == customerInfoId ){
                customerId += 1;
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }
    public void orderProductId(){
        String sql="SELECT product_id FROM category WHERE status= 'Available' ";
        connect=database.connectDb();
        try {
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            ObservableList listData=FXCollections.observableArrayList();

            while(result.next()){
                listData.add(result.getString("product_id"));
            }

            order_productID.setItems(listData);

            orderProductName();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void orderProductName(){

        String sql="SELECT product_name FROM category WHERE product_id='"
                +order_productID.getSelectionModel().getSelectedItem()+"'";

        connect=database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            ObservableList listData=FXCollections.observableArrayList();

            while(result.next()){
                listData.add(result.getString("product_name"));
            }

            order_productName.setItems(listData);


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private SpinnerValueFactory<Integer>spinner;

    public void orderSpinner(){
        spinner=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,50,0);

        order_quantity.setValueFactory(spinner);


    }

    private int qty;
    public void orderQuantity(){
        qty=order_quantity.getValue();
        System.out.println(qty);
    }





}
