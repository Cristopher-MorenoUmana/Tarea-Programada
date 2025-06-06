package com.una.tarea_programada;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TeamParticipationController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button backBtn;
    @FXML
    private TextField idTxtF;
    @FXML
    private TableView<?> teamsTableView;
    @FXML
    private TableColumn<?, ?> teamsNameColumn;
    @FXML
    private TableColumn<?, ?> teamsIdColumn;
    @FXML
    private TableView<?> participationTableView;
    @FXML
    private TableColumn<?, ?> tournamentsNameColumn;
    @FXML
    private TableColumn<?, ?> pointsColumn;
    @FXML
    private TableColumn<?, ?> tournamentPositionColumn;
    @FXML
    private TableColumn<?, ?> rankingPositionColumn;

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
    
    public void initialize() {

        updateWindowSize();
    }

    private void updateWindowSize() {

        Platform.runLater(() ->  App.getStage().sizeToScene());
    }
}
