package com.example.fxplswork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;
import java.util.Objects;

public class Mainpagecontroller {

    @FXML
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testin";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "krish9999";
    @FXML
    private TextField username,password;
    @FXML
    private AnchorPane mainap;
    private Parent root;
    private Stage stage;
    private Scene scene;
    double x=100;
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    public void Main(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));   //have to create maincnt
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main");
        stage.show();
    }
    public void goto_stf_r_custom(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("asgn.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main");
        stage.show();
    }
    public void canteenpage(ActionEvent event) throws IOException {
        root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup_login.fxml")));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setTitle("Canteen -> File");
        stage.setScene(scene);
        stage.show();
    }
    public void aboutpage(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("about.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setTitle("about -> File");
        stage.setScene(scene);
        stage.show();
    }
    public void contactpage(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("contact.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setTitle("contact -> File");
        stage.setScene(scene);
        stage.show();
    }

    public void backtoapln(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("asgn.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setTitle("Mainpage -> File");
        stage.setScene(scene);
        stage.show();
    }
    public void backtocanteen(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("canteen.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setTitle("Canteen -> File");
        stage.setScene(scene);
        stage.show();
    }
    public void logintomain(ActionEvent event) throws SQLException, IOException {
        String usn=username.getText();
        String pw=password.getText();
        Connection con=getConnection();
        Statement st=con.createStatement();
        String query="Select * from test1 where username='"+usn+"' AND pasword='"+pw+"';";
        ResultSet rs=st.executeQuery(query);
        if(!usn.equals("")&&!pw.equals("")){
            if(rs.next())
            {
                root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add.fxml")));
                stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                root=FXMLLoader.load((Objects.requireNonNull(getClass().getResource("invalidcredent.fxml"))));
                root.setLayoutX(300);
                root.setLayoutY(225);
                mainap.getChildren().add(root);
            }
        }
    }
    public void close(ActionEvent event) throws IOException {
      root=FXMLLoader.load(getClass().getResource("login.fxml"));
      stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
      scene=new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    public void open_admin(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("admin_login.fxml")); //administrator_home
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}