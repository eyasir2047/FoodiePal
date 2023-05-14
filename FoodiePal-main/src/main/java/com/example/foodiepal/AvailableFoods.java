package com.example.foodiepal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AvailableFoods implements AvailableFoodInterface {
    @FXML
    private Button availableFD_addBtn;

    @FXML
    public Button availableFD_btn;

    @FXML
    private Button availableFD_clearBtn;


    @FXML
    private TableView<categories> availableFD_tableView;


    @FXML
    private TableColumn<categories, String> availableFD_col_price;

    @FXML
    private TableColumn<categories, String> availableFD_col_productID;

    @FXML
    private TableColumn<categories, String> availableFD_col_productName;

    @FXML
    private TableColumn<categories, String> availableFD_col_status;

    @FXML
    private TableColumn<categories, String> availableFD_col_type;

    @FXML
    private Button availableFD_deleteBtn;

    @FXML
    public  AnchorPane availableFD_form;

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

    @FXML
    private TextField availableFD_search;

    @FXML
    private Button availableFD_updateBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;



    public void availableFDAdd(){
        String sql="INSERT INTO category (product_id,product_name,type,price,status) "
                +"VALUES(?,?,?,?,?)";
        connect= database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            prepare.setString(1,availableFD_productID.getText());
            prepare.setString(2,availableFD_productName.getText());
            prepare.setString(3,(String) availableFD_productType.getSelectionModel().getSelectedItem());
            prepare.setString(4,availableFD_productPrice.getText());
            prepare.setString(5,(String)availableFD_productStatus.getSelectionModel().getSelectedItem());

            Alert alert;

            if(availableFD_productID.getText().isEmpty()
                    || availableFD_productName.getText().isEmpty()
                    ||availableFD_productType.getSelectionModel()==null
                    || availableFD_productPrice.getText().isEmpty()
                    || availableFD_productStatus.getSelectionModel()==null){

                alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            }
            else {

                String checkData="SELECT product_id FROM category WHERE product_id='"
                        +availableFD_productID.getText()+"'";

                connect=database.connectDb();

                statement=connect.createStatement();
                result=statement.executeQuery(checkData);

                if(result.next()){
                    alert= new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID: "+availableFD_productID.getText()+" is already exit!");
                    alert.showAndWait();
                }
                else{
                    prepare.executeUpdate();

                    alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added|");
                    alert.showAndWait();

                    //to show the data
                    availableFDShowData();
                    //to clear the fields
                    availableFDClear();

                }



            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void availableFDUpdate(){
        String sql="UPDATE category SET product_name ='"
                +availableFD_productName.getText()+ "',type='"
                + availableFD_productType.getSelectionModel().getSelectedItem()+"',price='"
                +availableFD_productPrice.getText()+"',status='"
                +availableFD_productStatus.getSelectionModel().getSelectedItem()
                +"' WHERE product_id='"+availableFD_productID.getText()+"'";

        connect=database.connectDb();

        try{


            Alert alert;

            if(availableFD_productID.getText().isEmpty()
                    || availableFD_productName.getText().isEmpty()
                    || availableFD_productType.getSelectionModel().getSelectedItem()==null
                    || availableFD_productPrice.getText().isEmpty()
                    || availableFD_productStatus.getSelectionModel().getSelectedItem()==null){

                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fields");
                alert.showAndWait();

            }
            else{


                alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Product ID: "
                        +availableFD_productID.getText()+"?");

                Optional<ButtonType> option=alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();


                    statement=connect.createStatement();
                    statement.executeUpdate(sql);

                    //to show the data
                    availableFDShowData();
                    //to clear the fields
                    availableFDClear();

                }
                else{
                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();


                }


            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void availableFDDelete() {
        String sql = "DELETE FROM category WHERE product_id ='"
                + availableFD_productID.getText() + "'";

        connect = database.connectDb();
        try {


            Alert alert;

            if (availableFD_productID.getText().isEmpty()
                    || availableFD_productName.getText().isEmpty()
                    || availableFD_productType.getSelectionModel().getSelectedItem() == null
                    || availableFD_productPrice.getText().isEmpty()
                    || availableFD_productStatus.getSelectionModel().getSelectedItem() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank fields");
                alert.showAndWait();

            } else {


                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Product ID: "
                        + availableFD_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();


                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    //to show the data
                    availableFDShowData();
                    //to clear the fields
                    availableFDClear();

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();


                }


            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void availableFDClear(){
        availableFD_productID.setText("");
        availableFD_productName.setText("");
        availableFD_productType.getSelectionModel().clearSelection();
        availableFD_productPrice.setText("");
        availableFD_productStatus.getSelectionModel().clearSelection();

    }



    public ObservableList<categories> availableFDListData(){

        //ObservableList<categories>listData=FXCollections.observableArrayList();
        final ObservableList<categories>listData= FXCollections.observableArrayList();

        String sql="SELECT * FROM category";

        connect=database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            categories cat;

            while(result.next()){
                cat=new categories(result.getString("product_id"),
                        result.getString("product_name"),result.getString("type"),
                        result.getDouble("price"),result.getString("status"));

                listData.add(cat);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return  listData;

    }

//    public void availableFDSearch(){
//
//
//
//       FilteredList<categories>filter=new FilteredList<>(availableFDList,e->true);
//
//
//
//
//          availableFD_search.textProperty().addListener((observabl, newValue, oldValue) -> {
//
//                       filter.setPredicate(predicateCategories -> {
//
//
//                           if ( newValue == null || newValue.isEmpty() ) {
//                                return true;
//                            }
//
//
//
//                           String searchKey = newValue.toLowerCase();
//
//                            if (predicateCategories.getProductId().toLowerCase().contains(searchKey)) {
//                                return true;
//                            } else if (predicateCategories.getName().toLowerCase().contains(searchKey)) {
//                                return true;
//                            } else if (predicateCategories.getType().toLowerCase().contains(searchKey)) {
//                                return true;
//                            } else if (predicateCategories.getPrice().toString().contains(searchKey)) {
//                                return true;
//                            } else if (predicateCategories.getStatus().toLowerCase().contains(searchKey)) {
//                                return true;
//                            } else {
//                                return false;
//                            }
////
//
//
//
//                            });
//                });
//
//        SortedList<categories>sortList=new SortedList<>(filter);
//        sortList.comparatorProperty().bind(availableFD_tableView.comparatorProperty());
//
//        availableFD_tableView.setItems(sortList);
//
//
//
//    }
//
//
//
//   private  ObservableList<categories>availableFDList;
//
//    public  void availableFDShowData(){
//        availableFDList=availableFDListData();
//        availableFD_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
//        availableFD_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        availableFD_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
//        availableFD_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        availableFD_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
//
//
//        availableFD_tableView.setItems(availableFDList);
//
//
//
//    }


    ///-----------chatgpt

    private FilteredList<categories> filteredAvailableFDList;
    private SortedList<categories> sortedAvailableFDList;

    public void availableFDShowData() {

        ObservableList<categories> availableFDList = availableFDListData();
        availableFD_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        availableFD_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availableFD_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        availableFD_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        availableFD_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        filteredAvailableFDList = new FilteredList<>(availableFDList, (category) -> true);
        sortedAvailableFDList = new SortedList<>(filteredAvailableFDList);
        sortedAvailableFDList.comparatorProperty().bind(availableFD_tableView.comparatorProperty());

        availableFD_tableView.setItems(sortedAvailableFDList);
    }

    public void availableFDSearch() {
        String searchKey = availableFD_search.getText().toLowerCase();

        filteredAvailableFDList.setPredicate((category) -> {
            if (searchKey.isEmpty()) {
                return true;
            }
            return category.getProductId().toLowerCase().contains(searchKey)
                    || category.getName().toLowerCase().contains(searchKey)
                    || category.getType().toLowerCase().contains(searchKey)
                    || category.getPrice().toString().contains(searchKey)
                    || category.getStatus().toLowerCase().contains(searchKey);
        });
    }


    //------------end

    public void availableFDSelect(){
        categories catData=availableFD_tableView.getSelectionModel().getSelectedItem();
        int num=availableFD_tableView.getSelectionModel().getSelectedIndex();

        if((num-1)<-1){
            return;
        }
        availableFD_productID.setText(catData.getProductId());
        availableFD_productName.setText(catData.getName());
        availableFD_productPrice.setText(String.valueOf(catData.getPrice()));



    }






    //Available foods/drinks

    private  String[] categories={"Meals","Drinks"};

    public  void availableFDType(){
        List<String> listCat=new ArrayList<>();

        for(String data:categories){
            listCat.add(data);
        }

        ObservableList listData=FXCollections.observableArrayList(listCat);
        availableFD_productType.setItems(listData);


    }
    private String[] status={"Available","Not Available"};
    public void availableFDStatus(){
        List<String> listStatus= new ArrayList<>();
        for(String data: status){
            listStatus.add(data);
        }
        ObservableList listData= FXCollections.observableArrayList(listStatus);
        availableFD_productStatus.setItems(listData);

    }

}
