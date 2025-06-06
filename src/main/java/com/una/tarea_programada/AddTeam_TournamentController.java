package com.una.tarea_programada;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddTeam_TournamentController {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private TextField idTeamTxtF;
    @FXML
    private TableView<?> teamsTableView;
    @FXML
    private TableColumn<?, ?> teamColumn;
    @FXML
    private TableColumn<?, ?> teamIDColumn;
    @FXML
    private TableView<?> tournamentTeamsTableView;
    @FXML
    private TableColumn<?, ?> tournamentTeamsColumn;
    @FXML
    private TableColumn<?, ?> tournamentTeamsIdColumn;
    @FXML
    private Button backBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;

    @FXML
    private void switchToTournamentRegister() throws IOException {
        App.setRoot("TournamentRegister");
    }
    
    public void initialize() {
       
    }    
    
}
