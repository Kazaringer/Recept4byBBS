import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.converter.DefaultStringConverter;

import javax.swing.table.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeController {

    private DataRecipe recipe;

    private MainController mainController;

    @FXML
    private TextField plus;

    @FXML
    private TextField minus;

    @FXML
    private TextField quantity;

    @FXML
    private TextField wetnessPercent;

    @FXML
    private TextField outputMass;

    @FXML
    private RadioButton wetness;

    @FXML
    private RadioButton output;

    @FXML
    private TextField recipeName;

    @FXML
    private TextField normDoc;

    @FXML
    private TableView<TableRecipe> tableView;

    @FXML
    private TableColumn<TableRecipe, String> nameColumn;

    @FXML
    private TableColumn<TableRecipe, String> lossColumn;

    @FXML
    private TableColumn<TableRecipe, String> nettoColumn;

    @FXML
    private TableColumn<TableRecipe, String> bruttoColumn;

    @FXML
    private TextField acidity;
    @FXML
    private TextField dataSource;

    @FXML
    private TextField number;

    @FXML
    private TextField recipeDescription;

    @FXML
    private ComboBox<String> lossBox;

    //Для расчетов

    private ArrayList<String> selectedComs;


    @FXML
    private void initialize(){
        quantity.setDisable(true);
        outputMass.setDisable(true);

        wetness.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(wetness.isSelected()){
                    mainController.getSelectionBranch().setWetnessButton(true);
                    wetnessPercent.editableProperty().bind(wetness.selectedProperty());
                    outputMass.setDisable(true);
                    wetnessPercent.setDisable(false);
                    plus.setDisable(false);
                    minus.setDisable(false);
                    quantity.setDisable(true);
                }else {
                    mainController.getSelectionBranch().setWetnessButton(false);
                    outputMass.editableProperty().bind(output.selectedProperty());
                    wetnessPercent.setDisable(true);
                    outputMass.setDisable(false);
                    plus.setDisable(true);
                    minus.setDisable(true);
                    quantity.setDisable(false);


                }
            }
        });

        selectedComs = new ArrayList<String>();

        this.mainController = Navigator.gerMainController();
        mainController.setRecipeController(this);

        DBConnector database = new DBConnector();


        lossBox.setItems(database.getTotalLoss());






        nameColumn.setCellValueFactory(new PropertyValueFactory<TableRecipe, String>("name"));
        nameColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), database.getComps2Name()));
        nameColumn.setOnEditCommit((TableColumn.CellEditEvent<TableRecipe, String> event) -> {
            TablePosition<TableRecipe, String> pos = event.getTablePosition();
                String newFullName = event.getNewValue();
                int row = pos.getRow();
                TableRecipe tableRecipe = event.getTableView().getItems().get(row);
                tableRecipe.setName(newFullName);

                if(row < selectedComs.size()) {
                    selectedComs.set(row, newFullName);
                }else{
                    selectedComs.add(newFullName);
                }

                if(event.getTableView().getItems().size()==row+1)
                event.getTableView().getItems().add(new TableRecipe());

        });



        lossColumn.setCellValueFactory(new PropertyValueFactory<TableRecipe, String>("loss"));
        lossColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), database.getLossName()));
        lossColumn.setOnEditCommit((TableColumn.CellEditEvent<TableRecipe, String> event) -> {
                TablePosition<TableRecipe, String> pos = event.getTablePosition();
                String newFullName = event.getNewValue();
                int row = pos.getRow();
                TableRecipe tableRecipe = event.getTableView().getItems().get(row);
                tableRecipe.setLoss(newFullName);
                if(event.getTableView().getItems().size()==row+1)
                    event.getTableView().getItems().add(new TableRecipe());

        });

        bruttoColumn.setCellValueFactory(new PropertyValueFactory<>("bruttoMass"));
        bruttoColumn.setCellFactory(TextFieldTableCell.<TableRecipe> forTableColumn());
        bruttoColumn.setOnEditCommit((TableColumn.CellEditEvent<TableRecipe, String> event) -> {
            TablePosition<TableRecipe, String> pos = event.getTablePosition();
            String newFullName = event.getNewValue();
            int row = pos.getRow();
            TableRecipe tableRecipe = event.getTableView().getItems().get(row);
            try {
                Integer.parseInt(newFullName);
            } catch (NumberFormatException e) {
                newFullName = "0";
            }finally {
                tableRecipe.setBruttoMass(newFullName);
            }

            if(event.getTableView().getItems().size()==row+1)
                event.getTableView().getItems().add(new TableRecipe());
        });

        nettoColumn.setCellValueFactory(new PropertyValueFactory<>("nettoMass"));
        nettoColumn.setCellFactory(TextFieldTableCell.<TableRecipe> forTableColumn());
        nettoColumn.setOnEditCommit((TableColumn.CellEditEvent<TableRecipe, String> event) -> {
            TablePosition<TableRecipe, String> pos = event.getTablePosition();
            String newFullName = event.getNewValue();
            int row = pos.getRow();
            TableRecipe tableRecipe = event.getTableView().getItems().get(row);
            try {
                Integer.parseInt(newFullName);
            } catch (NumberFormatException e) {
                newFullName = "0";
            }finally {
                tableRecipe.setNettoMass(newFullName);
            }
            if(event.getTableView().getItems().size()==row+1)
                event.getTableView().getItems().add(new TableRecipe());
        });


        tableView.setEditable(true);
        database.connectClose();

    }

    @FXML
    private void asd(TableColumn.CellEditEvent<TableRecipe, String> event){

    }

    @FXML
        private void recipeRename(){
        mainController.recipeUpdate(recipeName.getText());
    }

    @FXML
        private void normDocRename(){mainController.getSelectionBranch().setNameDoc(normDoc.getText());
    }

    @FXML
    private void dataSourceRename(){mainController.getSelectionBranch().setDataSource(dataSource.getText());}

    @FXML
    private void acidityRecount(){mainController.getSelectionBranch().setAcid(acidity.getText());}

    @FXML
    private void numberRecount(){mainController.getSelectionBranch().setNumber(number.getText());}

    @FXML
    private void rewriteDescription(){mainController.getSelectionBranch().setDescription(recipeDescription.getText());}

    @FXML
    private void coldLossChange(){mainController.getSelectionBranch().setColdLoss(lossBox.getValue());}

    @FXML
    private void wetnessRecount(){mainController.getSelectionBranch().setWetness(wetnessPercent.getText());}

    @FXML
    private void plusRecount(){mainController.getSelectionBranch().setPlus(plus.getText());}

    @FXML
    private void minusRecount(){mainController.getSelectionBranch().setMinus(minus.getText());}

    @FXML
    private void quantityRecount(){mainController.getSelectionBranch().setQuantity(quantity.getText());}

    @FXML
    private void outputRecount(){mainController.getSelectionBranch().setOutput(outputMass.getText());}


    public void setRecipe(DataRecipe recipe){
        this.recipe = recipe;
        recipeName.setText(recipe.getName());
        normDoc.setText(recipe.getNameDoc());
        dataSource.setText(recipe.getDataSource());
        acidity.setText(recipe.getAcid());
        number.setText(recipe.getNumber());
        recipeDescription.setText(recipe.getDescription());
        tableView.setItems(recipe.getTableData());
        lossBox.setValue(recipe.getColdLoss());
        wetnessPercent.setText(recipe.getWetness());
        plus.setText(recipe.getPlus());
        minus.setText(recipe.getMinus());
        quantity.setText(recipe.getQuantity());
        outputMass.setText(recipe.getOutput());
        if(recipe.getWetnessButton() == true ){
            wetness.selectedProperty().setValue(true);
        }else{
            output.selectedProperty().setValue(true);
        }
    }

    //Пересчет
    public void recalculation(){
        Calc calc = new Calc();
        calc.calculation(recipe);
        calc = null;
    }
}
