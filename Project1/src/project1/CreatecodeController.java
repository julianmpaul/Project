/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 * FXML Controller class
 *
 * @author Jeankie
 */
public class CreatecodeController implements Initializable {
        
        private int num, new_id;
        Connection con;
        public String id;
        private FileInputStream fis;
        PreparedStatement pst;
    
        private RestrictiveTextField code;
        @FXML
        private AnchorPane pane;
        @FXML
        private ImageView image;
        Image images;
        @FXML
        private Button create,print;
        
        
        @FXML
        private void create(ActionEvent event) throws OutputException, IOException, SQLException{
            
            if(code.getText() == null || code.getText().isEmpty() ){
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Message");
                alert1.setHeaderText(null);
                alert1.setContentText("Code is empty !");
                alert1.showAndWait();
        }else{
            
                try {
                    Barcode barcode = BarcodeFactory.createCode128B(code.getText());
                    barcode.setBarHeight(60);
                    barcode.setBarWidth(2);
                    File imgFile = new File("testsize.png");
                    BarcodeImageHandler.savePNG(barcode, imgFile);
                    BarcodeImageHandler.saveJPEG(barcode, imgFile);
                    
                    images = new Image(imgFile.toURI().toString(),120,150,true,true);
                    BufferedImage bufferedImage = ImageIO.read(imgFile);
                    javafx.scene.image.Image images1 = SwingFXUtils.toFXImage(bufferedImage, null);
                    image.setImage(images1);
                    
                    con = Database.connect();
            
                    String SQL = "select max(imageid) from tblimage;";
                    ResultSet rs = con.createStatement().executeQuery(SQL);
            
                if (rs.next()) {
                    String text = rs.getString("max(imageid)");
                    String asd = text.substring(3);
                    
                    id = asd;
                    num = Integer.parseInt(id);
                    new_id = num + 1;
                    id = "ID-00" + new_id;
                    if ((new_id >= 10) && (new_id <= 100)) {
                        this.id = "ID-0" + new_id;
                    } else if ((new_id >= 100) && (new_id <= 999)) {
                        this.id = "ID-" + new_id;
                    }
                }
                
                fis = new FileInputStream(imgFile);
                String SQL1 = "INSERT INTO `database`.`tblimage` "
                    + "(`imageid`,`image`)"
                    + " VALUES (?,?); ";
                
            
            pst = con.prepareStatement(SQL1);
            pst.setString(1, id);
            pst.setBinaryStream(2, (InputStream)fis, (int)imgFile.length());
            pst.execute();
            
                con.close();
                    
                print.setDisable(false);
                } catch (BarcodeException ex) {
                    System.out.println(ex);
                }
                
        }
            
        }
        
        @FXML
        private void print(ActionEvent event){
         
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Print");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to print the barcode ?");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
           
        
            
            //String pdfFileName = "C:\\Users\\Jeankie\\Documents\\NetBeansProjects\\JavaFXApplication6\\src\\javafxapplication6\\1.pdf";
        try{
    con = Database.connect();
     
    JasperDesign jd = JRXmlLoader.load("C:\\Users\\Jeankie\\Desktop\\Project1\\src\\reports\\report1.jrxml");
    JRDesignQuery  newQuery= new JRDesignQuery();
    String SQL = "SELECT imageid from tblimage where imageid = '"+id+"';";
    newQuery.setText(SQL);
    jd.setQuery(newQuery);
    JasperReport jasperreport = JasperCompileManager.compileReport(jd);
    JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport,null,con);
    JasperViewer.viewReport(jasperprint,false);
    //JasperExportManager.exportReportToPdfFile(jasperprint, pdfFileName);
    }catch(SQLException | JRException ee){
    System.out.println(ee);
    }
        print.setDisable(true);
        
        id = null;
        image.setImage(null);
        code.setText(null);
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
        image.setImage(null);
        
        code = new RestrictiveTextField();
        code.maxLengthProperty().set(30);
        code.setLayoutX(22);
        code.setLayoutY(103);
        code.setPrefSize(283, 25);
        code.setText(null);
        code.setPromptText("Barcode");
        
        print.setDisable(true);
        
        pane.getChildren().addAll(code);
    }    
    
}
