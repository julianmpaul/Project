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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Jeankie
 */
public class SupplierController implements Initializable {
        
        private RestrictiveTextField name;
        private RestrictiveTextArea description,address,contactperson,contactnumber;
        Connection con;
        private int num, new_id;
        @FXML
        private Label supplierid;
        @FXML
        private Pane pane;
        @FXML
        private Button btnsave,btnupdate,btndelete,btnreset,btncancel;
        @FXML
        private TableView<supplier> supplier;
        @FXML
        private TableColumn tcid,tcname,tcdescription,tcaddress,tccontactperson,tccontactnumber,tclog;
            
            @FXML
            private void save(ActionEvent event){
                                   
 
                if((name.getText() == null || description.getText() == null || address.getText() == null || contactperson.getText() == null ||contactnumber.getText() == null) ||
                        (name.getText().isEmpty() || description.getText().isEmpty() || address.getText().isEmpty() || contactperson.getText().isEmpty() ||contactnumber.getText().isEmpty())){
                
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please input all the data!");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                
                }else{
                    
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to save the data ?");
                    alert.initStyle(StageStyle.UTILITY);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        String nam = null;
                    try {
                
                con = Database.connect();
                String SQL = "SELECT supplierName FROM tblsupplier WHERE supplierName = '"+name.getText()+"'";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                
                
                while (rs.next()) {
                    nam = rs.getString(1);
                }
                
                con.close();
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
                    
                    if(null == (nam)){
                        try{
           
                    con = Database.connect();
                    Statement st= con.createStatement();

                        String SQL1 = "INSERT INTO `database`.`tblsupplier` (`supplierID`, `supplierName`, `description`,"
                                + " `address`, `contactperson`, `contactnumber`, `loggedID`)"
                                + "VALUES ('"+supplierid.getText()+"', '"+name.getText()+"', '"+description.getText()+"',"
                                + " '"+address.getText()+"', '"+contactperson.getText()+"', '"+contactnumber.getText()+"', '"+LoginController.loggedid+"'); ";
                        
                        st.executeUpdate(SQL1);
                        con.close();
                        
                         alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully Saved!");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    
                    name.setText(null);
                    description.setText(null);
                    address.setText(null);
                    contactperson.setText(null);
                    contactnumber.setText(null);
                    
                    
                    buttons();
                    refreshid();
                        
                        }catch(SQLException e){
                            System.out.println(e);
                        }
                    }else{
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Message");
                            alert1.setHeaderText(null);
                            alert1.setContentText("The supplier name has a duplicate value !");
                            alert1.initStyle(StageStyle.UTILITY);
                            alert1.showAndWait();
                    }
                    
                }
                }
            }
            
