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
public class createuserinfotable {
    public void ID(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("ID")){
                            str = new SimpleStringProperty(String.valueOf(s.getID()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    
    public void fffname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Firstname")){
                            str = new SimpleStringProperty(String.valueOf(s.getfName()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void mmmname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Middlename")){
                            str = new SimpleStringProperty(String.valueOf(s.getmName()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void lllname(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Lastname")){
                            str = new SimpleStringProperty(String.valueOf(s.getlName()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void user(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Username")){
                            str = new SimpleStringProperty(String.valueOf(s.getusername()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
    public void pass(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Password")){
                            str = new SimpleStringProperty(String.valueOf(s.getpass()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
     public void posi(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Position")){
                            str = new SimpleStringProperty(String.valueOf(s.getposition()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
     public void add(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Address")){
                            str = new SimpleStringProperty(String.valueOf(s.getadd()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
     public void gen(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Gender")){
                            str = new SimpleStringProperty(String.valueOf(s.getgender()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
     public void birth(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Birthdate")){
                            str = new SimpleStringProperty(String.valueOf(s.getbirth()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
     public void status(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Status")){
                            str = new SimpleStringProperty(String.valueOf(s.getstatus()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
     public void cp(TableColumn col){
        
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserController.createuser,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(TableColumn.CellDataFeatures<UserController.createuser, String> param) {                                                                                             
                        UserController.createuser s = param.getValue();
                        StringProperty str = new SimpleStringProperty("");
                        if(col.getText().equals("Contact No.")){
                            str = new SimpleStringProperty(String.valueOf(s.getcp()));
                           
                        }
                        
                        return str;
                    }                   

                });
}
}
