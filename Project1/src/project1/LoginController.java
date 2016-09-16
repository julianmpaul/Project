/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import static project1.StringUtilities.pad;

/**
 * FXML Controller class
 *
 * @author Jeankie
 */
public class LoginController implements Initializable {
    public String hourString,hourString1,time1,minuteString,secondString,ampmString;
    public static RestrictiveTextField username;
    private RestrictivePasswordField password;
    public String user,pass;
    public static String userID,loggedid;
    public Stage stage;
    public Parent root;
    Connection con;
    private int num, new_id;
    @FXML
    public Label Time,Date;
    public static String date;
    @FXML
    private Pane loginpane;
    
    
   
    
    @FXML
    private void exit(ActionEvent event){
      // LoginController.display("");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           System.out.println("System exit from login.");
           System.exit(0);
        }
        
    }
    
    
    
   @FXML
    private void login(ActionEvent event) throws IOException{
        
        
        
        try{
        con = Database.connect();
        
         String SQL = "SELECT username,PASSWORD,userID FROM tbluser where username = '"+username.getText()+"' and password = '"+password.getText()+"'";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            if(rs.next()){
                user = rs.getString("username");
                pass = rs.getString("PASSWORD");
                userID = rs.getString("userID");
                
              }
            con.close();
            
        }catch(SQLException ee){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Connection Error");
            alert.setHeaderText(null);
            alert.setContentText("Unable to connect in database!");
            alert.initStyle(StageStyle.UTILITY);

                String exceptionText = ee.toString();

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
            alert.getDialogPane().setExpandableContent(expContent);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
         if(username.getText().equals(user) && password.getText().equals(pass)){
      /**
 
 */       
             ((Node)(event.getSource())).getScene().getWindow().hide();
            try {

                root = FXMLLoader.load(getClass().getResource("Menu1.fxml"));
              Scene scene = new Scene(root);
              stage = new Stage();
            //stage.setTitle("Menu");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.centerOnScreen();
            
            stage.show();
            
            
            try{
            con = Database.connect();
            
             String SQL = "select max(loggedID) from tbllogged;";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            
                if (rs.next()) {
                    String text = rs.getString("max(loggedID)");
                    String asd = text.substring(7);
                    String aa = asd;
                    loggedid = asd;
                    num = Integer.parseInt(loggedid);
                    new_id = num + 1;
                    loggedid = "Logged-" +"0000" + new_id;
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.loggedid = ("Logged-" + "000" + new_id);
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.loggedid = ("Logged-" + "00" + new_id);
                    } else if (new_id >= 999) {
                        this.loggedid = ("Logged-" + "0" + new_id);
                    }
                    
                    
                    
                }
                con.close();
                }catch(NumberFormatException | SQLException e){
                    System.out.println(e);
            }
            
            try{
           
        con = Database.connect();
        Statement st= con.createStatement();
         
            String SQL1 = "INSERT INTO `database`.`tbllogged` (`loggedID`, `userID`, `Date`, `TimeIn`)"
                    + "VALUES ('"+loggedid+"','"+userID+"', '"+Date.getText()+"', '"+time1+"'); ";
            st.executeUpdate(SQL1);
            }catch(SQLException e){
                System.out.println(e);
            }
            
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         }else if(username.getText().isEmpty() || password.getText().isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Please input Username and Password !");
              alert.initStyle(StageStyle.UTILITY);
              alert.showAndWait();
            
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Incorrect Username or Password");
              alert.initStyle(StageStyle.UTILITY);
              alert.showAndWait();
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
        CurrentDate();
        bindToTime();
        
        date =Date.getText();
        username = new RestrictiveTextField();
       // username.restrictProperty().set("[a-z]");
        username.maxLengthProperty().set(20);
        username.setLayoutX(147);
        username.setLayoutY(142);
        username.setPrefSize(197, 25);
        //username.setFont(12);
        username.setPromptText("Username");
        
        password = new RestrictivePasswordField();
        //password.restrictProperty().set("[0-9]");
        password.maxLengthProperty().set(20);
        password.setLayoutX(147);
        password.setLayoutY(183);
        password.setPrefSize(197, 25);
        //password.setFont("12px");
        password.setPromptText("Password");
        
        loginpane.getChildren().addAll(username,password);
        
        
    }    
    public void CurrentDate(){
    Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    Date.setText(year+"/"+(month+1)+"/"+day);
    
    }    
    private void bindToTime() {
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
          Calendar time = Calendar.getInstance();
           hourString = pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
           hourString1 = pad(2, ' ', time.get(Calendar.HOUR_OF_DAY) == 0 ? "12" : time.get(Calendar.HOUR_OF_DAY) + "");
           minuteString = StringUtilities.pad(2, '0', time.get(Calendar.MINUTE) + "");
           secondString = StringUtilities.pad(2, '0', time.get(Calendar.SECOND) + "");
           ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
          Time.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
          
          time1 = (hourString1 + ":" + minuteString + ":" + secondString);
    }),
      new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }
}
class StringUtilities {
 
  public static String pad(int fieldWidth, char padChar, String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = s.length(); i < fieldWidth; i++) {
      sb.append(padChar);
    }
    sb.append(s);

    return sb.toString();
  
    }    
}
    

