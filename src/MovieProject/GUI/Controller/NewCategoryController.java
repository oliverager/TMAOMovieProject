package MovieProject.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewCategoryController extends BaseController implements Initializable {

    @FXML
    private Button cancelCategory;

    @Override
    public void setup() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage=(Stage) cancelCategory.getScene().getWindow();
        stage.close();
    }
}
