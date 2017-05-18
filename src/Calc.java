import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by User on 30.04.2017.
 */
public class Calc {
    private Comps finalCalc;
    private ArrayList<Comps> comps;
    private ArrayList<Comps> loss;
    private ArrayList<TotalLoss> totalLoss;
    private float myCalcExitNetto = 0;
    private float myCalcSumSuhie = 0;
    private float myCalcSumNetto = 0;
    private float myCalcexiSuhie = 0;
    public Calc() {
        DBConnector database = new DBConnector();
        this.totalLoss = database.getTotalLossData();
        this.comps = database.getCompsData();
        this.loss = database.getPrLossData();

        database.connectClose();
    }


    //производит расчеты
    public void   calculation(DataRecipe recipe){

        finalCalc = new Comps();
        for (int i = 0; i < recipe.getTableData().size(); i++) {
            try {
                for (int j = 0; j < comps.size(); j++) {
                    if (recipe.getTableData().get(i).getName().equals(comps.get(j).getName())) {
                        float netto = Float.parseFloat(recipe.getTableData().get(i).getNettoMass());
                        for (int k = 0; k < loss.size(); k++)
                            if (recipe.getTableData().get(i).getLoss().equals(loss.get(k).getName() + " " + "(" + new DecimalFormat("#0.00").format(loss.get(k).getWater()) + ")")) {
                                myCalcSumSuhie += (netto * (100 - comps.get(j).getWater()) * (100 - loss.get(k).getBrutto()))/ 10000;


                                myCalcSumNetto += netto * (100 - loss.get(k).getNetto()) / 100;
                                finalCalc.setWater(finalCalc.getWater() + comps.get(j).getWater() * netto * (100 - loss.get(k).getWater()) / 10000);
                                finalCalc.setCaloric(finalCalc.getCaloric() + comps.get(j).getCaloric() * netto * (100 - loss.get(k).getCaloric()) / 10000);
                                finalCalc.setStarch(finalCalc.getStarch() + comps.get(j).getStarch() * netto * (100 - loss.get(k).getStarch()) / 10000);
                                finalCalc.setSaccharides(finalCalc.getSaccharides() + comps.get(j).getSaccharides() * netto * (100 - loss.get(k).getSaccharides()) / 10000);
                                finalCalc.setCellulose(finalCalc.getCellulose() + comps.get(j).getCellulose() * netto * (100 - loss.get(k).getCellulose()) / 10000);
                                finalCalc.setCholesterol(finalCalc.getCholesterol() + comps.get(j).getCholesterol() * netto * (100 - loss.get(k).getCholesterol()) / 10000);
                                finalCalc.setFat(finalCalc.getFat() + comps.get(j).getFat() * netto * (100 - loss.get(k).getFat()) / 10000);
                                finalCalc.setAcid(finalCalc.getAcid() + comps.get(j).getAcid() * netto * (100 - loss.get(k).getAcid()) / 10000);
                                finalCalc.setAsh(finalCalc.getAsh() + comps.get(j).getAsh() * netto * (100 - loss.get(k).getAsh()) / 10000);
                                finalCalc.setMineralCa(finalCalc.getMineralCa() + comps.get(j).getMineralCa() * netto * (100 - loss.get(k).getMineralCa()) / 10000);
                                finalCalc.setMineralFe(finalCalc.getMineralFe() + comps.get(j).getMineralFe() * netto * (100 - loss.get(k).getMineralFe()) / 10000);
                                finalCalc.setMineralK(finalCalc.getMineralK() + comps.get(j).getMineralK() * netto * (100 - loss.get(k).getMineralK()) / 10000);
                                finalCalc.setMineralMg(finalCalc.getMineralMg() + comps.get(j).getMineralMg() * netto * (100 - loss.get(k).getMineralMg()) / 10000);
                                finalCalc.setMineralNa(finalCalc.getMineralNa() + comps.get(j).getMineralNa() * netto * (100 - loss.get(k).getMineralNa()) / 10000);
                                finalCalc.setMineralP(finalCalc.getMineralP() + comps.get(j).getMineralP() * netto * (100 - loss.get(k).getMineralP()) / 10000);
                                finalCalc.setProtein(finalCalc.getProtein() + comps.get(j).getProtein() * netto * (100 - loss.get(k).getProtein()) / 10000);
                                finalCalc.setVitaminA(finalCalc.getVitaminA() + comps.get(j).getVitaminA() * netto * (100 - loss.get(k).getVitaminA()) / 10000);
                                finalCalc.setVitaminB(finalCalc.getVitaminB() + comps.get(j).getVitaminB() * netto * (100 - loss.get(k).getVitaminB()) / 10000);
                                finalCalc.setVitaminB1(finalCalc.getVitaminB1() + comps.get(j).getVitaminB1() * netto * (100 - loss.get(k).getVitaminB1()) / 10000);
                                finalCalc.setVitaminB2(finalCalc.getVitaminB2() + comps.get(j).getVitaminB2() * netto * (100 - loss.get(k).getVitaminB2()) / 10000);
                                finalCalc.setVitaminC(finalCalc.getVitaminC() + comps.get(j).getVitaminC() * netto * (100 - loss.get(k).getVitaminC()) / 10000);
                                finalCalc.setVitaminPP(finalCalc.getVitaminPP() + comps.get(j).getVitaminPP() * netto * (100 - loss.get(k).getVitaminPP()) / 10000);

                            }
                    }
                }
            } catch (NullPointerException e) {
            }
        }
            try {
                //расчет по выходу
                if (recipe.getWetnessButton() == false && Float.parseFloat(recipe.getOutput()) > 0) {
                    if (recipe.getColdLoss() != null) {
                        for (int i = 0; i < totalLoss.size(); i++) {
                            if (totalLoss.get(i).getName() == recipe.getColdLoss()) {
                                myCalcSumSuhie = myCalcSumSuhie * (100 - totalLoss.get(i).getNumber()) / 100;
                                break;
                            }
                        }
                    } else {
                        myCalcexiSuhie = myCalcSumSuhie;
                        myCalcExitNetto = Float.parseFloat(recipe.getOutput());
                    }
                }else {
                    myCalcexiSuhie = myCalcSumSuhie;
                    myCalcExitNetto = myCalcSumNetto;
                }
            }catch (NumberFormatException e) {

            }

            finalCalc.setNetto(myCalcSumNetto);

            if(myCalcExitNetto > 0) {
                finalCalc.setWater(finalCalc.getWater() / (myCalcExitNetto / 100));
                finalCalc.setCaloric(finalCalc.getCaloric() / (myCalcExitNetto / 100));
                finalCalc.setStarch(finalCalc.getStarch() / (myCalcExitNetto / 100));
                finalCalc.setSaccharides(finalCalc.getSaccharides() / (myCalcExitNetto / 100));
                finalCalc.setCellulose(finalCalc.getCellulose() / (myCalcExitNetto / 100));
                finalCalc.setCholesterol(finalCalc.getCholesterol() / (myCalcExitNetto / 100));
                finalCalc.setFat(finalCalc.getFat() / (myCalcExitNetto / 100));
                finalCalc.setAcid(finalCalc.getAcid() / (myCalcExitNetto / 100));
                finalCalc.setAsh(finalCalc.getAsh() / (myCalcExitNetto / 100));
                finalCalc.setMineralCa(finalCalc.getMineralCa() / (myCalcExitNetto / 100));
                finalCalc.setMineralFe(finalCalc.getMineralFe() / (myCalcExitNetto / 100));
                finalCalc.setMineralK(finalCalc.getMineralK() / (myCalcExitNetto / 100));
                finalCalc.setMineralMg(finalCalc.getMineralMg() / (myCalcExitNetto / 100));
                finalCalc.setMineralNa(finalCalc.getMineralNa() / (myCalcExitNetto / 100));
                finalCalc.setMineralP(finalCalc.getMineralP() / (myCalcExitNetto / 100));
                finalCalc.setProtein(finalCalc.getProtein() / (myCalcExitNetto / 100));
                finalCalc.setVitaminA(finalCalc.getVitaminA() / (myCalcExitNetto / 100));
                finalCalc.setVitaminB(finalCalc.getVitaminB() / (myCalcExitNetto / 100));
                finalCalc.setVitaminB1(finalCalc.getVitaminB1() / (myCalcExitNetto / 100));
                finalCalc.setVitaminB2(finalCalc.getVitaminB2() / (myCalcExitNetto / 100));
                finalCalc.setVitaminC(finalCalc.getVitaminC() / (myCalcExitNetto / 100));
                finalCalc.setVitaminPP(finalCalc.getVitaminPP() / (myCalcExitNetto / 100));
            }

        finalCalc.setWater((1 - myCalcexiSuhie/myCalcExitNetto) * 100);

        System.out.println(finalCalc.getWater());
        recipe.setCalc(finalCalc);
        finalCalc = null;
    }



    public Comps getFinalCalc(){
        return finalCalc;
    }
}
