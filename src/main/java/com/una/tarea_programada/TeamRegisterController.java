package com.una.tarea_programada;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class TeamRegisterController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private TextField teamTxtF;
    @FXML
    private TextField logoUrlTxtF;
    @FXML
    private Text idTxt;
    @FXML
    private TextField idTxtF;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private TableView<?> sportsTableView;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> sportColumn;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private Button backBtn;

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
    
    public void initialize() {

        updateWindowSize();
    }
      private void updateWindowSize() {

        Platform.runLater(() -> App.getStage().sizeToScene());
    }
}
