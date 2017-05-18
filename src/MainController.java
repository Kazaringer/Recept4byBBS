import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.print.Book;
import java.util.ArrayList;

/**
 * Created by romab on 22.04.2017.
 */
public class MainController {

    private Alert alert;
    private BookController bookController;
    private RecipeController recipeController;

    private DataBook dataBook;
    private ArrayList<DataRecipe> recipes;

    private BookTree bookTree;

    @FXML private StackPane stackPane;

    @FXML private TreeView treePlace;

    @FXML private void initialize(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Невыбранна рецептура");
        alert.setHeaderText(null);
        alert.setContentText("Выберете рецептуру!");


        dataBook = new DataBook();
        recipes = new ArrayList<DataRecipe>();
        bookTree = new BookTree(treePlace,dataBook,recipes);
    }

    @FXML private void clickttc(){

        if(treePlace.getSelectionModel().getSelectedIndex() > 0) {
            recipeController.recalculation();
            new ReportGenerateCard(dataBook,recipes.get(treePlace.getSelectionModel().getSelectedIndex()-1));
        }
        else
            alert.showAndWait();
    }

    @FXML
    private void clicktc() {
        if(treePlace.getSelectionModel().getSelectedIndex() > 0) {
            new ReportGenerateList();

        }
        else
            alert.showAndWait();
    }

    @FXML
    private void clickreclist() {
        new ReportGenerate();
    }
    @FXML private void addRecipe(){
        bookTree.addBranch();
    }
    @FXML private void treeSelect(MouseEvent mouseEvent){

                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                   if(treePlace.getSelectionModel().getSelectedIndex()==0) {
                       Navigator.loadVista(Navigator.BOOK);
                   }else {
                       Navigator.loadVista(Navigator.RECIPE);
                       if(treePlace.getSelectionModel().getSelectedIndex() >0)
                       recipeController.setRecipe(recipes.get(treePlace.getSelectionModel().getSelectedIndex()-1));
                   }
                }

    }
    @FXML
    private void deleteRecipes(){
        if (treePlace.getSelectionModel().getSelectedIndex() > 0) {
            treePlace.getRoot().getChildren().clear();
            Navigator.loadVista(Navigator.BOOK);
        }
    }
    @FXML
    private void deleteRecipe () {
        if (treePlace.getSelectionModel().getSelectedIndex() > 0) {
            recipes.remove(treePlace.getSelectionModel().getSelectedIndex() - 1);
            treePlace.getRoot().getChildren().remove(treePlace.getSelectionModel().getSelectedIndex() - 1);

            Navigator.loadVista(Navigator.BOOK);

        }
    }


    public DataBook getDataBook(){
        return dataBook;
    }

    public void setPain(Node node) {
        stackPane.getChildren().setAll(node);
    }


    public void bookRename(String newName){
        dataBook.setMyName(newName);
        bookTree.bookRename();
    }
    public void recipeUpdate(String newName){
        recipes.get(treePlace.getSelectionModel().getSelectedIndex()-1).setName(newName);
        bookTree.recipeRename(treePlace.getSelectionModel().getSelectedIndex()-1);
    }

    public DataRecipe getSelectionBranch(){return recipes.get(treePlace.getSelectionModel().getSelectedIndex()-1);}


    public void setBookController(BookController bookController){
        this.bookController = bookController;
    }
    public void setRecipeController(RecipeController recipeController){
        this.recipeController = recipeController;
    }
}
