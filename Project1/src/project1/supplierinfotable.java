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
public class supplierinfotable {
    public void ID(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SupplierController.supplier,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<SupplierController.supplier, String> param) {                                                                                             
                        SupplierController.supplier s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getID()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void name(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SupplierController.supplier,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<SupplierController.supplier, String> param) {                                                                                             
                        SupplierController.supplier s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Supplier Name")){
                            str = new SimpleStringProperty(String.valueOf(s.getsname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void description(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SupplierController.supplier,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<SupplierController.supplier, String> param) {                                                                                             
                        SupplierController.supplier s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Description")){
                            str = new SimpleStringProperty(String.valueOf(s.getsdescription()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void address(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SupplierController.supplier,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<SupplierController.supplier, String> param) {                                                                                             
                        SupplierController.supplier s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Address")){
                            str = new SimpleStringProperty(String.valueOf(s.getsaddress()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void conperson(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SupplierController.supplier,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<SupplierController.supplier, String> param) {                                                                                             
                        SupplierController.supplier s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Contact Person/s")){
                            str = new SimpleStringProperty(String.valueOf(s.getscontactperson()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void connumber(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SupplierController.supplier,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<SupplierController.supplier, String> param) {                                                                                             
                        SupplierController.supplier s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Contact No.")){
                            str = new SimpleStringProperty(String.valueOf(s.getscontactnumber()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void log(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SupplierController.supplier,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<SupplierController.supplier, String> param) {                                                                                             
                        SupplierController.supplier s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Log")){
                            str = new SimpleStringProperty(String.valueOf(s.getslog()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
   
}
