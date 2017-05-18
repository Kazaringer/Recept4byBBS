/**
 * Created by romab on 20.03.2017.
 */
public class DataBook {
    //Порядковый номер
    int myId;
    public int getMyId(){
        return myId;
    }
    public void setMyId(int id){
        myId = id;
    }

    //Название книги
    String bookName = "Новая книга";
    public String getMyName(){
        return bookName;
    }
    public void setMyName(String name){
        bookName = name;
    }

    //название фирмы
    String firmName = "Новая фирма";
    public String getFirmName(){
        return firmName;
    }
    public void setFirmNameMyName(String name){
        firmName = name;
    }

    //ФИО навчальника
    String clientName = "ФИО";
    public String getСlientName(){
        return clientName;
    }
    public void setClientName(String name){
        clientName = name;
    }

    //Должность начальника
    String postClientName = "Должность";
    public String getPostClientName(){
        return postClientName;
    }
    public void setPostClientName(String name){
        postClientName = name;
    }
    //Должность разработчика
    String developerName = "Имя";
    public String getDeveloperName(){
        return developerName;
    }
    public void setDeveloperName(String name){
        developerName = name;
    }

    //Фирма разработчика
    String developerFirmName = "Фирма";
    public String getDeveloperFirmName(){
        return developerFirmName;
    }
    public void setDeveloperFirmName(String name){
        developerFirmName = name;
    }


}
