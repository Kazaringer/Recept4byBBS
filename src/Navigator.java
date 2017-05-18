import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Created by romab on 22.04.2017.
 */
public class Navigator {

    public static final String MAIN = "mainPane.fxml";
    public static final String BOOK = "bookPane.fxml";
    public static final String RECIPE = "recipePane.fxml";


    private static MainController mainController;
    private static BookController bookController;

    private static FXMLLoader loader;

    public static void setMainController(MainController mainController) {
       Navigator.mainController = mainController;
    }
    public static MainController gerMainController() {
        return mainController;
    }

    public static void loadVista(String fxml) {
        try {
            loader = null;
            loader = new FXMLLoader();
            mainController.setPain(
                    loader.load(
                            Navigator.class.getResource(
                                    fxml
                            )
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
