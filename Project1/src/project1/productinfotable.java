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
public class productinfotable {
    
    
    public void ID(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product No.")){
                            str = new SimpleStringProperty(String.valueOf(s.getID()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void code(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product Code")){
                            str = new SimpleStringProperty(String.valueOf(s.getnumber()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void name(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product Name")){
                            str = new SimpleStringProperty(String.valueOf(s.getname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void description(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Description")){
                            str = new SimpleStringProperty(String.valueOf(s.getdescription()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void stock(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Stock in Hand")){
                            str = new SimpleStringProperty(String.valueOf(s.getstock()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void sellprice(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Price")){
                            str = new SimpleStringProperty(String.valueOf(s.getpsp()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void sellpricewvat(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Price w/ Vat")){
                            str = new SimpleStringProperty(String.valueOf(s.getpspwv()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void reorderp(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Reorder Point")){
                            str = new SimpleStringProperty(String.valueOf(s.getreorder()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    
    public void bbname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Brand/Breed")){
                            str = new SimpleStringProperty(String.valueOf(s.getbbname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void cname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductController.product,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductController.product, String> param) {                                                                                             
                        ProductController.product s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Category")){
                            str = new SimpleStringProperty(String.valueOf(s.getcatname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
}
