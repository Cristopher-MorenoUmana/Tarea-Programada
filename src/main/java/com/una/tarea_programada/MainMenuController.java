package com.una.tarea_programada;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private Button tournamentRegisterBtn;
    @FXML
    private Button sportRegisterBtn;
    @FXML
    private Button teamRegisterBtn;
    @FXML
    private Button clashBtn;
    @FXML
    private Button playBtn;
    @FXML
    private Button participationBtn;

    @FXML
    private void switchToTournamentRegister() throws IOException {
        App.setRoot("TournamentRegister");
    }
    
    @FXML
    private void switchToSportRegister() throws IOException {
        App.setRoot("SportRegister");
    }
    
    @FXML
    private void switchToTeamRegister() throws IOException {
        App.setRoot("TeamRegister");
    }
    
    @FXML
    private void switchToMatchMenu() throws IOException {
        App.setRoot("MatchMenu");
    }
    
    @FXML
    private void switchToTeamParticipationMenu() throws IOException {
        App.setRoot("TeamParticipationMenu");
    }
    
    @FXML
    private void switchToClashMenu() throws IOException {
        App.setRoot("ClashMenu");
    }
    
    public void initialize() {
       
        
    }   
}
