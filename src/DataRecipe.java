import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Created by romab on 22.04.2017.
 */
public class DataRecipe {


    String dataSourceName;
    String acid;
    String num;
    String description;
    ObservableList<TableRecipe> tableData;
    String name = "Новый рецепт";
    String nameDoc;
    int id;
    String coldLoss;
    Comps calc;
    Boolean wetnessButton = true;
    String wetness;
    String output;
    String plus;
    String minus;
    String quantity;


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMinus() {
        return minus;
    }

    public void setMinus(String minus) {
        this.minus = minus;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getWetness() {
        return wetness;
    }

    public void setWetness(String wetness) {
        this.wetness = wetness;
    }

    public Comps getCalc() {
        return calc;
    }

    public void setCalc(Comps calc) {
        this.calc = calc;
    }

    public DataRecipe(){
        calc = new Comps();
        tableData = FXCollections.observableArrayList();
        tableData.add(new TableRecipe());
}

public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNameDoc()
    {
        return nameDoc;
    }
    public void setNameDoc(String nameDoc)
    {
        this.nameDoc = nameDoc;
    }

    public String getDataSource()
    {
        return dataSourceName;
    }

    public void setDataSource(String dataSourceName){
        this.dataSourceName = dataSourceName;
    }

    public String getAcid()
    {
        return acid;
    }

    public void setAcid(String acid){
        this.acid = acid;
    }

    public String getNumber()
    {
        return num;
    }

    public void setNumber(String num){
        this.num = num;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getColdLoss(){return coldLoss;}
    public void setColdLoss(String coldLoss){
        this.coldLoss=coldLoss;
    }

    //public void setTableData(ObservableList<TableRecipe> tableData){
        //this.tableData=tableData;
   // }
    public ObservableList<TableRecipe> getTableData(){return tableData;}
    public Boolean getWetnessButton(){return wetnessButton;}

    public  void setWetnessButton(Boolean wetnessButton){this.wetnessButton=wetnessButton;}


}
