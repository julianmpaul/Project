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
public class BrandBreedController implements Initializable {
        private RestrictiveTextField name;
        private RestrictiveTextArea description;
        private int num, new_id;
        Connection con;
        
            @FXML
            private Button btnsave,btnreset,btndelete,btnupdate,btncancel;
            @FXML
            private Label bbid;
            @FXML
            private TableView<bb> brandbreed;
            @FXML
            private TableColumn tcid,tcname,tclog,tcdescription;
            @FXML
            private Pane pane;
            
            
            @FXML
            private void save(ActionEvent e){
                
                if((name.getText() == null || description.getText() == null) || (name.getText().isEmpty() || description.getText().isEmpty())){
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
                String SQL = "SELECT brandname FROM tblbrandbreed WHERE brandname = '"+name.getText()+"'";
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
                            
                            String SQL1 = "INSERT INTO `database`.`tblbrandbreed` "
                                    + "(`bbID`, `brandname`, `description`, `loggedID`)"
                                    + " VALUES ('"+bbid.getText()+"', '"+name.getText()+"', '"+description.getText()+"', '"+LoginController.loggedid+"'); ";
                            
                            st.executeUpdate(SQL1);
                            con.close();
                            
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Succesfully Saved!");
                            alert.initStyle(StageStyle.UTILITY);
                            alert.showAndWait();
                            
                            refreshid();
                            
                            name.setText(null);
                            description.setText(null);
                            
                            buttons();
                            
                    }catch (SQLException ex) {
                            System.out.println(ex);
                        }
                        
                    }else{
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Message");
                            alert1.setHeaderText(null);
                            alert1.setContentText("The brand/breed name has a duplicate value !");
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
                   description.setText(null);
                }
               buttons(); 
            }
            @FXML
            private void update(ActionEvent e){
                if((name.getText() == null || description.getText() == null) || (name.getText().isEmpty() || description.getText().isEmpty())){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please input all the data for update !");
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
                        try{
                            con = Database.connect();
                            Statement st= con.createStatement();
                            
                            String SQL1 = "UPDATE `database`.`tblbrandbreed` SET `bbID` = '"+bbid.getText()+"' ,"
                                    + "`brandname` = '"+name.getText()+"' ,`description` = '"+description.getText()+"',`loggedID` = '"+LoginController.loggedid+"'"
                                    + " WHERE `bbID` = '"+bbid.getText()+"'; ";
                            st.executeUpdate(SQL1);
                            con.close();
                            
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Succesfully updated !");
                            alert.initStyle(StageStyle.UTILITY);
                            alert.showAndWait();
                            
                            name.setText(null);
                            description.setText(null);
                             
                            buttons();
                            refreshid();

                        }catch( SQLException ex){
                            System.out.println(ex);
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
                        try{
                            con = Database.connect();
                            Statement st= con.createStatement();
                            
                            String SQL1 = "DELETE FROM `database`.`tblbrandbreed` WHERE `bbID` = '"+bbid.getText()+"'; ";
                            st.executeUpdate(SQL1);
                            st.close();
                            
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Succesfully deleted!");
                            alert.initStyle(StageStyle.UTILITY);
                            alert.showAndWait();
                            
                            name.setText(null);
                            description.setText(null);
                            
                            buttons();
                            refreshid();
                            
                            con.close();
                            
            
                             

                        }catch( SQLException ex){
                            
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Message");
            alert1.setHeaderText(null);
            alert1.setContentText("Brand or Breed was used in products and cannot be deleted !");
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
            private void cancel(ActionEvent e){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to cancel the update/delete ?");
                    alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                        
                            
                    name.setText(null);
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
        name.setLayoutX(148);
        name.setLayoutY(150);
        name.setPrefSize(200, 25);
        name.setText(null);
        name.setPromptText("Name");
        
        description = new RestrictiveTextArea();
        description.maxLengthProperty().set(200);
        description.setLayoutX(148);
        description.setLayoutY(192);
        description.setPrefSize(200, 200);
        description.setText(null);
        description.setWrapText(true);
        description.setPromptText("Description");
        
        pane.getChildren().addAll(name,description);
        
               
                refreshid();
                buttons();
        
        brandbreed.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (brandbreed.getSelectionModel().getSelectedItem() != null) {
                
               
               bbid.setText(newValue.cID);
               name.setText(newValue.cname);
               description.setText(newValue.cdescription);
               
                btnsave.setDisable(true);
                btnreset.setDisable(true);
                btnupdate.setDisable(false);
                btndelete.setDisable(false);
                btncancel.setDisable(false);

                
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
            
             String SQL = "select max(bbID) from tblbrandbreed;";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            
                if (rs.next()) {
                    String text = rs.getString("max(bbID)");
                    String asd = text.substring(3);
                    String aa = asd;
                    bbid.setText(asd);
                    num = Integer.parseInt(bbid.getText());
                    new_id = num + 1;
                    bbid.setText("BB-00" + new_id);
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.bbid.setText("BB-0" + new_id);
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.bbid.setText("BB-" + new_id);
                    }
                }
                
                con.close();
                }catch(NumberFormatException | SQLException e){
            }
}
private void refresh() {
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
          brandbreedinfotable bt = new brandbreedinfotable();
                            bt.ID(tcid);
                            bt.name(tcname);
                            bt.description(tcdescription);
                            bt.log(tclog);
                            brandbreed.getItems().setAll(getbbInfo());
           
    }),
      new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }

    public class bb{
        private String cID,cname,cdescription,cwarranty,clog;
        
        
        public bb(String bbID,String bbname,String bbdescription,String loggedID){
            
            this.cID = bbID;
            this.cname = bbname;
            this.cdescription = bbdescription;
            this.clog = loggedID;
            
            
            
        }
        
        public String getID(){              return cID;  }
        public void setID(String bbID) {  cID = bbID; }
        //---------
        public String getname(){            return cname;   }
        public void setname(String bbname) {  cname = bbname;   }
        //---------
        public String getdescription(){            return cdescription;   }
        public void setdescription(String bbdescription) {  cdescription = bbdescription;   }
        //---------
        public String getlog(){        return clog;  }
        public void setlog(String loggedID) {  clog = loggedID;  }
        
        
    }
        
        public List<bb> getbbInfo() {
        List RL = new LinkedList();
        try {
            
             con = Database.connect();
        
         String SQL = "SELECT * FROM tblbrandbreed;";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                String bbID = rs.getString(1);
                String bbname = rs.getString(2);
                String bbdescription = rs.getString(3);
                String loggedID = rs.getString(4);
                
                
                
                RL.add(new bb(bbID,bbname,bbdescription,loggedID));
            }con.close();
        } catch (SQLException ex) {
            System.out.println("Brand/Breed Error: "+ex);
        }
        return RL;
        }
}
