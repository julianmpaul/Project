/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Jeankie
 */
public class UserController implements Initializable {

    public Stage stage;
    public Parent root;
    
    
    
    //---------UTILITIES.USER Pane START
    private int num, new_id;
    private RestrictiveTextArea addres;
    private RestrictiveTextField fname,mname,lname,uname,pword,connum;
    private String gder,usern;
    @FXML
    private DatePicker bdate;
    @FXML
    private ComboBox poscombo,stacombo;
    @FXML
    private TableView<createuser> createusertable;
    @FXML
    private TableColumn tccreateid,tcfname,tcmname,tclname,tcusername,tcpassword,tcposition,tcaddress,tcgender,tcstatus,tccontact,tcbirthdate;
    @FXML
    private RadioButton male,female;
    @FXML
    private Pane userpane1;
    @FXML
    private Button btncreate,btnreset,btnupdate,btndelete,btncancel;
    @FXML
    private Label useridlabel;
    Connection con;
    
    
    @FXML
    private void create(ActionEvent event){
        btnupdate.setDisable(true);
         btndelete.setDisable(true);
         btncancel.setDisable(true);
         
        if((fname.getText() == null || mname.getText() == null || lname.getText() == null || uname.getText() == null || pword.getText() == null ||  connum.getText() == null || addres.getText() == null || poscombo.getValue() == null || stacombo.getValue() == null || bdate.getValue() == null) || (fname.getText().isEmpty() || mname.getText().isEmpty() || lname.getText().isEmpty() || uname.getText().isEmpty() || pword.getText().isEmpty() ||  connum.getText().isEmpty() || addres.getText().isEmpty())){
            
               Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("All items must have contents!");
                alert.showAndWait();
        }else{
            String usern = null;
            if(male.isSelected()){
            gder = "Male";
        }else if(female.isSelected()){
            gder = "Female";
        }
            
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
                 
                 Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Message");
                alert1.setHeaderText(null);
                alert1.setContentText("Are you sure you want to save data ?");
                alert1.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert1.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
            try{
           
                con = Database.connect();
                Statement st= con.createStatement();

                    String SQL1 = "INSERT INTO `database`.`tbluser` (`userID`, `position`, `firstname`, `middlename`, `lastname`, `username`, `password`, `gender`, `birthdate`, `address`, `status`, `cellphone`) "
                            + "VALUES ('"+useridlabel.getText()+"','"+poscombo.getValue()+"', '"+fname.getText()+"', '"+mname.getText()+"', '"+lname.getText()+"', '"+uname.getText()+"', '"+pword.getText()+"', '"+gder+"', '"+bdate.getValue()+"', '"+addres.getText()+"', '"+stacombo.getValue()+"', '"+connum.getText()+"'); ";
                    st.executeUpdate(SQL1);
                    con.close();
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Succesfully Saved!");
                alert.showAndWait();
        
                buttons();
                refreshid();
                datareset();
        
            }catch(SQLException ee){
                
            System.out.println(ee);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText("Saving Error");
            alert.setContentText("Unable to save data in database!");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            
                }
            
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
    private void update(ActionEvent event){
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to update the user record ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           if(male.isSelected()){
            gder = "Male";
        }else if(female.isSelected()){
            gder = "Female";
        }
                    if((fname.getText() == null || mname.getText() == null || lname.getText() == null || uname.getText() == null || pword.getText() == null ||  connum.getText() == null || addres.getText() == null || poscombo.getValue() == null || stacombo.getValue() == null || bdate.getValue() == null) || (fname.getText().isEmpty() || mname.getText().isEmpty() || lname.getText().isEmpty() || uname.getText().isEmpty() || pword.getText().isEmpty() ||  connum.getText().isEmpty() || addres.getText().isEmpty())){
                    
                Alert aler = new Alert(Alert.AlertType.WARNING);
                aler.setTitle("Message");
                aler.setHeaderText(null);
                aler.setContentText("All items must have contents!");
                aler.showAndWait();
                
                    }else{
            try{
           
                con = Database.connect();
                Statement st= con.createStatement();
         
            String SQL1 = "UPDATE `database`.`tbluser` SET `username` = '"+uname.getText()+"' ,`password` = '"+pword.getText()+"' ,"
                    + "`position` = '"+poscombo.getValue()+"' ,`firstname` = '"+fname.getText()+"' ,`middlename` = '"+mname.getText()+"' ,"
                    + "`lastname` = '"+lname.getText()+"' ,`address` = '"+addres.getText()+"' ,`status` = '"+stacombo.getValue()+"' ,"
                    + "`cellphone` = '"+connum.getText()+"',`birthdate`='"+bdate.getValue()+"',`gender` = '"+gder+"'  WHERE `userID` = '"+useridlabel.getText()+"';";
            st.executeUpdate(SQL1);
            con.close();
            
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Message");
                alert1.setHeaderText(null);
                alert1.setContentText("Succesfully Updated!");
                alert1.showAndWait();
        
                buttons();
                refreshid();
                datareset();
                
       
                
                        }catch(SQLException ee){
            System.out.println(ee);         
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Message");
            alert2.setHeaderText("Saving Error");
            alert2.setContentText("Unable to update data in database!");
            alert2.initStyle(StageStyle.UTILITY);
            alert2.showAndWait();
                }
                    }
            
        }
       
    }
    
    @FXML
    private void delete(ActionEvent event){
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the user record ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            try{
           
        con = Database.connect();
        Statement st= con.createStatement();
         
            String SQL1 = "DELETE FROM `database`.`tbluser` WHERE `userID` = '"+useridlabel.getText()+"';";
            st.executeUpdate(SQL1);
            con.close();
            
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Message");
                alert1.setHeaderText(null);
                alert1.setContentText("Succesfully Deleted!");
                alert1.showAndWait();
        
                
                refreshid();
                buttons();
                datareset();
              
                }catch(SQLException ee){
                    
                System.out.println(ee);         
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Message");
                alert2.setHeaderText("Deleting Error");
                alert2.setContentText("Unable to delete data in database!");
                alert2.initStyle(StageStyle.UTILITY);
                alert2.showAndWait();
                            
                        }
            
           
        }
    }
    
