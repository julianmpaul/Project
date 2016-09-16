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
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Jeankie
 */
public class ProductSuppliedsController implements Initializable {
        
        Connection con;
        String pid;
        
        @FXML
        private TableView<sup> suppliertable;
        @FXML
        private TableView<pro> producttable;
        @FXML
        private TableView<slied> suppliedtable;
        @FXML
        private TableColumn tcsupplier,tcsupplierid;
        @FXML
        private TableColumn tcproductname,tcproductcode,tcproductid;
        @FXML
        private TableColumn tcsupplierid1,tcsupplier1,tcproductname1,tcproductcode1,tcproductid1;
        @FXML
        private TextField suppliername,supplierid,productname,productcode;
        @FXML
        private Button add,remove;
        
        
        @FXML
        void add(ActionEvent event){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to add the product to the supplier ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           
            
           try{
                            con = Database.connect();
                            Statement st= con.createStatement();
                            
                            String SQL1 = "INSERT INTO `database`.`tblsuppliedproducts` (`productID`, `supplierID`) VALUES ('"+pid+"', '"+supplierid.getText()+"');";
                            
                            st.executeUpdate(SQL1);
                            con.close();
                            
                            productname.setText(null);
                            productcode.setText(null);
                            pid = null;
                            
                            
                            refreshsupplierdproduct();
                            refreshproduct();
                            
                            add.setDisable(true);
                            remove.setDisable(true);
                            
               
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Succesfully Added!");
                            alert.initStyle(StageStyle.UTILITY);
                            alert.showAndWait();
                           
                            
                    }catch (SQLException ex) {
                            System.out.println(ex);
                        }
        }
        }
        
        @FXML
        void remove(ActionEvent event){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to remove the product from this supplier ?");
                    alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                        try{
                            con = Database.connect();
                            Statement st= con.createStatement();
                            
                            String SQL1 = "DELETE FROM `database`.`tblsuppliedproducts`"
                                    + " WHERE `productID` = '"+pid+"' AND`supplierID` = '"+supplierid.getText()+"'; ";
                            st.executeUpdate(SQL1);
                            con.close();
                            
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Succesfully removed!");
                            alert.initStyle(StageStyle.UTILITY);
                            alert.showAndWait();
                            
                            add.setDisable(true);
                            remove.setDisable(true);
                            
                                 
                            refreshproduct();
                            refreshsupplierdproduct();
                            
                            productname.setText(null);
                            productcode.setText(null);
                            pid = null;
                            
                          }catch( SQLException ex){
                            System.out.println(ex);
            }
                }
        }
        
        
        
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
                // TODO
                
                refreshproduct();
                
                add.setDisable(true);
                remove.setDisable(true);
                
                
            try {
                suppliedproducts s = new suppliedproducts();
                
                s.sname(tcsupplier);
                s.sID(tcsupplierid);
                
                suppliertable.getItems().setAll(getsupInfo());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            suppliertable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (suppliertable.getSelectionModel().getSelectedItem() != null) {
                
               
               suppliername.setText(newValue.supnamee);
               supplierid.setText(newValue.supIDD);
               productname.setText(null);
               productcode.setText(null);
               pid = null;
               
               add.setDisable(true);
               remove.setDisable(true);
               
               
               
               refreshsupplierdproduct();
               
               
               
            }
      
        });
            
            producttable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (producttable.getSelectionModel().getSelectedItem() != null) {
                
               
               productname.setText(newValue.pronamee);
               productcode.setText(newValue.procode);
               pid = newValue.proIDD;
               
               if(supplierid.getText() == null || supplierid.getText().isEmpty()){
                   System.out.println("wlang laman");
               }else{
                  try{
                      String positive = null;
                        con = Database.connect();

                         String SQL = "SELECT tp.productID FROM tblproducts tp JOIN tblsuppliedproducts tsp"
                                 + " ON tsp.`productID` = tp.`productID` AND tsp.`supplierID` = '"+supplierid.getText()+"'"
                                 + " AND tsp.productID = '"+pid+"';";
                        ResultSet rs = con.createStatement().executeQuery(SQL);

                            if (rs.next()) {
                              positive = rs.getString(1);
                            }
                            if(positive != null){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Product is already added to the supplier !");
                                alert.initStyle(StageStyle.UTILITY);
                                alert.showAndWait();
                                
                                add.setDisable(true);
                                remove.setDisable(true);
                                
                            }else{
                                add.setDisable(false);
                            }
                            con.close();
                            }catch(NumberFormatException | SQLException e){
                                System.out.println(e);
                        }
               }
               
               
               
            }
      
        });
        
            
            suppliedtable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (suppliedtable.getSelectionModel().getSelectedItem() != null) {
                
               
               productname.setText(newValue.pronamee);
               productcode.setText(newValue.procode);
               pid = newValue.proIDD;
               
               add.setDisable(true);
                remove.setDisable(false);
                
              
            }
      
        });
            
        
    }
        private void refreshproduct() {
            
            try {
                     suppliedproducts s = new suppliedproducts();

                     s.pname(tcproductname);
                     s.pID(tcproductid);
                     s.pCode(tcproductcode);

                     producttable.getItems().setAll(getproInfo());


                 } catch (SQLException ex) {
                     System.out.println(ex);
                 }
     
  }
        
        private void refreshsupplierdproduct() {
            try {
                suppliedproducts s = new suppliedproducts();
                
                s.sliedname(tcproductname1);
                s.sliedproID(tcproductid1);
                s.sliedCode(tcproductcode1);
                s.sliedsupname(tcsupplier1);
                s.sliedsupid(tcsupplierid1);
                
                suppliedtable.getItems().setAll(getsliedInfo());
                
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
  public class sup{
        private String supnamee,supIDD;
        
        
        public sup(String supID,String supname){
            
            this.supnamee = supID;
            this.supIDD = supname;
            
        }
        
        
        public String getsname(){            return supnamee;   }
        public void setsname(String supID) {  supnamee = supID;   }
        //---------
        public String getsID(){              return supIDD;  }
        public void setsID(String supname) {  supIDD = supname; }
        //---------
        
        
    }
        
        public List<sup> getsupInfo() throws SQLException {
        List RL = new LinkedList();
        
            
             con = Database.connect();
        
         String sql = "SELECT supplierName,supplierID FROM tblsupplier;";

            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                String supID = rs.getString(1);
                String supname = rs.getString(2);
                
             RL.add(new sup(supID,supname));
            }con.close();
            
        
        return RL;
        }
        
        //----------------------------------------
        
        public class pro{
        private String pronamee,proIDD,procode;
        
        
        public pro(String proN,String proI,String proC){
            
            this.pronamee = proN;
            this.proIDD = proI;
            this.procode = proC;
            
        }
        
        
        public String getpname(){            return pronamee;   }
        public void setpname(String proN) {  pronamee = proN;   }
        //---------
        public String getpID(){              return proIDD;  }
        public void setpID(String proI) {  proIDD = proI; }
        //---------
        public String getpcode(){              return procode;  }
        public void setpcode(String proC) {  procode = proC; }
        //---------
        
    }
        
        public List<pro> getproInfo() throws SQLException {
        List RL = new LinkedList();
        
            
             con = Database.connect();
        
             String query = "SELECT productname,productID,productcode FROM tblproducts";

            ResultSet rs = con.createStatement().executeQuery(query);
            
            while (rs.next()) {
                
                String proN = rs.getString(1);
                String proI = rs.getString(2);
                String proC = rs.getString(3);
               
             RL.add(new pro(proN,proI,proC));
             
            }con.close();
        
        return RL;
        }
        
        //----------------------------------------
        
        public class slied{
        private String pronamee,proIDD,procode,supNN,supII;
        
        
        public slied(String proN,String proI,String proC,String supN,String supI){
            
            this.pronamee = proN;
            this.proIDD = proI;
            this.procode = proC;
            this.supNN = supN;
            this.supII = supI;
            
        }
        
        
        public String getpname(){            return pronamee;   }
        public void setpname(String proN) {  pronamee = proN;   }
        //---------
        public String getpID(){              return proIDD;  }
        public void setpID(String proI) {  proIDD = proI; }
        //---------
        public String getpcode(){              return procode;  }
        public void setpcode(String proC) {  procode = proC; }
        //---------
        public String getsname(){              return supNN;  }
        public void setsname(String supN) {  supNN = supN; }
        //---------
        public String getsid(){              return supII;  }
        public void setsid(String supI) {  supII = supI; }
        //---------
        
    }
        
        public List<slied> getsliedInfo() throws SQLException {
        List RL = new LinkedList();
        
            
             con = Database.connect();
        
             String query = "SELECT tp.productname,tp.productID,tp.productcode,ts.`supplierName`,ts.`supplierID` FROM tblproducts tp\n" +
                    "JOIN tblsuppliedproducts tsp \n" +
                    " JOIN tblsupplier ts\n" +
                    " WHERE (tsp.`supplierID` = '"+supplierid.getText()+"' AND tsp.`supplierID` = ts.`supplierID` AND tsp.`productID` = tp.`productID`)";

            ResultSet rs = con.createStatement().executeQuery(query);
            
            while (rs.next()) {
                
                String proN = rs.getString(1);
                String proI = rs.getString(2);
                String proC = rs.getString(3);
                String supN = rs.getString(4);
                String supI = rs.getString(5);
                
             RL.add(new slied(proN,proI,proC,supN,supI));
             
            }con.close();
        
        return RL;
        }
}
