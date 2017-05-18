import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by romab on 22.04.2017.
 */
public class BookController {

    private MainController mainController;

    @FXML private TextField bookName;
    @FXML private TextField postClientName;
    @FXML private TextField developerName;
    @FXML private TextField developerFirmName;
    @FXML private TextField firmName;
    @FXML private TextField clientName;

    @FXML
    private void initialize(){
        this.mainController = Navigator.gerMainController();
        mainController.setBookController(this);

        bookName.setText(mainController.getDataBook().getMyName());
        firmName.setText(mainController.getDataBook().getFirmName());
        clientName.setText(mainController.getDataBook().getСlientName());
        postClientName.setText(mainController.getDataBook().getPostClientName());
        developerName.setText(mainController.getDataBook().getDeveloperName());
        developerFirmName.setText(mainController.getDataBook().getDeveloperFirmName());
    }

    @FXML
    private void bookRename(){
        mainController.bookRename(bookName.getText());
    }

    @FXML
    private void reFirmName(){
        mainController.getDataBook().setFirmNameMyName(firmName.getText());
    }

    @FXML
    private void reClientName(){
        mainController.getDataBook().setClientName(clientName.getText());
    }

    @FXML
    private void rePostClientName(){
        mainController.getDataBook().setPostClientName(postClientName.getText());
    }

    @FXML
    private void reDeveloperName(){
        mainController.getDataBook().setDeveloperName(developerName.getText());
    }

    @FXML
    private void reDeveloperFirmName(){
        mainController.getDataBook().setDeveloperFirmName(developerFirmName.getText());
    }
}
