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
public class suppliedproducts {
    
    public void sname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.sup,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.sup, String> param) {                                                                                             
                        ProductSuppliedsController.sup s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Supplier")){
                            str = new SimpleStringProperty(String.valueOf(s.getsname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void sID(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.sup,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.sup, String> param) {                                                                                             
                        ProductSuppliedsController.sup s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Supplier ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getsID()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    //------------------------------------------------------------------------------------------
    
    
    public void pname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.pro,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.pro, String> param) {                                                                                             
                        ProductSuppliedsController.pro s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product Name")){
                            str = new SimpleStringProperty(String.valueOf(s.getpname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void pID(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.pro,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.pro, String> param) {                                                                                             
                        ProductSuppliedsController.pro s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getpID()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void pCode(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.pro,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.pro, String> param) {                                                                                             
                        ProductSuppliedsController.pro s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product Code")){
                            str = new SimpleStringProperty(String.valueOf(s.getpcode()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
     //------------------------------------------------------------------------------------------
    
    
    public void sliedname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.slied,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.slied, String> param) {                                                                                             
                        ProductSuppliedsController.slied s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product Name")){
                            str = new SimpleStringProperty(String.valueOf(s.getpname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void sliedproID(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.slied,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.slied, String> param) {                                                                                             
                        ProductSuppliedsController.slied s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getpID()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void sliedCode(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.slied,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.slied, String> param) {                                                                                             
                        ProductSuppliedsController.slied s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Product Code")){
                            str = new SimpleStringProperty(String.valueOf(s.getpcode()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void sliedsupname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.slied,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.slied, String> param) {                                                                                             
                        ProductSuppliedsController.slied s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Supplier")){
                            str = new SimpleStringProperty(String.valueOf(s.getsname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void sliedsupid(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProductSuppliedsController.slied,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ProductSuppliedsController.slied, String> param) {                                                                                             
                        ProductSuppliedsController.slied s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Supplier ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getsid()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
}
