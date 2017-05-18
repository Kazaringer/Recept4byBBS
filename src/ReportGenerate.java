import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.HashMap;

/**
 * Created by Ilya on 13.05.2017.
 */
public class ReportGenerate {
    DefaultTableModel tableModel;

    public ReportGenerate() {
        JasperPrint jasperPrint;
        TableModelData();
        try {
             File file1 = new File("Blank_A4_4.jrxml");
             File file2 = new File("Blank_A4_4.jasper");
            System.out.print(file2.getAbsolutePath());
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
        String[] columnNames = {"number", "recept", "firm", "boss", "developer"};
        String[][] data = {
                {"1", "lol", "BBS", "Roman", "Venomancer"},
                {"2", "dfgjh"},
                {"3", "domino"},
                {"4", "reytkejrt"}
        };
        tableModel = new DefaultTableModel(data, columnNames);
    }
}
