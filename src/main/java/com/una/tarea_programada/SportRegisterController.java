package com.una.tarea_programada;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class SportRegisterController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button backBtn;
    @FXML
    private TextField sportNameTxtF;
    @FXML
    private TextField ballUrlTxtF;
    @FXML
    private Button editBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TableView<?> sportsTableView;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }

    public void initialize() {

        updateWindowSize();
    }

    private void updateWindowSize() {
        
        double[] anchorPane = {this.mainAnchorPane.getMinWidth(), this.mainAnchorPane.getHeight()};

        App.resizeWindow(anchorPane[0], anchorPane[1]);
    }
}
