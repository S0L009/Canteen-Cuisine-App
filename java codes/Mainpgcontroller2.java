package com.example.fxplswork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Mainpgcontroller2 {
    @FXML
    private TextField addelt,removeelt,addelt1,addelt2;

    @FXML
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Canteen";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "krish9999";

    private Parent root;
    private Scene scene;
    private Stage stage;
    private String qy;
    private int rs_aff;
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    public void add_elt_2_table(ActionEvent event) throws SQLException {
        String add_em=addelt.getText();
        String add_em1=addelt1.getText();
        String add_em2=addelt2.getText();
        add_em=add_em.toLowerCase().replaceAll(" ","");
        add_em1=add_em1.toLowerCase().replaceAll(" ","");
        add_em2=add_em2.toLowerCase().replaceAll(" ","");
        addelt.setText("");
        addelt1.setText("");
        addelt2.setText("");
        Connection cn=getConnection();
        Statement st=cn.createStatement();
        qy="insert into Main_Menu(food_items,price_item,food_type) value ('"+add_em+"',"+add_em1+",'"+add_em2+"');";
        rs_aff= st.executeUpdate(qy);
        if(rs_aff>0)
        {
            System.out.println("elt added");
        }
        else {
            System.out.println("elt failed to add");
        }
        st.close();
        cn.close();
    }
    public void remove_elt_2_table(ActionEvent event) throws SQLException {
        String remove_em=removeelt.getText();
        removeelt.setText("");
        Connection cn=getConnection();
        Statement st=cn.createStatement();
        qy="delete from Main_Menu where food_items='"+remove_em+"';";
        rs_aff=st.executeUpdate(qy);
        if(rs_aff>0)
        {
            System.out.println("elt removed");
        }
        else {
            System.out.println("elt deln failed");
        }
        st.close();
        cn.close();
    }

    public void backtolocations(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("login.fxml"));
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
