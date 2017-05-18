import javafx.scene.control.ComboBox;

/**
 * Created by User on 23.04.2017.
 */
public class TableRecipe {
    private String name="";
    private String loss="";
    private  String nettoMass="0";
    private String bruttoMass="0";



    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLoss(){
        return loss;
    }

    public void setLoss(String loss){
        this.loss = loss;
    }

    public void setNettoMass(String nettoMass){this.nettoMass = nettoMass;}
    public String getNettoMass(){return nettoMass;}

    public void setBruttoMass(String bruttoMass){
        this.bruttoMass = bruttoMass;
    }

    public String getBruttoMass(){return bruttoMass;}
}

