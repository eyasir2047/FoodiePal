package com.example.foodiepal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Dashboard extends OrderPay implements dashboardInterface  {
    @FXML
    private BarChart<?, ?> dashboard_ICChart;

    @FXML
    private Label dashboard_NC;

    @FXML
    private BarChart<?, ?> dashboard_NOCChart;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Label dashboard_Tincome;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public void dashboardNC(){
        String sql = "SELECT COUNT(id) FROM product_info";
        int nc =0;
        connect = database.connectDb();
        try {
            statement =connect.createStatement();
            result = statement.executeQuery(sql);
            if(result.next()){
                nc = result.getInt("COUNT(id)");
            }
            dashboard_NC.setText(String.valueOf(nc));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardTI(){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql ="SELECT SUM(total) FROM product_info WHERE date = '"+sqlDate+"'";
        connect = database.connectDb();
        double ti =0;
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);
            if(result.next()){
                ti = result.getDouble("SUM(total)");
            }
            dashboard_TI.setText("$" + String.valueOf(ti));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void dashboardTIncome(){
        String sql = "SELECT SUM(total) FROM product_info";
        connect = database.connectDb();
        double ti =0;
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);
            if(result.next()){
                ti = result.getDouble("SUM(total)");
            }
            dashboard_Tincome.setText("$" + String.valueOf(ti));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardNOCCChart(){
        dashboard_NOCChart.getData().clear();
        String sql = "SELECT date, COUNT(id) FROM product_info GROUP by date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        connect = database.connectDb();
        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            dashboard_NOCChart.getData().add(chart);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void dashboardICC(){
        dashboard_ICChart.getData().clear();
        String sql = "SELECT date, SUM(total) FROM product_info GROUP BY date ORDER BY TIMESTAMP(total) ASC LIMIT 7";
        connect = database.connectDb();
        try {

            XYChart.Series chart = new XYChart.Series();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
            }
            dashboard_ICChart.getData().add(chart);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
