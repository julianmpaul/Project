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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
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
public class ProductController implements Initializable {
        Connection con;
        private int num, new_id;
        private String catid,bbid;
        private RestrictiveTextField code,name,stock,reorder,price,pricewvat;
        private RestrictiveTextArea description;
        @FXML
        private Pane pane;
        @FXML
        private TableView <product> producttable;
        @FXML
        private TableColumn tcproductnumber,tcproductcode,tcproductname,tcdescription,tcstockinhand,tcprice,tcpricewvat,tcreorder,tcbb,tccategory;
        @FXML
        private Label productidlabel;
        @FXML
        private Button btnsave,btnreset,btnupdate,btndelete,btncancel;
        @FXML
        private ComboBox category,brandbreed;
        
        @FXML
        private void save(ActionEvent e){
            if(( code.getText() == null || name.getText() == null || description.getText() == null || reorder.getText() == null || price.getText() == null || pricewvat.getText() == null || category.getValue() == null || brandbreed.getValue() == null ) || ( code.getText().isEmpty() || name.getText().isEmpty() || description.getText().isEmpty() || reorder.getText().isEmpty() || price.getText().isEmpty() || pricewvat.getText().isEmpty())){
                    
                alertinput();
                
            }else{
                
                String pnumb = null,pnam = null;
                
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to update the data ?");
                    alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    try {
                
                con = Database.connect();
                String SQL = "SELECT productnumber FROM tblproducts WHERE productcode = '"+code.getText()+"' OR productname = '"+name.getText()+"';";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                String SQL1 = "SELECT productname FROM tblproducts WHERE productname = '"+name.getText()+"' OR productcode = '"+code.getText()+"' ;";
                ResultSet rs1 = con.createStatement().executeQuery(SQL1);
                
                while (rs.next()) {
                    pnumb = rs.getString(1);
                }
                while (rs1.next()) {
                    pnam = rs.getString(1);
                }
                con.close();
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
                    
                    if(null == (pnumb) && null ==(pnam)){
                        
                            
                                
                                try{
                            con = Database.connect();
                            Statement st= con.createStatement();
                            
                            String SQL1 = "INSERT INTO `database`.`tblproducts`"
                                    + " (`productID`, `productcode`, `productname`, `description`, `stock`,"
                                    + " `sellingprice`, `sellingpricewithvat`, `reorderpoint`,"
                                    + " `bbID`, `categoryID`, `loggedID`) "
                                    + " VALUES ('"+productidlabel.getText()+"', '"+code.getText()+"', '"+name.getText()+"',"
                                    + " '"+description.getText()+"', '"+stock.getText()+"', '"+price.getText()+"',"
                                    + " '"+pricewvat.getText()+"', '"+reorder.getText()+"',"
                                    + " '"+bbid+"', '"+catid+"', '"+LoginController.loggedid+"'); ";
                            
                            st.executeUpdate(SQL1);
                            con.close();
                            
                            refreshid();
                            datareset();
                            buttons();
                            
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Message");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Succesfully Saved !");
                            alert1.initStyle(StageStyle.UTILITY);
                            alert1.showAndWait();
                            
                            
                        }catch(SQLException ee){
                            System.out.println(ee);
                    }
                    
                    }else{
                            Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Message");
                            alert1.setHeaderText(null);
                            alert1.setContentText("The product code or name has a duplicate value !");
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
                alert.setContentText("Are you sure you want to reset ?");
                alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    datareset();
                    buttons();
                    
                }
            }
        
        @FXML
        private void update(ActionEvent e){
            if(( code.getText() == null || name.getText() == null || description.getText() == null || reorder.getText() == null || price.getText() == null || pricewvat.getText() == null || category.getValue() == null || brandbreed.getValue() == null ) || ( code.getText().isEmpty() || name.getText().isEmpty() || description.getText().isEmpty() || reorder.getText().isEmpty() || price.getText().isEmpty() || pricewvat.getText().isEmpty())){
                    
                alertinput();
                
            }else{
                
                String pnumb = null,pnam = null;
                
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to save the data ?");
                    alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                               try{
                            con = Database.connect();
                            Statement st= con.createStatement();
                            String SQL = "UPDATE `database`.`tblproducts`"
                                    + " SET `productcode` = '"+code.getText()+"' ,"
                                    + "`productname` = '"+name.getText()+"' ,`description` = '"+description.getText()+"' ,"
                                    + "`stock` = '"+stock.getText()+"' ,`sellingprice` = '"+price.getText()+"' ,"
                                    + "`sellingpricewithvat` = '"+pricewvat.getText()+"' ,`reorderpoint` = '"+reorder.getText()+"' ,"
                                    + " `bbID` = '"+bbid+"', `categoryID` = '"+catid+"', `loggedID` = '"+LoginController.loggedid+"' WHERE `productID` = '"+productidlabel.getText()+"'; ";
                            st.executeUpdate(SQL);
                            con.close();
                            
                            refreshid();
                            datareset();
                            buttons();
                            
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Message");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Succesfully Updated!");
                            alert1.initStyle(StageStyle.UTILITY);
                            alert1.showAndWait();
                            
                            
                        }catch(SQLException ee){
                            System.out.println(ee);
                    
                    }
                    
                        
                }
            }
        }
        
        @FXML
        private void delete(ActionEvent e){
            
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to save the data ?");
                    alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                try {
                    con = Database.connect();
                    Statement st= con.createStatement();
                    
                    String SQL1 = "DELETE FROM `database`.`tblproducts` WHERE `productID` = '"+productidlabel.getText()+"'; ";
                    st.executeUpdate(SQL1);
                    st.close();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully deleted!");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    
                    refreshid();
                    datareset();
                    buttons();
                    
                } catch (SQLException ex) {
                   Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Message");
            alert1.setHeaderText(null);
            alert1.setContentText("Product was used and cannot be deleted !");
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
                    alert.setContentText("Are you sure you want to cancel update/delete ?");
                    alert.initStyle(StageStyle.UTILITY);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    refreshid();
                    datareset();
                    buttons();
                    
                    
                }
        }
        
        @FXML
        private void category(ActionEvent e){
            try {
                
                con = Database.connect();
                String SQL = "SELECT categoryID FROM tblcategory WHERE categoryName = '"+category.getValue()+"';";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                while (rs.next()) {
                    catid = rs.getString(1);
                }
                con.close();
            } catch (SQLException ex) {
                
            }
        }
        
        @FXML
        private void brandbreed(ActionEvent e){
            try {
                
                con = Database.connect();
                String SQL = "SELECT bbid FROM tblbrandbreed where brandname = '"+brandbreed.getValue()+"';";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                while (rs.next()) {
                    bbid = rs.getString(1);
                }
                con.close();
            } catch (SQLException ex) {
                
            }
        }
        /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        refresh();
        buttons();
        
        code = new RestrictiveTextField();
        code.maxLengthProperty().set(50);
        code.setLayoutX(144);
        code.setLayoutY(131);
        code.setPrefSize(200, 25);
        code.setPromptText("Product Code");
        
        name = new RestrictiveTextField();
        name.maxLengthProperty().set(50);
        name.setLayoutX(144);
        name.setLayoutY(179);
        name.setPrefSize(200, 25);
        name.setPromptText("Product Name");
        
        description = new RestrictiveTextArea();
        description.maxLengthProperty().set(200);
        description.setLayoutX(144);
        description.setLayoutY(227);
        description.setPrefSize(200, 127);
        description.setWrapText(true);
        description.setPromptText("Description");
        
        stock = new RestrictiveTextField();
        stock.restrictProperty().set("[0-9]");
        stock.maxLengthProperty().set(5);
        stock.setLayoutX(499);
        stock.setLayoutY(86);
        stock.setPrefSize(200, 25);
        
        stock.setPromptText("Stock In Hand");
        stock.setEditable(false);
        
        reorder = new RestrictiveTextField();
        reorder.restrictProperty().set("[0-9]");
        reorder.maxLengthProperty().set(5);
        reorder.setLayoutX(499);
        reorder.setLayoutY(134);
        reorder.setPrefSize(200, 25);
        reorder.setPromptText("Reorder Point");
        
        
        price = new RestrictiveTextField();
        price.restrictProperty().set("[.,0-9]");
        price.maxLengthProperty().set(5);
        price.setLayoutX(499);
        price.setLayoutY(184);
        price.setPrefSize(200, 25);
        price.setPromptText("Price");
        
        price.setOnKeyReleased((KeyEvent me) -> {
            if(price.getText() == null || price.getText().isEmpty()){
                pricewvat.setText("0");
            }else{
                try {
                    double pricing2 = 0.0;
                    con = Database.connect();
                    
                    String SQL = "select sum(percentage) from tblvat;";
                    ResultSet rs = con.createStatement().executeQuery(SQL);
                    
                    if (rs.next()) {
                        pricing2 =  rs.getDouble(1);
                        
                    }
                    String a = price.getText();
                    double pricing1 = Double.parseDouble(a);
                    double pricewvat1 = (pricing1*pricing2)+pricing1;
                    String b = Double.toString(pricewvat1);
                    pricewvat.setText(b);
                    
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });
        pricewvat = new RestrictiveTextField();
        pricewvat.restrictProperty().set("[.,0-9]");
        pricewvat.maxLengthProperty().set(8);
        pricewvat.setLayoutX(499);
        pricewvat.setLayoutY(235);
        pricewvat.setPrefSize(200, 25);
        pricewvat.setPromptText("Price w/ vat");
        pricewvat.setEditable(false);
        
        pane.getChildren().addAll(code,name,description,stock,reorder,price,pricewvat);
        
        datareset();
        
        refreshid();
        
         catbran();
        
         producttable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            
            if (producttable.getSelectionModel().getSelectedItem() != null) {
                
              
               productidlabel.setText(newValue.pID);
               code.setText(newValue.pnumber);
               name.setText(newValue.pname);
               description.setText(newValue.pdescription);
               category.setValue(newValue.pcname);
               brandbreed.setValue(newValue.pbname);
               String a = String.valueOf(newValue.pstock);
               String b = String.valueOf(newValue.reorder);
               String d = String.valueOf(newValue.psp);
               String e = String.valueOf(newValue.pspwv);
               stock.setText(a);
               reorder.setText(b);
               price.setText(d);
               pricewvat.setText(e);        
               
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
        btndelete.setDisable(true);
        btncancel.setDisable(true);
    }
    
    private void alertinput(){
        
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Please input all the data!");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                
    }
    private void catbran() {
        try {
            
            con = Database.connect();
            String SQL = "SELECT categoryName FROM tblcategory;";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while (rs.next()) {
                category.getItems().addAll(rs.getString(1));
            }
            con.close();
        } catch (SQLException ex) {
            
        }
        try {
            
            con = Database.connect();            
            String SQL = "SELECT brandname FROM tblbrandbreed;";
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while (rs.next()) {
                brandbreed.getItems().addAll(rs.getString(1));
            }
            con.close();
        } catch (SQLException ex) {
            
        }
    }
    private void datareset() {
                            code.setText(null);
                            name.setText(null);
                            reorder.setText(null);
                            price.setText(null);
                            stock.setText("0");
                            pricewvat.setText(null);
                            description.setText(null);
                            category.setValue(null);
                            brandbreed.setValue(null);
    }
    private void refreshid() {
    
            try {
                con = Database.connect();
                
                String SQL = "select max(productID) from tblproducts;";
                ResultSet rs = con.createStatement().executeQuery(SQL);
                
                if (rs.next()) {
                    
                    String text = rs.getString("max(productID)");
                    String asd = text.substring(4);
                    String aa = asd;
                    productidlabel.setText(asd);
                    num = Integer.parseInt(productidlabel.getText());
                    new_id = num + 1;
                    productidlabel.setText("Pro-0000" + new_id);
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.productidlabel.setText("Pro-000" + new_id);
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.productidlabel.setText("Pro-0" + new_id);
                    }else if ((new_id >= 1000) && (new_id <= 9999)) {
                        this.productidlabel.setText("Pro-" + new_id);
                    }
                }
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
        
           
    
    }
    
    private void refresh() {
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
         productinfotable pt = new productinfotable();
        pt.ID(tcproductnumber);
        pt.code(tcproductcode);
        pt.name(tcproductname);
        pt.description(tcdescription);
        pt.stock(tcstockinhand);
        pt.sellprice(tcprice);
        pt.sellpricewvat(tcpricewvat);
        pt.reorderp(tcreorder);
        
        pt.bbname(tcbb);
        pt.cname(tccategory);
        producttable.getItems().setAll(getproductInfo());
        
           
    }),
      new KeyFrame(Duration.seconds(1))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }
    
    
    
    public class product{
        private String pID,pnumber,pname,pdescription,pbname,pcname;
        private int pstock,psp,pspwv,reorder;
        
        
        public product(String proID,String pronumber,String proname,String prodescription,int stock1,int sp,int spwv,int reorderp,String bbname,String catname){
            
            this.pID = proID;
            this.pnumber = pronumber;
            this.pname = proname;
            this.pdescription = prodescription;
            this.pstock = stock1;
            this.psp = sp;
            this.pspwv = spwv;
            this.reorder = reorderp;
            
            this.pbname = bbname;
            this.pcname = catname;
  
            
        }
        
        public String getID(){              return pID;  }
        public void setID(String proID) {  pID = proID; }
        //---------
        public String getnumber(){              return pnumber;  }
        public void setnumber(String pronumber) {  pnumber = pronumber; }
        //---------
        public String getname(){            return pname;   }
        public void setname(String proname) {  pname = proname;   }
        //---------
        public String getdescription(){            return pdescription;   }
        public void setdescription(String prodescription) {  pdescription = prodescription;   }
        //---------
        public int getstock(){            return pstock;   }
        public void setstock(int stock1) {  pstock = stock1;   }
        //---------
        public int getpsp(){            return psp;   }
        public void setpsp(int sp) {  psp = sp;   }
        //---------
        public int getpspwv(){            return pspwv;   }
        public void setpspwv(int spwv) {  pspwv = spwv;   }
        //---------
        public int getreorder(){            return reorder;   }
        public void setreorder(int reorderp) {  reorder = reorderp;   }
        //---------
        public String getbbname(){            return pbname;   }
        public void setbbname(String bbname) {  pbname = bbname;   }
        //---------
        public String getcatname(){        return pcname;  }
        public void setcatname(String catname) {  pcname = catname;  }
        
        
    }
        
        public List<product> getproductInfo() {
        List RL = new LinkedList();
        try {
            
             con = Database.connect();
        
         String SQL = "SELECT tp.productid,tp.productcode,tp.productname,"
                 + "tp.description,tp.stock,tp.sellingprice,tp.sellingpricewithvat,"
                 + "tp.reorderpoint,tb.`brandname`,tc.`categoryName`"
                 + " FROM tblproducts AS tp INNER JOIN tblbrandbreed AS tb ON tp.`bbID` = tb.`bbID`"
                 + " INNER JOIN tblcategory AS tc ON tp.`categoryID` = tc.`categoryID`;";

            ResultSet rs = con.createStatement().executeQuery(SQL);
            
            while (rs.next()) {
                String proID = rs.getString(1);
                String pronumber = rs.getString(2);
                String proname = rs.getString(3);
                String prodescription = rs.getString(4);
                int stock1 = rs.getInt(5);
                int sp = rs.getInt(6);
                int spwv = rs.getInt(7);
                int reorderp = rs.getInt(8);
                 String bbname = rs.getString(9);
                String catname = rs.getString(10);
                
                
                RL.add(new product(proID,pronumber,proname,prodescription,stock1,sp,spwv,reorderp,bbname,catname));
            }con.close();
        } catch (SQLException ex) {
            System.out.println("Product Error: "+ex);
        }
        return RL;
        }
        
}    
    

