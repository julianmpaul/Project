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
public class VATController implements Initializable {

    private RestrictiveTextField name,percentage;
        private RestrictiveTextArea description;
        private int num, new_id;
        Connection con;
        
            @FXML
            private Button btnsave,btnreset,btndelete,btnupdate,btncancel;
            @FXML
            private Label vatid;
            @FXML
            private TableView<vat> vat;
            @FXML
            private TableColumn tcid,tcname,tclog,tcpercentage,tcdescription;
            @FXML
            private Pane pane;
            
            
            @FXML
            private void save(ActionEvent e){
                
                 
                if((name.getText() == null || description.getText() == null || percentage.getText() == null) || 
                        (name.getText().isEmpty() || description.getText().isEmpty() || percentage.getText().isEmpty())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Please input all the data!");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                        
                }else{
                    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to save the data ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    String nam = null;
                    try {
                
                con = Database.connect();
                String SQL = "SELECT vatname FROM tblvat WHERE vatname = '"+name.getText()+"'";
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

                        String SQL1 = "INSERT INTO `database`.`tblvat`"
                                + " (`vatID`, `vatname`, `vatdescription`, `percentage`, `loggedID`)"
                                + " VALUES ('"+vatid.getText()+"', '"+name.getText()+"', '"+description.getText()+"',"
                                + " '."+percentage.getText()+"', '"+LoginController.loggedid+"'); ";
                        
                        st.executeUpdate(SQL1);
                        con.close();
                    
                        name.setText(null);
                    percentage.setText(null);
                    description.setText(null);
                        
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully Saved!");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    
                       
                        buttons();
                        
                    
                   
                        refreshid();
                        
                }catch(NumberFormatException | SQLException ee){
                    System.out.println(ee);
            } 
                    }else{
                         Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Message");
                            alert1.setHeaderText(null);
                            alert1.setContentText("The vat name has a duplicate value !");
                            alert1.initStyle(StageStyle.UTILITY);
                            alert1.showAndWait();
                    }
                   
                }
                }
                
                
                
            }
            @FXML
            private void reset(ActionEvent e){
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to reset the fields ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                   name.setText(null);
                   percentage.setText(null);
                   description.setText(null);
                }
                buttons();
                
            }
            @FXML
            private void update(ActionEvent e){
                
                if((name.getText() == null || description.getText() == null || percentage.getText() == null) || 
                        (name.getText().isEmpty() || description.getText().isEmpty() || percentage.getText().isEmpty())){
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Please input all the data for update!");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                
                }else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update the data ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                        try {
                            con = Database.connect();
                        
                        Statement st= con.createStatement();
                        String SQL1 = "UPDATE `database`.`tblvat` SET `vatname` = '"+name.getText()+"' ,`vatdescription` = '"+description.getText()+"'"
                                + " ,`percentage` = '."+percentage.getText()+"',`loggedID` = '"+LoginController.loggedid
                                +"' WHERE `vatID` = '"+vatid.getText()+"';"; 
                        st.executeUpdate(SQL1);
                        con.close();
                        
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Message");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Succesfully Updated!");
                        alert1.showAndWait();
                    } catch (SQLException ex) {
                            System.out.println(ex);
                        }
                    
                    
                    buttons();
                
                    name.setText(null);
                   percentage.setText(null);
                   description.setText(null);
                   
                    refreshid();
                }
                
                }
            }
            @FXML
            private void delete(ActionEvent e){
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the data ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                        try {
                            con = Database.connect();
                        
                        Statement st= con.createStatement();
                        String SQL1 = "DELETE FROM `database`.`tblvat` WHERE `vatID` = '"+vatid.getText()+"'; ";
                        st.executeUpdate(SQL1);
                        con.close();
                        
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Message");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Succesfully Deleted!");
                        alert1.showAndWait();
                    } catch (SQLException ex) {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Message");
            alert1.setHeaderText(null);
            alert1.setContentText("Vat was used in products and cannot be deleted !");
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
                    
                    
                   buttons();
                
                    name.setText(null);
                    percentage.setText(null);
                    description.setText(null);
                   
                    refreshid();
                }
                
            }
            @FXML
            private void cancel(ActionEvent e){
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to cancel ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                buttons();
                
                    name.setText(null);
                   percentage.setText(null);
                   description.setText(null);
                   
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
        name.setLayoutX(151);
        name.setLayoutY(126);
        name.setPrefSize(200, 25);
        name.setText(null);
        name.setPromptText("Vat Name");
        
        percentage = new RestrictiveTextField();
        percentage.restrictProperty().set("[.,0-9]");
        percentage.maxLengthProperty().set(10);
        percentage.setLayoutX(151);
        percentage.setLayoutY(362);
        percentage.setPrefSize(136, 25);
        percentage.setText(null);
        percentage.setPromptText("Percentage");
        
        description = new RestrictiveTextArea();
        description.maxLengthProperty().set(200);
        description.setLayoutX(151);
        description.setLayoutY(163);
        description.setPrefSize(200, 162);
        description.setText(null);
        description.setWrapText(true);
        description.setPromptText("Vat Description");
        
        pane.getChildren().addAll(name,percentage,description);
        
        buttons();
        refresh();
        refreshid();
        
        
        vat.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (vat.getSelectionModel().getSelectedItem() != null) {
                
               
               vatid.setText(newValue. vID);
               name.setText(newValue. vname);
               description.setText(newValue.vdescription);
               percentage.setText(newValue.vpercentage);
               
               
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
            
             String SQL = "select max(vatID) from tblvat;";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            
                if (rs.next()) {
                    String text = rs.getString("max(vatID)");
                    String asd = text.substring(4);
                    String aa = asd;
                    vatid.setText(asd);
                    num = Integer.parseInt(vatid.getText());
                    new_id = num + 1;
                    vatid.setText("Vat-00" + new_id);
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.vatid.setText("Vat-0" + new_id);
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.vatid.setText("Vat-" + new_id);
                    }
                }
                con.close();
                }catch(NumberFormatException | SQLException e){
                    System.out.println(e);
            }
    }
    private void refresh() {
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
          vatinfotable vt = new vatinfotable();
        vt.ID(tcid);
        vt.name(tcname);
        vt.description(tcdescription);
        vt.percentage(tcpercentage);
        vt.log(tclog);
         vat.getItems().setAll(getVatInfo());
           
    }),
      new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }
    public class vat{
        private String vID,vname,vdescription,vpercentage,vlog;
        
        
        public vat(String vatID,String vatname,String vatdescription,String vatpercentage,String loggedID){
            
            this.vID = vatID;
            this.vname = vatname;
            this.vdescription = vatdescription;
            this.vpercentage = vatpercentage;
            this.vlog = loggedID;
            
            
            
        }
        
        public String getID(){              return vID;  }
        public void setID(String vatID) {  vID = vatID; }
        //---------
        public String getname(){            return vname;   }
        public void setname(String vatname) {  vname = vatname;   }
        //---------
        public String getdescription(){            return vdescription;   }
        public void setdescription(String vatdescription) {  vdescription = vatdescription;   }
        //---------
        public String getpercentage(){            return vpercentage;   }
        public void setpercentage(String vatpercentage) {  vpercentage = vatpercentage;   }
        //---------
        public String getlog(){        return vlog;  }
        public void setlog(String loggedID) {  vlog = loggedID;  }
        
        
    }
        
        public List<vat> getVatInfo() {
        List RL = new LinkedList();
        try {
            
             con = Database.connect();
        
         String SQL = "SELECT * FROM tblvat;";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                String vatID = rs.getString(1);
                String vatname = rs.getString(2);
                String vatdescription = rs.getString(3);
                String vatpercentage = rs.getString(4);
                String loggedID = rs.getString(5);
                
                
                
                RL.add(new vat(vatID,vatname,vatdescription,vatpercentage,loggedID));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Vat Error: "+ex);
        }
        return RL;
        }
}
