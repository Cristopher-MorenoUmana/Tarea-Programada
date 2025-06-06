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

public class TournamentRegisterController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button backBtn;
    @FXML
    private TextField nameTxtF;
    @FXML
    private TextField durationTxtF;
    @FXML
    private TextField sportTxtF;
    @FXML
    private Text idTxt;
    @FXML
    private TextField idTxtF;
    @FXML
    private TableView<?> tournamentTableView;
    @FXML
    private TableColumn<?, ?> tournamentNameColumn;
    @FXML
    private TableColumn<?, ?> sportNameColumn;
    @FXML
    private TableColumn<?, ?> teamsColumn;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Text successTxt;
    @FXML
    private Button addTeamBtn;

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("MainMenu");
    }
    
    @FXML
    private void switchToAddTeam_TournamentMenu() throws IOException {
        App.setRoot("AddTeam_TournamentMenu");
    }
    
        public void initialize() {

        updateWindowSize();
    }

    private void updateWindowSize() {

        Platform.runLater(() ->  App.getStage().sizeToScene());
    }
}