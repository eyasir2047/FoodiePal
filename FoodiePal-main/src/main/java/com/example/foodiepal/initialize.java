package com.example.foodiepal;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class initialize  extends dashboardController implements Initializable {
    public void initialize(URL url, ResourceBundle rb) {
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