            @FXML
            private void update(ActionEvent event){
                    
                if((name.getText() == null || description.getText() == null || address.getText() == null || contactperson.getText() == null ||contactnumber.getText() == null) ||
                        (name.getText().isEmpty() || description.getText().isEmpty() || address.getText().isEmpty() || contactperson.getText().isEmpty() ||contactnumber.getText().isEmpty())){
                
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please input all the data for update!");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                }else{
                    
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to update the data ?");
                    alert.initStyle(StageStyle.UTILITY);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        try {
                        con = Database.connect();
                        Statement st= con.createStatement();
                        String SQL1 = "UPDATE `database`.`tblsupplier` SET `supplierName` = '"+name.getText()+"' ,`description` = '"+description.getText()+"' ,`address` = '"+address.getText()+"' ,`contactperson` = '"+contactperson.getText()+"' ,`contactnumber` = '"+contactnumber.getText()+"',`loggedID` = '"+LoginController.loggedid+"' WHERE `supplierID` = '"+supplierid.getText()+"'";
                        st.executeUpdate(SQL1);
                        con.close();
                        
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Message");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Succesfully Updated!");
                        alert1.showAndWait();
                        
                        name.setText(null);
                        description.setText(null);
                        address.setText(null);
                        contactperson.setText(null);
                        contactnumber.setText(null);
            
                        buttons();
                        refreshid();
                        
                        } catch (SQLException ex) {
                            System.out.println(ex);
                        }
                        
                        
                    }
                    
                }
            }
            
            @FXML
            private void delete(ActionEvent event){
        
                Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete the data ?");
                    alert.initStyle(StageStyle.UTILITY);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        try {
                        con = Database.connect();
                        Statement st2= con.createStatement();
                        String SQL1 = "DELETE FROM `database`.`tblsupplier` WHERE `supplierID` = '"+supplierid.getText()+"';";
                        st2.executeUpdate(SQL1);
                        con.close();
                        
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Message");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Succesfully Deleted!");
                        alert1.showAndWait();
                        
                        name.setText(null);
                        description.setText(null);
                        address.setText(null);
                        contactperson.setText(null);
                        contactnumber.setText(null);
            
                        buttons();
                        refreshid();
                        
                        } catch (SQLException ex) {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Message");
            alert1.setHeaderText(null);
            alert1.setContentText("Supplier was used in products and cannot be deleted !");
            alert1.initStyle(StageStyle.UTILITY);

                String exceptionText = ex.toString();

            Label label = new Label("The exception stacktrace is:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
            alert1.getDialogPane().setExpandableContent(expContent);
            alert1.initStyle(StageStyle.UTILITY);
            alert1.showAndWait();
                        }
                        
                        
                    }
                    
            }
            
            @FXML
            private void reset(ActionEvent event){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to reset ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK){
                    name.setText(null);
                    description.setText(null);
                    address.setText(null);
                    contactperson.setText(null);
                    contactnumber.setText(null);
                }
                buttons();
                    }
            
            @FXML
            private void cancel(ActionEvent event){
        
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to cancel ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
            
        
                        name.setText(null);
                        description.setText(null);
                        address.setText(null);
                        contactperson.setText(null);
                        contactnumber.setText(null);
            
                        buttons();
                        refreshid();
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
        
        name = new RestrictiveTextField();
        name.maxLengthProperty().set(50);
        name.setLayoutX(155);
        name.setLayoutY(118);
        name.setPrefSize(200, 25);
        name.setText(null);
        name.setPromptText("Supplier Name");
        
        description = new RestrictiveTextArea();
        description.maxLengthProperty().set(200);
        description.setLayoutX(155);
        description.setLayoutY(153);
        description.setPrefSize(200, 65);
        description.setText(null);
        description.setWrapText(true);
        description.setPromptText("Supplier Description");
        
        address = new RestrictiveTextArea();
        address.maxLengthProperty().set(200);
        address.setLayoutX(155);
        address.setLayoutY(226);
        address.setPrefSize(200, 65);
        address.setText(null);
        address.setWrapText(true);
        address.setPromptText("Address");
        
        contactperson = new RestrictiveTextArea();
        contactperson.maxLengthProperty().set(100);
        contactperson.setLayoutX(155);
        contactperson.setLayoutY(299);
        contactperson.setPrefSize(200, 65);
        contactperson.setText(null);
        contactperson.setWrapText(true);
        contactperson.setPromptText("Contact Person/s");
        
        contactnumber = new RestrictiveTextArea();
        contactnumber.maxLengthProperty().set(100);
        contactnumber.setLayoutX(155);
        contactnumber.setLayoutY(370);
        contactnumber.setPrefSize(200, 65);
        contactnumber.setText(null);
        contactnumber.setWrapText(true);
        contactnumber.setPromptText("Contact Number/s");
        
        pane.getChildren().addAll(name,address,description,contactperson,contactnumber);
        
        buttons();
        refresh();
        refreshid();
        
        supplier.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (supplier.getSelectionModel().getSelectedItem() != null) {
                
               
               supplierid.setText(newValue.sID);
               name.setText(newValue.sname);
               description.setText(newValue.sdescription);
               address.setText(newValue.saddress);
               contactperson.setText(newValue.scontactperson);
               contactnumber.setText(newValue.scontactnumber);
               
                btnupdate.setDisable(false);
                btndelete.setDisable(false);
                btncancel.setDisable(false);

                btnsave.setDisable(true);
                btnreset.setDisable(true);
               
            }
      
        });
        
    }    
    
    private void buttons() {
        
                btnsave.setDisable(false);
                btnreset.setDisable(false);
                btnupdate.setDisable(true);
                btndelete.setDisable(true);
                btncancel.setDisable(true);
                
    }
    
    private void refreshid() {
        try{
            con = Database.connect();
            
             String SQL = "select max(supplierID) from tblsupplier;";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            
                if (rs.next()) {
                    String text = rs.getString("max(supplierID)");
                    String asd = text.substring(4);
                    String aa = asd;
                    supplierid.setText(asd);
                    num = Integer.parseInt(supplierid.getText());
                    new_id = num + 1;
                    supplierid.setText("Sup-00" + new_id);
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.supplierid.setText("Sup-0" + new_id);
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.supplierid.setText("Sup-" + new_id);
                    }
                }
                con.close();
                }catch(NumberFormatException | SQLException e){
            }
    }
    private void refresh() {
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
           supplierinfotable st = new supplierinfotable();
        st.ID(tcid);
        st.name(tcname);
        st.description(tcdescription);
        st.address(tcaddress);
        st.conperson(tccontactperson);
        st.connumber(tccontactnumber);
        st.log(tclog);
        supplier.getItems().setAll(getSupplierInfo());
           
    }),
      new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }
    
    public class supplier{
        private String sID,sname,sdescription,saddress,scontactperson,scontactnumber,slog;
        
        
        public supplier(String supplierID,String suppliername,String supplierdescription,String supplieraddress,String suppliercontactperson,String suppliercontactnumber,String supplierlog){
            
            this.sID = supplierID;
            this.sname = suppliername;
            this.sdescription = supplierdescription;
            this.saddress = supplieraddress;
            this.scontactperson = suppliercontactperson;
            this.scontactnumber = suppliercontactnumber;
            this.slog = supplierlog;
            
            
        }
        
        public String getID(){              return sID;  }
        public void setID(String supplierID) {  sID = supplierID; }
        //---------
        public String getsname(){            return sname;   }
        public void setsname(String suppliername) {  sname = suppliername;   }
        //---------
        public String getsdescription(){            return sdescription;   }
        public void setsdescription(String supplierdescription) {  sdescription = supplierdescription;   }
        //---------
        public String getsaddress(){            return saddress;   }
        public void setsaddress(String supplieraddress) {  saddress = supplieraddress;   }
        //---------
        public String getscontactperson(){        return scontactperson;  }
        public void setscontactperson(String suppliercontactperson) {  scontactperson = suppliercontactperson;  }
        //---------
        public String getscontactnumber(){            return scontactnumber;   }
        public void setscontactnumber(String suppliercontactnumber) {  scontactnumber = suppliercontactnumber;   }
        //---------
        public String getslog(){        return slog;    }
        public void setslog(String supplierlog) {  slog = supplierlog;    }
        
    }
        
        public List<supplier> getSupplierInfo() {
        List RL = new LinkedList();
        try {
            
             con = Database.connect();
        
         String SQL = "SELECT * FROM tblsupplier;";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                String supplierID = rs.getString(1);
                String suppliername = rs.getString(2);
                String supplierdescription = rs.getString(3);
                String supplieraddress = rs.getString(4);
                String suppliercontactperson = rs.getString(5);
                String suppliercontactnumber = rs.getString(6);
                String supplierlog = rs.getString(7);
                
                
                RL.add(new supplier(supplierID,suppliername,supplierdescription,supplieraddress,suppliercontactperson,suppliercontactnumber,supplierlog));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Supplier Error: "+ex);
        }
        
        return RL;
        }
}
