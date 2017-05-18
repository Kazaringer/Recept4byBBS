import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Created by romab on 25.03.2017.
 */
public class BookTree {


    private TreeView bookTree;
    private TreeItem<String> rootItem;

    private ArrayList<DataRecipe> recipes;


    DataBook dBook;


    public BookTree(TreeView bookTree, DataBook dBook,ArrayList<DataRecipe> recipes) {
        this.dBook = dBook;
        this.bookTree = bookTree;
        createTree(bookTree,dBook);
        this.recipes = recipes;
    }

    public void createTree(TreeView bookTree, DataBook dBook) {
        rootItem = null;
        rootItem = new TreeItem<String>(dBook.getMyName());
        rootItem.setExpanded(true);
        bookTree.setRoot(rootItem);
    }

    public void addBranch(){
        recipes.add(new DataRecipe());
        rootItem.getChildren().add(new TreeItem<String>(recipes.get(recipes.size()-1).getName()));
        recipes.get(recipes.size()-1).setId(recipes.size()-1);
    }


    public void bookRename(){
        rootItem.setValue(dBook.getMyName());
    }


    public void recipeRename(int id){
        rootItem.getChildren().get(id).setValue(recipes.get(id).getName());
    }

    public TreeItem getRoot(){
        return rootItem;
    }

}
