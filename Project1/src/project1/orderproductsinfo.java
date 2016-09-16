/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Jeankie
 */
public class orderproductsinfo {
     public void suppliername(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrderProductsController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<OrderProductsController.product, String> param) {                                                                                             
                        OrderProductsController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Supplier")){
                            str = new SimpleStringProperty(String.valueOf(s.gesname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void proID(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrderProductsController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<OrderProductsController.product, String> param) {                                                                                             
                        OrderProductsController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getID()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
   
    public void proname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrderProductsController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<OrderProductsController.product, String> param) {                                                                                             
                        OrderProductsController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product Name")){
                            str = new SimpleStringProperty(String.valueOf(s.getname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void stock(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrderProductsController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<OrderProductsController.product, String> param) {                                                                                             
                        OrderProductsController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Remaining Stock/s")){
                            str = new SimpleStringProperty(String.valueOf(s.getstock()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
}
