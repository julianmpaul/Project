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
public class CategoryController implements Initializable {

        private RestrictiveTextField name,warranty;
        private RestrictiveTextArea description;
        private int num, new_id;
        Connection con;
        
            @FXML
            private Button btnsave,btnreset,btndelete,btnupdate,btncancel;
            @FXML
            private Label categoryid;
            @FXML
            private TableView<category> category;
            @FXML
            private TableColumn tcid,tcname,tclog,tcwarranty,tcdescription;
            @FXML
            private Pane pane;
            
            
            @FXML
            private void save(ActionEvent e){
                
                if((name.getText() == null || description.getText() == null || warranty.getText() == null) || (name.getText().isEmpty() || description.getText().isEmpty() || warranty.getText().isEmpty())){
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
                String SQL = "SELECT categoryName FROM tblcategory WHERE categoryName = '"+name.getText()+"'";
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

                        String SQL1 = "INSERT INTO `database`.`tblcategory` "
                                + "(`categoryID`, `categoryName`, `description`, `warranty`, `loggedID`)"
                                + " VALUES ('"+categoryid.getText()+"', '"+name.getText()+"', '"+description.getText()+"',"
                                + " '"+warranty.getText()+"', '"+LoginController.loggedid+"'); ";
                        
                        st.executeUpdate(SQL1);
                        con.close();
                        
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Succesfully Saved!");
                            alert.initStyle(StageStyle.UTILITY);
                            alert.showAndWait();
                    
                           name.setText(null);
                           warranty.setText(null);
                           description.setText(null);
                    
                    
                       
                        buttons();
                        refreshid();
                        
                        }catch(SQLException eee){
                            System.out.println(eee);
                        }  
                    }else{
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Message");
                            alert1.setHeaderText(null);
                            alert1.setContentText("The category name has a duplicate value !");
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
                   warranty.setText(null);
                   description.setText(null);
                }
                refreshid();
                buttons();
                
            }
            @FXML
            private void update(ActionEvent e){
                
                if((name == null || description == null || warranty == null) || (name.getText().isEmpty() || description.getText().isEmpty() || warranty.getText().isEmpty())){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
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
                        String SQL1 = "UPDATE `database`.`tblcategory` SET `categoryName` = '"+name.getText()+"',"
                                + "`warranty` = '"+warranty.getText()+"' ,`description` = '"+description.getText()+"',`loggedID` = '"+LoginController.loggedid+"' "
                                + "WHERE `categoryID` = '"+categoryid.getText()+"';";
                        st.executeUpdate(SQL1);
                        con.close();
                        
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Message");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Succesfully Updated!");
                        alert1.showAndWait();
                        
                        refreshid();
                        buttons();
                        
                        name.setText(null);
                        warranty.setText(null);
                        description.setText(null);
                   
                        
                        
                       
                        
                    }catch(SQLException eeee){
                        System.out.println(eeee);
                    }
                    
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
                        String SQL1 = "DELETE FROM `database`.`tblcategory` WHERE `categoryID` = '"+categoryid.getText()+"'; ";
                        st.executeUpdate(SQL1);
                        con.close();
                        
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Message");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Succesfully deleted!");
                        alert1.showAndWait();
                        
                        name.setText(null);
                        warranty.setText(null);
                        description.setText(null);
                   
                       buttons(); 
                       refreshid();
                       
                    }catch(SQLException eeee){
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Message");
            alert1.setHeaderText(null);
            alert1.setContentText("Category was used in products and cannot be deleted !");
            alert1.initStyle(StageStyle.UTILITY);

                String exceptionText = eeee.toString();

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
            private void cancel(ActionEvent e){
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to cancel ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){

                        name.setText(null);
                        warranty.setText(null);
                        description.setText(null);
                   
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
        refresh();
        name = new RestrictiveTextField();
        name.maxLengthProperty().set(50);
        name.setLayoutX(145);
        name.setLayoutY(134);
        name.setPrefSize(197, 25);
        name.setText(null);
        name.setPromptText("Category Name");
        
        warranty = new RestrictiveTextField();
        warranty.restrictProperty().set("[0-9]");
        warranty.maxLengthProperty().set(2);
        warranty.setLayoutX(145);
        warranty.setLayoutY(357);
        warranty.setPrefSize(148, 25);
        warranty.setText(null);
        warranty.setPromptText("Number of Days");
        
        description = new RestrictiveTextArea();
        description.maxLengthProperty().set(200);
        description.setLayoutX(145);
        description.setLayoutY(178);
        description.setPrefSize(200, 159);
        description.setText(null);
        description.setWrapText(true);
        description.setPromptText("Category Description");
        
        pane.getChildren().addAll(name,warranty,description);
        
                buttons();
                
        refreshid();
        
        category.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (category.getSelectionModel().getSelectedItem() != null) {
                
               
               categoryid.setText(newValue.cID);
               name.setText(newValue.cname);
               description.setText(newValue.cdescription);
               warranty.setText(newValue.cwarranty);
               
                btnsave.setDisable(true);
                btnreset.setDisable(true);
                btnupdate.setDisable(false);
                btndelete.setDisable(false);
                btncancel.setDisable(false);

                
               
            }
      
        });
    }    
    private void buttons(){
                        btnsave.setDisable(false);
                        btnreset.setDisable(false);
                        btnupdate.setDisable(true);
                        btncancel.setDisable(true);
                        btndelete.setDisable(true);
    }
    private void refreshid(){
        try{
            con = Database.connect();
            
             String SQL = "select max(categoryID) from tblcategory;";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            
                if (rs.next()) {
                    String text = rs.getString("max(categoryID)");
                    String asd = text.substring(4);
                    String aa = asd;
                    categoryid.setText(asd);
                    num = Integer.parseInt(categoryid.getText());
                    new_id = num + 1;
                    categoryid.setText("Cat-00" + new_id);
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.categoryid.setText("Cat-0" + new_id);
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.categoryid.setText("Cat-" + new_id);
                    }
                }
                con.close();
                }catch(NumberFormatException | SQLException ee){
            }
    }
    private void refresh() {
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
          categoryinfotable ct = new categoryinfotable();
        ct.ID(tcid);
        ct.name(tcname);
        ct.description(tcdescription);
        ct.warranty(tcwarranty);
        ct.log(tclog);
        category.getItems().setAll(getCategoryInfo());
           
    }),
      new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }
    public class category{
        private String cID,cname,cdescription,cwarranty,clog;
        
        
        public category(String categoryID,String categoryname,String categorydescription,String ccwarranty,String loggedID){
            
            this.cID = categoryID;
            this.cname = categoryname;
            this.cdescription = categorydescription;
            this.cwarranty = ccwarranty;
            this.clog = loggedID;
            
            
            
        }
        
        public String getID(){              return cID;  }
        public void setID(String categoryID) {  cID = categoryID; }
        //---------
        public String getname(){            return cname;   }
        public void setname(String categoryname) {  cname = categoryname;   }
        //---------
        public String getdescription(){            return cdescription;   }
        public void setdescription(String categorydescription) {  cdescription = categorydescription;   }
        //---------
        public String getwarranty(){            return cwarranty;   }
        public void setwarranty(String ccwarranty) {  cwarranty = ccwarranty;   }
        //---------
        public String getlog(){        return clog;  }
        public void setlog(String loggedID) {  clog = loggedID;  }
        
        
    }
        
        public List<category> getCategoryInfo() {
        List RL = new LinkedList();
        try {
            
             con = Database.connect();
        
         String SQL = "SELECT * FROM tblcategory;";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                String categoryID = rs.getString(1);
                String categoryname = rs.getString(2);
                String categorydescription = rs.getString(3);
                String ccwarranty = rs.getString(4);
                String loggedID = rs.getString(5);
                
                
                
                RL.add(new category(categoryID,categoryname,categorydescription,ccwarranty,loggedID));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("Category Error: " +ex);
        }
        return RL;
        }
}
