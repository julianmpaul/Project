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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jeankie
 */
public class ProfileController implements Initializable {
        Connection con;
        private RestrictiveTextArea addres;
    private RestrictiveTextField fname,mname,lname,uname,pword,connum;
    
    @FXML
    private Button btnupdate,btnsave,btncancel;
    @FXML
    private DatePicker bdate;
    @FXML
    private ComboBox poscombo,stacombo;
    @FXML
    private RadioButton male,female;
    @FXML
    private Pane userpane1;
    @FXML
    private Label useridlabel;
    private String gder;
    
    
    @FXML
    private void update(ActionEvent event){
        
        
        
            btnsave.setDisable(false);
            btncancel.setDisable(false);
            btnupdate.setDisable(true);
        
            addres.setEditable(true);
            fname.setEditable(true);
            mname.setEditable(true);
            lname.setEditable(true);
            uname.setEditable(true);
            pword.setEditable(true);
            connum.setEditable(true);
            stacombo.setDisable(false);
            poscombo.setDisable(false);
            male.setDisable(false);
            female.setDisable(false);
            bdate.setDisable(false);
    }
    
    
    @FXML
    private void save(ActionEvent event){
        
            if((fname.getText().isEmpty() || mname.getText().isEmpty() || lname.getText().isEmpty() || uname.getText().isEmpty() || pword.getText().isEmpty() ||  connum.getText().isEmpty() || addres.getText().isEmpty() || poscombo.getValue() == null || stacombo.getValue() == null || bdate.getValue() == null) || (fname.getText() == null || mname.getText() == null || lname.getText() == null || uname.getText() == null || pword.getText() == null ||  connum.getText() == null || addres.getText() == null)){
            
               Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("All items must have contents !");
                alert.showAndWait();
        }else{
                String usern = null;
            try {
            
             con = Database.connect();
        
         String SQL = "SELECT username from tbluser where username ='"+uname.getText()+"'";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                 usern = rs.getString(1);
            }
            con.close();
            
        } catch (SQLException ex) {
         System.out.println(ex);
        }
             if(usern == null){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to update your user profile ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           
            
            try{
           
                if(male.isSelected()){
            gder = "Male";
        }else if(female.isSelected()){
            gder = "Female";
        }
                
        con = Database.connect();
        Statement st= con.createStatement();
        
         
            String SQL1 = "UPDATE `database`.`tbluser` SET `username` = '"+uname.getText()+"' ,`password` = '"+pword.getText()+"' ,"
                    + "`firstname` = '"+fname.getText()+"' ,`middlename` = '"+mname.getText()+"' ,"
                    + "`lastname` = '"+lname.getText()+"' ,`address` = '"+addres.getText()+"' ,`status` = '"+stacombo.getValue()+"' ,"
                    + "`cellphone` = '"+connum.getText()+"',`gender` = '"+gder+"',`birthdate` = '"+bdate.getValue()+"'  WHERE `userID` = '"+useridlabel.getText()+"';";
            st.executeUpdate(SQL1);
            con.close();
            
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Message");
                alert1.setHeaderText(null);
                alert1.setContentText("Succesfully Updated!");
                alert1.showAndWait();
        
                btnupdate.setDisable(false);
                btnsave.setDisable(true);
                btncancel.setDisable(true);
                
                }catch(SQLException ee){
                            
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Message");
            alert2.setHeaderText("Saving Error");
            alert2.setContentText("Unable to save profile in database!");
            alert2.initStyle(StageStyle.UTILITY);
            alert2.showAndWait();
                }
            
            refreshdata();
            
            addres.setEditable(false);
            fname.setEditable(false);
            mname.setEditable(false);
            lname.setEditable(false);
            uname.setEditable(false);
            pword.setEditable(false);
            connum.setEditable(false);
            stacombo.setDisable(true);
            poscombo.setDisable(true);
            male.setDisable(true);
            female.setDisable(true);
            bdate.setDisable(true);
            
            
       }
             }else{
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Username is already in used !");
                alert.showAndWait();  
             }
        
         
        
    }
        
    }
    
    @FXML
    private void cancel(ActionEvent event){
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel update ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           
        btnsave.setDisable(true);
        btncancel.setDisable(true);
        btnupdate.setDisable(false);
        
        refreshdata();
         
            addres.setEditable(false);
            fname.setEditable(false);
            mname.setEditable(false);
            lname.setEditable(false);
            uname.setEditable(false);
            pword.setEditable(false);
            connum.setEditable(false);
            stacombo.setDisable(true);
            poscombo.setDisable(true);
            male.setDisable(true);
            female.setDisable(true);
            bdate.setDisable(true);
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
        
        btnsave.setDisable(true);
        btncancel.setDisable(true);
         
        fname = new RestrictiveTextField();
        fname.maxLengthProperty().set(25);
        fname.setLayoutX(131);
        fname.setLayoutY(125);
        fname.setPrefSize(202, 25);
       
        
        
        mname = new RestrictiveTextField();
        mname.maxLengthProperty().set(25);
        mname.setLayoutX(131);
        mname.setLayoutY(170);
        mname.setPrefSize(202, 25);
       
        
        lname = new RestrictiveTextField();
        lname.maxLengthProperty().set(25);
        lname.setLayoutX(131);
        lname.setLayoutY(214);
        lname.setPrefSize(202, 25);
        
        
        uname = new RestrictiveTextField();
        uname.maxLengthProperty().set(25);
        uname.setLayoutX(470);
        uname.setLayoutY(170);
        uname.setPrefSize(202, 25);
        
        
        pword = new RestrictiveTextField();
        pword.maxLengthProperty().set(25);
        pword.setLayoutX(470);
        pword.setLayoutY(214);
        pword.setPrefSize(202, 25);
       
        addres = new RestrictiveTextArea();
        addres.maxLengthProperty().set(102);
        addres.setLayoutX(470);
        addres.setLayoutY(283);
        addres.setPrefSize(202, 178);
        addres.setWrapText(true);
       
        
        connum= new RestrictiveTextField();
        connum.restrictProperty().set("[0-9]");
        connum.maxLengthProperty().set(11);
        connum.setLayoutX(131);
        connum.setLayoutY(425);
        connum.setPrefSize(202, 25);
        
        stacombo.getItems().addAll("Single","Married","Widowed");
        
        addres.setEditable(false);
            fname.setEditable(false);
            mname.setEditable(false);
            lname.setEditable(false);
            uname.setEditable(false);
            pword.setEditable(false);
            connum.setEditable(false);
            stacombo.setDisable(true);
            poscombo.setEditable(false);
            male.setDisable(true);
            female.setDisable(true);
            bdate.setDisable(true);
            
        userpane1.getChildren().addAll(addres,fname,mname,lname,uname,pword,connum);
        
        
         refreshdata();
         
         
        
    }
    private void refreshdata() {
     try {
            
             con = Database.connect();
        
         String SQL = "SELECT userID,firstname,middlename,lastname,username,PASSWORD,POSITION,address,gender,birthdate,status,cellphone FROM tbluser where username = '"+LoginController.username.getText()+"';";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                useridlabel.setText(rs.getString(1));
                fname.setText(rs.getString(2));
                mname.setText(rs.getString(3));
                lname.setText(rs.getString(4));
                uname.setText(rs.getString(5));
                pword.setText(rs.getString(6));
                poscombo.setValue(rs.getString(7));
                addres.setText(rs.getString(8));
                if("Male".equals(rs.getString(9))){
                    male.setSelected(true);
                }else{
                    female.setSelected(true);
                }
                
               bdate.setValue(rs.getDate(10).toLocalDate());
                stacombo.setValue(rs.getString(11));
                connum.setText(rs.getString(12));
                
                
               
             }
            con.close();
            
        } catch (SQLException ex) {
           System.out.println(ex); 
        }
    }    
    
}
