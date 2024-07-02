package com.example.fxplswork;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class mpgcontroller3 {
    Label lb,rev;
    String tst;
    Button itemm;
    Label[] deln_labels;
    int size_review=0;
    String rev_names[]=new String[100];
    int size=0;
    int k;
    int sno;
    private static String account_name;
    public void set_account_name(String name){
     account_name=name;
    }
    public String get_account_name(){
        return account_name;
    }

    @FXML
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Canteen";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "krish9999";
    @FXML
    private static final String DB_URL1 = "jdbc:mysql://localhost:3306/Review"; //store_rev
    @FXML
    private static final String DB_URL2="jdbc:mysql://localhost:3306/testin";
    private Parent root;
    private Scene scene;

    public static String username="",password="";
    private Stage stage;
    private int x,y,r1;
    private String qy;
    static String item_name;
    private Label rev_lb;
    public void set_itemname(String name){
        item_name=name;
    }
    public String get_itemname(){
        return item_name;
    }
    @FXML
    private TextField custom_un,custom_pw,custom_pw1,custom_pw2,custom_un1;

    @FXML
    private AnchorPane app=new AnchorPane();
    @FXML
    private AnchorPane revpane=new AnchorPane();
    @FXML
    private TextField search_menu;
    @FXML
    private TextArea rev_txt;
    @FXML
    private TextField stars;
    @FXML
    private Pane CApane;
    @FXML
    private Pane LGpane;
    Label[] del_rating=new Label[1];
    int size_del_rating=0;
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    public Connection getConnection1() throws SQLException {
        return DriverManager.getConnection(DB_URL1, DB_USER, DB_PASSWORD);
    }
    public Connection getConnection2() throws SQLException {
        return DriverManager.getConnection(DB_URL2, DB_USER, DB_PASSWORD);
    }
    public void reviews1(ActionEvent event) throws IOException {
        root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("review_4_item.fxml")));
        scene=new Scene(root);
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Review");
        stage.show();
    }
    int saved_coords[][]={{20,144},{20,290},{430,144},{430,290}};

    public  void add_reviews(MouseEvent event) throws SQLException {
        int i=0;
        lb=new Label();  //have to get the text
        lb.setText(get_itemname());
        lb.setLayoutX(250);
        lb.setLayoutY(37);
        lb.setPrefWidth(350);
        lb.setPrefHeight(78);
        lb.setTextFill(Color.WHITE);
        lb.setFont(Font.font("Harrington",35));
        lb.setAlignment(Pos.CENTER);
        revpane.getChildren().add(lb);
        Connection cnnn=getConnection1();
        Statement sttt=cnnn.createStatement();
        ResultSet vals;
        qy="select item_rating from store_rev where food_item='"+get_itemname()+"';";
        vals=sttt.executeQuery(qy);
        if(vals.next()){
            if(size_del_rating==1){
                revpane.getChildren().remove(del_rating[0]);
                size_del_rating--;
            }
            lb=new Label();
            del_rating[0]=lb;
            size_del_rating++;
            tst=vals.getString("item_rating");
            lb.setText("Rating -> "+tst);
            lb.setLayoutX(600);
            lb.setLayoutY(70);
            lb.setPrefWidth(200);
            lb.setTextFill(Color.WHITE);
            lb.setFont(Font.font("Harrington",20));
            lb.setAlignment(Pos.CENTER);
            revpane.getChildren().add(lb);
        }
        String col="",acc_nm="";
        while (i<4){
            i++;
            qy="select * from store_rev where food_item='"+get_itemname()+"';";
            vals=sttt.executeQuery(qy);
            if(vals.next()) {
                col = vals.getString("item_review" + i);
            }
            if(col==null){
                col="";
            }
            qy="select * from store_rev where food_item='"+get_itemname()+"';";
            vals=sttt.executeQuery(qy);
            if(vals.next()) {
                acc_nm = vals.getString("acc_" + i);
            }
            if(acc_nm==null){
                acc_nm="";
            }
            Label acc_name=new Label();
            acc_name.setText(acc_nm.toUpperCase());
            acc_name.setLayoutX(saved_coords[i-1][0]);
            acc_name.setLayoutY(saved_coords[i-1][1]-30);
            acc_name.setStyle("-fx-background-color:transparent;");
            acc_name.setFont(Font.font("Microsoft New Tai Lue",15));
            acc_name.setTextFill(Color.rgb(255,212,0));
            acc_name.setPrefWidth(150);
            acc_name.setAlignment(Pos.CENTER);
            rev_lb=new Label();
            rev_lb.setText(i+") "+col);
            rev_lb.setStyle("-fx-text-fill:rgb(92, 212, 186);-fx-background-color:transparent;");
            rev_lb.setFont(Font.font("Harrington",16));
            rev_lb.setLayoutX(saved_coords[i-1][0]);
            rev_lb.setLayoutY(saved_coords[i-1][1]);
            rev_lb.setPrefWidth(390);
            rev_lb.setWrapText(true);
            revpane.getChildren().add(rev_lb);
            revpane.getChildren().add(acc_name);
            String qy1="";
            Button trigger=new Button();
            trigger.setText("Raise");
            trigger.setBackground(Background.fill(Color.BLACK));
            trigger.setTextFill(Color.RED);
            trigger.setFont(Font.font("Microsoft New Tai Lue",15));
            trigger.prefWidth(40);
            trigger.prefHeight(40);
            trigger.setLayoutX(saved_coords[i-1][0]+300);
            trigger.setLayoutY(saved_coords[i-1][1]-30);
            String str="update store_rev set trig_"+i+"='1' where food_item='"+get_itemname()+"';";
            trigger.setOnAction(Event->{
                try {
                    set_trig_To_1(str);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            revpane.getChildren().add(trigger);
        }
    }
    public void set_trig_To_1(String query) throws SQLException {
        Connection cn=getConnection1();
        Statement st=cn.createStatement();
        int rst=st.executeUpdate(query);
        System.out.println(query);
        if(rst>0){
            System.out.println("addded 1");
        }
        else {
            System.out.println("1 didnt add");
        }
    }
    public void display(MouseEvent event) throws SQLException {
        Connection cn=getConnection();
        Statement st=cn.createStatement();
        qy="select * from Main_menu where food_type = 'milkshake' or food_type = 'juice';";
        ResultSet rs=st.executeQuery(qy);
        sno=1;
        x=10;
        y=155;
        while (rs.next()){
            String item=rs.getString("food_items");
            String cost=rs.getString("price_item");
            /*System.out.println(sno+") "+item+" - "+cost);*/
            itemm=new Button();
            itemm.setStyle("-fx-background-color:transparent");
            itemm.setAlignment(Pos.CENTER_LEFT);
            itemm.setText(sno+") "+Character.toUpperCase(item.charAt(0))+item.substring(1)+" - "+cost);
            itemm.setFont(Font.font("Consolas",15));
            itemm.setOnAction(Event->{
                try {
                    set_itemname(Character.toUpperCase(item.charAt(0))+item.substring(1));
                    reviews1(Event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            itemm.setPrefWidth(235);
            itemm.setPrefHeight(27);
            itemm.setLayoutX(x);
            itemm.setLayoutY(y);
            y=y+30;
            app.getChildren().add(itemm);
            itemm.setTextFill(Color.rgb(229, 231, 230));
            sno++;
        }
        sno=0;
        qy="select * from Main_menu where food_type like 'maincourse';";
        rs=st.executeQuery(qy);
        sno=1;
        x=255;
        y=155;
        while (rs.next()){
            String item=rs.getString("food_items");
            String cost=rs.getString("price_item");
            itemm=new Button();
            itemm.setText(sno+") "+Character.toUpperCase(item.charAt(0))+item.substring(1)+" - "+cost);
            itemm.setFont(Font.font("Consolas",15));
            itemm.setStyle("-fx-background-color:transparent");
            itemm.setOnAction(Event->{
                try {
                    set_itemname(Character.toUpperCase(item.charAt(0))+item.substring(1));
                    reviews1(Event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            itemm.setAlignment(Pos.CENTER_LEFT);
            itemm.setPrefWidth(199);
            itemm.setPrefHeight(27);
            itemm.setLayoutX(x);
            itemm.setLayoutY(y);
            y=y+30;
            app.getChildren().add(itemm);
            itemm.setTextFill(Color.rgb(229, 231, 230));
            sno++;
        }
        sno=0;
        qy="select * from Main_menu where food_type = 'icecream' or food_type='dessert';";
        rs=st.executeQuery(qy);
        sno=1;
        x=480;
        y=155;
        while (rs.next()){
            String item=rs.getString("food_items");
            String cost=rs.getString("price_item");
            itemm=new Button();
            itemm.setText(sno+") "+Character.toUpperCase(item.charAt(0))+item.substring(1)+" - "+cost);
            itemm.setFont(Font.font("Consolas",15));
            itemm.setStyle("-fx-background-color:transparent");
            itemm.setOnAction(Event->{
                try {
                    set_itemname(Character.toUpperCase(item.charAt(0))+item.substring(1));
                    reviews1(Event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            itemm.setPrefWidth(199);
            itemm.setPrefHeight(27);
            itemm.setAlignment(Pos.CENTER_LEFT);
            itemm.setLayoutX(x);
            itemm.setLayoutY(y);
            y=y+30;
            app.getChildren().add(itemm);
            itemm.setTextFill(Color.rgb(229, 231, 230));
            sno++;
        }
        sno=0;
        qy="select * from Main_menu where food_type like '%snack%';";
        rs=st.executeQuery(qy);
        sno=1;
        x=725;
        y=155;
        while (rs.next()){
            String item=rs.getString("food_items");
            String cost=rs.getString("price_item");
            /*System.out.println(sno+") "+Character.toUpperCase(item.charAt(0))+item.substring(1)+" - "+cost);*/
            itemm=new Button();
            itemm.setText(sno+") "+Character.toUpperCase(item.charAt(0))+item.substring(1)+" - "+cost);
            itemm.setFont(Font.font("Consolas",15));
            itemm.setAlignment(Pos.CENTER_LEFT);
            itemm.setStyle("-fx-background-color:transparent");
            itemm.setOnAction(Event->{
                try {
                    set_itemname(Character.toUpperCase(item.charAt(0))+item.substring(1));
                    reviews1(Event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            itemm.setPrefWidth(199);
            itemm.setPrefHeight(27);
            itemm.setLayoutX(x);
            itemm.setLayoutY(y);
            /*lb.setAlignment(Pos.CENTER);*/
            y=y+30;
            app.getChildren().add(itemm);
            itemm.setTextFill(Color.rgb(229, 231, 230));
            sno++;
        }
        rs.close();
        st.close();
        cn.close();
    }
    public void close(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("login.fxml"));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void signuppage(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup_login.fxml")));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void set_both(String un,String pw){
        username=un;
        password=pw;
    }
    public void signuppage2(ActionEvent event) throws IOException, SQLException {
        String txt1=custom_un.getText();
        String txt2=custom_pw.getText();
        String txt3=custom_pw1.getText(); //validation
        ResultSet rst;
        if(!txt1.equals("")&&!txt2.equals("")&&!txt3.equals(""))
        {
            qy="select * from test1 where username='"+txt1+"';";
            Connection cn=getConnection2();
            Statement st=cn.createStatement();
            rst=st.executeQuery(qy);
            if(rst.next()){
                txt1="Username already exists..";
                Label lb=new Label();
                lb.setText(txt1);
                root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customsignupbox.fxml")));
                root.setLayoutX(110);
                root.setLayoutY(150);
                CApane.getChildren().add(root);
                lb.setFont(Font.font("Microsoft JhengHei Light",15));
                lb.setLayoutX(167);
                lb.setTextFill(Color.RED);
                lb.setLayoutY(170);
                CApane.getChildren().add(lb);
            }
            else {
                   if(txt2.equals(txt3)){
                       if((txt1.toLowerCase()).contains("cb.en."))
                       {
                           // add un and pw to sql
                           qy="insert into test1(username,pasword) value ('"+txt1+"','"+txt2+"');";
                           set_account_name(txt1);
                           set_both(txt1,txt2);
                           int ab=st.executeUpdate(qy);
                           root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu_notdec.fxml")));
                           stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
                           scene=new Scene(root);
                           stage.setScene(scene);
                           stage.show();
                       }
                       else {
                           txt1="Username format - CB.EN.XXXXXXXX";
                           Label lb=new Label();
                           lb.setText(txt1);
                           root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customsignupbox.fxml")));
                           root.setLayoutX(110);
                           root.setLayoutY(150);
                           CApane.getChildren().add(root);
                           lb.setFont(Font.font("Microsoft JhengHei Light",15));
                           lb.setTextFill(Color.RED);
                           lb.setLayoutX(127);
                           lb.setLayoutY(170);
                           CApane.getChildren().add(lb);
                       }
                }
                else {
                       txt1="Password's doesnt match..";
                       Label lb=new Label();
                       lb.setText(txt1);
                       root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customsignupbox.fxml")));
                       root.setLayoutX(110);
                       root.setLayoutY(150);
                       CApane.getChildren().add(root);
                       lb.setFont(Font.font("Microsoft JhengHei Light",15));
                       lb.setTextFill(Color.RED);
                       lb.setLayoutX(167);
                       lb.setLayoutY(170);
                       CApane.getChildren().add(lb);
                }
                rst.close();
                st.close();
                cn.close();
            }
            }
        }
    public void goto_login4custom(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login4custom.fxml")));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void automatic_details(MouseEvent event){
        custom_un1.setText(username);
        custom_pw2.setText(password);
    }
    public void login2menu(ActionEvent event) throws SQLException, IOException {
        String t0,t1;
        if(!username.equals("")&&!password.equals("")){
            if(!username.equals(custom_un1.getText())||!password.equals(custom_pw2.getText())){
                t0=custom_un1.getText();
                t1=custom_pw2.getText();
                username=t0;
                password=t1;
            }
            else {
                t0=username;
                t1=password;
            }
        }
        else {
            set_both(custom_un1.getText(),custom_pw2.getText());
            t0=custom_un1.getText();
            t1=custom_pw2.getText();
        }
/*        t0=custom_un1.getText();
        t1=custom_pw2.getText();*/
        Connection cn=getConnection2();
        Statement st=cn.createStatement();
        qy="select * from test1 where username='"+t0+"' AND pasword='"+t1+"';";
        ResultSet rst=st.executeQuery(qy);
        if(!t0.equals("")&&!t1.equals("")){
            if(rst.next()){
                if(!(t0.toLowerCase()).contains("cb.en.")){
                    lb=new Label();
                    lb.setFont(Font.font("Microsoft JhengHei Light",15));
                    lb.setLayoutX(100);
                    lb.setLayoutY(150);
                    lb.setTextFill(Color.RED);
                    lb.setText("Username doesnt contain - CB.EN.XXXXXXXX");
                    root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customsignupbox2.fxml")));
                    root.setLayoutX(85);
                    root.setLayoutY(140);
                    LGpane.getChildren().add(root);
                    LGpane.getChildren().add(lb);
                }
                else{
                    set_account_name(t0);
                    root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu_notdec.fxml")));
                    stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                    scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
            else {
                lb=new Label();
                lb.setFont(Font.font("Microsoft JhengHei Light",15));
                lb.setLayoutX(110);
                lb.setLayoutY(150);
                lb.setTextFill(Color.RED);
                lb.setText("The username or password is invalid...");
                root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customsignupbox2.fxml")));
                root.setLayoutX(95);
                root.setLayoutY(140);
                LGpane.getChildren().add(root);
                LGpane.getChildren().add(lb);
            }
        }
    }
    public void back2location(ActionEvent event) throws IOException {
     root=FXMLLoader.load(getClass().getResource("canteen.fxml"));
     stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
     scene=new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    public void back2menu(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("asgn.fxml"));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void search_in_menu(KeyEvent event) throws  SQLException {
        String quy;
        String srch=search_menu.getText();
        if(srch.equals("")){
            srch=".";
        }
            Connection cnn=getConnection();
            Statement st= cnn.createStatement();
            int X=691,Y=60,incX=0;
            srch=srch.toLowerCase();
            size=size-1;
            qy="Select food_items,price_item from Main_menu where food_items like '"+srch+"%';";
            quy="select Count(food_items) as size from main_menu where food_items like '"+srch+"%';";
            while (size>=0){
                app.getChildren().remove(deln_labels[size]);
                size--;
            }
            ResultSet rtt=st.executeQuery(quy);
            if(rtt.next()){size=rtt.getInt("size");}
            deln_labels=new Label[size];
            k=0;
            ResultSet rst=st.executeQuery(qy);
            while (rst.next()){
                qy=rst.getString("food_items");
                lb=new Label(Character.toUpperCase(qy.charAt(0))+qy.substring(1)+" -> "+rst.getString("price_item"));
                lb.setLayoutX(690);
                lb.setLayoutY(Y+incX);
                incX=incX+30;
                lb.setPrefHeight(30);
                lb.setBackground(Background.fill(Color.rgb(44, 48, 46)));
                lb.setPrefWidth(195);
                lb.setAlignment(Pos.CENTER);
                lb.setFont(Font.font("Poor Richard",18));
                lb.setTextFill(Paint.valueOf("rgb(0, 255, 195)"));
                String fewStyle = "-fx-border-color: rgb(0, 255, 195);" +"-fx-border-width: 0 0 1 0;"+"-fx-opacity: 0.9;";
                app.getChildren().add(lb);
                lb.setStyle(fewStyle);
                deln_labels[k]=lb;
                k+=1;
            }
            rst.close();
            st.close();
            cnn.close();
    }
    public void backtomainmenu(ActionEvent event) throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu_notdec.fxml")));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void submit_review(ActionEvent event) throws SQLException {
        String txt=rev_txt.getText();
        String txt_strs=stars.getText();
        Connection cnnn=getConnection1();
        Statement sttt=cnnn.createStatement();
        qy="select * from store_rev where food_item='"+get_itemname()+"';";
        ResultSet vals=sttt.executeQuery(qy);
        String revv_text = "0";
        int vall;
        int i=0;
        if((!txt.equals(""))&&(!txt_strs.equals(""))){
            rev_txt.setText("");
            stars.setText("");
            if(vals.next()){
                //food_item already there
                while(revv_text!=null&&i<4){
                    i++;
                    revv_text=vals.getString("item_review"+i);
                }
                qy="update store_rev set item_review"+i+"='"+txt+"' where food_item='"+get_itemname()+"';";
                vall=sttt.executeUpdate(qy);
                qy="update store_rev set acc_"+i+"='"+get_account_name()+"' where food_item='"+get_itemname()+"';";
                vall=sttt.executeUpdate(qy);
                qy="update store_rev set trig_"+i+"='"+0+"' where food_item='"+get_itemname()+"';";
                vall=sttt.executeUpdate(qy);
                if(vall>0){
                    System.out.println("new item review acc added");
                }
                else {
                    System.out.println("new item review not acc added");
                }
                qy="select item_rating from store_rev where food_item='"+get_itemname()+"' ";
                vals=sttt.executeQuery(qy);
                if(vals.next()){
                    revv_text=vals.getString("item_rating");
                    System.out.println(revv_text);
                    vall=Integer.parseInt(revv_text)+Integer.parseInt(txt_strs);
                    revv_text=String.valueOf(vall);
                    System.out.println(revv_text);
                    qy="update store_rev set item_rating='"+revv_text+"' where food_item='"+get_itemname()+"';";
                    vall=sttt.executeUpdate(qy);
                    if(vall>0){
                        System.out.println("existing item review added");
                    }
                    else {
                        System.out.println("existing item review not added");
                    }
                }
            }
            else {
                //food_item not there, have to add
                qy="insert into store_rev(food_item) value('"+get_itemname()+"');";
                vall=sttt.executeUpdate(qy);
                qy="update store_rev set item_rating='"+txt_strs+"' where food_item='"+get_itemname()+"';";
                vall=sttt.executeUpdate(qy);
                if(vall>0){
                    System.out.println("new item added");
                }
                else {
                    System.out.println("new item not added");
                }
                qy="update store_rev set item_review"+1+"='"+txt+"' where food_item='"+get_itemname()+"';";
                vall=sttt.executeUpdate(qy);
                if(vall>0){
                    System.out.println("new item review added");
                }
                else {
                    System.out.println("new item review not added");
                }
                qy="update store_rev set acc_"+1+"='"+get_account_name()+"' where food_item='"+get_itemname()+"';";
                vall=sttt.executeUpdate(qy);
                qy="update store_rev set trig_"+1+"='"+0+"' where food_item='"+get_itemname()+"';";
                vall=sttt.executeUpdate(qy);
                if(vall>0){
                    System.out.println("new item review acc added");
                }
                else {
                    System.out.println("new item review not acc added");
                }
            }
        }
    }
    public void gotomenu_notdec(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("menu_notdec.fxml"));
        stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
