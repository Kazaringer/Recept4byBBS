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
public class ReportGenerateList {
    DefaultTableModel tableModel;

    File file1 = new File("Blank_A4_2.jrxml");
    File file2 = new File("Blank_A4_2.jasper");

    public ReportGenerateList() {
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
        String[] columnNames = {"raw", "rawquantity", "rawgram", "number", "firm", "boss", "developer"};
        String[][] data = {
                {"15", "48", "54", "1", "BBS", "Roman", "Venomancer"},
                {"sdfsdf", "sdfsdqeq1", "123sdaq"},
                {"123asf", "sdfa1asd", "ppllkj"},
                {"sRED", "sdxqqz12", "1xcvx"}
        };
        tableModel = new DefaultTableModel(data, columnNames);
    }
}
