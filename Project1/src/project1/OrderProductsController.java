/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jeankie
 */
public class OrderProductsController implements Initializable {

        Connection con;
        String suppliers;
        private int num, new_id;
        private RestrictiveTextField qty,price;
        @FXML
        private AnchorPane pane;
        @FXML
        private ComboBox cbsupplier;
        @FXML
        private Label orderno;
        @FXML
        private Button add,remove,make,print;
        @FXML
        private TableView<product> tblproducts;
        @FXML
        private TableColumn tcsupplier,tcproductid,tcproductname,tcremaining;
        @FXML
        private TableView tblorder;
        @FXML
        private TableColumn tcsupplier1,tcproductid1,tcqty,tctotal;
        @FXML
        private TextField productname,productid,stocks,total;
        
        @FXML
        void supplier(ActionEvent event){
            try {
                
                con = Database.connect();
                String SQL = "  SELECT ts.supplierID FROM tblsupplier ts WHERE ts.`supplierName` = '"+cbsupplier.getValue()+"'";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                while (rs.next()) {
                    suppliers = rs.getString(1);
                    System.out.println(suppliers);
                }
                refreshorderno();
                refreshproducts();
                textfield();
                con.close();
            } catch (SQLException ex) {
                
            }
        }
        
        @FXML
        void add(ActionEvent event){
            if((productname.getText() == null || productid.getText() == null || price.getText() == null || qty.getText() == null)
                || (productname.getText().isEmpty() || productid.getText().isEmpty() || price.getText().isEmpty() || qty.getText().isEmpty())    ){
                            
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Please input all the fields !");
                            alert.initStyle(StageStyle.UTILITY);
                            alert.showAndWait();
            }else{
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Add product to the list ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    //INSERT INTO `database`.`tblorderproducts` (`orderno`, `supplierid`, `productid`, `quantity`, `price`, `total`, `dateoforder`, `loggedID`) VALUES ('Order-00001', 'Sup-001', 'Pro-00002', '1', '10', '10', '2016-09-16', 'Logged-00001'); 
                    try{
                            con = Database.connect();
                            Statement st= con.createStatement();
                            
                            String SQL1 = "INSERT INTO `database`.`tblorderlist`"
                                    + " (`orderno`, `supplierid`, `productid`, `quantity`, `price`, `total`, `dateoforder`, `loggedID`)"
                                    + " VALUES ('"+orderno.getText()+"', '"+suppliers+"', '"+productid.getText()+"',"
                                    + " '"+qty.getText()+"', '"+price.getText()+"', '"+total.getText()+"',"
                                    + " '"+LoginController.date+"', '"+LoginController.loggedid+"');";
                            
                            st.executeUpdate(SQL1);
                            con.close();
                            
                           
                            
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Message");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Succesfully Added !");
                            alert1.initStyle(StageStyle.UTILITY);
                            alert1.showAndWait();
                            
                            
                        }catch(SQLException ee){
                            System.out.println(ee);
                    }
                    
                }
                
            }
        }
        @FXML
        void remove(ActionEvent event){
            
        }
        @FXML
        void make(ActionEvent event){
            
        }
        @FXML
        void print(ActionEvent event){
            
        }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        qty = new RestrictiveTextField();
        qty.restrictProperty().set("[0-9]");
        qty.maxLengthProperty().set(3);
        qty.setLayoutX(536);
        qty.setLayoutY(267);
        qty.setPrefSize(200, 25);
        qty.setText(null);
        qty.setPromptText("Quantity");
        
        price = new RestrictiveTextField();
        price.restrictProperty().set("[.,0-9]");
        price.maxLengthProperty().set(5);
        price.setLayoutX(536);
        price.setLayoutY(305);
        price.setPrefSize(200, 25);
        price.setText(null);
        price.setPromptText("Price");
        
        pane.getChildren().addAll(qty,price);
        
        buttons();
        refreshorderno();
        supplierbox();
        
        tblproducts.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (tblproducts.getSelectionModel().getSelectedItem() != null) {
                
             productname.setText(newValue.pname);
             productid.setText(newValue.pID);
             String a = String.valueOf(newValue.pstock);
             stocks.setText(a);
             add.setDisable(false);
            }
      
        });
        
        price.setOnKeyReleased((KeyEvent me) -> {
            if((price.getText() == null || qty.getText() == null) || (price.getText().isEmpty() || qty.getText().isEmpty())){
                
            }else{
                
                    
                    
                    String a = price.getText();
                    String b = qty.getText();
                    double pricing1 = Double.parseDouble(a);
                    double pricing2 = Double.parseDouble(b);
                    double pricewvat1 = (pricing1*pricing2);
                    String c = Double.toString(pricewvat1);
                    total.setText(c);
                    
                
            }
        });
        
    }    
    
    private void textfield(){
        qty.setText(null);
        price.setText(null);
        total.setText(null);
        stocks.setText(null);
        productname.setText(null);
        productid.setText(null);
    }
    private void refreshproducts(){
        
        orderproductsinfo opi = new orderproductsinfo();
        opi.suppliername(tcsupplier);
        opi.proname(tcproductname);
        opi.proID(tcproductid);
        opi.stock(tcremaining);
        tblproducts.getItems().setAll(getproductInfo());
        
    }
    private void buttons() {
        add.setDisable(true);
        remove.setDisable(true);
        make.setDisable(true);
        print.setDisable(true);
    }
    
    private void supplierbox(){
        try {
                
                con = Database.connect();
                String SQL = "SELECT DISTINCT ts.suppliername FROM tblsupplier ts "
                        + "INNER JOIN tblsuppliedproducts tsp ON ts.`supplierID` = tsp.`supplierID`";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                while (rs.next()) {
                    cbsupplier.getItems().addAll(rs.getString(1));
                }
                con.close();
            } catch (SQLException ex) {
                
            }
    }
    private void refreshorderno(){
        
        try {
                con = Database.connect();
                
                String SQL = "select max(orderno) from tblorderlist;";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                
                if (rs.next()) {
                    
                    String text = rs.getString("max(orderno)");
                    String asd = text.substring(6);
                    String aa = asd;
                    orderno.setText(asd);
                    num = Integer.parseInt(orderno.getText());
                    new_id = num + 1;
                    orderno.setText("Order-0000" + new_id);
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.orderno.setText("Order-000" + new_id);
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.orderno.setText("Order-0" + new_id);
                    }else if ((new_id >= 1000) && (new_id <= 9999)) {
                        this.orderno.setText("Order-" + new_id);
                    }
                }
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        
    }
    public class product{
        private String supnamee,pID,pname;
        private int pstock;
        
        
        public product(String supname,String proname,String proID,int stock){
            
            this.supnamee = supname;
            this.pname = proname;
            this.pID = proID;
            this.pstock = stock;
            
            
        }
        //---------
        public String gesname(){              return supnamee;  }
        public void setsname(String supname) {  supnamee = supname; }
        //---------
        public String getname(){            return pname;   }
        public void setname(String proname) {  pname = proname;   }
        //---------
        public String getID(){              return pID;  }
        public void setID(String proID) {  pID = proID; }
        //---------
        public int getstock(){            return pstock;   }
        public void setstock(int stock) {  pstock = stock;   }
       
        
        
    }
        
        public List<product> getproductInfo() {
        List RL = new LinkedList();
        try {
            
             con = Database.connect();
        
         String SQL = "SELECT DISTINCT ts.suppliername,tp.`productname`,tp.`productID`,tp.`stock` FROM tblsupplier ts"
                 + " INNER JOIN tblsuppliedproducts tsp ON ts.`supplierID` = tsp.`supplierID`"
                 + " INNER JOIN tblproducts tp ON tp.`productID` = tsp.`productID`"
                 + " WHERE tsp.`supplierID` = '"+suppliers+"'";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                
                String supname = rs.getString(1);
                String proname = rs.getString(2);
                String proID = rs.getString(3);
                int stock = rs.getInt(4);
                
                
                
                RL.add(new product(supname,proname,proID,stock));
            }con.close();
        } catch (SQLException ex) {
            System.out.println("Product Error: "+ex);
        }
        return RL;
        }
}