    @FXML
    private void reset(ActionEvent event){
        
        
            
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to reset data ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
            datareset();
            
        }
        buttons();
        
    }
    
    @FXML
    private void cancel(ActionEvent event){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel update/delete ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
            refreshid();
            buttons();
            datareset();
            
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
        buttons();
        
        fname = new RestrictiveTextField();
        fname.maxLengthProperty().set(30);
        fname.setLayoutX(131);
        fname.setLayoutY(125);
        fname.setPrefSize(202, 25);
        fname.setPromptText("First Name");
        
        
        mname = new RestrictiveTextField();
        mname.maxLengthProperty().set(30);
        mname.setLayoutX(131);
        mname.setLayoutY(170);
        mname.setPrefSize(202, 25);
        mname.setPromptText("Middle Name");
        
        lname = new RestrictiveTextField();
        lname.maxLengthProperty().set(30);
        lname.setLayoutX(131);
        lname.setLayoutY(214);
        lname.setPrefSize(202, 25);
        lname.setPromptText("Last Name");
        
        poscombo.setPromptText("Position");
        poscombo.getItems().addAll("Administrator","Secretary","Cashier");
        
        uname = new RestrictiveTextField();
        uname.maxLengthProperty().set(30);
        uname.setLayoutX(470);
        uname.setLayoutY(170);
        uname.setPrefSize(202, 25);
        uname.setPromptText("Username");
        
        pword = new RestrictiveTextField();
        pword.maxLengthProperty().set(30);
        pword.setLayoutX(470);
        pword.setLayoutY(214);
        pword.setPrefSize(202, 25);
        pword.setPromptText("Password");
        
        stacombo.setPromptText("Status");
        stacombo.getItems().addAll("Single","Married","Widowed");
                
        addres = new RestrictiveTextArea();
        addres.maxLengthProperty().set(200);
        addres.setLayoutX(470);
        addres.setLayoutY(283);
        addres.setPrefSize(202, 178);
        addres.setWrapText(true);
        addres.setPromptText("Address");
        
        connum= new RestrictiveTextField();
        connum.restrictProperty().set("[0-9]");
        connum.maxLengthProperty().set(21);
        connum.setLayoutX(131);
        connum.setLayoutY(429);
        connum.setPrefSize(202, 25);
        connum.setPromptText("Contact Number");
        
        userpane1.getChildren().addAll(addres,fname,mname,lname,uname,pword,connum);
        
        createusertable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (createusertable.getSelectionModel().getSelectedItem() != null) {
                
               
               fname.setText(newValue.fname1);
               mname.setText(newValue.mname1);
               lname.setText(newValue.lname1);
               uname.setText(newValue.usern1);
               pword.setText(newValue.pass1);
               connum.setText(newValue.cp1);
               addres.setText(newValue.add1);
               useridlabel.setText(newValue.user1);
               poscombo.setValue(newValue.pos1);
               stacombo.setValue(newValue.stat1);
               useridlabel.setText(newValue.user1);
               bdate.setValue(newValue.birth1.toLocalDate());
             
                btnupdate.setDisable(false);
                btndelete.setDisable(false);
                btncancel.setDisable(false);
                btncreate.setDisable(true);
                btnreset.setDisable(true);
             
               if("Male".equals(newValue.gend1)){
                male.setSelected(true);
            }else{
                female.setSelected(true);  
               }
               
               
            }
      
        });
        
        datareset();
        refreshid();
        
       
    }
    private void datareset() {
            fname.setText(null);
            mname.setText(null);
            lname.setText(null);
            uname.setText(null);
            pword.setText(null);
            addres.setText(null);
            connum.setText(null);
            poscombo.setValue(null);
            stacombo.setValue(null);
            bdate.setValue(null);   
    }
    private void buttons() {
        
                btncreate.setDisable(false);
                btnreset.setDisable(false);
                btnupdate.setDisable(true);
                btndelete.setDisable(true);
                btncancel.setDisable(true);
                
    }
    
    private void refreshid() {
    
          try{
            con = Database.connect();
            
             String SQL = "select max(userID) from tbluser;";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            
                if (rs.next()) {
                    String text = rs.getString("max(userID)");
                    String asd = text.substring(5);
                    String aa = asd;
                    useridlabel.setText(asd);
                    num = Integer.parseInt(useridlabel.getText());
                    new_id = num + 1;
                    useridlabel.setText("USER-00" + new_id);
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.useridlabel.setText("USER-0" + new_id);
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.useridlabel.setText("USER-" + new_id);
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
          createuserinfotable us = new createuserinfotable();
        us.ID(tccreateid);
        us.fffname(tcfname);
        us.mmmname(tcmname);
        us.lllname(tclname);
        us.user(tcusername);
        us.pass(tcpassword);
        us.posi(tcposition);
        us.add(tcaddress);
        us.birth(tcbirthdate);
        us.gen(tcgender);
        us.status(tcstatus);
        us.cp(tccontact);
        createusertable.getItems().setAll(getUserInfo());
           
    }),
      new KeyFrame(Duration.seconds(2))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }    
    public class createuser{
        private String user1,fname1,mname1,lname1,usern1,pass1,pos1,add1,gend1,stat1,cp1;
        private Date birth1;
        
        public createuser(String userID, String ffname, String mmname, String llname,String username,String password,String position,String address,String genderr,Date birthdatee,String statuss,String cellphonee){
            
            this.user1 = userID;
            this.fname1 = ffname;
            this.mname1 = mmname;
            this.lname1 = llname;
            this.usern1 = username;
            this.pass1 = password;
            this.pos1 = position;
            this.add1 = address;
            this.birth1 = birthdatee;
            this.gend1 = genderr;
            this.stat1 = statuss;
            this.cp1 = cellphonee;
            
        }
        
        public String getID(){              return user1;  }
        public void setID(String userID) {  user1 = userID; }
        //---------
        public String getfName(){            return fname1;   }
        public void setfName(String ffname) {  fname1 = ffname;   }
        //---------
        public String getmName(){            return mname1;   }
        public void setmName(String mmname) {  mname1 = mmname;   }
        //---------
        public String getlName(){            return lname1;   }
        public void setlName(String llname) {  lname1 = llname;   }
        //---------
        public String getusername(){        return usern1;  }
        public void setusername(String username) {  usern1 = username;  }
        //---------
        public String getpass(){            return pass1;   }
        public void setpass(String password) {  pass1 = password;   }
        //---------
        public String getposition(){        return pos1;    }
        public void setposition(String position) {  pos1 = position;    }
        //---------
        public String getadd(){             return add1;    }
        public void setadd(String address) {    add1 = address; }
        //---------
        public Date getbirth(){             return birth1;    }
        public void setbirth(Date birthdatee) {    birth1 = birthdatee; }
        //---------
        public String getgender(){             return gend1;    }
        public void setgender(String genderr) {    gend1 = genderr; }
        //---------
        public String getstatus(){             return stat1;    }
        public void setstatus(String statuss) {    stat1 = statuss; }
        //---------
        public String getcp(){             return cp1;    }
        public void setcp(String cellphonee) {    cp1 = cellphonee; }
        //---------
        
        
    }
        
        public List<createuser> getUserInfo() {
        List RL = new LinkedList();
        try {
            
             con = Database.connect();
        
         String SQL = "SELECT userID,firstname,middlename,lastname,username,PASSWORD,POSITION,address,gender,birthdate,status,cellphone FROM tbluser;";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                String userID = rs.getString(1);
                String ffname = rs.getString(2);
                String mmname = rs.getString(3);
                String llname = rs.getString(4);
                String username = rs.getString(5);
                String password = rs.getString(6);
                String position = rs.getString(7);
                String address = rs.getString(8);
                String genderr = rs.getString(9);
                Date birthdatee = rs.getDate(10);
                String statuss = rs.getString(11);
                String cellphonee = rs.getString(12);
                
                RL.add(new createuser(userID,ffname,mmname,llname,username,password,position,address,genderr,birthdatee,statuss,cellphonee));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);}
        return RL;
        }
}
