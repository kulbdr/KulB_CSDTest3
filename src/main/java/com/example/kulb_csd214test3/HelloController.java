package com.example.kulb_csd214test3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
public class HelloController implements Initializable {

    public TextField icustomerId;
    public TextField icustomerName;
    public TextField imobileNumber;
    public TextField ipizzaSize;

    public TextField itoppingNumber;

    public TextField itotalBill;
    @FXML
    private TableView<order> tableView;
    @FXML
    private TableColumn<order, Integer> customerId;
    @FXML
    private TableColumn<order, String> customerName;
    @FXML
    private TableColumn<order, String> mobileNumber;
    @FXML
    private TableColumn<order, String> pizzaSize;

    @FXML
    private TableColumn<order, String> toppingNumber;

    @FXML
    private TableColumn<order, String> totalBill;

    ObservableList<order> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerId.setCellValueFactory(new
                PropertyValueFactory<order, Integer>("customerId"));
        customerName.setCellValueFactory(new
                PropertyValueFactory<order, String>("customerName"));
        mobileNumber.setCellValueFactory(new
                PropertyValueFactory<order, String>("mobileNumber"));
        pizzaSize.setCellValueFactory(new
                PropertyValueFactory<order, String>("pizzaSize"));

        toppingNumber.setCellValueFactory(new
                PropertyValueFactory<order, String>("toppingNumber"));

        totalBill.setCellValueFactory(new
                PropertyValueFactory<order, String>("totalBill"));
        tableView.setItems(list);
    }

    public void Hellobutton(ActionEvent actionEvent) {
        list.clear();
        tableView.setItems(list);
        populateTable();
    }

    public void populateTable() {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_test3";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM ordertable";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int customerId = resultSet.getInt("customerId");
                String customerName = resultSet.getString("customerName");
                String mobileNumber = resultSet.getString("mobileNumber");
                String pizzaSize = resultSet.getString("pizzaSize");
                String toppingNumber = resultSet.getString("toppingNumber");
                String totalBill = resultSet.getString("totalBill");
                tableView.getItems().add(new order(customerId, customerName, mobileNumber,
                        pizzaSize, toppingNumber, totalBill));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CreateData(ActionEvent actionEvent) {
        // Get the input values from the TextFields
        String getcustomerId = icustomerId.getText();
        String getcustomerName = icustomerName.getText();
        String getmobileNumber = imobileNumber.getText();
        String getpizzaSize = ipizzaSize.getText();
        int gettoppingNumber = Integer.parseInt(itoppingNumber.getText());  // Convert to integer
        String gettotalBill = itotalBill.getText(); // This field will be auto-calculated

        // Calculate the total bill
        double totalBill = calculateTotalBill(getpizzaSize, gettoppingNumber);

        // Set the total bill to the corresponding TextField
        itotalBill.setText(String.format("%.2f", totalBill));  // Format as currency (e.g., $15.00)

        // Database connection and insert logic
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_test3";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            // Prepare the SQL query
            String query = "INSERT INTO `ordertable`(`customerName`, `mobileNumber`, `pizzaSize`, `toppingNumber`, `totalBill`) " +
                    "VALUES ('" + getcustomerName + "','" + getmobileNumber + "','" + getpizzaSize + "','" + gettoppingNumber + "','" + totalBill + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);  // Execute the insert query
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void UpdateData(ActionEvent actionEvent) {
        String getcustomerId = icustomerId.getText();
        String getcustomerName = icustomerName.getText();
        String getmobileNumber = imobileNumber.getText();
        String getpizzaSize = ipizzaSize.getText();
        String gettoppingNumber = itoppingNumber.getText();
        String gettotalBill = itotalBill.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/db_test3";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database

            String query = "UPDATE `ordertable` SET `customerName`='" + getcustomerName + "',``='" + getmobileNumber + "',`pizzaSize`='" + pizzaSize + "'`toppingNumber`='" + toppingNumber + "'`totalBill`='" + totalBill + "' WHERE `customerId` = '" + getcustomerId + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteData(ActionEvent actionEvent) {
        String getcustomerId = icustomerId.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/db_test3";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM ordertable WHERE `customerId`= '" + getcustomerId + "' ";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void ReadData(ActionEvent actionEvent) {
        String getcustomerId = icustomerId.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/db_test3";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM ordertable WHERE `customerId`= '" + getcustomerId + "' ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int customerId = resultSet.getInt("customerId");
                String customerName = resultSet.getString("customerName");
                String mobileNumber = resultSet.getString("mobileNumber");
                String pizzaSize = resultSet.getString("pizzaSize");
                String toppingNumber = resultSet.getString("toppingNumber");
                String totalBill = resultSet.getString("totalBill");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double calculateTotalBill(String pizzaSize, int toppingNumber) {
        double pizzaPrice = 0.0;
        double toppingPrice = 1.50;
        double hstRate = 0.15; // HST is 15%


        switch (pizzaSize.toUpperCase()) {
            case "XL":
                pizzaPrice = 15.00;
                break;
            case "L":
                pizzaPrice = 12.00;
                break;
            case "M":
                pizzaPrice = 10.00;
                break;
            case "S":
                pizzaPrice = 8.00;
                break;
            default:
                pizzaPrice = 0.0;
                break;
        }


        double toppingTotal = toppingNumber * toppingPrice;


        double subtotal = pizzaPrice + toppingTotal;


        double hstAmount = subtotal * hstRate;


        double totalBill = subtotal + hstAmount;

        return totalBill;
    }

}

