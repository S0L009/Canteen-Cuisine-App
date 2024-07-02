package com.example.fxplswork;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
                          //ADMIN
public class mpgcrtlr4 {
    @FXML
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testin";  //test1  ..for storing accounts
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "krish9999";
    Button delete;
    @FXML
    private static final String DB_URL1 = "jdbc:mysql://localhost:3306/Review";
    @FXML
    private static final String DB_URL2 = "jdbc:mysql://localhost:3306/only_admin";
    @FXML
    private TextField staff_usn,staff_pw;
    @FXML
    private AnchorPane admin_pane;
    String qy;
    Label lb;
    @FXML
    private AnchorPane del_ap;
    @FXML
    private TextField admin_un,admin_pw;
    Label[] arr=new Label[1];
    int size_arr=0;
    private Parent root;
    private Scene scene;
    private Stage stage;
    public void go_to_admin_page(ActionEvent event) throws SQLException, IOException {
        String txt1=admin_un.getText();
        String txt2=admin_pw.getText();
        Connection con=getConnection2();
        Statement st=con.createStatement();
        qy="select * from for_admin where username='"+txt1+"' and pasword='"+txt2+"';";
        ResultSet rs=st.executeQuery(qy);
        if(rs.next()){
            backtoadmin_home(event);
        }
        else{
            admin_un.setText("");
            admin_pw.setText("");
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    public Connection getConnection1() throws SQLException {
        return DriverManager.getConnection(DB_URL1, DB_USER, DB_PASSWORD);
    }
    public Connection getConnection2() throws SQLException {
        return DriverManager.getConnection(DB_URL2, DB_USER, DB_PASSWORD);
    }
    public void create_staff_acc(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminstrator_cracc.fxml")));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void create_acc_Stf(ActionEvent event) throws SQLException, IOException {
        String text0=staff_usn.getText();
        String text1=staff_pw.getText();
        if(!text0.equals("")&&!text1.equals("")){
            staff_usn.setText("");
            staff_pw.setText("");
            Connection con=getConnection();
            Statement st=con.createStatement();
            qy="select username from test1 where username='"+text0+"'";
            ResultSet rstt=st.executeQuery(qy);
            if(rstt.next()){
                text0="Username already there, try another one..";
            }
            else {
                qy = "insert into test1(username,pasword) value ('" + text0 + "','" + text1 + "');";
                int rst = st.executeUpdate(qy);
                text0 = "Username and Password added successfully 😃😀";
            }
            System.out.println(text0);
            Label tt=new Label();
            tt.setText(text0);
            tt.setLayoutX(260);
            tt.setLayoutY(240);
            tt.setTextFill(Color.rgb(65, 165, 238));
            tt.setFont(Font.font("Bookman Old Style",14));
            tt.setPrefWidth(285);
            tt.setPrefHeight(54);
            tt.setWrapText(true);
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("success_error_box.fxml")));
            root.setLayoutX(240);
            root.setLayoutY(240);
            admin_pane.getChildren().add(root);
            admin_pane.getChildren().add(tt);
        }
    }
    public void backtohome(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("asgn.fxml")));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void backtoadmin_home(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("administrator_home.fxml")));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void del_staff_acc(ActionEvent event) throws IOException, SQLException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminstrator_acc_del.fxml")));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void delete_accounts(String t1,ActionEvent event){
        try {
            qy="delete from test1 where username='"+t1+"'";
            Connection conn=getConnection();
            Statement stt=conn.createStatement();
            int rs=stt.executeUpdate(qy);
            if(rs>0){
                del_staff_acc(event);
            }
            else {
                System.out.println(" account didnt delete");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void del_staff_acc1(MouseEvent event) throws SQLException {
        int x,y,i=1;
        x=170;y=120;
        int x1=580;
        Connection con=getConnection();
        Statement st=con.createStatement();
        qy="select * from test1;";
        ResultSet rst=st.executeQuery(qy);
        while (rst.next()){
            String t1=rst.getString("username");
            delete=new Button();
            delete.setLayoutX(x1);
            delete.setLayoutY(y-5);
            delete.setPrefHeight(5);
            delete.setPrefWidth(80);
            delete.setFont(Font.font("Brush Script MT",17));
            delete.setBackground(Background.fill(Color.BLACK));
            delete.setTextFill(Color.RED);
            delete.setBorder(Border.stroke(Color.RED));
            delete.setText("Delete "+i);
            delete.setOnAction(Event->{
                delete_accounts(t1,Event);
            });
            lb=new Label();
            lb.setText(i+") Username -> '"+rst.getString("username")+"' , Password -> '"+rst.getString("pasword")+"'");
            lb.setFont(Font.font("Microsoft New Tai Lue",15));
            lb.setTextFill(Color.WHITE);
            lb.setLayoutX(x);
            lb.setLayoutY(y);
            y+=40;
            i++;
            del_ap.getChildren().add(lb);
            del_ap.getChildren().add(delete);
        }
        rst.close();
        st.close();
        con.close();
    }
    public void goto_del_rev(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("delete_rev_trig.fxml")));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private AnchorPane del_Rev_ap;
    public void delete_reviews(String query,ActionEvent event) throws SQLException, IOException {
        Connection con=getConnection1();
        Statement st=con.createStatement();
        int val=st.executeUpdate(query);
        if(val>0){
            goto_del_rev(event);
        }
    }
    public void display_Rev_del(MouseEvent event) throws SQLException {
        int i=0,j=0;
        int x=45,y=140,xB=600;
        Connection cn=getConnection1();
        Statement st=cn.createStatement();
        while (i<4){
            i++;
            qy="select food_item,item_review"+i+",acc_"+i+" from store_rev where trig_"+i+"='1';";
            ResultSet rst=st.executeQuery(qy);
            while (rst.next()){
                j++;
                Button del_4_rev=new Button();
                del_4_rev.setText("Delete "+j);
                del_4_rev.setBackground(Background.fill(Color.BLACK));
                del_4_rev.setLayoutX(xB+150);
                del_4_rev.setLayoutY(y-10);
                del_4_rev.setPrefWidth(90);
                del_4_rev.setPrefHeight(30);
                del_4_rev.setTextFill(Color.RED);
                del_4_rev.setBorder(Border.stroke(Color.RED));
                Label lb=new Label();
                Label lb1=new Label();
                lb1.setText((rst.getString("acc_"+i)).toUpperCase());
                lb1.setLayoutX(xB);
                lb1.setLayoutY(y);
                lb1.setFont(Font.font("Consolas",15));
                lb1.setPrefWidth(150);
                lb1.setTextFill(Color.rgb(255,212,0));
                lb.setText(j+") "+rst.getString("food_item")+" -> "+rst.getString("item_review"+i));
                lb.setLayoutX(x);
                lb.setLayoutY(y);
                lb.setPrefWidth(500);
                lb1.setAlignment(Pos.CENTER);
                lb.setFont(Font.font("Consolas",15));
                lb.setTextFill(Color.WHITE);
                lb.setWrapText(true);
                del_Rev_ap.getChildren().add(lb);
                del_Rev_ap.getChildren().add(lb1);
                del_Rev_ap.getChildren().add(del_4_rev);
                y=y+40;
                del_4_rev.setFont(Font.font("Microsoft New Tai Lue",12));
                String txt="update store_rev set item_review"+i+"=null, acc_"+i+"=null,trig_"+i+"=0,item_rating=0 where food_item='"+rst.getString("food_item")+"';";
                del_4_rev.setOnAction(Event->{
                    try {
                        delete_reviews(txt,Event);
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }

                });
            }
        }
    }
}
