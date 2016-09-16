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
public class categoryinfotable {
    public void ID(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategoryController.category,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CategoryController.category, String> param) {                                                                                             
                        CategoryController.category s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getID()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void name(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategoryController.category,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CategoryController.category, String> param) {                                                                                             
                        CategoryController.category s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Category Name")){
                            str = new SimpleStringProperty(String.valueOf(s.getname()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void description(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategoryController.category,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CategoryController.category, String> param) {                                                                                             
                        CategoryController.category s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Description")){
                            str = new SimpleStringProperty(String.valueOf(s.getdescription()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void warranty(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategoryController.category,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CategoryController.category, String> param) {                                                                                             
                        CategoryController.category s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Warranty Days")){
                            str = new SimpleStringProperty(String.valueOf(s.getwarranty()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void log(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CategoryController.category,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<CategoryController.category, String> param) {                                                                                             
                        CategoryController.category s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Logged ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getlog()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
}