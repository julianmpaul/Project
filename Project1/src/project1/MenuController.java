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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import static project1.StringUtilities1.pad;
/**
 * FXML Controller class
 *
 * @author Jeankie
 */
public class MenuController implements Initializable {   
    
    public Stage stage;
    public Parent root;
    public String hourString,hourString1,minuteString,secondString,ampmString,time1;
    Connection con;
    
    @FXML
    private Label Time,Date,username,position;
    @FXML
    public AnchorPane menu,menuright; 
    
    @FXML
    private void exit(ActionEvent event){
      
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
           
        con = Database.connect();
        Statement st= con.createStatement();
         
            String SQL1 = "UPDATE `database`.`tbllogged` SET `TimeOut` = '"+time1+"' WHERE `loggedID` = '"+LoginController.loggedid+"';";
            st.executeUpdate(SQL1);
           con.close();
              }catch(SQLException e){
                 System.out.println(e);
              }
           
           System.exit(0);
        }
        
    }
    
    @FXML
    private void logout(ActionEvent event){
      
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           ((Node)(event.getSource())).getScene().getWindow().hide();
            try {
              root = FXMLLoader.load(getClass().getResource("Login1.fxml"));
              
              try{
           
        con = Database.connect();
        Statement st= con.createStatement();
         
            String SQL1 = "UPDATE `database`.`tbllogged` SET `TimeOut` = '"+time1+"' WHERE `loggedID` = '"+LoginController.loggedid+"';";
            st.executeUpdate(SQL1);
                con.close();
              }catch(SQLException e){
                 System.out.println(e);
              }
              
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            
            stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.centerOnScreen();
            stage.show();
            
        }
        
        
    }
    //---------
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    //---------------TRANSACTION
    @FXML
    private void orderproduct(ActionEvent event) throws IOException{
        
        menu.getChildren().clear();
        menu.getChildren().add(FXMLLoader.load(getClass().getResource("OrderProducts.fxml")));
    }
    
    //---------------UTILITIES
    @FXML
    private void user(ActionEvent event) throws IOException{
        
        menu.getChildren().clear();
        menu.getChildren().add(FXMLLoader.load(getClass().getResource("User.fxml")));
    }
    
    @FXML
    private void profile(ActionEvent event) throws IOException{
        
      menu.getChildren().clear();
      menu.getChildren().add(FXMLLoader.load(getClass().getResource("Profile.fxml")));
        
   }
    
    //---------------HELP
    
    @FXML
    private void faq(ActionEvent event){
       
        
        
    }
    
    //---------------------MAINTENANCE
    @FXML
    private void brandbreed(ActionEvent event) throws IOException{
        
      menu.getChildren().clear();
      menu.getChildren().add(FXMLLoader.load(getClass().getResource("BrandBreed.fxml"))); 
      
    }
    
    @FXML
    private void category(ActionEvent event) throws IOException{
        
      menu.getChildren().clear();
      menu.getChildren().add(FXMLLoader.load(getClass().getResource("Category.fxml"))); 
      
    }
    
    @FXML
    private void product(ActionEvent event) throws IOException{
        
      menu.getChildren().clear();
      menu.getChildren().add(FXMLLoader.load(getClass().getResource("Product.fxml"))); 
      
    }
    
    @FXML
    private void supplier(ActionEvent event) throws IOException{
        
      menu.getChildren().clear();
      menu.getChildren().add(FXMLLoader.load(getClass().getResource("Supplier.fxml"))); 
      
    }
    
    @FXML
    private void vat(ActionEvent event) throws IOException{
        
      menu.getChildren().clear();
      menu.getChildren().add(FXMLLoader.load(getClass().getResource("VAT.fxml"))); 
      
    }
    
    @FXML
    private void createcode(ActionEvent event) throws IOException{
        
      menuright.getChildren().clear();
      menuright.getChildren().add(FXMLLoader.load(getClass().getResource("Createcode.fxml"))); 
      
    }
    
    @FXML
    private void productsupplieds(ActionEvent event) throws IOException{
        menu.getChildren().clear();
        menu.getChildren().add(FXMLLoader.load(getClass().getResource("ProductSupplieds.fxml"))); 
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CurrentDate();
        bindToTime();
        
        try {
                
                con = Database.connect();
                String SQL = "SELECT POSITION,username FROM tbluser WHERE userID = '"+LoginController.userID+"'";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                
                
                while (rs.next()) {
                    position.setText("  "+rs.getString(1));
                    username.setText("  "+rs.getString(2));
                }
                
                con.close();
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
    
    }
    
    
    
    public void CurrentDate(){
    Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    Date.setText("Date: "+(month+1)+"/"+day+"/"+year);
    } 
    
    private void bindToTime() {
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
          Calendar time = Calendar.getInstance();
           hourString = pad(2, ' ', time.get(Calendar.HOUR) == 0 ? "12" : time.get(Calendar.HOUR) + "");
           hourString1 = pad(2, ' ', time.get(Calendar.HOUR_OF_DAY) == 0 ? "12" : time.get(Calendar.HOUR_OF_DAY) + "");
           minuteString = StringUtilities1.pad(2, '0', time.get(Calendar.MINUTE) + "");
           secondString = StringUtilities1.pad(2, '0', time.get(Calendar.SECOND) + "");
           ampmString = time.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
          Time.setText("Time:"+hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
          time1 = (hourString1 + ":" + minuteString + ":" + secondString);
    }),
      new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }
    }



class StringUtilities1{
 
  public static String pad(int fieldWidth, char padChar, String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = s.length(); i < fieldWidth; i++) {
      sb.append(padChar);
    }
    sb.append(s);

    return sb.toString();
  
    }
}

    

