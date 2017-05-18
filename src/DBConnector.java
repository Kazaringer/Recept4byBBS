import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.text.TableView;
import java.sql.*;
import java.util.ArrayList;


public class DBConnector {
    private ArrayList<Comps> comps;
    private ArrayList<Comps> prLoss;
    private ArrayList<TotalLoss> totalLoss;


    public DBConnector(){
        connect();
        comps  = new ArrayList<Comps>();
        prLoss = new ArrayList<Comps>();
        totalLoss = new ArrayList<TotalLoss>();
    }

    private Connection conn;
    private Statement stmt;
    private ResultSet request;

    public void connect() {
        conn = null;
        stmt = null;
        request = null;

        try {
            //Указываем драйвер
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //Создаём соединение с бд
            conn = DriverManager.getConnection("jdbc:sqlite:components.sqlite");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            //Создаём объект statement
            stmt = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // метод возвращает лист с именами из таблицы comps2
    public ObservableList<String> getComps2Name(){
        ObservableList<String> comps2List = FXCollections.observableArrayList();


        //Выполняем запрос
        try {
            request = stmt.executeQuery("SELECT name,water from comps2");
            while (request.next()) {
                comps2List.add(request.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //закрываем запрос
        if(request != null) try {
            request.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comps2List;
    }

    // метод возвращает лист с именами из таблицы comps2
    public ObservableList<String> getLossName(){
        ObservableList<String> loss = FXCollections.observableArrayList();


        //Выполняем запрос
        try {
            request = stmt.executeQuery("SELECT name,water from processLoss");
            while (request.next()) {

                loss.add(request.getString("name")+" "+"("+request.getString("water")+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //закрываем запрос
        if(request != null) try {
            request.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loss;
    }
    public ArrayList<Comps> getCompsData(){

        try {
            request = stmt.executeQuery("SELECT * from comps2");
            while (request.next()) {
                Comps interCalc = new Comps();
                interCalc.setId(request.getInt("id"));
                interCalc.setName(request.getString("name"));
                interCalc.setWater(request.getFloat("water"));
                interCalc.setCaloric(request.getFloat("caloric"));
                interCalc.setFat(request.getFloat("fat"));
                interCalc.setCholesterol(request.getFloat("cholesterol"));
                interCalc.setProtein(request.getFloat("protein"));
                interCalc.setStarch(request.getFloat("starch"));
                interCalc.setCellulose(request.getFloat("cellulose"));
                interCalc.setVitaminA(request.getFloat("vitaminA"));
                interCalc.setVitaminB(request.getFloat("vitaminB"));
                interCalc.setVitaminB1(request.getFloat("vitaminB1"));
                interCalc.setVitaminB2(request.getFloat("vitaminB2"));
                interCalc.setVitaminPP(request.getFloat("vitaminPP"));
                interCalc.setVitaminC(request.getFloat("vitaminC"));
                interCalc.setMineralK(request.getFloat("mineralK"));
                interCalc.setMineralCa(request.getFloat("mineralCa"));
                interCalc.setMineralNa(request.getFloat("mineralNa"));
                interCalc.setMineralMg(request.getFloat("mineralMg"));
                interCalc.setMineralP(request.getFloat("mineralP"));
                interCalc.setMineralFe(request.getFloat("mineralFe"));
                interCalc.setComment(request.getString("comment"));
                interCalc.setNormativDoc(request.getString("normativDoc"));
                interCalc.setBrutto(request.getFloat("brutto"));
                interCalc.setSaccharides(request.getFloat("saccharides"));
                interCalc.setAcid(request.getFloat("acid"));
                interCalc.setAsh(request.getFloat("ash"));
                interCalc.setInSostav(request.getBoolean("inSostav"));
                interCalc.setInRecept(request.getBoolean("inRecept"));
                comps.add(interCalc);
                interCalc = null;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //закрываем запрос
        if(request != null) try {
            request.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comps;
    }

    public ArrayList<TotalLoss> getTotalLossData(){

        try {
            request = stmt.executeQuery("SELECT * from totalLoss");
            while (request.next()) {
                TotalLoss interCalc = new TotalLoss();
                interCalc.setId(request.getInt("id"));
                interCalc.setName(request.getString("name"));
                interCalc.setComment(request.getString("comment"));
                interCalc.setNumber(request.getFloat("number"));
                totalLoss.add(interCalc);
                interCalc = null;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //закрываем запрос
        if(request != null) try {
            request.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalLoss;
    }


    public ArrayList<Comps> getPrLossData(){

        try {
            request = stmt.executeQuery("SELECT * from processLoss");
            while (request.next()) {
                Comps interCalc = new Comps();
                interCalc.setId(request.getInt("id"));
                interCalc.setName(request.getString("name"));
                interCalc.setWater(request.getFloat("water"));
                interCalc.setCaloric(request.getFloat("caloric"));
                interCalc.setFat(request.getFloat("fat"));
                interCalc.setCholesterol(request.getFloat("cholesterol"));
                interCalc.setProtein(request.getFloat("protein"));
                interCalc.setStarch(request.getFloat("starch"));
                interCalc.setCellulose(request.getFloat("cellulose"));
                interCalc.setVitaminA(request.getFloat("vitaminA"));
                interCalc.setVitaminB(request.getFloat("vitaminB"));
                interCalc.setVitaminB1(request.getFloat("vitaminB1"));
                interCalc.setVitaminB2(request.getFloat("vitaminB2"));
                interCalc.setVitaminPP(request.getFloat("vitaminPP"));
                interCalc.setVitaminC(request.getFloat("vitaminC"));
                interCalc.setMineralK(request.getFloat("mineralK"));
                interCalc.setMineralCa(request.getFloat("mineralCa"));
                interCalc.setMineralNa(request.getFloat("mineralNa"));
                interCalc.setMineralMg(request.getFloat("mineralMg"));
                interCalc.setMineralP(request.getFloat("mineralP"));
                interCalc.setMineralFe(request.getFloat("mineralFe"));
                interCalc.setComment(request.getString("comment"));
                interCalc.setNormativDoc(request.getString("normativDoc"));
                interCalc.setBrutto(request.getFloat("brutto"));
                interCalc.setSaccharides(request.getFloat("saccharides"));
                interCalc.setAcid(request.getFloat("acid"));
                interCalc.setAsh(request.getFloat("ash"));
                interCalc.setInSostav(request.getBoolean("inSostav"));
                interCalc.setInRecept(request.getBoolean("inRecept"));
                prLoss.add(interCalc);
                interCalc = null;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //закрываем запрос
        if(request != null) try {
            request.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prLoss;
    }


    public ObservableList<String> getTotalLoss() {
        ObservableList<String> totalLoss = FXCollections.observableArrayList();

        try {
            request = stmt.executeQuery("SELECT number,name from totalLoss");
            while (request.next()) {

                totalLoss.add(request.getString("number")+"%"+"("+request.getString("name")+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(request != null) try {
            request.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalLoss;
    }



    //Закрываем соеденение с бд
    public void connectClose(){
        if(stmt != null) try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(conn != null) try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
