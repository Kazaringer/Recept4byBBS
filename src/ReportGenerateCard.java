import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.virtualization.FloatSerializer;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ilya on 13.05.2017.
 */
public class ReportGenerateCard {
    private DataBook dataBook;
    DefaultTableModel tableModel;
    DataRecipe recipe;
    Comps finalCalc;


    public ReportGenerateCard(DataBook dataBook,DataRecipe recipe) {
        File file1 = new File("Blank_A4_1.jrxml");
        File file2 = new File("Blank_A4_1.jasper");
        this.dataBook = dataBook;
        this.recipe = recipe;
        this.finalCalc = recipe.getCalc();

        JasperPrint jasperPrint;
        TableModelData();
        try {
            JasperCompileManager.compileReportToFile(file1.getAbsolutePath());
            jasperPrint = JasperFillManager.fillReport(file2.getAbsolutePath(), new HashMap(),
                    new JRTableModelDataSource(tableModel));
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    private void TableModelData(){
        String[] columnNames = {"raw", "brutto", "netto", "number",
                "recept", "firm", "boss", "developer", "exit", "proteins", "fats", "carbogydrates", "energy", "cal", "indicatorfloat", "acidityint",
                "starchint", "saccharidesint", "acidint", "ashint", "celluloseint", "cholesterolint", "proteinint", "MineralKint", "MineralCAint", "MineralMGint",
                "MineralNAint", "MineralFEint", "vitaminAint", "VitaminBint", "VitaminB1int", "VitaminB2int", "VitaminCint", "VitaminPPint", "MineralPint"};




        String[][] data = {

                {"", "", "", recipe.getNumber(), recipe.getName(), dataBook.getFirmName(), dataBook.getСlientName()},
                { "", "","", "",
                        "", "", "",dataBook.getDeveloperName(), Float.toString(finalCalc.getNetto()), Float.toString(finalCalc.getProtein()), Float.toString(finalCalc.getFat()),
                        Float.toString(finalCalc.getSaccharides() + finalCalc.getStarch()), Float.toString(finalCalc.getCaloric()), Float.toString(finalCalc.getCaloric() *(float)4.184),
                        Float.toString(finalCalc.getWater()), Float.toString(finalCalc.getAcid()),
                        Float.toString(finalCalc.getStarch()),Float.toString(finalCalc.getSaccharides()), Float.toString(finalCalc.getAcid()), Float.toString(finalCalc.getAsh()),
                        Float.toString(finalCalc.getCellulose()),Float.toString(finalCalc.getCholesterol()), Float.toString(finalCalc.getProtein()),
                        Float.toString(finalCalc.getMineralK()), Float.toString(finalCalc.getMineralCa()), Float.toString(finalCalc.getMineralMg()),
                        Float.toString(finalCalc.getMineralNa()), Float.toString(finalCalc.getMineralFe()), Float.toString(finalCalc.getVitaminA()),
                        Float.toString(finalCalc.getVitaminB()),  Float.toString(finalCalc.getVitaminB1()),  Float.toString(finalCalc.getVitaminB2()),
                        Float.toString(finalCalc.getVitaminC()),  Float.toString(finalCalc.getVitaminPP()),  Float.toString(finalCalc.getMineralP())},
            };

        tableModel = new DefaultTableModel(data, columnNames);

    }
}
